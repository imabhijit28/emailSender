package com.java.emailSender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.java.emailSender.service.EmailService;

@SpringBootTest
public class EmailSenderTest {

	@Autowired
	private EmailService emailService;
	
	@Test
	void emailSendTest() {
		System.out.println("Sending Mail");
		emailService.sendEmail("imabhijitks28@gmail.com", "This is from SpringBoot", "Email send tested successfully");
	}
	
	@Test
	void emailSendWithHtmlTest() {
		String html = "" + "<h1 style='color:red;border:1px solid red;'>Hello World</h1>" + "";
		System.out.println("Sending HTML Mail");
		emailService.sendEmailWithHtml("imabhijitks28@gmail.com", "This is HTML email send", html);
	}
	
	@Test
	void emailSendWithFile() {
		System.out.println("Sending Mail with File");
		emailService.sendEmailWithFile("imabhijitks28@gmail.com", "This is HTML email send", "This mail contains file",
				new File("src/main/resources/hacker.jpg"));
	}
	
	@Test
	void emailSendWithInputStream() throws FileNotFoundException {
		System.out.println("Sending Mail with InputStream");
		File file = new File("src/main/resources/hacker.jpg");
		InputStream ins = new FileInputStream(file);
		emailService.sendEmailWithInputStream("imabhijitks28@gmail.com", "This is InputStream email send", "This mail contains file",ins);
	}
}
