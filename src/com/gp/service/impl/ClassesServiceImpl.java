package com.gp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.ClassesMapper;
import com.gp.model.pojo.Classes;
import com.gp.service.ClassesService;

@Service
public class ClassesServiceImpl implements ClassesService {
	@Autowired
	ClassesMapper classesMapper;
	
	public int add(Classes c) {
		return classesMapper.add(c);
	}
	public int addMany(List<Classes> listClass) {
		int flag = 0;
		for(Classes c : listClass) {
			flag = classesMapper.add(c);
			if(flag == 0) return flag;
		}
		return flag;
	}
	public int getByIdAName(int id, String name) {
		return classesMapper.getByIdAName(id, name);
	}
	public Integer getIdByName(String name) {
		return classesMapper.getIdByName(name);
	}
}
