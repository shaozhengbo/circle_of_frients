package com.ibm.picasso.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ibm.picasso.domain.User;

@Mapper
@Repository
public interface UserDao {
	@Select("SELECT * FROM user WHERE username = #{username}")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "username", column = "username"), 
			@Result(property = "birth", column = "birth"),
			@Result(property = "sex", column = "sex"),
			@Result(property = "mail", column = "mail"),
			@Result(property = "phonenumber", column = "phonenumber"),
			@Result(property = "major", column = "major"),
			@Result(property = "createtime", column = "createtime"),
			@Result(property = "img", column = "img")})
	User selectUserByUsername(@Param("username")String username);
	
	@Select("SELECT * FROM user WHERE username = #{username} And password = #{password}")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "username", column = "username"), 
			@Result(property = "birth", column = "birth"),
			@Result(property = "sex", column = "sex"),
			@Result(property = "mail", column = "mail"),
			@Result(property = "phonenumber", column = "phonenumber"),
			@Result(property = "major", column = "major"),
			@Result(property = "createtime", column = "createtime"),
			@Result(property = "img", column = "img")})
	User selectUserByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
	
	@Select("SELECT * FROM user WHERE phonenumber = #{phonenumber}")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "username", column = "username"), 
			@Result(property = "birth", column = "birth"),
			@Result(property = "sex", column = "sex"),
			@Result(property = "mail", column = "mail"),
			@Result(property = "phonenumber", column = "phonenumber"),
			@Result(property = "major", column = "major"),
			@Result(property = "createtime", column = "createtime"),
			@Result(property = "img", column = "img")})
	User selectUserByPhonenumber(@Param("phonenumber")String phonenumber);
	
	@Update("UPDATE user SET username = #{username}, birth = #{birth}, sex = #{sex}, mail = #{mail}, phonenumber = #{phonenumber}, major = #{major}, img = #{img} WHERE id = #{id}")
	int updateUser(User user);
	
	@Update("UPDATE user SET password = #{password} where id = #{id}")
	int updatePassword(User user);

	@Insert("INSERT INTO user(username, password, createtime, img) VALUES(#{username}, #{password}, #{createtime}, #{img})")
	int insertUser(User user);

	@Select("SELECT * FROM user WHERE id = #{id}")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "username", column = "username"), 
			@Result(property = "birth", column = "birth"),
			@Result(property = "sex", column = "sex"),
			@Result(property = "mail", column = "mail"),
			@Result(property = "phonenumber", column = "phonenumber"),
			@Result(property = "major", column = "major"),
			@Result(property = "createtime", column = "createtime"),
			@Result(property = "img", column = "img")})
	User selectUserById(Long id);

	@Select("SELECT * FROM user WHERE username like '${searchStr}%' or phonenumber like '{searchStr}%' or mail like '%{searchStr}%'")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "username", column = "username"), 
			@Result(property = "birth", column = "birth"),
			@Result(property = "sex", column = "sex"),
			@Result(property = "mail", column = "mail"),
			@Result(property = "phonenumber", column = "phonenumber"),
			@Result(property = "major", column = "major"),
			@Result(property = "createtime", column = "createtime"),
			@Result(property = "img", column = "img")})
	List<User> fuzzyQuery(@Param("searchStr")String searchStr);
}
