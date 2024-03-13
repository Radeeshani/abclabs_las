package com.healthsoft.abclabs.abclabs_las_web.model;

public class Appointment {
	private int id;
private String dateTime;
private int testTypeId;
private int reportId;
private int branchId;
private int patientId;

public Appointment(int id, String dateTime, int testTypeId, int reportId, int branchId, int patientId) {
	super();
	this.id = id;
	this.dateTime = dateTime;
	this.testTypeId = testTypeId;
	this.reportId = reportId;
	this.branchId = branchId;
	this.patientId = patientId;
}

public Appointment(String dateTime, int testTypeId, int branchId, int patientId) {
	super();
	this.dateTime = dateTime;
	this.testTypeId = testTypeId;
	this.branchId = branchId;
	this.patientId = patientId;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDateTime() {
	return dateTime;
}
public void setDateTime(String dateTime) {
	this.dateTime = dateTime;
}
public int getTestTypeId() {
	return testTypeId;
}
public void setTestTypeId(int testTypeId) {
	this.testTypeId = testTypeId;
}
public int getReportId() {
	return reportId;
}
public void setReportId(int reportId) {
	this.reportId = reportId;
}
public int getBranchId() {
	return branchId;
}
public void setBranchId(int branchId) {
	this.branchId = branchId;
}
public int getPatientId() {
	return patientId;
}
public void setPatientId(int patientId) {
	this.patientId = patientId;
}


}
