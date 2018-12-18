package com.ibm.picasso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.ibm.picasso")
public class CircleOfFriendsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircleOfFriendsApplication.class, args);
	}
}
