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
import com.ibm.picasso.pojo.MessagePojo;
import com.ibm.picasso.service.impl.UserServiceImpl;
import com.ibm.picasso.util.Currency;
import com.ibm.picasso.util.Util;

@Controller
@RequestMapping("/User/*")
public class UserController {
	@Autowired
	UserServiceImpl userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static String msg = "";

	@RequestMapping(value = "getUserById")
	@ResponseBody
    public MessagePojo getUserById(String id) {
        logger.info("getUserById start");
		User user = userService.findUserById(Long.valueOf(id));
        logger.info("getUserById end");
		if (user != null) {
			msg = Currency.SEARCHHAVE;
		} else {
			user = new User();
			msg = Currency.SEARCHNULL;
		}
        return new MessagePojo(user, msg);
	}

	@RequestMapping(value = "getUserByUsername")
	@ResponseBody
	public MessagePojo getUserByUsername(String username) {
		logger.info("getUserByUsername start");
		User user = userService.findUserByUsername(username);
		logger.info("getUserByUsername end");
		if (user != null) {
			msg = Currency.SEARCHHAVE;
		} else {
			user = new User();
			msg = Currency.SEARCHNULL;
		}
		return new MessagePojo(user, msg);
	}

	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo login(String username, String password, HttpSession session)
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
		MessagePojo message = new MessagePojo(user, msg);
		return message;
	}

	@RequestMapping(value = "getUserByPhonenumber")
	@ResponseBody
	public MessagePojo getUserByPhonenumber(String phonenumber) throws Exception {
		logger.info("getUserByUsernameAndPassword start");
		User user = userService.findUserByPhonenumber(phonenumber);
		if (user != null) {
			msg = Currency.SEARCHHAVE;
		} else {
			user = new User();
			msg = Currency.SEARCHNULL;
		}
		logger.info("getUserByUsernameAndPassword end");
		return new MessagePojo(user, msg);
	}

	@RequestMapping(value = "registerUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo registerUser(User user) throws Exception {
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
		return new MessagePojo(user, msg);
	}

	@RequestMapping(value = "updateUser", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo updateUser(User user) throws Exception {
		logger.info("updateUser start");
		User userById = userService.findUserById(user.getId());
		userById.setUsername(user.getUsername());
		userById.setBirth(user.getBirth());
		userById.setImg(user.getImg());
		userById.setMail(user.getMail());
		userById.setMajor(user.getMajor());
		userById.setPhonenumber(user.getPhonenumber());
		userById.setSex(user.getSex());
		boolean result = userService.updateUser(userById);
		if (result) {
			msg = Currency.SUCCESS;
		} else {
			msg = Currency.ERROR;
		}
		logger.info("updateUser end");
		return new MessagePojo(user, msg);
	}

	@RequestMapping(value = "updatePassword", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo updatePassword(User user) throws Exception {
		logger.info("updatePassword start");
		user.setPassword(Util.MD5(user.getPassword()));
		boolean result = userService.updatePassword(user);
		if (result) {
			msg = Currency.SUCCESS;
		} else {
			msg = Currency.ERROR;
		}
		logger.info("updatePassword end");
		return new MessagePojo(user, msg);
	}

    @RequestMapping(value = "goForgetPassword")
    public ModelAndView goForgetPassword(String username) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forgetPassword.html");
        modelAndView.addObject("username", username);
        return modelAndView;
    }
    
    @RequestMapping("searchUser")
    @ResponseBody
    public MessagePojo searchUser(String searchStr) {
    	msg = "ok";
    	List<User> userList = userService.findUserBySearchStr(searchStr);
    	return new MessagePojo(userList,msg);
    }
}
