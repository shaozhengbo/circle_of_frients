package com.ibm.picasso.dao;

import com.ibm.picasso.domain.Friends;

public interface FriendsDao {
    int deleteByPrimaryKey(Long id);

    int insert(Friends record);

    int insertSelective(Friends record);

    Friends selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);
}