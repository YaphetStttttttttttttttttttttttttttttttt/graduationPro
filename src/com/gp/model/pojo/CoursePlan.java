package com.gp.model.pojo;

import java.util.List;

import com.gp.model.vo.SchoolTerm;

public class CoursePlan {
	private Course cid;
	private Teacher tid;
	private int startYear;
	private SchoolTerm startTerm;
	private String timeAPlace;
	
	List<SelectCourse> selectCourses;
	
	public CoursePlan() {}
	public CoursePlan(Course cid, Teacher tid, int startYear, SchoolTerm startTerm, String timeAPlace) {
		this.cid = cid;
		this.tid = tid;
		this.startYear = startYear;
		this.startTerm = startTerm;
		this.timeAPlace = timeAPlace;
	}
	public Course getCid() {
		return cid;
	}
	public void setCid(Course cid) {
		this.cid = cid;
	}
	public Teacher getTid() {
		return tid;
	}
	public void setTid(Teacher tid) {
		this.tid = tid;
	}
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public SchoolTerm getStartTerm() {
		return startTerm;
	}
	public void setStartTerm(SchoolTerm startTerm) {
		this.startTerm = startTerm;
	}
	public String getTimeAPlace() {
		return timeAPlace;
	}
	public void setTimeAPlace(String timeAPlace) {
		this.timeAPlace = timeAPlace;
	}
	public List<SelectCourse> getSelectCourses() {
		return selectCourses;
	}
	public void setSelectCourses(List<SelectCourse> selectCourses) {
		this.selectCourses = selectCourses;
	}
	
	
}
