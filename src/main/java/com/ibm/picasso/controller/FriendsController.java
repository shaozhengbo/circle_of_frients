package com.ibm.picasso.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.ibm.picasso.pojo.ResultPojo;
import com.ibm.picasso.service.FriendsService;
import com.ibm.picasso.service.UserService;
import com.ibm.picasso.util.Util;

@Controller
@RequestMapping("/Friends/*")
public class FriendsController {

	@Autowired
	private FriendsService friendsService;
	
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static String msg = "";

	/**
	 * 查询所有的好友
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "getAllFriends", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo getAllFriends(HttpSession session) {
		logger.info("getAllFriends start");

		msg = "查询失败";

		List<Friends> friendsLst = friendsService.findAllFriendsByUid((User) session.getAttribute("user"));

		if (friendsLst != null) {
			msg = "查询成功";
		} else {
			friendsLst = new ArrayList<>();
		}

		logger.info("getAllFriends end");
		return new ResultPojo(friendsLst, msg);
	}
	
	/**
	 * 添加好友
	 * @param uid
	 * @param session
	 * @return
	 */
	@RequestMapping(value="addFriends", method=RequestMethod.POST, params="application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo addFriends(String uid, HttpSession session) {
		logger.info("addFriends start");

		msg = "添加失败";

		User findUser = userService.findUserById(Long.valueOf(uid));
		Friends friends = new Friends();
		friends.setState(0);
		friends.setUid1((User)session.getAttribute("user"));
		friends.setUid2(findUser);
		friends.setCreatetime(Util.getNowDate());
		int result = friendsService.addFriends(friends);

		if (result > 0) {
			msg = "添加成功";
		}

		logger.info("addFriends end");
		return new ResultPojo(friends, msg);
	}
	
	/**
	 * 判断是不是好友关系
	 * @param uid
	 * @param session
	 * @return
	 */
	@RequestMapping(value="isFriends", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo isFriends(String uid, HttpSession session) {
		logger.info("isFriends start");

		msg = "查询失败";

		User findUser = userService.findUserById(Long.valueOf(uid));
		Friends friends = new Friends();
		friends.setUid1((User)session.getAttribute("user"));
		friends.setUid2(findUser);
		friends = friendsService.isFriends(friends);

		if (friends.getId() != null) {
			msg = "是好友";
		} else {
			msg = "不是好友";
		}

		logger.info("isFriends end");
		return new ResultPojo(friends, msg);
	}

}
