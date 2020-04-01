package com.gp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.TeacherMapper;
import com.gp.model.pojo.Teacher;
import com.gp.service.TeacherService;
import com.gp.service.UserService;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	TeacherMapper teacherMapper;
	@Autowired
	UserService userService;
	
	public int getCountById(long id) {
		return teacherMapper.getCountById(id);
	}
	public int add(Teacher teacher) {
		int flag = 0;
		flag = teacherMapper.add(teacher);
		if(flag == 0) {
			return flag;
		}else {
			flag = userService.addTeacher(teacher.getId());
			if(flag == 0) return flag;
		}
		return flag;
	}
	public int addMany(List<Teacher> listTeachers) {
		int flag = 0;
		for(Teacher teacher : listTeachers) {
			flag = teacherMapper.add(teacher);
			if(flag == 0) {
				return flag;
			}else {
				flag = userService.addTeacher(teacher.getId());
				if(flag == 0) return flag;
			}
		}
		return flag;
	}
}
