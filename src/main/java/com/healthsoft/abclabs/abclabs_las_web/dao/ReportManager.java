package com.healthsoft.abclabs.abclabs_las_web.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.healthsoft.abclabs.abclabs_las_web.model.Report;
import com.healthsoft.abclabs.abclabs_las_web.util.EmailSenderUtil;

public class ReportManager {

	public int addReport(Report report) {
		int generatedId=0;
		
		String uniqueName=report.getUrl();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String query = String.format("INSERT INTO `report` (`datetime`, `url`, `technician_id`, `doctor_id`)\n"
				+ "VALUES\n" + "	('%s', '%s', 1, 1);\n" + "", sdf.format(new Date()), uniqueName);

		try (PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS)) {

			// Execute the query
			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				// Retrieve the generated keys
				try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						generatedId = generatedKeys.getInt(1);
						System.out.println("Generated ID: " + generatedId);


						
						
						return generatedId;
						
						

					} else {
						System.out.println("Failed to retrieve generated ID.");
					}
				}

			} else {
				System.out.println("No records inserted.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("File upload failed with an unknown error.");

		}
		return generatedId;
	}
}
