package com.ibm.picasso.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.picasso.domain.Friends;
import com.ibm.picasso.domain.Image;
import com.ibm.picasso.domain.Message;
import com.ibm.picasso.domain.User;
import com.ibm.picasso.pojo.MessagePojo;
import com.ibm.picasso.service.impl.FriendsServiceImpl;
import com.ibm.picasso.service.impl.ImageServiceImpl;
import com.ibm.picasso.service.impl.MessageServiceImpl;
import com.ibm.picasso.util.Util;

@Controller
@RequestMapping("/Message/*")
public class MessageController {
	@Value("${fileUploadPath}")
	private String fileUploadPath;

	@Autowired
	MessageServiceImpl messageService;
	@Autowired
	ImageServiceImpl imageService;
	@Autowired
	FriendsServiceImpl friendsService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static String msg = "";

	@RequestMapping(value = "getAllMessage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo getAllMessage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Friends> friendList = friendsService.getFriendList(user.getId());
		String uid = user.getId() + "";
		if(friendList.size() == 0 || friendList == null) {
		} else {
			for (Friends f : friendList) {
				uid += "," + f.getUid2().getId();
			}
		}
		List<Message> result = messageService.getAllMessageInUid(uid);
		
		if (result.size() == 0) {
			msg = "获取到条" + result.size() + "消息";
		} else {
			msg = "完成";
		}
		return new MessagePojo(result, msg);
	}

	@RequestMapping(value = "sendMessage", method = RequestMethod.POST, produces = "application/json; chatset=utf-8")
	@ResponseBody
	public MessagePojo sendMessage(@RequestParam("file") MultipartFile file, String message, HttpSession session,
			HttpServletRequest req) throws IOException {
		logger.info("sendMessage start");
		Message messageObj = new Message();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return new MessagePojo(null, "请先登陆");
		}
		messageObj.setMessage(message);
		messageObj.setCreatetime(new Date());
		messageObj.setUid(user);
		messageObj.setFrom((long) 0);
		messageObj.setStatue(0);
		if (file != null) {
			try {
				// 2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
				String fileName = System.currentTimeMillis() + "_" + UUID.randomUUID() + "."
						+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
				// 3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
				String destFileName = fileUploadPath + fileName;

				File destFile = new File(destFileName);
				if (!destFile.getParentFile().exists()) {
					destFile.getParentFile().mkdirs();
				}

				file.transferTo(destFile);
				Image image = new Image();
				image.setSrc("/image/" + fileName);
				image.setCreatetime(new Date());
				imageService.uploadImage(image);
				messageObj.setPid(imageService.selectImage(image.getSrc()));
				//加水印
				Util.c(messageObj.getUid().getUsername(), destFileName, destFileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (messageObj.getMessage() == null) {
			messageObj.setMessage("");
		}
		int result = messageService.sendMessage(messageObj);

		if (result == 1) {
			msg = "发布成功";
		} else {
			msg = "发布失败";
		}
		logger.info("sendMessage end");
		return new MessagePojo(message, msg);
	}

	@RequestMapping(value = "deleteMessage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public MessagePojo deleteMessage(Long id) {
		logger.info("deleteMessage start");
		msg = "删除失败";
		int result = messageService.deleteMessage(id);
		if (result == 1) {
			msg = "删除成功";
		}
		logger.info("deleteMessage end");
		return new MessagePojo(null, msg);
	}

}
