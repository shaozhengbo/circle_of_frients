package com.ibm.picasso.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ibm.picasso.domain.Image;

public interface ImageDao {
	@Insert("INSERT INTO image(src,createtime) VALUES(#{src}, #{createtime})")
    int insert(Image image);

	@Select("SELECT * FROM IMAGE WHERE id = #{id}")
    Image selectByPrimaryKey(Long id);
	
	@Select("SELECT * FROM IMAGE WHERE src = #{src}")
	Image selectBySrc(@Param("src")String src);
}