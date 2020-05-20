package com.gp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.CoursePlanMapper;
import com.gp.model.pojo.CoursePlan;
import com.gp.model.pojo.Teacher;
import com.gp.model.pojo.TimeAndPlace;
import com.gp.service.CoursePlanService;
import com.gp.service.TimeAndPlaceService;

@Service
public class CoursePlanServiceImpl implements CoursePlanService {
	@Autowired
	CoursePlanMapper coursePlanMapper;
	@Autowired
	TimeAndPlaceService tapService;
	
	public int add(CoursePlan coursePlan) {
		coursePlan.setTime_place1(addTimes(coursePlan.getTime_place1()));
		coursePlan.setTime_place2(addTimes(coursePlan.getTime_place2()));
		coursePlan.setTime_place3(addTimes(coursePlan.getTime_place3()));
		return coursePlanMapper.add(coursePlan);
	}
	public int addMany(List<CoursePlan> coursePlans) {
		int flag = 0;
		for(CoursePlan coursePlan : coursePlans) {
			coursePlan.setTime_place1(addTimes(coursePlan.getTime_place1()));
			coursePlan.setTime_place2(addTimes(coursePlan.getTime_place2()));
			coursePlan.setTime_place3(addTimes(coursePlan.getTime_place3()));
			flag = coursePlanMapper.add(coursePlan);
			if(flag == 0) return flag;
		}
		return flag;
	}
	public int getCountByAll(CoursePlan coursePlan) {
		return coursePlanMapper.getCountByAll(coursePlan);
	}
	public int getCountById(long id) {
		return coursePlanMapper.getCountById(id);
	}
	public int getCountByTid(long tid) {
		return coursePlanMapper.getCountByTid(tid);
	}
	public List<CoursePlan> stuSelect(long id){
		return coursePlanMapper.stuSelect(id);
	}
	private TimeAndPlace addTimes(TimeAndPlace time) {
		if(time == null) {
			time = new TimeAndPlace();
			time.setId(1);
		}else {
			int add = tapService.add(time);
			time.setId(tapService.getId(time));
		}
		return time;
	}
	public int stuSelectCount(long id) {
		return coursePlanMapper.stuSelectCount(id);
	}
}
