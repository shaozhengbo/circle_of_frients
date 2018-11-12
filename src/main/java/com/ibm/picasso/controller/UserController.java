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
import org.springframework.web.servlet.ModelAndView;

import com.ibm.picasso.domain.User;
import com.ibm.picasso.pojo.ResultPojo;
import com.ibm.picasso.service.UserService;
import com.ibm.picasso.util.Currency;
import com.ibm.picasso.util.Util;

@Controller
@RequestMapping("/User/*")
public class UserController {
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static String msg = "";

	/**
	 * 个人信息页面跳转
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getUserById")
	public ModelAndView getUserById(String id) {
		ModelAndView modelAndView = new ModelAndView();
		logger.info("getUserById start");
		User user = userService.findUserById(Long.valueOf(id));
		logger.info("getUserById end");
		if (user != null) {
			msg = Currency.SEARCHHAVE;
		} else {
			user = new User();
			msg = Currency.SEARCHNULL;
		}
		modelAndView.setViewName("personal_information.html");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	/**
	 * 通过用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "getUserByUsername")
	@ResponseBody
	public ResultPojo getUserByUsername(String username) {
		logger.info("getUserByUsername start");
		User user = userService.findUserByUsername(username);
		logger.info("getUserByUsername end");
		if (user != null) {
			msg = Currency.SEARCHHAVE;
		} else {
			user = new User();
			msg = Currency.SEARCHNULL;
		}
		return new ResultPojo(user, msg);
	}

	/**
	 * 登陆
	 * 
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getUserByUsernameAndPassword", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo getUserByUsernameAndPassword(String username, String password, HttpSession session)
			throws Exception {
		logger.info("getUserByUsernameAndPassword start");
		User user = userService.findUserByUsernameAndPassword(username, Util.MD5(password));
		if (user != null) {
			session.setAttribute("user", user);
			msg = Currency.SEARCHHAVE;
		} else {
			user = new User();
			msg = Currency.SEARCHNULL;
		}
		logger.info("getUserByUsernameAndPassword end");
		ResultPojo message = new ResultPojo(user, msg);
		return message;
	}

	/**
	 * 模糊查询用户
	 * 
	 * @param searchStr
	 * @return
	 */
	@RequestMapping(value = "searchUser", method=RequestMethod.POST, produces="appliction/json; charset = utf-8")
	@ResponseBody
	public ResultPojo searchUser(String searchStr) {
		logger.info("searchUser start");
		List<User> userLst = userService.searchUser(searchStr);
		if (userLst.size() == 0) {
			msg = Currency.SEARCHHAVE;
		} else {
			msg = Currency.SEARCHNULL;
		}
		logger.info("searchUser end");
		return new ResultPojo(userLst, msg);
	}

	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "registerUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo registerUser(User user) throws Exception {
		logger.info("registerUser start");
		user.setPassword(Util.MD5(user.getPassword()));
		user.setCreatetime(Calendar.getInstance().getTime());
		boolean result = userService.registerUser(user);
		if (result) {
			msg = Currency.REGISTERSUCCESS;
		} else {
			msg = Currency.REGISTERERROR;
		}
		logger.info("registerUser end");
		return new ResultPojo(user, msg);
	}

	/**
	 * 更新用户资料
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "updateUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo updateUser(User user) {
		logger.info("updateUser start");
		boolean result = userService.updateUser(user);
		if (result) {
			msg = Currency.SUCCESS;
		} else {
			msg = Currency.ERROR;
		}
		logger.info("updateUser end");
		return new ResultPojo(user, msg);
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updatePassword", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo updatePassword(User user) throws Exception {
		logger.info("updatePassword start");
		user.setPassword(Util.MD5(user.getPassword()));
		boolean result = userService.updatePassword(user);
		if (result) {
			msg = Currency.SUCCESS;
		} else {
			msg = Currency.ERROR;
		}
		logger.info("updatePassword end");
		return new ResultPojo(user, msg);
	}

	/**
	 * 忘记密码页面跳转
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "goForgetPassword")
	public ModelAndView goForgetPassword(String username) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forgetPassword.html");
		modelAndView.addObject("username", username);
		return modelAndView;
	}

	/**
	 * 重置密码
	 * 
	 * @param username
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultPojo resetPassword(String username, String email) throws Exception {
		logger.info("resetPassword start");
		User user = userService.findUserByUsername(username);
		user.setPassword(Util.MD5("123456"));
		boolean result = userService.updatePassword(user);
		if (result) {
			msg = Currency.SUCCESS;
		} else {
			msg = Currency.ERROR;
		}
		logger.info("resetPassword end");
		return new ResultPojo(user, msg);
	}
}
