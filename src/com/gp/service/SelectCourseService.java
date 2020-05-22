package com.gp.service;

import java.util.List;

import com.gp.model.pojo.SelectCourse;

public interface SelectCourseService {
	List<SelectCourse> getByStudent(long sid);
	
	int add(long sid, long cpid);
	
	int delete(long sid, long cpid);
	
	List<SelectCourse> getStuScore(long sid, int start);
	
	int getStuScoreCount(long sid);
	
	List<SelectCourse> getStuList(long tid, int start);
	
	int getStuListCount(long tid);
	
	int updateTGrade(long sid, long cpid, int score);
}
