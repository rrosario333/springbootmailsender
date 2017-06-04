package com.cts.mail.controller;

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
	public String triggerEmail(@RequestParam(value = "toEmail", required = true) String toEmail,
			@RequestParam(value = "subject") String subject, @RequestParam(value = "message") String message) {

		String result = mailClientService.prepareAndSend(toEmail, subject, message);
		return result;

	}

}
