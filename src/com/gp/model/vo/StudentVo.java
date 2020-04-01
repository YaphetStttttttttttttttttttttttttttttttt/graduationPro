package com.gp.model.vo;

import com.gp.model.pojo.Student;

public class StudentVo {
	private Student student;
	private boolean flag;
	private String msg;
	
	public StudentVo() {}
	public StudentVo(Student student, boolean flag, String msg) {
		this.student = student;
		this.flag = flag;
		this.msg = msg;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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
