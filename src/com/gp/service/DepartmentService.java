package com.gp.service;

import java.util.List;

import com.gp.model.pojo.Department;

public interface DepartmentService {
	int add(Department department);
	
	int addMany(List<Department> listDepartments);
	
	Integer getIdByName(String name);
	
	int getCountByIdAName(int id, String name);
}
