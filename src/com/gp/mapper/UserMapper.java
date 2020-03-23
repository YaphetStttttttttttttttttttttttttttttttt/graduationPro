package com.gp.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	public int slogin(@Param("username") long username, @Param("password") String password);
	
	public int tlogin(@Param("username") long username, @Param("password") String password);
	
	public int alogin(@Param("username") String username, @Param("password") String password);
}
