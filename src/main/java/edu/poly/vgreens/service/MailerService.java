package edu.poly.vgreens.service;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import edu.poly.vgreens.model2.MailResponse;
import edu.poly.vgreens.model2.MailModel;


public interface MailerService {

	void send(String to, String subject, String body) throws MessagingException;

	void send(MailModel mail) throws MessagingException;

	MailResponse sendEmailWithHTML(MailModel request, Map<String, Object> model);

	

	
}
