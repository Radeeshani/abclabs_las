package com.healthsoft.abclabs.abclabs_las_web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutController extends HttpServlet{
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession(false);

    // Check if the session exists
    if (session != null) {
        // Invalidate the session
        session.invalidate();

        // Redirect to a logout success page or wherever needed
        response.sendRedirect("admin_login");
        System.out.println("Session invalidated");
    } else {
        // Redirect to a login page or wherever needed
        response.sendRedirect("admin_login");
    }
}
}
