package com.gp.service;

import java.util.List;

import com.gp.model.pojo.CoursePlan;
import com.gp.model.pojo.Teacher;

public interface CoursePlanService {
	int add(CoursePlan coursePlan);
	
	int addMany(List<CoursePlan> coursePlans);
	
	int getCountByAll(CoursePlan coursePlan);
	
	int getCountById(long id);
	
	int getCountByTid(long tid);
}
