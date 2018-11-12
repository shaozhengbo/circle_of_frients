package com.ibm.picasso.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.picasso.domain.Friends;
import com.ibm.picasso.domain.User;

@Service
@Transactional
public interface FriendsService {

	List<Long> getAllFriendsIds(User user);

	List<Friends> findAllFriendsByUid(User user);

	int addFriends(Friends friends);

	Friends isFriends(Friends friends);
}
