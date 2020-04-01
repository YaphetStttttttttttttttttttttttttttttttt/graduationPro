package com.gp.model.vo;

import com.gp.model.pojo.Course;

public class CourseVo {
	private Course course;
	private boolean flag;
	private String msg;
	
	public CourseVo() {}
	public CourseVo(Course course, boolean flag, String msg) {
		this.course = course;
		this.flag = flag;
		this.msg = msg;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
