package com.ibm.picasso.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.picasso.domain.Friends;
import com.ibm.picasso.domain.User;
import com.ibm.picasso.pojo.MessagePojo;
import com.ibm.picasso.service.impl.FriendsServiceImpl;

@Controller
@RequestMapping("/Friend/*")
public class FriendController {

	@Autowired
	FriendsServiceImpl friendsService;

	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);

	private static String msg = "";

	@RequestMapping(value = "addFriend", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo addFriend(String uid, HttpSession session) {
		logger.info("addFriend start");
		User user = (User) session.getAttribute("user");
		Friends friends = new Friends();
		friends.setUid1(user);
		User addUser = new User();
		addUser.setId(Long.valueOf(uid));
		friends.setUid2(addUser);
		friends.setState(0);
		friends.setCreatetime(Calendar.getInstance().getTime());
		friends.setUpdatetime(Calendar.getInstance().getTime());
		int result = friendsService.addFriends(friends);
		if (result == 1) {
			msg = "添加成功";
		} else {
			msg = "添加失败";
		}
		logger.info("addFriend start");
		return new MessagePojo(friends, msg);
	}
	
	@RequestMapping(value = "isFriend", method = RequestMethod.POST, produces = "application/json; chatset=utf-8")
	@ResponseBody
	public MessagePojo isFriend(String uid, HttpSession session) throws IOException {
		logger.info("isFriend start");
		User user = (User)session.getAttribute("user");
		Friends friend = friendsService.isFriend(user.getId(), Long.valueOf(uid));
		if(friend != null) {
			msg = "是好友";
		} else {
			msg = "不是好友";
		}
		logger.info("isFriend end");
		return new MessagePojo(friend, msg);
	}

	@RequestMapping(value = "removeFriend", method = RequestMethod.POST, produces = "application/json; chatset=utf-8")
	@ResponseBody
	public MessagePojo removeFriend(String uid) throws IOException {
		logger.info("removeFriend start");

		logger.info("removeFriend end");
		return new MessagePojo(null, msg);
	}

}
