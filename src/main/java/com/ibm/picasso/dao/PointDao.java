package com.ibm.picasso.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.ibm.picasso.domain.Point;

public interface PointDao {
	@Delete("DELETE FROM POINT WHERE MID=#{mid.id} and UID=#{uid.id}")
	int deleteByPrimaryKey(Point point);

	@Insert("insert POINT(mid,uid,createtime) values(#{mid.id},#{uid.id},#{createtime})")
	int insert(Point point);

	@Select("select * from point where mid = #{mid.id}")
	List<Point> selectByMid(Point point);

	@Select("select * from point where mid = #{mid.id} and uid = #{uid.id}")
	Point selectByMidAndUid(Point point);
}