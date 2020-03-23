package com.gp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gp.pojo.Course;

public interface CourseMapper {
	public List<Course> listCourse(@Param("sid") long sid);
	
	public List<Course> listSelectCourse(@Param("sid") long sid);
}
