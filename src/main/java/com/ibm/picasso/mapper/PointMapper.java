package com.ibm.picasso.mapper;

import com.ibm.picasso.domain.Point;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PointMapper {
	@Delete("DELETE FROM POINT WHERE id = #{id}")
    int deleteByPrimaryKey(@Param(value="id")Long id);

	@Insert("insert into point(mid, uid, createtime) values(#{mid.id}, #{uid.id}, #{createtime})")
    int insert(Point point);

	@Select("SELECT * FROM POINT WHERE MID = #{mid.id} AND UID = #{uid.id}")
    Point selectByMidAndUid(Point point);

    @Select("SELECT * FROM POINT WHERE MID = #{mid}")
    List<Point> selectByMid(@Param("mid") Long mid);
}