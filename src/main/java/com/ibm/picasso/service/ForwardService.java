package com.ibm.picasso.service;

import com.ibm.picasso.domain.Forward;

public interface ForwardService {

    /**
     * 转发
     *
     * @param forward
     * @return
     */
    int addForwardRecord(Forward forward);

    /**
     * 获取转发数
     *
     * @param forward
     * @return
     */
    int getForwardNumber(Forward forward);
}
