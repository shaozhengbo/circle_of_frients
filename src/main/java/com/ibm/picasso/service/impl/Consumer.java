package com.ibm.picasso.service.impl;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.ibm.picasso.util.Util;

@Component
public class Consumer {
	@Autowired
	private JavaMailSenderImpl senderImpl;
	
	@JmsListener(destination = "mail_lp")
    public void sendMail(Message message) throws JMSException, MessagingException {
        MapMessage mm = (MapMessage) message;
        String title = mm.getString("title");
        String email = mm.getString("email");
        Util.sendMail(title, "注册通知",email,senderImpl);
    }

}
