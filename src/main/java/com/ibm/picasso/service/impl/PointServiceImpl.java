package com.ibm.picasso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.picasso.domain.Point;
import com.ibm.picasso.mapper.PointMapper;
import com.ibm.picasso.service.PointService;

public class PointServiceImpl implements PointService{
	
	@Autowired
	private PointMapper pointMapper;

	@Override
	public int pointMessage(Point point) {
		if(pointMapper.selectByMidAndUid(point) == null) {
			return pointMapper.insert(point);
		} else {
			pointMapper.deleteByPrimaryKey(point.getId());
			return -1;
		}
	}

}
