package com.gp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.UserMapper;
import com.gp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	
	public int slogin(long username, String password) {
		return userMapper.slogin(username, password);
	}
	public int tlogin(long username, String password) {
		return userMapper.tlogin(username, password);
	}
	public int alogin(String username, String password) {
		return userMapper.alogin(username, password);
	}
}
