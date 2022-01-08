package com.managment.presentialmanagment.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.managment.presentialmanagment.domain.Request;

public interface EmailService {
	
	void sendRequestWaiting(Request request);
	
	void sendEmail(SimpleMailMessage msg); 
	
	void sendRequestWaitingHtmlEmail(Request obj);
	
	void sendHtmlEmail(MimeMessage msg);
}
