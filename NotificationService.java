package com.example;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

public class NotificationService {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String SMTP_USER = "niveditavs.2004@gmail.com"; // Replace with your Gmail address
    private static final String SMTP_PASSWORD = "Nivedita@2004"; // Replace with your Gmail password or App Password

    public static void sendNotification(String recipientEmail, String subject, String messageText) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(SMTP_USER, SMTP_PASSWORD);
            }
        });

        try {
        	Message message = new MimeMessage(session);
        	message.setFrom(new InternetAddress(SMTP_USER));
        	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        	message.setSubject(subject); message.setText(messageText);
        	Transport.send(message);
        	System.out.println("Notification sent to " + recipientEmail);
        	}
        catch (MessagingException e) { 
        		System.err.println("Failed to send notification to " + recipientEmail + ": " + e.getMessage()); 
        		}
    }
}
