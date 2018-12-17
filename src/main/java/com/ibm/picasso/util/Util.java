package com.ibm.picasso.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.imageio.ImageIO;

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

	public static void sendMail(String toMail, String title, String msg, JavaMailSenderImpl senderImpl) {
		// 设定mail server
		senderImpl.setHost("smtp.qq.com");
		senderImpl.setProtocol("smtp");
		senderImpl.setUsername("690143820@qq.com");
		senderImpl.setPassword("zjzstpgydptebbhj");
		senderImpl.setPort(587);
		senderImpl.setDefaultEncoding("UTF-8");

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("690143820@qq.com");
		message.setTo(toMail);
		message.setSubject(title);
		message.setText(msg);
		senderImpl.send(message);
	}

	// 水印参数
	// 水印透明度
	private static float alpha = 0.5f;
	// 水印横向位置
	private static int positionWidth = 100;
	// 水印纵向位置
	private static int positionHeight = 100;
	// 水印文字字体
	private static Font font = new Font("黑体", Font.BOLD, 72);
	// 水印文字颜色
	private static Color color = Color.RED;

	/**
	 * 给图片添加水印文字
	 *
	 * @param logoText   水印文字
	 * @param srcImgPath 源图片路径
	 * @param targerPath 目标图片路径
	 */
	public static void c(String logoText, String srcImgPath, String targerPath) {
		markImageByText(logoText, srcImgPath, targerPath, null);
	}

	/**
	 * 给图片添加水印文字、可设置水印文字的旋转角度
	 *
	 * @param logoText
	 * @param srcImgPath
	 * @param targerPath
	 * @param degree
	 */
	public static void markImageByText(String logoText, String srcImgPath, String targerPath, Integer degree) {

		InputStream is = null;
		OutputStream os = null;
		try {
			// 1、源图片
			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);

			// 2、得到画笔对象
			Graphics2D g = buffImg.createGraphics();
			// 3、设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
					0, null);
			// 4、设置水印旋转
			if (null != degree) {
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
			}
			// 5、设置水印文字颜色
			g.setColor(color);
			// 6、设置水印文字Font
			g.setFont(font);
			// 7、设置水印文字透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			// 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
			g.drawString(logoText, positionWidth, positionHeight);
			// 9、释放资源
			g.dispose();
			// 10、生成图片
			os = new FileOutputStream(targerPath);
			ImageIO.write(buffImg, "JPG", os);

			System.out.println("图片完成添加水印文字");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
