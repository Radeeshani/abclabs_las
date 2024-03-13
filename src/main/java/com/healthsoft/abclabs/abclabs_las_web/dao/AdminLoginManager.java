package com.healthsoft.abclabs.abclabs_las_web.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginManager {
	// login data check method
		public boolean checkloginData(String username, String password) {	

	        String sql = "SELECT * FROM `admin` WHERE username = ? AND password = ?";
	        
	        try (PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement(sql)) {
	            // Set parameters
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);

	            // Execute the query
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    // Process the results
	            		return true;
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
							
			return false;
		}
}
