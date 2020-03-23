package com.gp.pojo;

public class Select_course {
	private Student sid;
	private Course cid;
	private float f_grade;
	private float s_grade;
	private float t_grade;
	
	public Select_course() {}
	public Select_course(Student sid, Course cid, float f_grade, float s_grade, float t_grade) {
		this.sid = sid;
		this.cid = cid;
		this.f_grade = f_grade;
		this.s_grade = s_grade;
		this.t_grade = t_grade;
	}
	public Student getSid() {
		return sid;
	}
	public void setSid(Student sid) {
		this.sid = sid;
	}
	public Course getCid() {
		return cid;
	}
	public void setCid(Course cid) {
		this.cid = cid;
	}
	public float getF_grade() {
		return f_grade;
	}
	public void setF_grade(float f_grade) {
		this.f_grade = f_grade;
	}
	public float getS_grade() {
		return s_grade;
	}
	public void setS_grade(float s_grade) {
		this.s_grade = s_grade;
	}
	public float getT_grade() {
		return t_grade;
	}
	public void setT_grade(float t_grade) {
		this.t_grade = t_grade;
	}
	
	
}
