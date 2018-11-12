package com.ibm.picasso.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.picasso.domain.Message;
import com.ibm.picasso.domain.User;
import com.ibm.picasso.mapper.MessageMapper;
import com.ibm.picasso.service.MessageService;

public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;

	@Override
	public int releaseMessage(Message message) {
		return messageMapper.insert(message);
	}

	@Override
	public int forwardMessage(Message message) {
		return messageMapper.insert(message);
	}

	@Override
	public List<Message> getAllMessages(List<Long> uids) {
		List<Message> resultLst = new ArrayList<Message>();
		for (Long uid : uids) {
			List<Message> messageLst = messageMapper.selectMessagesByUid(uid);
			resultLst.addAll(messageLst);
		}
		return resultLst;
	}

	@Override
	public List<Message> getAllMessagesByUid(User user) {
		return messageMapper.selectMessagesByUid(user.getId());
	}

	@Override
	public Message getMessageById(Long id) {
		return messageMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateMessage(Message message) {
		return messageMapper.updateMessage(message);
	}

}
