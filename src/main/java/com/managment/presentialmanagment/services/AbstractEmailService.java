package com.managment.presentialmanagment.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.managment.presentialmanagment.domain.Request;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendRequestWaiting(Request obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromRequest(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromRequest(Request obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());	
		sm.setFrom(sender);
		sm.setSubject("You Request was created");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

	
}
