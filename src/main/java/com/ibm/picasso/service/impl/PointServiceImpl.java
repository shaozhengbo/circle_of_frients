package com.ibm.picasso.service.impl;

import com.ibm.picasso.domain.Point;
import com.ibm.picasso.mapper.PointMapper;
import com.ibm.picasso.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public int getPointNumber(Point point) {
        List<Point> pointList = pointMapper.selectByMid(point.getMid().getId());
        if (pointList == null) {
            pointList = new ArrayList<>();
        }
        return pointList.size();
    }

}
