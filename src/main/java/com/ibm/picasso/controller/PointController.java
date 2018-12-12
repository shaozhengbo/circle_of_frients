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
	public MessagePojo point(String mid, HttpSession session) {
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
	
	@RequestMapping(value = "unPoint", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo unPoint(String mid, HttpSession session) {
		logger.info("unPoint start");
		Message message = messageService.getMessageById(Long.valueOf(mid));
		User user = (User) session.getAttribute("user");
		Point point = new Point();
		point.setMid(message);
		point.setUid(user);
		int result = pointService.unPoint(point);
		if (result == 0) {
			msg = "取消点赞失败";
		} else {
			msg = "取消点赞成功";
		}
		logger.info("unPoint end");
		return new MessagePojo(result, msg);
	}
	
	@RequestMapping(value = "getPointNum", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo getPointNum(String mid) {
		logger.info("getPointNum start");
		Message message = messageService.getMessageById(Long.valueOf(mid));
		Point point = new Point();
		point.setMid(message);
		List<Point> pointNum = pointService.getPointNum(point);
		if (pointNum == null) {
			msg = "查询点赞数失败";
		} else {
			msg = "查询点赞数成功";
		}
		logger.info("getPointNum end");
		return new MessagePojo(pointNum.size(), msg);
	}
	
	@RequestMapping(value = "isPointed", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo isPointed(String mid, HttpSession session) {
		logger.info("isPointed start");
		boolean result = true;
		Point point = new Point();
		point.setMid(messageService.getMessageById(Long.valueOf(mid)));
		point.setUid((User) session.getAttribute("user"));
		point = pointService.findByMidAndUid(point);
		if (point == null) {
			msg = "没点赞";
		} else {
			msg = "点赞了";
			result = false;
		}
		logger.info("isPointed end");
		return new MessagePojo(result, msg);
	}
}
