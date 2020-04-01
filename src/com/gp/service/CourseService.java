package com.gp.service;

import java.util.List;

import com.gp.model.pojo.Course;

public interface CourseService {
	//List<Course> listCourse(long sid);
	
	//List<Course> listSelectCourse(long sid);
	
	int add(Course course);
	
	int addMany(List<Course> listCourses);
	
	int getById(long id);
	
	int getByName(String name);
	
	Long getIdByName(String name);
}
