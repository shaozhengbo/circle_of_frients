package com.ibm.picasso.mapper;

import com.ibm.picasso.domain.Forward;

public interface ForwardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Forward record);

    int insertSelective(Forward record);

    Forward selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Forward record);

    int updateByPrimaryKey(Forward record);
}