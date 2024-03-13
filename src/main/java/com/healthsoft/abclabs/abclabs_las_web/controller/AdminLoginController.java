package com.healthsoft.abclabs.abclabs_las_web.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.healthsoft.abclabs.abclabs_las_web.dao.MySQL;
import com.healthsoft.abclabs.abclabs_las_web.service.AdminLoginService;

@WebServlet("/admin_login")
public class AdminLoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/views/admin_login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AdminLoginService service=AdminLoginService.getAdminLoginService();
		
		// Retrieve username and password from the request
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// check details using AdminLoginService 
		boolean loginSuccessful = service.checkloginData(username, password);

		// Send response based on login success
		if (loginSuccessful) {
			response.getWriter().write("success");
			
	        // Get the session
	        HttpSession session = request.getSession(true);

	        // Set a session attribute
	        session.setAttribute("username", username);
	        
		} else {
			response.getWriter().write("Invalid Username or Password");
		}
	}

}
