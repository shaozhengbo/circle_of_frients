package com.ibm.picasso.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ibm.picasso.domain.Point;

public interface PointMapper {
	@Delete("DELETE FROM POINT WHERE id = #{id}")
    int deleteByPrimaryKey(@Param(value="id")Long id);

	@Insert("insert into point(mid, uid, createtime) values(#{mid}, #{uid}, #{createtime})")
    int insert(Point point);

	@Select("SELECT * FROM POINT WHERE MID = #{mid} AND UID = #{uid}")
    Point selectByMidAndUid(Point point);
}