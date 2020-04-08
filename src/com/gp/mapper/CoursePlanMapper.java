package com.gp.mapper;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.CoursePlan;
import com.gp.model.pojo.Teacher;

public interface CoursePlanMapper {
	public int add(CoursePlan coursePlan);
	
	public int getCountByAll(CoursePlan coursePlan);
	
	public int getCountById(@Param("id") long id);
	
	public int getCountByTid(@Param("tid") long tid);
}
