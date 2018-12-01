package com.ibm.picasso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.picasso.dao.MessageDao;
import com.ibm.picasso.domain.Message;
import com.ibm.picasso.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	@Override
	public int sendMessage(Message message) {
		return messageDao.insert(message);
	}

}
