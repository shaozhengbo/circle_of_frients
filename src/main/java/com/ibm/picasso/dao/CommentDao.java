package com.ibm.picasso.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ibm.picasso.domain.Comment;

public interface CommentDao {
	@Delete("DELETE FROM Comment WHERE id=#{id}")
	int deleteByPrimaryKey(Comment comment);

	@Insert("insert Comment(mid,uid,comment,createtime) values(#{mid.id},#{uid.id},#{comment},#{createtime})")
	int insert(Comment comment);

	@Select("select * from Comment where mid = #{mid.id} order by createtime desc")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "uid", column = "uid", one = @One(select = "com.ibm.picasso.dao.UserDao.selectUserById")),
			@Result(property = "mid", column = "mid", one = @One(select = "com.ibm.picasso.dao.MessageDao.selectByPrimaryKey")),
			@Result(property = "comment", column = "comment"),
			@Result(property = "createtime", column = "createtime") })
	List<Comment> selectByMid(Long mid);

}