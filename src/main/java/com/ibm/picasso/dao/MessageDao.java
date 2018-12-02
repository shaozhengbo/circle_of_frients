package com.ibm.picasso.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ibm.picasso.domain.Message;

public interface MessageDao {
	int deleteByPrimaryKey(Long id);

	@Insert("insert into message(uid, message, pids, `from`, statue, createtime) values(#{uid.id}, #{message}, #{pids}, #{from}, #{statue}, #{createtime})")
	int insert(Message message);

	int insertSelective(Message record);

	@Select("select * from message where id = #{id} order by createtime desc")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "uid", column = "uid", one = @One(select = "com.ibm.picasso.dao.UserDao.selectUserById")),
			@Result(property = "message", column = "message"), @Result(property = "pids", column = "pids"),
			@Result(property = "from", column = "from", one = @One(select = "com.ibm.picasso.dao.MessageDao.selectByPrimaryKey")),
			@Result(property = "statue", column = "statue"), @Result(property = "createtime", column = "createtime"),
			@Result(property = "deletetime", column = "deletetime") })
	Message selectByPrimaryKey(@Param("id") Long id);

	@Select("select * from message where uid = #{uid} order by createtime desc")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "uid", column = "uid", one = @One(select = "com.ibm.picasso.dao.UserDao.selectUserById")),
			@Result(property = "message", column = "message"), @Result(property = "pids", column = "pids"),
			@Result(property = "from", column = "from", one = @One(select = "com.ibm.picasso.dao.MessageDao.selectByPrimaryKey")),
			@Result(property = "statue", column = "statue"), @Result(property = "createtime", column = "createtime"),
			@Result(property = "deletetime", column = "deletetime") })
	List<Message> selectByUid(@Param("uid") Long uid);

	int updateByPrimaryKeySelective(Message record);

	int updateByPrimaryKey(Message record);
}