package com.cts.mail.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mail.service.MailClientService;

/**
 * @author Robert Rosario
 *
 */
@RestController
public class EmailTriggerController {

	@Autowired
	private MailClientService mailClientService;

	/**
	 * @param toEmail
	 * @param subject
	 * @param message
	 * @return
	 */
	@RequestMapping(path = "/trigger", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	public List<String> triggerEmail(@RequestParam(value = "toEmail", required = true) String toEmail,
			@RequestParam(value = "subject") String subject, @RequestParam(value = "message") String message) {

		List<String> email=new ArrayList<>(Arrays.asList("robertrosarioselvaraj@gmail.com","rrosario333@gmail.com","rrosario333@gmail.com"));
		
		List<String> actualEmail = mailClientService.prepareAndSend(email, subject, message);
		if(!actualEmail.isEmpty()){
			 actualEmail = mailClientService.prepareAndSend(actualEmail, subject, message);
		}
		return actualEmail;

	}

}
