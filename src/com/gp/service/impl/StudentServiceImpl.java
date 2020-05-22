package com.gp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.StudentMapper;
import com.gp.model.pojo.Student;
import com.gp.model.pojo.Teacher;
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
    	int flag = 0;
		flag = studentMapper.add(student);
		if(flag == 0) {
			return flag;
		}else {
			flag = userService.addStudent(student.getId());
		}
		return flag;
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
    public int update(Student student) {
    	return studentMapper.update(student);
    }
    public List<Student> getStudent(long cpid){
    	return studentMapper.getStudent(cpid);
    }
    public int delete(Student student) {
		int flag = userService.deleteStudent(student.getId());
		if(flag == 0) {
			return flag;
		}else {
			return studentMapper.delete(student);
		}
	}
	public int deleteMany(List<Student> listStudents) {
		int flag = 0;
		for(Student student : listStudents) {
			flag = userService.deleteStudent(student.getId());
			if(flag == 0) {
				return flag;
			}else {
				flag = studentMapper.delete(student);
				if(flag == 0) return flag;
			}
		}
		return flag;
	}
	public List<Student> getAll() {
		return studentMapper.getAll();
	}
	public List<Student> getBySelect(long id, String name, int cid, int start){
		return studentMapper.getBySelect(id, name, cid, start);
	}
	public int totleCount(long id, String name, int deid) {
		return studentMapper.totleCount(id, name, deid);
	}
}
