package com.gp.mapper;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.Admin;
import com.gp.model.pojo.StudentUser;
import com.gp.model.pojo.TeacherUser;

public interface UserMapper {
	public StudentUser slogin(@Param("username") long username, @Param("password") String password);
	
	public TeacherUser tlogin(@Param("username") long username, @Param("password") String password);
	
	public Admin alogin(@Param("username") String username, @Param("password") String password);
	
	public int addStudent(@Param("username") long username);
	
	public int addTeacher(@Param("username") long username);
	
	public int addAdmin(@Param("username") String username);
	
	public int deleteTeacher(@Param("username") long username);
}
