package com.ibm.picasso.service;

import java.util.List;

import com.ibm.picasso.domain.Friends;

public interface FriendsService {
	
	int addFriends(Friends friends);
	
	int removeFriends(Friends friends);
	
	Friends isFriend(Long uid1, Long uid2);
	
	List<Friends> getFriendList(Long uid1);
}
