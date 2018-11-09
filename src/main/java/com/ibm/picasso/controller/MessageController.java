package com.ibm.picasso.controller;

import java.util.Calendar;
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
import com.ibm.picasso.pojo.ResultPojo;
import com.ibm.picasso.service.FriendsService;
import com.ibm.picasso.service.MessageService;

@Controller
@RequestMapping("/Message/*")
public class MessageController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private FriendsService friendsService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static String msg = "";

	@RequestMapping(value = "getAllMessages", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo showAllMessages(HttpSession session) {
		logger.info("showAllMessages start");
		msg = "获取成功";
		List<Long> friendsIdLst = friendsService.getAllFriendsIds((User) session.getAttribute("user"));
		List<Message> lst = messageService.getAllMessages(friendsIdLst);
		if (lst == null) {
			msg = "获取失败";
		}
		logger.info("showAllMEssages end");
		return new ResultPojo(lst, msg);
	}

	@RequestMapping(value = "getAllMessagesByUid", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo getAllMessagesByUid(HttpSession session) {
		logger.info("getAllMessageByUid start");
		msg = "获取成功";
		List<Message> lst = messageService.getAllMessagesByUid((User) session.getAttribute("user"));
		if (lst == null) {
			msg = "获取失败";
		}
		logger.info("getAllMessageByUid end");
		return new ResultPojo(lst, msg);
	}

	@RequestMapping(value = "releaseMessage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo releaseMessage(Message message, HttpSession session) {
		logger.info("releaseMessage start");
		msg = "发布失败";
		message.setCreatetime(Calendar.getInstance().getTime());
		message.setStatue(0);
		message.setFrom((long) 0);
		message.setUid((User) session.getAttribute("user"));
		int result = messageService.releaseMessage(message);
		if (result > 0) {
			msg = "发布成功";
		}
		logger.info("releaseMessage end");
		return new ResultPojo(message, msg);
	}

	@RequestMapping(value = "forwardMessage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo forwardMessage(Message message, HttpSession session) {
		logger.info("forwardMessage start");
		msg = "转发失败";
		Message newMessage = new Message();
		newMessage.setUid((User) session.getAttribute("user"));
		newMessage.setCreatetime(Calendar.getInstance().getTime());
		newMessage.setStatue(0);
		newMessage.setFrom(message.getId());
		int result = messageService.forwardMessage(newMessage);
		if (result > 0) {
			msg = "转发成功";
		}
		logger.info("forwardMessage end");
		return new ResultPojo(message, msg);
	}
}
