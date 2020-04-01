package com.gp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.UserMapper;
import com.gp.model.pojo.Admin;
import com.gp.model.pojo.StudentUser;
import com.gp.model.pojo.TeacherUser;
import com.gp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	
	public StudentUser slogin(long username, String password) {
		return userMapper.slogin(username, password);
	}
	public TeacherUser tlogin(long username, String password) {
		return userMapper.tlogin(username, password);
	}
	public Admin alogin(String username, String password) {
		return userMapper.alogin(username, password);
	}
	public int addStudent(long username) {
		return userMapper.addStudent(username);
	}
	public int addTeacher(long username) {
		return userMapper.addTeacher(username);
	}
	public int addAdmin(String username) {
		return userMapper.addAdmin(username);
	}
}
