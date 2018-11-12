package com.ibm.picasso.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.picasso.domain.Friends;
import com.ibm.picasso.domain.User;
import com.ibm.picasso.mapper.FriendsMapper;
import com.ibm.picasso.service.FriendsService;

public class FriendsServiceImpl implements FriendsService {
	
	@Autowired
	private FriendsMapper friendsMapper;

	@Override
	public List<Long> getAllFriendsIds(User user) {
		List<Friends> friendsLst = friendsMapper.selectAllFriendsByUid1(user);
		List<Long> friendsIdLst = new ArrayList<>();
		for(Friends f:friendsLst) {
			friendsIdLst.add(f.getUid2().getId());
		}
		return friendsIdLst;
	}

	@Override
	public int createFriendRelationship(Friends friends) {
		return 0;
	}

}
