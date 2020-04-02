package com.gp.model.vo;

import com.gp.model.pojo.Teacher;

public class TeacherVo {
	private Teacher teacher;
	private boolean flag;
	private String msg;
	
	public TeacherVo() {}
	public TeacherVo(Teacher teacher, boolean flag, String msg) {
		this.teacher = teacher;
		this.flag = flag;
		this.msg = msg;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
