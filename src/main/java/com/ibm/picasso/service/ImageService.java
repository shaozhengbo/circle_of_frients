package com.ibm.picasso.service;

import com.ibm.picasso.domain.Image;

public interface ImageService {
	
	int uploadImage(Image image);
	
	Image selectImage(String src);
}
