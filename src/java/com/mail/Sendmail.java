package com.mail;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Sendmail {
    void attachment(MimeMessage msg,String path,boolean isattachment){
        
        try {
            Multipart emailContent = new MimeMultipart();
            
            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("My multipart text");
            
            //Attachment body part.
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            pdfAttachment.attachFile(path);
            
            //Attach body parts
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachment);
            
            //Attach multipart to message
            msg.setContent(emailContent);
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(Sendmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean sendmsg(String data){
        final String username = "usedemo2022@gmail.com";
		final String password = "Usedemo22@";
		String fromEmail = "usedemo2022@gmail.com";
		String toEmail = "usedemo2022@gmail.com";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		//Start our mail message
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));
//	to one person		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
// to multiple person
 msg.addRecipients(Message.RecipientType.TO, 
                      InternetAddress.parse("19tuec134@skct.edu.in,usedemo2022@gmail.com,19tuec113@skct.edu.in"));
			msg.setSubject("COVID INDIA");
			msg.setContent(data,"text/html");

		
			Transport.send(msg);
			System.out.println("Sent message");
		}catch (MessagingException e) {
//			e.printStackTrace();
                    return false;
		}
        return true;
    }
	public static void main(String[] args) {
		//authentication info
		final String username = "usedemo2022@gmail.com";
		final String password = "Usedemo22@";
		String fromEmail = "usedemo2022@gmail.com";
		String toEmail = "usedemo2022@gmail.com";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		//Start our mail message
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject("Subject Line");
			msg.setText("HI I send a mail");

		
			Transport.send(msg);
			System.out.println("Sent message");
		}catch (MessagingException e) {
			e.printStackTrace();
		}
        }
}