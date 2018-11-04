package com.ibm.picasso.dao;

import com.ibm.picasso.domain.Album;

public interface AlbumDao {

    int deleteByPrimaryKey(Long id);

    int insert(Album record);

    int insertSelective(Album record);

    Album selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKey(Album record);
}