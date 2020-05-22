package com.gp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.Student;

public interface StudentService {
	List<Student> list();
	
	int add(Student student);
	
	int addMany(List<Student> listStudents);
	
	int getById(long id);
	
	int update(Student student);
	
	List<Student> getStudent(long id);
	
	int delete(Student student);
	
	int deleteMany(List<Student> listStudents);
	
	List<Student> getAll();
    
    List<Student> getBySelect(long id, String name, int cid, int start);

	int totleCount(long id, String name,int cid);
}
