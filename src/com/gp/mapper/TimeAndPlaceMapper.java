package com.gp.mapper;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.TimeAndPlace;

public interface TimeAndPlaceMapper {
	public int getFlag(TimeAndPlace time);
	
	public Integer getId(TimeAndPlace time);
	
	public int add(TimeAndPlace time);
	
	public int getStuSCFlag(@Param("tap") TimeAndPlace tap, @Param("sid") long sid);
	
	public TimeAndPlace getById(@Param("id") int id);
}
