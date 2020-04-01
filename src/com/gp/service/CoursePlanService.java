package com.gp.service;

import java.util.List;

import com.gp.model.pojo.CoursePlan;

public interface CoursePlanService {
	int add(CoursePlan coursePlan);
	
	int addMany(List<CoursePlan> coursePlans);
	
	int getByAll(CoursePlan coursePlan);
}
