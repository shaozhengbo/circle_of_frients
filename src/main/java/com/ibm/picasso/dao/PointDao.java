package com.ibm.picasso.dao;

import com.ibm.picasso.domain.Point;

public interface PointDao {
    int deleteByPrimaryKey(Long id);

    int insert(Point record);

    int insertSelective(Point record);

    Point selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Point record);

    int updateByPrimaryKey(Point record);
}