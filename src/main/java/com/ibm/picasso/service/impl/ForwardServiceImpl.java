package com.ibm.picasso.service.impl;

import com.ibm.picasso.domain.Forward;
import com.ibm.picasso.mapper.ForwardMapper;
import com.ibm.picasso.service.ForwardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ForwardServiceImpl implements ForwardService{
	
	@Autowired
	private ForwardMapper forwardMapper;

	@Override
	public int addForwardRecord(Forward forward) {
		return forwardMapper.insert(forward);
	}

    @Override
    public int getForwardNumber(Forward forward) {
        List<Forward> forwardList = forwardMapper.selectByMid(forward.getMid().getId());
        if (forwardList == null) {
            forwardList = new ArrayList<>();
        }
        return forwardList.size();
    }

}
