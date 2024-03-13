package com.healthsoft.abclabs.abclabs_las_web.service;

import java.sql.SQLException;

import com.healthsoft.abclabs.abclabs_las_web.dao.AppointmentManager;
import com.healthsoft.abclabs.abclabs_las_web.model.Appointment;

public class AppointmentService {
	private static AppointmentService appointmentService;
	
	private AppointmentService(){
		
	}

	public static AppointmentService getAppointmentService() {
		if (appointmentService == null) {
			appointmentService = new AppointmentService();
		}
		return appointmentService;
	}
	
	private AppointmentManager getAppointmentManager() {
		return new AppointmentManager();
	}
	
	public int addAppointment(Appointment appointment) throws SQLException {
		return getAppointmentManager().addAppointment(appointment);
	}

	public String getLastAppointmentDateTime(String appointmentDate,int branch) throws Exception {
		 return getAppointmentManager().getLastAppointmentDateTime(appointmentDate, branch);
	}
	
	public void updateReportId(int appointmentId,int reportId) {
		 getAppointmentManager().updateReportId(appointmentId, reportId);
	}

}
