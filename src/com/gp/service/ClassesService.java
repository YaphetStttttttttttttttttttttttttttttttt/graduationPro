package com.gp.service;

import java.util.List;

import com.gp.model.pojo.Classes;
import com.gp.model.pojo.Teacher;

public interface ClassesService {
	int add(Classes c);
	
	int addMany(List<Classes> listClasses);
	
	int getCountByIdAName(int id, String name);
	
	Integer getIdByName(String name);
	
	int getCountByTid(long tid);
}
