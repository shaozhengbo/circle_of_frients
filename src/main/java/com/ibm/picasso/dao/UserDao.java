package com.ibm.picasso.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ibm.picasso.domain.User;

@Mapper
@Repository
public interface UserDao {
	@Select("SELECT * FROM user WHERE username = #{username}")
	User selectUserByUsername(@Param("username")String username);
	
	@Select("SELECT * FROM user WHERE username = #{username} And password = #{password}")
	User selectUserByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
	
	@Select("SELECT * FROM user WHERE phonenumber = #{phonenumber}")
	User selectUserByPhonenumber(@Param("phonenumber")String phonenumber);
	
	@Update("UPDATE user SET username = #{username}, birth = #{birth}, sex = #{sex}, mail = #{mail}, phonenumber = #{phonenumber} WHERE id = #{id}")
	int updateUser(User user);
	
	@Update("UPDATE user SET password = #{password} where id = #{id}")
	int updatePassword(User user);

	@Insert("INSERT INTO user(username, password) VALUES(#{username}, #{password})")
	int insertUser(User user);
}
