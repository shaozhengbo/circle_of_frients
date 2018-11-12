package com.ibm.picasso.mapper;

import java.util.List;

import com.ibm.picasso.domain.Friends;
import com.ibm.picasso.domain.User;

public interface FriendsMapper {

    int insert(Friends record);

    List<Friends> selectAllFriendsByUid1(User user);

	Friends selectFriendsByUid1AndUid2(Friends friends);
}