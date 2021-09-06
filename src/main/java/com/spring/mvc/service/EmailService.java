package com.spring.mvc.service;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	private static String FROM="javatechnology4@gmail.com";
	
	
	public void sendMail(String to, String subject) {
		 MimeMessage message = javaMailSender.createMimeMessage();
	        try {
				MimeMessageHelper helper = new MimeMessageHelper(message,
				        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				        StandardCharsets.UTF_8.name());
					helper.setTo(to);
			        helper.setText("Welcome", true);
			        helper.setSubject(subject);
			        helper.setFrom(FROM);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
		
		javaMailSender.send(message);
	}

}
