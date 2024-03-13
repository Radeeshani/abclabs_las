package com.healthsoft.abclabs.abclabs_las_web.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.healthsoft.abclabs.abclabs_las_web.dao.MySQL;
import com.healthsoft.abclabs.abclabs_las_web.model.Patient;
import com.healthsoft.abclabs.abclabs_las_web.service.PatientService;
import com.healthsoft.abclabs.abclabs_las_web.util.EmailSenderUtil;

@WebServlet("/registerPatient")
public class RegisterPatientController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		PatientService service=PatientService.getPatientService();

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int gender = Integer.parseInt(request.getParameter("gender"));
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String adl1 = request.getParameter("addressLine1");
		String adl2 = request.getParameter("addressLine2");

		if (firstName.isEmpty()) {
			response.getWriter().write("<h3>Registration Failed</h3><p>Please enter your First Name</p>");
		} else if (lastName.isEmpty()) {
			response.getWriter().write("<h3>Registration Failed</h3><p>Please enter your Last Name</p>");
		} else if (!Pattern.matches("^[0]{1}[7]{1}[01245678]{1}[0-9]{7}", mobile)) {
			response.getWriter()
					.write("<h3>Registration Failed</h3><p>Please enter a valid Sri Lankan mobile number.</p>");
		} else if (!Pattern.matches("^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])", email)) {
			response.getWriter().write("<h3>Registration Failed</h3><p>Please enter a valid email address.</p>");
		} else {

			try {

				ResultSet rs = MySQL.search("SELECT `id` FROM `patient` WHERE `email`='" + email + "'");

				if (rs.next()) {
					response.getWriter().write("<h3>Registration failed.</h3></br><h4>User with this email already exists. Please use another email or Contact customer care to find your ID.</h4>");
				} else {

					Patient patient=new Patient(firstName, lastName, mobile, email, adl1, adl2, gender);
					
					int generatedId=service.addPatient(patient);
					
					String title=null;
					
					if (gender == 1) {
						title = "Mr.";
					} else if (gender == 2) {
						title = "Mrs.";
					} else {

					}
															
					EmailSenderUtil emailSender = new EmailSenderUtil();

					String to = email;
					String subject = "Your Patient ID at ABC Laboratories";
					String message = "<!DOCTYPE html>\n"
							+ "<html lang=\"en\">\n"
							+ "<head>\n"
							+ "    <meta charset=\"UTF-8\">\n"
							+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
							+ "    <title>Registration Confirmation</title>\n"
							+ "</head>\n"
							+ "<body>\n"
							+ "    <p>Dear "+title+" "+firstName+" "+lastName+",</p>\n"
							+ "    <p>We would like to inform you that you are successfully registered to ABC Laboratories, and you have been assigned a Patient ID for future reference.</p>\n"
							+ "    <p><strong>Patient ID:</strong> "+generatedId+"</p>\n"
							+ "    <p>This Patient ID will be essential for any future interactions with ABC Laboratories, including appointments, test results, and other related services. Please make a note of your Patient ID and keep it in a secure place.</p>\n"
							+ "    <p>If you have any questions or need further assistance, feel free to contact our customer support team at <a href=\"[Customer Support Email/Phone Number]\">Customer Support Email/Phone Number</a>.</p>\n"
							+ "    <p>Thank you for choosing ABC Laboratories. We appreciate the opportunity to serve you, and we look forward to providing you with excellent healthcare services.</p>\n"
							+ "    <p>Best regards,<br>Customer care team, ABC Laboratories<br>+94 (0)11 123 4567<br><a href=\"mailto:info@abclaboratories.lk\">info@abclaboratories.lk</a></p>\n"
							+ "</body>\n"
							+ "</html>";

					// Send the email
					emailSender.sendEmail(to, subject, message);
					
					response.getWriter().write(
							"<h3>Successfully Registered.</h3><h4>Your patient ID had sent to your email.</h4>");																									
					

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
