package com.healthsoft.abclabs.abclabs_las_web.controller;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.healthsoft.abclabs.abclabs_las_web.dao.MySQL;
import com.healthsoft.abclabs.abclabs_las_web.model.Patient;
import com.healthsoft.abclabs.abclabs_las_web.model.Report;
import com.healthsoft.abclabs.abclabs_las_web.service.AppointmentService;
import com.healthsoft.abclabs.abclabs_las_web.service.PatientService;
import com.healthsoft.abclabs.abclabs_las_web.service.ReportService;
import com.healthsoft.abclabs.abclabs_las_web.util.EmailSenderUtil;
import com.healthsoft.abclabs.abclabs_las_web.util.RenameFileUtil;

@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class FileUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReportService service = ReportService.getReportService();
		AppointmentService appointmentService = AppointmentService.getAppointmentService();
		PatientService patientService = PatientService.getPatientService();

		String savePath = getServletContext().getRealPath("/reports");
		System.out.println(savePath);

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		for (Part part : request.getParts()) {
			RenameFileUtil renameFileUtil = RenameFileUtil.getFileUtil();
			String fileName = renameFileUtil.extractFileName(part);
			String uniqueName = renameFileUtil.generateUniqueFileName(fileName);

			part.write(savePath + File.separator + uniqueName);

			Report report = new Report();
			report.setUrl(uniqueName);

			int generatedId = service.addReport(report);

			int id = Integer.parseInt(request.getParameter("id"));

			appointmentService.updateReportId(id, generatedId);

			try {
				Patient patient = patientService.getPatientByAppointmentId(id);

				String email = patient.getEmail();
				String firstName = patient.getFirstName();
				String lastName = patient.getLastName();
				int gender = patient.getGenderId();

				String title = null;

				if (gender == 1) {
					title = "Mr.";
				} else if (gender == 2) {
					title = "Mrs.";
				} else {

				}

				EmailSenderUtil emailSender = new EmailSenderUtil();

				// Specify the recipient, subject, and message
				String to = email;
				String subject = "Test Result Report Released | ABC Laboratories";
				String message = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n"
						+ "    <meta charset=\"UTF-8\">\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
						+ "    <title>Test Result Report Released</title>\n" + "</head>\n"
						+ "<body style=\"font-family: 'Arial', sans-serif;\">\n" + "\n"
						+ "    <div style=\"background-color: #f4f4f4; padding: 20px; text-align: center;\">\n"
						+ "        <h2 style=\"color: #333;\">Test Result Report Released</h2>\n" + "    </div>\n"
						+ "\n" + "    <div style=\"padding: 20px;\">\n" + "        <p>Dear " + title + " " + firstName
						+ " " + lastName + ",</p>\n" + "\n"
						+ "        <p>We are pleased to inform you that your test result report is now available. You can download the report by clicking the button below:</p>\n"
						+ "\n" + "        <!-- Download Button -->\n" + "        <a href=\""
						+ getServletContext().getAttribute("HOST") + getServletContext().getAttribute("BASE_URL")
						+ "reports/" + uniqueName
						+ "\" style=\"display: inline-block; background-color: #4CAF50; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-top: 20px;\">\n"
						+ "            Download Report\n" + "        </a>\n" + "\n" + "            </br></br>or" + "\n"
						+ "\n"
						+ "            </br></br>Find your reports in Our website reports section using report ID.\n"
						+ "\n" + "\n" + "            </br></br>Your Report ID: <b>" + generatedId + "</b>\n" + "\n"
						+ "\n"
						+ "        <p>If you have any questions or concerns, please feel free to contact us. Thank you for choosing our services.</p>\n"
						+ "\n" + "        <p>Best regards,<br> Customer care team,\n"
						+ "</br>ABC Laboratories Pvt Ltd.\n" + "</br>+94 (0)11 123 4567\n"
						+ "</br>info@abclaboratories.lk</p>\n" + "    </div>\n" + "\n" + "</body>\n" + "</html>\n" + "";

				// Send the email
				emailSender.sendEmail(to, subject, message);

				response.getWriter().write("File uploaded Successfully. Information email had sent to patient.");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
