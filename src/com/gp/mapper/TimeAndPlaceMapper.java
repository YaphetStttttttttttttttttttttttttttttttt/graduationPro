package com.gp.mapper;

import com.gp.model.pojo.TimeAndPlace;

public interface TimeAndPlaceMapper {
	public int getFlag(TimeAndPlace time);
	
	public Integer getId(TimeAndPlace time);
	
	public int add(TimeAndPlace time);
}
