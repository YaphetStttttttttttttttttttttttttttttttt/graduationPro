package com.gp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.SelectCourse;

public interface SelectCourseMapper {
	public List<SelectCourse> getByStudent(@Param("sid") long sid);
	
	public int add(@Param("sid") long sid, @Param("cpid") long cpid);
	
	public int delete(@Param("sid") long sid, @Param("cpid") long cpid);
	
	public List<SelectCourse> getStuScore(@Param("sid") long sid);
	
	public int getStuScoreCount(@Param("sid") long sid);
}
