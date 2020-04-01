package com.gp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.CourseMapper;
import com.gp.model.pojo.Course;
import com.gp.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseMapper courseMapper;
	/*
	public List<Course> listCourse(long sid){
		return courseMapper.listCourse(sid);
	}
	
	public List<Course> listSelectCourse(long sid){
		return courseMapper.listSelectCourse(sid);
	}
	*/
	public int add(Course course) {
		return courseMapper.add(course);
	}
	public int addMany(List<Course> listCourses) {
		int flag = 0;
		for(Course course : listCourses) {
			flag = courseMapper.add(course);
			if(flag == 0) return flag;
		}
		return flag;
	}
	public int getById(long id) {
		return courseMapper.getById(id);
	}
	public int getByName(String name) {
		return courseMapper.getByName(name);
	}
	public Long getIdByName(String name) {
		return courseMapper.getIdByName(name);
	}
}
