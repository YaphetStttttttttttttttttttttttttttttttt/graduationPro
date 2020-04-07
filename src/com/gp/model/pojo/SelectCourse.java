package com.gp.model.pojo;

public class SelectCourse {
	private Student sid;
	private CoursePlan cpid;
	private float fGrade;
	private float sGrade;
	private float tGrade;
	
	public SelectCourse() {}
	public SelectCourse(Student sid, CoursePlan cpid, float fGrade, float sGrade, float tGrade) {
		this.sid = sid;
		this.cpid = cpid;
		this.fGrade = fGrade;
		this.sGrade = sGrade;
		this.tGrade = tGrade;
	}
	public Student getSid() {
		return sid;
	}
	public void setSid(Student sid) {
		this.sid = sid;
	}
	public CoursePlan getCpid() {
		return cpid;
	}
	public void setCpid(CoursePlan cpid) {
		this.cpid = cpid;
	}
	public float getfGrade() {
		return fGrade;
	}
	public void setfGrade(float fGrade) {
		this.fGrade = fGrade;
	}
	public float getsGrade() {
		return sGrade;
	}
	public void setsGrade(float sGrade) {
		this.sGrade = sGrade;
	}
	public float gettGrade() {
		return tGrade;
	}
	public void settGrade(float tGrade) {
		this.tGrade = tGrade;
	}
	
}
