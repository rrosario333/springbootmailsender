package com.cts.mail.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * @author Robert Rosario
 *
 */
@Service
public class MailClientService {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MailContentBuilder mailContentBuilder;

	/**
	 * @param toEmail
	 * @param fromEmail
	 * @param subject
	 * @param message
	 * @return
	 */
	public String prepareAndSend(String toEmail, String fromEmail, String subject, String message) {

		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(fromEmail);
			messageHelper.setTo(toEmail);
			messageHelper.setSubject(subject);
			String content = mailContentBuilder.build(message);
			System.out.println(content);
			messageHelper.setText(content, true);
		};
		try {
			mailSender.send(messagePreparator);
			return "{\"message\": \"OK\"}";
		} catch (MailException e) {
			return "{\"message\": \"Error\"}";
		}

	}
}
