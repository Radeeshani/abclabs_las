package com.healthsoft.abclabs.abclabs_las_web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.healthsoft.abclabs.abclabs_las_web.util.AppConfigReaderUtil;

public class MySqlConnectorImpl implements DbConnector {

	
	public Connection getDbConnection() throws ClassNotFoundException, SQLException {
		
		AppConfigReaderUtil configReaderUtil = new AppConfigReaderUtil();
		final String USERNAME = configReaderUtil.getProperty("mysql.username");
		final String PASSWORD = configReaderUtil.getProperty("mysql.password");
		final String DATABASE = configReaderUtil.getProperty("mysql.database");
		final String PORT = configReaderUtil.getProperty("mysql.port");

		Class forName = Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT + "/" + DATABASE, USERNAME,
				PASSWORD);
		
		return connection;
	}

	
}
