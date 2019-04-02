package com.ibm.picasso.service;

import java.util.List;

import com.ibm.picasso.domain.Point;

public interface PointService {
	
	int point(Point point);
	
	int unPoint(Point point);
	
	List<Point> getPointNum(Point point);
	
	Point findByMidAndUid(Point point);
	
	List<Point> getHotPoint();
}
