package com.ibm.picasso.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class Util {
	public static String MD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(str.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有这个md5算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {

		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
	}

	
	public static void sendMail(String toMail, JavaMailSenderImpl senderImpl) {
		// 设定mail server
	    senderImpl.setHost("smtp.qq.com");
	    senderImpl.setProtocol("smtp");
	    senderImpl.setUsername("690143820@qq.com");
	    senderImpl.setPassword("zjzstpgydptebbhj");
	    senderImpl.setPort(587);
	    senderImpl.setDefaultEncoding("UTF-8");
		
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("690143820@qq.com");
        message.setTo("690143820@qq.com");
        message.setSubject("【密码重置通知】");
        message.setText("由于系统采用MD5加密了您的密码，现在已将您的登陆密码重置为123456，请尽快登陆后修改您的登陆密码。");
        senderImpl.send(message);
	}
}
