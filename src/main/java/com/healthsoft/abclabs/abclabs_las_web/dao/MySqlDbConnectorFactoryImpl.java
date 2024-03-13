package com.healthsoft.abclabs.abclabs_las_web.dao;

public class MySqlDbConnectorFactoryImpl implements DbConnectorFactory{

	@Override
	public DbConnector getDbConnector() {
		
		return new MySqlConnectorImpl();
	}
	
}
