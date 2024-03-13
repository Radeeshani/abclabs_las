package com.healthsoft.abclabs.abclabs_las_web.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSenderUtil {

    private final String username; // Your email username
    private final String password; // Your email password
    private final String host; // SMTP server host
    private final String port; // SMTP server port

    public EmailSenderUtil() {
    	AppConfigReaderUtil configReader=new AppConfigReaderUtil();
    	
        this.username = configReader.getProperty("mail.username");
        this.password = configReader.getProperty("mail.password");
        this.host = configReader.getProperty("mail.smtp.host");
        this.port = configReader.getProperty("mail.smtp.port");
    }
    
    public void sendEmail(String to, String subject, String htmlContent) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(username));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            emailMessage.setSubject(subject);

            // Set the HTML content using setText with MimeBodyPart
            emailMessage.setText(htmlContent, "utf-8", "html");

            // Send the email
            Transport.send(emailMessage);

            System.out.println("HTML Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

