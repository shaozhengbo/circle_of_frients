package com.ibm.picasso.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.picasso.domain.Point;

@Service
@Transactional
public interface PointService {

	int pointMessage(Point point);
	
}
