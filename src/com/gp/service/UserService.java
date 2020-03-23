package com.gp.service;

public interface UserService {
	int slogin(long username, String password);
	
	int tlogin(long username, String password);
	
	int alogin(String username, String password);
}
