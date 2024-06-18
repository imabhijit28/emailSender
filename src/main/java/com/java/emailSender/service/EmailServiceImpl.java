package com.java.emailSender.service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Override
	public void sendEmail(String to, String subject, String message) {
		logger.info("Email send to : {}, subject : {}, message : {}",to,subject,message);
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(message);
			simpleMailMessage.setFrom("opjunior1999@gmail.com");

			mailSender.send(simpleMailMessage);
			logger.info("Email sent successfully");
		} catch (Exception e) {
			logger.error("Error while sending mail");
			e.printStackTrace();
		}
	}

	@Override
	public void sendEmail(String[] to, String subject, String message) {
		logger.info("Multiple Email send to : {}, subject : {}, message : {}",to,subject,message);
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(message);
			simpleMailMessage.setFrom("opjunior1999@gmail.com");

			mailSender.send(simpleMailMessage);
			logger.info("MiltipleEmail sent successfully");
		} catch (Exception e) {
			logger.error("Error while sending MiltipleEmail mail");
			e.printStackTrace();
		}

	}

	@Override
	public void sendEmailWithHtml(String to, String subject, String htmlContent) {
		logger.info("Email sendEmailWithHtml to : {}, subject : {}, message : {}",to,subject,htmlContent);
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); //Mimemessage,multipart,encoding
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("opjunior1999@gmail.com");
			helper.setText(htmlContent, true);

			mailSender.send(mimeMessage);
			logger.info("HTMLEmail sent successfully");
		} catch (Exception e) {
			logger.error("Error while sending html mail");
			e.printStackTrace();
		}
	}

	@Override
	public void sendEmailWithFile(String to, String subject, String message, File file) {
		logger.info("SendEmailWithFile send to : {}, subject : {}, message : {}",to,subject,message);
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("opjunior1999@gmail.com");
			helper.setText(message,true);
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			
			mailSender.send(mimeMessage);
			logger.info("EmailWithFile sent successfully");
		}  catch (Exception e) {
			logger.error("Error while sending EmailWithFile mail");
			e.printStackTrace();
		}

	}

	@Override
	public void sendEmailWithInputStream(String to, String subject, String message, InputStream ins) {
		logger.info("sendEmailWithInputStream send to : {}, subject : {}, message : {}",to,subject,message);
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("opjunior1999@gmail.com");
			helper.setText(message,true);
			File file = new File("src/main/resources/email/test.jpg");
			Files.copy(ins, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			
			mailSender.send(mimeMessage);
			logger.info("EmailWithFile sent successfully");
		} catch (Exception e) {
			logger.error("Error while sending sendEmailWithInputStream mail");
			e.printStackTrace();
		}
		
	}

}
