package com.ibm.picasso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.picasso.dao.PointDao;
import com.ibm.picasso.domain.Point;
import com.ibm.picasso.service.PointService;

@Service
public class PointServiceImpl implements PointService {
	
	@Autowired
	private PointDao pointDao;

	@Override
	public int point(Point point) {
		return pointDao.insert(point);
	}

	@Override
	public int unPoint(Point point) {
		return pointDao.deleteByPrimaryKey(point.getId());
	}

}
