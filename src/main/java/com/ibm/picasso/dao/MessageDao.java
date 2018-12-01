package com.ibm.picasso.dao;

import org.apache.ibatis.annotations.Insert;

import com.ibm.picasso.domain.Message;

public interface MessageDao {
    int deleteByPrimaryKey(Long id);

    @Insert("insert into message(uid, message, pids, `from`, statue, createtime) values(#{uid.id}, #{message}, #{pids}, #{from}, #{statue}, #{createtime})")
    int insert(Message message);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}