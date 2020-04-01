package com.gp.service;

import java.util.List;

import com.gp.model.pojo.Teacher;

public interface TeacherService {
	int getCountById(long id);
	
	int add(Teacher teacher);
	
	int addMany(List<Teacher> listTeachers);
}
