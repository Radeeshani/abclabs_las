package com.healthsoft.abclabs.abclabs_las_web.dao;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.Test;

public class MySQLTest {

	@Test
	public void testMySQLConnection() {
		try {
			ResultSet rs= MySQL.search("SELECT * FROM `gender`");
			assertTrue(rs.next());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
