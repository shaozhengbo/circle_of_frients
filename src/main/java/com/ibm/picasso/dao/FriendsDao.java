package com.ibm.picasso.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.ibm.picasso.domain.Friends;

public interface FriendsDao {
    int deleteByPrimaryKey(Long id);

    @Insert("insert into friends(uid1, uid2, state, createtime, updatetime) values(#{uid1.id}, #{uid2.id}, #{state}, #{createtime}, #{updatetime})")
    int insert(Friends record);

    int insertSelective(Friends record);

    Friends selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);

    @Select("select * from friends where uid1 = #{uid1} and uid2 = #{uid2}")
    @Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "uid1", column = "uid1", one = @One(select = "com.ibm.picasso.dao.UserDao.selectUserById")), 
			@Result(property = "uid2", column = "uid2", one = @One(select = "com.ibm.picasso.dao.UserDao.selectUserById")),
			@Result(property = "statue", column = "statue"),
			@Result(property = "createtime", column = "createtime"),
			@Result(property = "updatetime", column = "updatetime")})
	Friends selectByUid1AndUid2(@Param("uid1")Long uid1, @Param("uid2")Long uid2);

    @Select("select * from friends where uid1 = #{uid1}")
    @Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "uid1", column = "uid1", one = @One(select = "com.ibm.picasso.dao.UserDao.selectUserById")), 
			@Result(property = "uid2", column = "uid2", one = @One(select = "com.ibm.picasso.dao.UserDao.selectUserById")),
			@Result(property = "statue", column = "statue"),
			@Result(property = "createtime", column = "createtime"),
			@Result(property = "updatetime", column = "updatetime")})
	List<Friends> selectByUid1(@Param("uid1")Long uid1);
}