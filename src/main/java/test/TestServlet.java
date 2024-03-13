package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.healthsoft.abclabs.abclabs_las_web.util.EmailSenderUtil;

@WebServlet("/mail")
public class TestServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmailSenderUtil emailSender = new EmailSenderUtil();

		// Specify the recipient, subject, and message
		String to = "senooedu@gmail.com";
		String subject = "Test Result Report Released | ABC Laboratories";
		String message = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Test Result Report Released</title>\n" + "</head>\n"
				+ "<body style=\"font-family: 'Arial', sans-serif;\">\n" + "\n"
				+ "    <div style=\"background-color: #f4f4f4; padding: 20px; text-align: center;\">\n"
				+ "        <h2 style=\"color: #333;\">Test Result Report Released</h2>\n"
				+ "    </div>\n" + "\n" + "    <div style=\"padding: 20px;\">\n"
				+ "        <p>Dear Senura Chamod,</p>\n" + "\n"
				+ "        <p>We are pleased to inform you that your test result report is now available. You can download the report by clicking the button below:</p>\n"
				+ "\n" + "        <!-- Download Button -->\n"
				+ "        <a href=\""+getServletContext().getAttribute("HOST")+getServletContext().getAttribute("BASE_URL")+"reports/cec0642a-b907-452a-9595-a6ad9efc59ae.pdf\" style=\"display: inline-block; background-color: #4CAF50; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-top: 20px;\">\n"
				+ "            Download Report\n" + "        </a>\n" + "\n"
				+ "            </br></br>or" + "\n" + "\n"
				+ "            </br></br>Find your reports in Our website reports section using report ID.\n" + "\n" + "\n"
				+ "            </br></br>Your Report ID: <b>12</b>\n" + "\n" + "\n"
				+ "        <p>If you have any questions or concerns, please feel free to contact us. Thank you for choosing our services.</p>\n"
				+ "\n" + "        <p>Best regards,<br> Customer care team,\n"
						+ "</br>ABC Laboratories Pvt Ltd.\n"
						+ "</br>+94 (0)11 123 4567\n"
						+ "</br>info@abclaboratories.lk</p>\n"
				+ "    </div>\n" + "\n" + "</body>\n" + "</html>\n" + "";

		// Send the email
		emailSender.sendEmail(to, subject, message);

	}
	
	
}
