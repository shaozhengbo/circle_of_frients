package com.ibm.picasso.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ibm.picasso.domain.Message;

public interface MessageMapper {
	@Delete("UPDATE MESSAGE SET STATUE = 2, DELETETIME = #{DELETETIME} WHERE ID = #{id}")
    int deleteByPrimaryKey(Long id);

	@Insert("INSERT INTO MESSAGE(UID, MESSAGE, PIDS, FROM, STATUE, CREATETIME) VALUES(#{uid.id}, #{message}, #{pids}, #{from}, #{statue}, #{createtime})")
    int insert(Message message);

	@Select("SELECT * FROM MESSAGE WHERE ID = #{id}")
    Message selectByPrimaryKey(Long id);

	@Select("SELECT * FROM MESSAGE WHERE UID = #{uid}")
	List<Message> selectMessagesByUid(@Param("uid")Long uid);

	@Update("UPDATE MESSAGE SET STATUE = #{statue} WHERE ID = #{id}")
	int updateMessage(Message message);
}