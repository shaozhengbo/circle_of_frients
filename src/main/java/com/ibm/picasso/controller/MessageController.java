package com.ibm.picasso.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.picasso.domain.Message;
import com.ibm.picasso.domain.User;
import com.ibm.picasso.pojo.MessagePojo;
import com.ibm.picasso.service.impl.MessageServiceImpl;

@Controller
@RequestMapping("/Message/*")
public class MessageController {

	@Autowired
	MessageServiceImpl messageService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static String msg = "";
	
	@RequestMapping(value="getAllMessage", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo getAllMessage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Message> result = messageService.getAllMessageByUid(user.getId());
		if(result.size() == 0) {
			msg = "获取到条"+result.size()+"消息";
		} else {
			msg = "没有消息";
		}
		return new MessagePojo(result, msg);
	}


	@RequestMapping(value = "sendMessage", method = RequestMethod.POST, produces = "application/json; chatset=utf-8")
	@ResponseBody
	public MessagePojo sendMessage(Message message, HttpSession session) {
		logger.info("sendMessage start");
		User user = (User) session.getAttribute("user");
		message.setUid(user);
		message.setPids("");
		message.setFrom((long) 0);
		message.setStatue(0);
		int result = messageService.sendMessage(message);

		if (result == 1) {
			msg = "发布成功";
		} else {
			msg = "发布失败";
		}
		logger.info("sendMessage end");
		return new MessagePojo(message, msg);
	}
	
}
