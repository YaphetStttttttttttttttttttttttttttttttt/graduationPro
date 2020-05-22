package com.gp.service;

import com.gp.model.pojo.Admin;
import com.gp.model.pojo.StudentUser;
import com.gp.model.pojo.TeacherUser;

public interface UserService {
	StudentUser slogin(long username, String password);
	
	TeacherUser tlogin(long username, String password);
	
	Admin alogin(String username, String password);
	
	int addStudent(long username);
	
	int addTeacher(long username);
	
	int addAdmin(String username);
	
	int deleteTeacher(long username);
	
	int deleteStudent(long username);
}
