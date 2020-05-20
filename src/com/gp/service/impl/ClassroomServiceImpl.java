package com.gp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.ClassroomMapper;
import com.gp.service.ClassroomService;

@Service
public class ClassroomServiceImpl implements ClassroomService {

	@Autowired
	ClassroomMapper classroomMapper;
	
	public Integer getIdByName(String name) {
		return classroomMapper.getIdByName(name);
	}
}
