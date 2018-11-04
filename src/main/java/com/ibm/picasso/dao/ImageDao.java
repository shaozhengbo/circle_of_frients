package com.ibm.picasso.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.ibm.picasso.domain.Image;

public interface ImageDao {
	@Insert("INSERT INTO image VALUES(#{id}, #{src}, #{createtime})")
    int insert(Image image);

	@Select("SELECT * FROM IMAGE WHERE id = #{id}")
    Image selectByPrimaryKey(Long id);
}