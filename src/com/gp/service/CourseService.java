package com.gp.service;

import java.util.List;

import com.gp.pojo.Course;

public interface CourseService {
	List<Course> listCourse(long sid);
	
	List<Course> listSelectCourse(long sid);
}
