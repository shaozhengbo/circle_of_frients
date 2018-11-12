package com.ibm.picasso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.picasso.domain.Forward;
import com.ibm.picasso.mapper.ForwardMapper;
import com.ibm.picasso.service.ForwardService;

public class ForwardServiceImpl implements ForwardService{
	
	@Autowired
	private ForwardMapper forwardMapper;

	@Override
	public int addForwardRecord(Forward forward) {
		return forwardMapper.insert(forward);
	}

}
