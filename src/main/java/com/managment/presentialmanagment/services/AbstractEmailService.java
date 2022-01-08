package com.managment.presentialmanagment.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.managment.presentialmanagment.domain.Request;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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
	
	protected String htmlFromTemplateRequest(Request obj) {
		Context context = new Context();
		context.setVariable("request", obj);
		
		return templateEngine.process("email/requestConfirmation", context);
	}
	
	@Override
	public void sendRequestWaitingHtmlEmail(Request obj) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromRequest(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendRequestWaiting(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromRequest(Request obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		
		mmh.setTo(obj.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Resquest was opened successfully");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateRequest(obj), true);
		
		return mimeMessage;
	}
}
