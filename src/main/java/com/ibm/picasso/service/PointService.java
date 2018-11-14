package com.ibm.picasso.service;

import com.ibm.picasso.domain.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface PointService {

    /**
     * 点赞
     *
     * @param point
     * @return
     */
    int pointMessage(Point point);

    /**
     * 获取点赞数
     *
     * @param point
     * @return
     */
    int getPointNumber(Point point);
	
}
