package com.ibm.picasso.controller;

import java.util.ArrayList;
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

import com.ibm.picasso.domain.Comment;
import com.ibm.picasso.domain.Message;
import com.ibm.picasso.domain.User;
import com.ibm.picasso.pojo.MessagePojo;
import com.ibm.picasso.service.impl.CommentServiceImpl;
import com.ibm.picasso.service.impl.MessageServiceImpl;
import com.ibm.picasso.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/Comment/*")
public class CommentController {

	@Autowired
	MessageServiceImpl messageService;
	@Autowired
	UserServiceImpl userService;
	@Autowired
	CommentServiceImpl commentService;

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	private static String msg = "";

	@RequestMapping(value = "getCommentByMid", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo getAllMessage(String mid) {
		logger.info("getCommentByMid start");

		List<Comment> result = commentService.findCommentByMid(Long.valueOf(mid));
		if (result == null) {
			result = new ArrayList<Comment>();
		}

		logger.info("getCommentByMid end");
		return new MessagePojo(result, msg);
	}
	
	@RequestMapping(value = "getCommentNum", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo getCommentNum(String mid) {
		logger.info("getCommentNum start");

		List<Comment> result = commentService.findCommentByMid(Long.valueOf(mid));

		logger.info("getCommentNum end");
		return new MessagePojo(result.size(), msg);
	}

	@RequestMapping(value = "addComment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo addComment(String mid, String comment, HttpSession session) {
		logger.info("addComment start");
		User user = (User) session.getAttribute("user");
		Message message = messageService.getMessageById(Long.valueOf(mid));
		Comment commentObj = new Comment();
		commentObj.setUid(user);
		commentObj.setMid(message);
		commentObj.setCreatetime(Calendar.getInstance().getTime());
		commentObj.setComment(comment);
		int result = commentService.addComment(commentObj);
		logger.info("addComment end");
		return new MessagePojo(result, "");
	}

}
