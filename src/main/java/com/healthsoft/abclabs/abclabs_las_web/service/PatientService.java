package com.healthsoft.abclabs.abclabs_las_web.service;

import com.healthsoft.abclabs.abclabs_las_web.dao.PatientManager;
import com.healthsoft.abclabs.abclabs_las_web.model.Patient;

public class PatientService {
	private static PatientService patientService;

	private PatientService() {

	}

	public static PatientService getPatientService() {
		if (patientService == null) {
			patientService = new PatientService();
		}
		return patientService;
	}

	private PatientManager getPatientManager() {
		return new PatientManager();
	}
	
	public Patient getPatientById(int pid) throws Exception {
		return getPatientManager().getPatientById(pid);
	}
	
	public int addPatient(Patient patient) {
		return getPatientManager().addPatient(patient);
	}
	
	public Patient getPatientByAppointmentId(int id) throws Exception {
		return getPatientManager().getPatientByAppointmentId(id);
	}

}
