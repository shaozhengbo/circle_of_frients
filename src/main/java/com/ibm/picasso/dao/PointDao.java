package com.ibm.picasso.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import com.ibm.picasso.domain.Point;

public interface PointDao {
	@Delete("DELETE FROM POINT WHERE ID=#{id}")
	int deleteByPrimaryKey(Long id);

	@Insert("insert POINT(mid,uid,createtime) values(#{mid.id},#{uid.id},#{createtime})")
	int insert(Point point);
}