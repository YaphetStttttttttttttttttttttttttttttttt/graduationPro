package com.gp.mapper;

import org.apache.ibatis.annotations.Param;

public interface DisciplineMapper {
	Integer getIdByName(@Param("name") String name);
}
