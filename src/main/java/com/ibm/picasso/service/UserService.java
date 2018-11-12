package com.ibm.picasso.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.picasso.domain.User;

@Service
@Transactional
public interface UserService {
	
	User findUserById(Long id);
	
	User findUserByUsername(String username);
	
	User findUserByUsernameAndPassword(String username, String password);
	
	User findUserByPhonenumber(String phonenumber);
	
	boolean updateUser(User user);
	
	boolean updatePassword(User user);
	
	boolean registerUser(User user);

	List<User> searchUser(String searchStr);
}
