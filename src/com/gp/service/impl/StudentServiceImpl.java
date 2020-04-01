package com.gp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.StudentMapper;
import com.gp.model.pojo.Student;
import com.gp.service.StudentService;
import com.gp.service.UserService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
    StudentMapper studentMapper;
	@Autowired
	UserService userService;
	
    public List<Student> list(){
        return studentMapper.list();
    }
    public int add(Student student) {
    	return studentMapper.add(student);
    }
    public int addMany(List<Student>listStudents) {
    	int flag = 0;
    	for(Student student : listStudents) {
    		flag = studentMapper.add(student);
    		if(flag ==0) {
    			return flag;
    		}else {
    			flag = userService.addStudent(student.getId());
    			if(flag == 0) return flag;
    		}
    	}
    	return flag;
    }
    public int getById(long id) {
    	return studentMapper.getById(id);
    }
}
