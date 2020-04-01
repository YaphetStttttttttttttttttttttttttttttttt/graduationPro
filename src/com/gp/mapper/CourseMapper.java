package com.gp.mapper;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.Course;

public interface CourseMapper {
	/*
	public List<Course> listCourse(@Param("sid") long sid);
	
	public List<Course> listSelectCourse(@Param("sid") long sid);
	*/
	public int add(Course course);
	
	public int getById(@Param("id") long id);
	
	public int getByName(@Param("name") String name);
	
	public Long getIdByName(@Param("name") String name);
}
