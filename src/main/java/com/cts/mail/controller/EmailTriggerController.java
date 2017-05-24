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
	 * @param fromEmail
	 * @param subject
	 * @param message
	 * @return
	 */
	@RequestMapping(path = "/email/trigger", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	public String triggerEmail(@RequestParam(value = "toEmail", required = true) String toEmail,
			@RequestParam(value = "fromEmail", required = true) String fromEmail,
			@RequestParam(value = "subject") String subject, @RequestParam(value = "message") String message) {

		String result = mailClientService.prepareAndSend(toEmail, fromEmail, subject, message);
		return result;

	}

}
