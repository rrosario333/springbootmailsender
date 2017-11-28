package com.cts.mail.service;

import java.util.List;

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
	public List<String> prepareAndSend(List<String> toEmail, String subject, String message) {

		toEmail.forEach(email ->{
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			//messageHelper.setFrom(fromEmail);
			messageHelper.setTo(email);
			messageHelper.setSubject(subject);
			String content = mailContentBuilder.build(message);
			System.out.println(content);
			messageHelper.setText(content, true);
		};
		try {
			System.out.println("before mail");
			mailSender.send(messagePreparator);
			System.out.println("After mail");
			toEmail.remove(email);
		} catch (MailException e) {
			e.printStackTrace();
			
		}
		});
		return toEmail;
	}
}
