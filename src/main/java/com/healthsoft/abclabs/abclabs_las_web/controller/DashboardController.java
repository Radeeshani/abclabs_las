package com.healthsoft.abclabs.abclabs_las_web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="Dashboard",value = "/dashboard")
public class DashboardController extends HttpServlet{
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// Get the session
    HttpSession session = request.getSession(false);

    // Check if the session exists and if the attribute is set
    if (session != null && session.getAttribute("username") != null) {
        String username = (String) session.getAttribute("username");
        getServletContext().getRequestDispatcher("/WEB-INF/views/admin_dashboard.jsp").forward(request, response);
    } else {
        // Send unauthorized (401) response code
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("text/html");
        response.getWriter().println("<h3>Unauthorized request. Please login as an admin.</h3></br>"
        		+ "<a id='loginUrl' href='"+getServletContext().getAttribute("BASE_URL")+"admin_login'>Admin Login</a>");
        
//        response.sendRedirect("admin_login");
        }
    
}
}
