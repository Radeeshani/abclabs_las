package com.healthsoft.abclabs.abclabs_las_web.service;

import com.healthsoft.abclabs.abclabs_las_web.dao.AdminLoginManager;

public class AdminLoginService {
	private static AdminLoginService adminLoginService;

	private AdminLoginService() {

	}

	public static AdminLoginService getAdminLoginService() {
		if (adminLoginService == null) {
			adminLoginService = new AdminLoginService();
		}
		return adminLoginService;
	}

	public AdminLoginManager getAdminLoginManager() {
		return new AdminLoginManager();
	}

	public boolean checkloginData(String username, String password) {
		return getAdminLoginManager().checkloginData(username, password);
	}
}
