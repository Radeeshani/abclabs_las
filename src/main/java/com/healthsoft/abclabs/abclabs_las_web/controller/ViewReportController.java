package com.healthsoft.abclabs.abclabs_las_web.controller;

//import java.io.File;
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet(name = "Report",value = "/report")
//public class ViewReportServlet extends HttpServlet{
//
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    getServletContext().getRequestDispatcher("/Applications/SpringToolSuite4.app/Contents/MacOS/js/8daab6a9-74d9-4a10-9e10-d6be4e69e1fe.pdf").forward(request, response);
//	}
//}

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/report")
public class ViewReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pdfFileName = request.getParameter("url");

		// Redirect to the PDF file
		response.sendRedirect(getServletContext().getContextPath() + "/reports/" + pdfFileName);
	}
}
