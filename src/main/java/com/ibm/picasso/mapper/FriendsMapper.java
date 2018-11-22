package com.ibm.picasso.mapper;

import java.util.List;

import com.ibm.picasso.domain.Friends;
import com.ibm.picasso.domain.User;

public interface FriendsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Friends record);

    int insertSelective(Friends record);

    Friends selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);
    
    List<Friends> selectAllFriendsByUid1(User user);
}