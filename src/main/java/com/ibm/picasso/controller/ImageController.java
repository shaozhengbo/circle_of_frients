package com.ibm.picasso.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.picasso.pojo.MessagePojo;
import com.ibm.picasso.service.impl.ImageServiceImpl;

@Controller
@RequestMapping("/Image/*")
public class ImageController {

	@Autowired
	ImageServiceImpl imageService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static String msg = "";

	@RequestMapping(value = "uploadImage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo uploadImage(@RequestParam(value = "file") MultipartFile file, Model model,
			HttpServletRequest request) {
		logger.info("uploadImage start");
		return new MessagePojo(file, msg);
	}
}
