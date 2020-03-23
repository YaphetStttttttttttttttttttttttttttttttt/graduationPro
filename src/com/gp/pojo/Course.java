package com.gp.pojo;

import java.time.Year;

public class Course {
	private long id;
	private String name;
	private String type;
	private float credit;
	private int cycle;
	private Year start_time;
	private String start_term;
	private String place;
	private Teacher tid;
	
//	List<Select_course> selectCourse;
	Select_course selectCourse;
	
	public Course() {}
	public Course(long id, String name, String type, float credit, int cycle, Year start_time, String start_term,
			String place, Teacher tid, Select_course selectCourse) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.credit = credit;
		this.cycle = cycle;
		this.start_time = start_time;
		this.start_term = start_term;
		this.place = place;
		this.tid = tid;
		this.selectCourse = selectCourse;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	public Year getStart_time() {
		return start_time;
	}
	public void setStart_time(Year start_time) {
		this.start_time = start_time;
	}
	public String getStart_term() {
		return start_term;
	}
	public void setStart_term(String start_term) {
		this.start_term = start_term;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Teacher getTid() {
		return tid;
	}
	public void setTid(Teacher teacher) {
		this.tid = teacher;
	}
	public Select_course getSelectCourse() {
		return selectCourse;
	}
	public void setSelectCourse(Select_course selectCourse) {
		this.selectCourse = selectCourse;
	}
	
	
	
}
