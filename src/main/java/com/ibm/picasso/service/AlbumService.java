package com.ibm.picasso.service;

import com.ibm.picasso.domain.Album;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface AlbumService {

    int createAlbum(Album album);

    int updateAlbumName(Album album);
}
