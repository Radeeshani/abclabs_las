package com.healthsoft.abclabs.abclabs_las_web.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQL {

	private static Connection connection;

	private static Statement createConnection() throws Exception {
		if (connection == null) {
			connection = getConnection();
		}

		return connection.createStatement();
	}

	public static void iud(String query) {

		try {
			createConnection().executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static ResultSet search(String query) throws Exception {
		return createConnection().executeQuery(query);
	}

	public static DbConnector getDbConnector() {

		DbConnectorFactory factory = new MySqlDbConnectorFactoryImpl(); // if you can get this from config
		return factory.getDbConnector();
	}

	public static Connection getConnection() {
		if (connection == null) {
			try {

				DbConnector connector = getDbConnector();
				connection = connector.getDbConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

}
