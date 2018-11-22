package com.ibm.picasso.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ibm.picasso.domain.Image;

public interface ImageMapper {
	@Insert("INSERT INTO image VALUES(#{id}, #{src}, #{createtime})")
    int insert(Image image);

	@Select("SELECT * FROM IMAGE WHERE id = #{id}")
    Image selectByPrimaryKey(@Param("id")Long id);
}