package com.gp.mapper;

import org.apache.ibatis.annotations.Param;

public interface ClassroomMapper {
	public Integer getIdByName(@Param("name") String name);
}
