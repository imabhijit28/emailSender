package com.java.emailSender.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java.emailSender.bean.EmailRequest;
import com.java.emailSender.service.EmailService;
import com.java.emailSender.utility.Response;

@RestController
@RequestMapping("/api/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/send")
	public ResponseEntity<Response> sendEmail(@RequestBody EmailRequest emailRequest){
		emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
		return Response.buildResponse("Email Send Successfully", HttpStatus.OK);
	}
	
	@PostMapping("/sendEmailWithFile")
	public ResponseEntity<Response> sendEmailWithFile(@RequestPart EmailRequest emailRequest, @RequestPart MultipartFile multipartfile) throws IOException{
		emailService.sendEmailWithInputStream(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage(),multipartfile.getInputStream());
		return Response.buildResponse("Attachment Send Successfully", HttpStatus.OK);
	}

}
