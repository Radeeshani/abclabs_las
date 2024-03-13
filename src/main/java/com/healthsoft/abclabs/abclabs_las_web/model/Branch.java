package com.healthsoft.abclabs.abclabs_las_web.model;

public class Branch {
	private int id;
	private String city;
	private String address;
	private String telephone;

	public Branch() {

	}

	public Branch(int id, String city, String address, String telephone) {
		super();
		this.id = id;
		this.city = city;
		this.address = address;
		this.telephone = telephone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
