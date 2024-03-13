package com.healthsoft.abclabs.abclabs_las_web.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppConfigReaderUtilTest {
	private final AppConfigReaderUtil configReader;

	public AppConfigReaderUtilTest() {
		// Create an instance of AppConfigReader
		configReader = new AppConfigReaderUtil();
	}

	@Test
	public void databaseUrlMustBeNotNull() {
		//test AppConfigReaderUtil properties reading

		String databaseUrl = configReader.getProperty("mysql.database");

		assertNotNull(databaseUrl);

	}

	@Test
	public void mailUserNameMustBeNotNull() {
		//test AppConfigReaderUtil properties reading

		String databaseUrl = configReader.getProperty("mail.username");

		assertNotNull(databaseUrl);

	}
}
