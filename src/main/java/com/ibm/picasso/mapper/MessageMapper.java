package com.ibm.picasso.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.ibm.picasso.domain.Message;

public interface MessageMapper {
	@Delete("UPDATE MESSAGE SET STATUE = 2, DELETETIME = #{DELETETIME} WHERE ID = #{id}")
    int deleteByPrimaryKey(Long id);

	@Insert("INSERT INTO MESSAGE(UID, MESSAGE, PIDS, FROM, STATUE, CREATETIME) VALUES(#{uid}, #{message}, #{pids}, #{from}, #{statue}, #{createtime})")
    int insert(Message record);

	@Select("SELECT * FROM MESSAGE WHERE ID = #{id}")
    Message selectByPrimaryKey(Long id);

}