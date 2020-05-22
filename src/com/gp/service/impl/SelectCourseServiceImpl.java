package com.gp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.mapper.SelectCourseMapper;
import com.gp.model.pojo.SelectCourse;
import com.gp.service.SelectCourseService;

@Service
public class SelectCourseServiceImpl implements SelectCourseService {
	@Autowired
	SelectCourseMapper scMapper;
	
	public List<SelectCourse> getByStudent(long sid) {
		return scMapper.getByStudent(sid);
	}

	public int add(long sid, long cpid) {
		return scMapper.add(sid, cpid);
	}

	public int delete(long sid, long cpid) {
		return scMapper.delete(sid, cpid);
	}
	
	public List<SelectCourse> getStuScore(long sid, int start){
		return scMapper.getStuScore(sid, start);
	}
	
	public int getStuScoreCount(long sid) {
		return scMapper.getStuScoreCount(sid);
	}
	public List<SelectCourse> getStuList(long tid, int start){
		return scMapper.getStuList(tid, start);
	}
	public int getStuListCount(long tid) {
		return scMapper.getStuListCount(tid);
	}
	public int updateFGrade(long sid, long cpid, int score) {
		return scMapper.updateFGrade(sid, cpid, score);
	}
	public int updateSGrade(long sid, long cpid, int score) {
		return scMapper.updateSGrade(sid, cpid, score);
	}
	public int updateTGrade(long sid, long cpid, int score) {
		return scMapper.updateTGrade(sid, cpid, score);
	}
	public int getCountBySid(long sid) {
		return scMapper.getCountBySid(sid);
	}
	public List<SelectCourse> getManageerStuList(long sid, long cpid, int start){
		return scMapper.getManageerStuList(sid, cpid, start);
	}
	public int getManageerStuListCount(long sid, long cpid) {
		return scMapper.getManageerStuListCount(sid, cpid);
	}
}
