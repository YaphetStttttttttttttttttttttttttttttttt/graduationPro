package com.gp.mapper;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.Classes;
import com.gp.model.pojo.Teacher;

public interface ClassesMapper {
	public int add(Classes c);
	
	public int getCountByIdAName(@Param("id") int id, @Param("name") String name);
	
	public Integer getIdByName(@Param("name") String name);
	
	public int getCountByTid(@Param("tid") long tid);
}
