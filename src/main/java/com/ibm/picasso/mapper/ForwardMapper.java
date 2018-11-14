package com.ibm.picasso.mapper;

import com.ibm.picasso.domain.Forward;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ForwardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Forward record);

    int insertSelective(Forward record);

    Forward selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Forward record);

    int updateByPrimaryKey(Forward record);

    @Select("SELECT * FROM FORWARD WHERE mid = #{mid}")
    List<Forward> selectByMid(@Param("mid") Long mid);
}