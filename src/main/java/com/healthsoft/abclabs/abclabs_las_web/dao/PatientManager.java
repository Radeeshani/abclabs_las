package com.healthsoft.abclabs.abclabs_las_web.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.healthsoft.abclabs.abclabs_las_web.model.Patient;
import com.healthsoft.abclabs.abclabs_las_web.util.EmailSenderUtil;

public class PatientManager {

	public Patient getPatientById(int pid) throws Exception {
		Patient patient = null;

		ResultSet rs = MySQL.search("SELECT * FROM `patient` WHERE `id`=" + pid + "");
		if(rs.next()) {
			patient = new Patient();
			patient.setId(rs.getInt("id"));
			patient.setFirstName(rs.getString("first_name"));
			patient.setLastName(rs.getString("last_name"));
			patient.setEmail(rs.getString("email"));
			patient.setMobile(rs.getString("mobile"));
			patient.setAddressLine1(rs.getString("address_line_1"));
			patient.setAddressLine2(rs.getString("address_line_2"));
			patient.setGenderId(rs.getInt("gender_id"));	
		}

		return patient;
	}
	
	public int addPatient(Patient patient) {
		String firstName=patient.getFirstName();
		String lastName=patient.getLastName();
		String mobile=patient.getMobile();
		String email=patient.getEmail();
		String adl1=patient.getAddressLine1();
		String adl2=patient.getAddressLine2();
		int gender=patient.getGenderId();

		
		String query = String.format(
				"INSERT INTO `patient` (`first_name`, `last_name`, `mobile`, `email`, `address_line_1`, `address_line_2`,`gender_id`)\n"
						+ "VALUES\n" + "	('%s', '%s', '%s', '%s', '%s', '%s',%d);\n" + "",
				firstName, lastName, mobile, email, adl1, adl2, gender);

		try (PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS)) {

			// Execute the query
			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				// Retrieve the generated keys
				try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						int generatedId = generatedKeys.getInt(1);
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
			System.out.println("Registration failed with an unknown error.");

		}
		return 0;
	}
	
	public Patient getPatientByAppointmentId(int id) throws Exception {
		Patient patient = null;

		ResultSet rs = MySQL.search(
				"SELECT * FROM `patient` WHERE `id`=(SELECT `patient_id` FROM `appointment` WHERE `id`="+id+")");
		
		if(rs.next()) {
			patient = new Patient();
			patient.setId(rs.getInt("id"));
			patient.setFirstName(rs.getString("first_name"));
			patient.setLastName(rs.getString("last_name"));
			patient.setEmail(rs.getString("email"));
			patient.setMobile(rs.getString("mobile"));
			patient.setAddressLine1(rs.getString("address_line_1"));
			patient.setAddressLine2(rs.getString("address_line_2"));
			patient.setGenderId(rs.getInt("gender_id"));	
		}
		
		return patient;
	}

}
