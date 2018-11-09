package com.ibm.picasso.service;

import java.util.List;

import com.ibm.picasso.domain.Message;
import com.ibm.picasso.domain.User;

public interface MessageService {

	int releaseMessage(Message message);

	int forwardMessage(Message message);

	List<Message> getAllMessages(List<Long> uids);

	List<Message> getAllMessagesByUid(User user);

}
