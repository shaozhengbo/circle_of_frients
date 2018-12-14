package com.ibm.picasso.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ibm.picasso.domain.Point;

public interface PointDao {
	@Delete("DELETE FROM POINT WHERE MID=#{mid.id} and UID=#{uid.id}")
	int deleteByPrimaryKey(Point point);

	@Insert("insert POINT(mid,uid,createtime) values(#{mid.id},#{uid.id},#{createtime})")
	int insert(Point point);

	@Select("select * from point where mid = #{mid.id}")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "uid", column = "uid", one = @One(select = "com.ibm.picasso.dao.UserDao.selectUserById")),
			@Result(property = "message", column = "message"),
			@Result(property = "pid", column = "pid", one = @One(select = "com.ibm.picasso.dao.ImageDao.selectByPrimaryKey")),
			@Result(property = "from", column = "from", one = @One(select = "com.ibm.picasso.dao.MessageDao.selectByPrimaryKey")),
			@Result(property = "statue", column = "statue"), @Result(property = "createtime", column = "createtime"),
			@Result(property = "deletetime", column = "deletetime") })
	List<Point> selectByMid(Point point);

	@Select("select * from point where mid = #{mid.id} and uid = #{uid.id}")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "mid", column = "mid", one = @One(select = "com.ibm.picasso.dao.MessageDao.selectByPrimaryKey")),
			@Result(property = "uid", column = "uid", one = @One(select = "com.ibm.picasso.dao.UserDao.selectUserById")),
			@Result(property = "createtime", column = "createtime"), })
	Point selectByMidAndUid(Point point);
}