package com.ibm.picasso.service;

import java.util.List;

import com.ibm.picasso.domain.Message;

public interface MessageService {
	
	int sendMessage(Message message);
	
	List<Message> getAllMessageByUid(Long uid);
}
