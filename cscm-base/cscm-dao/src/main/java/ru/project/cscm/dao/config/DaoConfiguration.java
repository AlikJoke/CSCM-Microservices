package ru.project.cscm.dao.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DaoConfiguration {

	@Autowired
	private Environment env;

	@Bean(name = DataSourceJNDIDictionary.CSCM_DATA_SOURCE)
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setDefaultAutoCommit(false);
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));

		return dataSource;
	}
	
	@Bean(name = DataSourceJNDIDictionary.CSCM_SLAVE_DATA_SOURCE)
	public DataSource dataSourceSlave() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setDefaultAutoCommit(false);
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));

		return dataSource;
	}

	@Bean
	@Primary
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public PlatformTransactionManager transactionManagerSlave() {
		return new DataSourceTransactionManager(dataSourceSlave());
	}
	
	public static class DataSourceJNDIDictionary {
		
		public static final String CSCM_DATA_SOURCE = "jdbc/svwi/WICM";
		
		public static final String CSCM_SLAVE_DATA_SOURCE = "jdbc/svwi/WICM_Slave";
	}
}

