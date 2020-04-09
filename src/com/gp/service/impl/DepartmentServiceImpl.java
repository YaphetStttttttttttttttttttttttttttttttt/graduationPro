package com.gp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.DepartmentMapper;
import com.gp.model.pojo.Department;
import com.gp.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	DepartmentMapper departmentMapper;
	
	public int add(Department department) {
		return departmentMapper.add(department);
	}
	public int addMany(List<Department> listDepartments) {
		int flag = 0;
		for(Department department : listDepartments) {
			flag = departmentMapper.add(department);
			if(flag == 0) return flag;
		}
		return flag;
	}
	public Integer getIdByName(String name) {
		return departmentMapper.getIdByName(name);
	}
	public int getCountByIdAName(int id, String name) {
		return departmentMapper.getCountByIdAName(id, name);
	}
	public List<Department> get(){
		return departmentMapper.get();
	}
}
