package com.gp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.SelectCourseMapper;
import com.gp.model.pojo.SelectCourse;
import com.gp.service.SelectCourseService;

@Service
public class SelectCourseServiceImpl implements SelectCourseService {
	@Autowired
	SelectCourseMapper scMapper;
	
	public List<SelectCourse> getByStudent(long sid) {
		return scMapper.getByStudent(sid);
	}

	public int add(long sid, long cpid) {
		return scMapper.add(sid, cpid);
	}

	public int delete(long sid, long cpid) {
		return scMapper.delete(sid, cpid);
	}
	
	public List<SelectCourse> getStuScore(long sid){
		return scMapper.getStuScore(sid);
	}
	
	public int getStuScoreCount(long sid) {
		return scMapper.getStuScoreCount(sid);
	}
}
