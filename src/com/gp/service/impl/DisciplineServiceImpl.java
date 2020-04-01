package com.gp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.DisciplineMapper;
import com.gp.service.DisciplineService;

@Service
public class DisciplineServiceImpl implements DisciplineService {
	@Autowired
	DisciplineMapper disciplineMapper;
	
	public Integer getIdByName(String name) {
		return disciplineMapper.getIdByName(name);
	}
}
