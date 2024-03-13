package com.healthsoft.abclabs.abclabs_las_web.service;

import com.healthsoft.abclabs.abclabs_las_web.dao.ReportManager;
import com.healthsoft.abclabs.abclabs_las_web.model.Report;

public class ReportService {
	private static ReportService reportService;

	private ReportService() {

	}

	public static ReportService getReportService() {
		if (reportService == null) {
			reportService = new ReportService();
		}
		return reportService;
	}
	
	private ReportManager getReportManager() {
		return new ReportManager();
	}
	
	public int addReport(Report report) {
		return getReportManager().addReport(report);
	}

}
