package com.ibm.picasso.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

@Controller
public class ChatroomController {
	
	@RequestMapping(value = "/toChatroom", method=RequestMethod.GET, produces="applition/json; charset=utf-8")
    public ModelAndView index(String username, String password, HttpServletRequest request) throws UnknownHostException {
        if (StringUtils.isEmpty(username)) {
            username = "匿名用户";
        }
        ModelAndView mav = new ModelAndView("/chat");
        mav.addObject("username", username);
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        System.err.println(hostAddress + serverPort + contextPath);
        mav.addObject("webSocketUrl", "ws://"+InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+"/chat");
        return mav;
    }

}
