package com.gp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.TimeAndPlaceMapper;
import com.gp.model.pojo.TimeAndPlace;
import com.gp.service.TimeAndPlaceService;

@Service
public class TimeAndPlaceServiceImpl implements TimeAndPlaceService {
	@Autowired
	TimeAndPlaceMapper tapMapper;
	
	public int getFlag(TimeAndPlace time) {
		return tapMapper.getFlag(time);
	}
	
	public Integer getId(TimeAndPlace time) {
		return tapMapper.getId(time);
	}
	
	public int add(TimeAndPlace time) {
		return tapMapper.add(time);
	}
	public int addMany(List<TimeAndPlace> listTimes) {
		int flag = 0;
		for(TimeAndPlace tap : listTimes) {
			if(tap == null) {}
			else {
				System.out.println("week:" + tap.getWeek());
				flag = tapMapper.add(tap);
				if(flag == 0) return flag;
			}
		}
		return flag;
	}
	public int getStuSCFlag(TimeAndPlace time, long sid) {
		return tapMapper.getStuSCFlag(time, sid);
	}
	public TimeAndPlace getById(int id) {
		return tapMapper.getById(id);
	}
}
