package com.gp.mapper;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.Classes;

public interface ClassesMapper {
	public int add(Classes c);
	
	public int getByIdAName(@Param("id") int id, @Param("name") String name);
	
	public Integer getIdByName(@Param("name") String name);
}
