package com.ibm.picasso.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibm.picasso.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping("/index")
	public String toIndex() {
		logger.info("index start");
		logger.info("index end");
		return "index.html";
	}

}
