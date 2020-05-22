package com.gp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.CoursePlan;

public interface CoursePlanMapper {
	public int add(CoursePlan coursePlan);
	
	public int getCountByAll(CoursePlan coursePlan);
	
	public int getCountById(@Param("id") long id);
	
	public int getCountByTid(@Param("tid") long tid);
	
	public List<CoursePlan> stuSelect(@Param("id") long id, @Param("start") int start);
	
	public int stuSelectCount(@Param("id") long id);
	
	public List<CoursePlan> teaSelect(@Param("id") long id, @Param("start") int start);
	
	public int teaSelectCount(@Param("id") long id);
}
