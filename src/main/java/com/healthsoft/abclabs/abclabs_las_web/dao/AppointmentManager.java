package com.healthsoft.abclabs.abclabs_las_web.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.healthsoft.abclabs.abclabs_las_web.model.Appointment;
import com.healthsoft.abclabs.abclabs_las_web.util.EmailSenderUtil;
import com.healthsoft.abclabs.abclabs_las_web.util.TimeUtil;

public class AppointmentManager {

	public int addAppointment(Appointment appointment) throws SQLException {
		int appointmentId = 0;

		String datetime = appointment.getDateTime();
		int testType = appointment.getTestTypeId();
		int branch = appointment.getBranchId();
		int pid = appointment.getPatientId();

		String query = String
				.format("INSERT INTO `appointment` (`datetime`, `test_type_id`, `branch_id`, `patient_id`)\n"
						+ "VALUES\n" + "	('%s', %d, %d, %d);\n" + "", datetime, testType, branch, pid);

		PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement(query,
				Statement.RETURN_GENERATED_KEYS);

		// Execute the query
		int affectedRows = preparedStatement.executeUpdate();

		if (affectedRows > 0) {
			// Retrieve the generated keys
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int generatedId = generatedKeys.getInt(1);

					appointmentId = generatedId;

				} else {
					System.out.println("Failed to retrieve generated ID.");
				}
			}

		}
		return appointmentId;
	}

	public String getLastAppointmentDateTime(String appointmentDate, int branch) throws Exception {

		ResultSet rs = MySQL.search("SELECT MAX(`datetime`) FROM `appointment` WHERE `datetime`>='" + appointmentDate
				+ "' AND `branch_id`=" + branch + "");
		String datetime;
		rs.next();

		datetime = rs.getString("MAX(`datetime`)");

		if (datetime != null) {
			datetime = TimeUtil.getTimeUtil().addMinutes(datetime, 10);
		} else {
			datetime = appointmentDate + " 09:00:00";
		}

		return datetime;
	}
	
	public void updateReportId(int appointmentId,int reportId) {
		MySQL.iud("UPDATE `appointment` SET `report_id`=" + reportId
				+ " WHERE `appointment`.`id`=" + appointmentId + "");
	}
		
}
