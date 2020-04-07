package com.gp.mapper;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.CoursePlan;

public interface CoursePlanMapper {
	public int add(CoursePlan coursePlan);
	
	public int getCountByAll(CoursePlan coursePlan);
	
	public int getCountById(@Param("id") long id);
}
