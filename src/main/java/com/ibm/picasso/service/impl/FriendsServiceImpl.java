package com.ibm.picasso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.picasso.dao.FriendsDao;
import com.ibm.picasso.domain.Friends;
import com.ibm.picasso.service.FriendsService;

@Service
public class FriendsServiceImpl implements FriendsService {

	@Autowired
	private FriendsDao friendsDao;

	@Override
	public int addFriends(Friends friends) {
		return friendsDao.insert(friends);
	}

	@Override
	public int removeFriends(Friends friends) {
		return friendsDao.deleteByPrimaryKey(friends.getId());
	}

	@Override
	public Friends isFriend(Long uid1, Long uid2) {
		return friendsDao.selectByUid1AndUid2(uid1, uid2);
	}

	@Override
	public List<Friends> getFriendList(Long uid1) {
		return friendsDao.selectByUid1(uid1);
	}


}
