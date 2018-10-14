package com.ibm.picasso.service;

import com.ibm.picasso.domain.User;

public interface UserService {
	User findUserByUsername(String username);
	
	User findUserByUsernameAndPassword(String username, String password);
	
	User findUserByPhonenumber(String phonenumber);
	
	boolean updateUser(User user);
	
	boolean updatePassword(User user);
	
	boolean insertUser(User user);
}
