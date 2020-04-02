package com.gp.service;

import java.util.List;

import com.gp.model.pojo.Student;

public interface StudentService {
	List<Student> list();
	
	int add(Student student);
	
	int addMany(List<Student> listStudents);
	
	int getById(long id);
}
