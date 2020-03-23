package com.gp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.CourseMapper;
import com.gp.pojo.Course;
import com.gp.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseMapper courseMapper;
	
	public List<Course> listCourse(long sid){
		return courseMapper.listCourse(sid);
	}
	
	public List<Course> listSelectCourse(long sid){
		return courseMapper.listSelectCourse(sid);
	}
}
