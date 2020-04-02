package com.gp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.Department;

public interface DepartmentMapper {
	int add(Department department);
	
	int addMany(List<Department> listDepartments);
	
	Integer getIdByName(@Param("name") String name);
	
	int getCountByIdAName(@Param("id") int id, @Param("name") String name);
}
