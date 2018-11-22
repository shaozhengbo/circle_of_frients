package com.ibm.picasso.dao;

import com.ibm.picasso.domain.Forward;

public interface ForwardDao {
    int deleteByPrimaryKey(Long id);

    int insert(Forward record);

    int insertSelective(Forward record);

    Forward selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Forward record);

    int updateByPrimaryKey(Forward record);
}