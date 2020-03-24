package com.gp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.Select_courseMapper;
import com.gp.service.Select_courseService;

@Service
public class Select_courseServiceImpl implements Select_courseService {
	
	@Autowired
	Select_courseMapper scM;
	
	public int add(long sid, long cid) {
		return scM.add(sid, cid);
	}
}
