package com.gp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.Teacher;

public interface TeacherMapper {
	public int add(Teacher teacher);
	
	public int getCountById(@Param("id") long id);
	
	public List<Teacher> getAll();
}
