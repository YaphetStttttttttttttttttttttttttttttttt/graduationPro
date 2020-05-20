package com.gp.model.vo;

public class CoursePlanFormData {
	private long id;
	private String name;
	private long tid;
	private int schoolYear;
	private SchoolTerm schoolTerm;
	private String weeks;
	private String time1;
	private String time2;
	private String time3;
	public CoursePlanFormData() {}
	public CoursePlanFormData(long id, String name, long tid, int schoolYear, SchoolTerm schoolTerm, String weeks,
			String time1, String time2, String time3) {
		super();
		this.id = id;
		this.name = name;
		this.tid = tid;
		this.schoolYear = schoolYear;
		this.schoolTerm = schoolTerm;
		this.weeks = weeks;
		this.time1 = time1;
		this.time2 = time2;
		this.time3 = time3;
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
	public long getTid() {
		return tid;
	}
	public void setTid(long tid) {
		this.tid = tid;
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
	public String getWeeks() {
		return weeks;
	}
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	public String getTime3() {
		return time3;
	}
	public void setTime3(String time3) {
		this.time3 = time3;
	}
	
}
