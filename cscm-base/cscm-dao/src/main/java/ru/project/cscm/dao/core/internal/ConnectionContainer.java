package ru.project.cscm.dao.core.internal;

import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionContainer {

	private Connection connection;

	public ConnectionContainer(final Connection conn) {
		this.connection = conn;
	}

	public void create(final Connection conn) {
		this.connection = conn;
	}

	public Connection get() {
		return this.connection;
	}

	public void destroy() {
		try {
			get().close();
		} catch (SQLException e) {
			throw new RuntimeException("Destroy is failed", e);
		}
	}
}
