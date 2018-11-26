package com.ibm.picasso.service;

import java.util.List;

import com.ibm.picasso.domain.User;

public interface UserService {
	
	User findUserById(Long id);
	
	User findUserByUsername(String username);
	
	User findUserByUsernameAndPassword(String username, String password);
	
	User findUserByPhonenumber(String phonenumber);
	
	boolean updateUser(User user);
	
	boolean updatePassword(User user);
	
	boolean registerUser(User user);
	
	List<User> findUserBySearchStr(String searchStr);
}
