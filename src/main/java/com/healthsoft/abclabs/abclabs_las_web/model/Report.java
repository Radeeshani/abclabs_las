package com.healthsoft.abclabs.abclabs_las_web.model;

public class Report {
private int id;
private String dateTime;
private String url;
private String technician;
private String doctor;

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
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getTechnician() {
	return technician;
}
public void setTechnician(String technician) {
	this.technician = technician;
}
public String getDoctor() {
	return doctor;
}
public void setDoctor(String doctor) {
	this.doctor = doctor;
}


}
