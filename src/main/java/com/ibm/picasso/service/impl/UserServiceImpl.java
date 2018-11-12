package com.ibm.picasso.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.picasso.domain.User;
import com.ibm.picasso.mapper.UserMapper;
import com.ibm.picasso.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userDao;

	@Override
	public User findUserByUsername(String username) {
		User user = userDao.selectUserByUsername(username);
		return user;
	}

	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		return userDao.selectUserByUsernameAndPassword(username, password);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.updateUser(user) == 1 ? true : false;
	}

	@Override
	public boolean updatePassword(User user) {
		return userDao.updatePassword(user) == 1 ? true : false;
	}

	@Override
	public boolean registerUser(User user) {
		return userDao.insertUser(user) == 1 ? true : false;
	}

	@Override
	public User findUserByPhonenumber(String phonenumber) {
		return userDao.selectUserByPhonenumber(phonenumber);
	}

	@Override
	public User findUserById(Long id) {
		return userDao.selectUserById(id);
	}

	@Override
	public List<User> searchUser(String searchStr) {
		List<User> result = userDao.selectUserBySearchStr(searchStr);
		return result == null ? new ArrayList<>() : result;
	}
}
