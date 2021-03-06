package com.ibm.picasso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.picasso.dao.UserDao;
import com.ibm.picasso.domain.User;
import com.ibm.picasso.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

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
	public List<User> findUserBySearchStr(String searchStr) {
		List<User> userList = userDao.fuzzyQuery(searchStr);
		for(User user : userList) {
			user.setPassword("");
		}
		return userList;
	}
}
