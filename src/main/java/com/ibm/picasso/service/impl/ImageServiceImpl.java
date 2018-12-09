package com.ibm.picasso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.picasso.dao.ImageDao;
import com.ibm.picasso.domain.Image;
import com.ibm.picasso.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDao imageDao;

	@Override
	public int uploadImage(Image image) {
		return imageDao.insert(image);
	}

	@Override
	public Image selectImage(String src) {
		return imageDao.selectBySrc(src);
	}
}
