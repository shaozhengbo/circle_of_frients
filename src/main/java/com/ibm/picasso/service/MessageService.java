package com.ibm.picasso.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.picasso.domain.Message;
import com.ibm.picasso.domain.User;

@Service
@Transactional
public interface MessageService {

	int releaseMessage(Message message);

	int forwardMessage(Message message);

	List<Message> getAllMessages(List<Long> uids);

	List<Message> getAllMessagesByUid(User user);
	
	Message getMessageById(Long id);
	
	int updateMessage(Message message);
}
