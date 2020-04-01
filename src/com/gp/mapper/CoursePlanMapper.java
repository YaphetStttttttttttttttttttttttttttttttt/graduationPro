package com.gp.mapper;

import com.gp.model.pojo.CoursePlan;

public interface CoursePlanMapper {
	public int add(CoursePlan coursePlan);
	
	public int getByAll(CoursePlan coursePlan);
}
