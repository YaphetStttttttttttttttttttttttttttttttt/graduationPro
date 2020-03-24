package com.gp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.StudentMapper;
import com.gp.pojo.Student;
import com.gp.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
    StudentMapper studentMapper;
     
    public List<Student> list(){
        return studentMapper.list();
    }
}
