package com.gp.service;

import java.util.List;

import com.gp.model.pojo.Classes;

public interface ClassesService {
	int add(Classes c);
	
	int addMany(List<Classes> listClasses);
	
	int getByIdAName(int id, String name);
	
	Integer getIdByName(String name);
}
