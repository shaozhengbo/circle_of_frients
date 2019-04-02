package com.ibm.picasso.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ibm.picasso.domain.Message;

public interface MessageDao {
	@Delete("Update MESSAGE set statue = 1 where id = #{id}")
	int deleteByPrimaryKey(@Param("id")Long id);

	@Insert("insert into message(uid, message, pid, `from`, statue, createtime) values(#{uid.id}, #{message}, #{pid.id}, #{from}, #{statue}, #{createtime})")
	int insert(Message message);

	int insertSelective(Message record);

	@Select("select * from message where id = #{id} order by createtime desc")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "uid", column = "uid", one = @One(select = "com.ibm.picasso.dao.UserDao.selectUserById")),
			@Result(property = "message", column = "message"),
			@Result(property = "pid", column = "pid", one = @One(select = "com.ibm.picasso.dao.ImageDao.selectByPrimaryKey")),
			@Result(property = "from", column = "from", one = @One(select = "com.ibm.picasso.dao.MessageDao.selectByPrimaryKey")),
			@Result(property = "statue", column = "statue"), @Result(property = "createtime", column = "createtime"),
			@Result(property = "deletetime", column = "deletetime") })
	Message selectByPrimaryKey(@Param("id") Long id);

	@Select("select * from message where uid = #{uid} and statue = 0 order by createtime desc")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "uid", column = "uid", one = @One(select = "com.ibm.picasso.dao.UserDao.selectUserById")),
			@Result(property = "message", column = "message"),
			@Result(property = "from", column = "from", one = @One(select = "com.ibm.picasso.dao.MessageDao.selectByPrimaryKey")),
			@Result(property = "pid", column = "pid", one = @One(select = "com.ibm.picasso.dao.ImageDao.selectByPrimaryKey")),
			@Result(property = "statue", column = "statue"), @Result(property = "createtime", column = "createtime"),
			@Result(property = "deletetime", column = "deletetime") })
	List<Message> selectByUid(@Param("uid") Long uid);

	int updateByPrimaryKeySelective(Message record);

	int updateByPrimaryKey(Message record);

	@Select("select * from message where uid in (${uid}) and statue = 0 order by createtime desc limit #{index}, #{count}")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "uid", column = "uid", one = @One(select = "com.ibm.picasso.dao.UserDao.selectUserById")),
			@Result(property = "message", column = "message"),
			@Result(property = "from", column = "from", one = @One(select = "com.ibm.picasso.dao.MessageDao.selectByPrimaryKey")),
			@Result(property = "pid", column = "pid", one = @One(select = "com.ibm.picasso.dao.ImageDao.selectByPrimaryKey")),
			@Result(property = "statue", column = "statue"), @Result(property = "createtime", column = "createtime"),
			@Result(property = "deletetime", column = "deletetime") })
	List<Message> selectInUid(@Param("uid")String uid, @Param("index")int index, @Param("count")int count);

	@Select("select * from message where id in (${id}) and statue = 0")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "uid", column = "uid", one = @One(select = "com.ibm.picasso.dao.UserDao.selectUserById")),
			@Result(property = "message", column = "message"),
			@Result(property = "from", column = "from", one = @One(select = "com.ibm.picasso.dao.MessageDao.selectByPrimaryKey")),
			@Result(property = "pid", column = "pid", one = @One(select = "com.ibm.picasso.dao.ImageDao.selectByPrimaryKey")),
			@Result(property = "statue", column = "statue"), @Result(property = "createtime", column = "createtime"),
			@Result(property = "deletetime", column = "deletetime") })
	List<Message> selectInId(@Param("id")String id);
}