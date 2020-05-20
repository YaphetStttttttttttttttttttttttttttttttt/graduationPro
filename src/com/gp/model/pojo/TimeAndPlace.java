package com.gp.model.pojo;

import com.gp.model.vo.SchoolTerm;

public class TimeAndPlace {
	private int id;
	private long tid;
	private int weeks;
	private int week;
	private int time;
	private Classroom place;
	private int schoolYear;
	private SchoolTerm schoolTerm;
	
	public TimeAndPlace() {}

	public TimeAndPlace(long tid, int weeks, int week, int time, Classroom place, int schoolYear,
			SchoolTerm schoolTerm) {
		super();
		this.tid = tid;
		this.weeks = weeks;
		this.week = week;
		this.time = time;
		this.place = place;
		this.schoolYear = schoolYear;
		this.schoolTerm = schoolTerm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public int getWeeks() {
		return weeks;
	}

	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Classroom getPlace() {
		return place;
	}

	public void setPlace(Classroom place) {
		this.place = place;
	}

	public int getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(int schoolYear) {
		this.schoolYear = schoolYear;
	}

	public SchoolTerm getSchoolTerm() {
		return schoolTerm;
	}

	public void setSchoolTerm(SchoolTerm schoolTerm) {
		this.schoolTerm = schoolTerm;
	}
	
}
