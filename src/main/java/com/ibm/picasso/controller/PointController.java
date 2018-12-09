package com.ibm.picasso.controller;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.picasso.domain.Message;
import com.ibm.picasso.domain.Point;
import com.ibm.picasso.domain.User;
import com.ibm.picasso.pojo.MessagePojo;
import com.ibm.picasso.service.impl.MessageServiceImpl;
import com.ibm.picasso.service.impl.PointServiceImpl;

@Controller
@RequestMapping("/Point/*")
public class PointController {

	@Autowired
	PointServiceImpl pointService;

	@Autowired
	MessageServiceImpl messageService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static String msg = "";

	@RequestMapping(value = "point", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo point(String mid, String createtime, HttpSession session) {
		logger.info("point start");
		Message message = messageService.getMessageById(Long.valueOf(mid));
		User user = (User) session.getAttribute("user");
		Point point = new Point();
		point.setMid(message);
		point.setUid(user);
		point.setCreatetime(Calendar.getInstance().getTime());
		int result = pointService.point(point);
		if (result == 0) {
			msg = "点赞失败";
		} else {
			msg = "点赞成功";
		}
		logger.info("point end");
		return new MessagePojo(result, msg);
	}
}
