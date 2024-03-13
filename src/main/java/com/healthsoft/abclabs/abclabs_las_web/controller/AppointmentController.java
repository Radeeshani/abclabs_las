package com.healthsoft.abclabs.abclabs_las_web.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.healthsoft.abclabs.abclabs_las_web.dao.MySQL;
import com.healthsoft.abclabs.abclabs_las_web.model.Appointment;
import com.healthsoft.abclabs.abclabs_las_web.model.Branch;
import com.healthsoft.abclabs.abclabs_las_web.model.Patient;
import com.healthsoft.abclabs.abclabs_las_web.service.AppointmentService;
import com.healthsoft.abclabs.abclabs_las_web.service.BranchService;
import com.healthsoft.abclabs.abclabs_las_web.service.PatientService;
import com.healthsoft.abclabs.abclabs_las_web.util.EmailSenderUtil;
import com.healthsoft.abclabs.abclabs_las_web.util.TimeUtil;

@WebServlet("/appointment_servlet")
public class AppointmentController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		AppointmentService service = AppointmentService.getAppointmentService();
		PatientService patientService=PatientService.getPatientService();
		BranchService branchService=BranchService.getBranchService();

		int pid = Integer.parseInt(request.getParameter("patientId"));
		int testType = Integer.parseInt(request.getParameter("testType"));
		String appointmentDate = request.getParameter("appointmentDate");
		int branch = Integer.parseInt(request.getParameter("nearestBranch"));

		int appoinmentId = 0;

		Patient patient = null;

		try {
			patient = patientService.getPatientById(pid);

			if (patient!=null) {

				try {
					String datetime = service.getLastAppointmentDateTime(appointmentDate, branch);

					Appointment appointment=new Appointment(datetime, testType, branch, pid);
					
					appoinmentId=service.addAppointment(appointment);
					
					
					
					
					
					
					
					EmailSenderUtil emailSender = new EmailSenderUtil();

					try {
						String firstName = patient.getFirstName();
						String lastName = patient.getLastName();
						String email = patient.getEmail();
						int gender = patient.getGenderId();
						String title = null;
						if (gender == 1) {
							title = "Mr.";
						} else if (gender == 2) {
							title = "Mrs.";
						} else {

						}

						try {
							Branch branchData=branchService.getBranchById(branch);

							String venue = null;

							if (branchData!=null) {
								venue = branchData.getCity();
							}

							SimpleDateFormat sdf = new SimpleDateFormat("d MMMM, yyyy, hh:mm a");

							// Specify the recipient, subject, and message
							String to = email;
							String subject = "Appointment Successfully Reserved | ABC Laboratories";
							String message = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n"
									+ "    <meta charset=\"UTF-8\">\n"
									+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
									+ "    <title>Appointment Confirmation</title>\n" + "    <style>\n"
									+ "        body {\n"
									+ "            font-family: 'Arial', sans-serif;\n"
									+ "            margin: 20px;\n" + "            padding: 20px;\n"
									+ "            background-color: #f4f4f4;\n"
									+ "            text-align: center;\n" + "        }\n"
									+ "        .container {\n"
									+ "            background-color: #ffffff;\n"
									+ "            padding: 20px;\n"
									+ "            border-radius: 10px;\n"
									+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n"
									+ "            max-width: 600px;\n"
									+ "            margin: 0 auto;\n" + "        }\n" + "        h1 {\n"
									+ "            color: #333333;\n" + "        }\n" + "        p {\n"
									+ "            color: #555555;\n" + "        }\n"
									+ "        .appointment-details {\n"
									+ "            border-top: 2px solid #cccccc;\n"
									+ "            margin-top: 20px;\n"
									+ "            padding-top: 20px;\n" + "        }\n"
									+ "    </style>\n" + "</head>\n" + "<body>\n"
									+ "    <div class=\"container\">\n"
									+ "        <h1>Appointment Successfully Reserved</h1>\n"
									+ "        <p>Dear " + title + firstName + " " + lastName
									+ ",</p>\n"
									+ "        <p>Your appointment has been successfully reserved with ABC Laboratories.</p>\n"
									+ "        \n" + "        <div class=\"appointment-details\">\n"
									+ "            <p><strong>Appointment ID:</strong> " + appoinmentId
									+ "</p>\n"
									+ "            <p><strong>Appointment Date and Time:</strong> "
									+ datetime + "</p>\n" + "            <p><strong>Venue:</strong> "
									+ venue + " Branch Lab</p>\n" + "        </div>\n" + "\n"
									+ "        <p>For any inquiries or changes, please contact our customer service.</p>\n"
									+ "\n"
									+ "        <p>Thank you for choosing ABC Laboratories. We look forward to serving you.</p>\n"
									+ "\n" + "        <p>Best Regards,<br>ABC Laboratories Team</p>\n"
									+ "    </div>\n" + "</body>\n" + "</html>\n" + "";

							// Send the email
							emailSender.sendEmail(to, subject, message);
							
							response.getWriter()
							.print("<h3>Successfully reserved.</h3><h4>Your appointment ID: " + appoinmentId
									+ "</br>Confirmation email with appoinment ID sent to your email.</h4>");

						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					
					
					
					
					
					

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			System.out.println(date);
				;

			} else {
				response.getWriter().write(
						"<h3>Make Appointment failed.</h3><h4>Invalid patient ID. There's no patients with that ID. Please re-check it.</h4>");
			}

		} catch (Exception e) {

		}
	}
}
