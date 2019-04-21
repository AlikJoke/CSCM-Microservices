package ru.project.cscm.dao.core.internal;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import ru.project.cscm.dao.config.DaoConfiguration.DataSourceJNDIDictionary;

@Component
public class SchemeGenerator implements ApplicationListener<ContextRefreshedEvent> {

	@Resource(name = DataSourceJNDIDictionary.CSCM_DATA_SOURCE)
	private DataSource dataSource;
	
	@Value("${cscm.ddl.sequence.path}")
	private String sequencePath;

	private static final String TABLE_PREFIX = "T_CM_";
	private static final String DOT = ".";

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		final ConnectionContainer conn = new ConnectionContainer(null);
		try {
			conn.create(dataSource.getConnection());
			final List<String> sequence = getSequence();
			final String tableName = sequence.stream().filter(seq -> seq.toUpperCase().startsWith(TABLE_PREFIX))
					.findFirst().orElseThrow(RuntimeException::new);
			if (!isSchemeExists(conn.get(), tableName.substring(0, tableName.indexOf(DOT)))) {
				conn.get().setAutoCommit(false);

				sequence.forEach(e -> ScriptUtils.executeSqlScript(conn.get(),
						new ClassPathResource("ddl-scripts/postgres/" + e)));

				conn.get().commit();
			}
		} catch (SQLException e) {
			rollback(conn.get());
			throw new RuntimeException("Can't generate scheme", e);
		} finally {
			conn.destroy();
		}
	}

	private List<String> getSequence() {
		File result = null;
		try {
			result = File.createTempFile(UUID.randomUUID().toString(), null);
			FileUtils.copyURLToFile(this.getClass().getResource(this.sequencePath), result);
			return FileUtils.readLines(result, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException("Can't parse file with sequence of creation tables", e);
		} finally {
			result.delete();
		}
	}

	private boolean isSchemeExists(final Connection conn, final String tableName) throws SQLException {
		try (final PreparedStatement stmt = conn
				.prepareStatement("SELECT 1 FROM information_schema.tables WHERE table_name = ?")) {
			stmt.setString(1, tableName);
			try (final ResultSet rs = stmt.executeQuery()) {
				return rs.next();
			}
		}
	}

	private void rollback(final Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new RuntimeException("Rollback is failed", e);
		}
	}
}
