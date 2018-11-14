package com.ibm.picasso.controller;

import com.ibm.picasso.domain.Forward;
import com.ibm.picasso.domain.Message;
import com.ibm.picasso.domain.Point;
import com.ibm.picasso.domain.User;
import com.ibm.picasso.pojo.ResultPojo;
import com.ibm.picasso.service.ForwardService;
import com.ibm.picasso.service.FriendsService;
import com.ibm.picasso.service.MessageService;
import com.ibm.picasso.service.PointService;
import com.ibm.picasso.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/Message/*")
public class MessageController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private FriendsService friendsService;
	@Autowired
	private PointService pointService;
	@Autowired
	private ForwardService forwardService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static String msg = "";

	/**
	 * 获取所有朋友的朋友圈
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "getAllMessages", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo showAllMessages(HttpSession session) {
		logger.info("showAllMessages start");
		msg = "获取成功";
		// 获取所有朋友的id
		List<Long> friendsIdLst = friendsService.getAllFriendsIds((User) session.getAttribute("user"));
		List<Message> lst = messageService.getAllMessages(friendsIdLst);
		if (lst == null) {
			msg = "获取失败";
		}
		logger.info("showAllMessages end");
		return new ResultPojo(lst, msg);
	}

	/**
	 * 获取指定id用户的所有朋友圈
	 * 
	 * @param session
	 * @return
	 */
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

	/**
	 * 发布消息
	 * @param message
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "releaseMessage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo releaseMessage(Message message, HttpSession session) {
		logger.info("releaseMessage start");
		msg = "发布失败";
		message.setCreatetime(Util.getNowDate());
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
	
	/**
	 * 更新朋友圈状态
     * @param id
     * @param status
	 * @return
	 */
	@RequestMapping(value = "updateMessage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo updateMessage(String id, int status) {
		logger.info("updateMessage start");
		msg = "操作失败";
		Message message = messageService.getMessageById(Long.valueOf(id));
		
		if(message != null) {
			if(status == 2) {
				//删除
				message.setDeletetime(Util.getNowDate());
			}
			message.setStatue(status);
			int result = messageService.updateMessage(message);
			
			if(result > 0) {
				msg = "操作成功";
			}
		}
		logger.info("updateMessage end");
		return new ResultPojo(message, msg);
	}

	/**
	 * 转发朋友圈
	 * @param message
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "forwardMessage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo forwardMessage(Message message, HttpSession session) {
		logger.info("forwardMessage start");
		msg = "转发失败";
		Message newMessage = new Message();
		newMessage.setUid((User) session.getAttribute("user"));
		newMessage.setCreatetime(Util.getNowDate());
		newMessage.setStatue(0);
		newMessage.setFrom(message.getId());
		int result = messageService.forwardMessage(newMessage);
		if (result > 0) {
			msg = "转发成功";
			Forward forward = new Forward();
			forward.setMid(message);
			forward.setUid((User) session.getAttribute("user"));
			forward.setCreatetime(Util.getNowDate());
			forwardService.addForwardRecord(forward);
		}
		logger.info("forwardMessage end");
		return new ResultPojo(message, msg);
	}
	
	/**
	 * 点赞
	 * @param message
	 * @param session
	 * @return
	 */
	@RequestMapping(value="pointMessage", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo pointMessage(Message message, HttpSession session) {
		logger.info("pointMessage start");
		msg = "点赞失败";
		Point point = new Point();
		point.setMid(message);
		point.setUid((User)session.getAttribute("user"));
		point.setCreatetime(Util.getNowDate());
		int result = pointService.pointMessage(point);
		if(result > 0) {
			msg = "点赞成功";
		} else if (result == -1) {
			msg = "取消点赞成功";
		}
		logger.info("pointMessage end");
		return new ResultPojo(point, msg);
	}
}
