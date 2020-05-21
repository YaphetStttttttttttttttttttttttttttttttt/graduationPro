package com.gp.service;

import java.util.List;

import com.gp.model.pojo.TimeAndPlace;

public interface TimeAndPlaceService {
	int getFlag(TimeAndPlace time);
	
	Integer getId(TimeAndPlace time);
	
	int add(TimeAndPlace time);
	
	int addMany(List<TimeAndPlace> listTimes);
	
	int getStuSCFlag(TimeAndPlace time, long sid);
	
	TimeAndPlace getById(int id);
}
