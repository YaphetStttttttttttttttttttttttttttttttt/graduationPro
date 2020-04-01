package com.gp.model.vo;

import com.gp.model.pojo.Classes;

public class ClassesVo {
	private Classes classes;
	private boolean flag;
	private String msg;
	
	public ClassesVo() {}
	public ClassesVo(Classes classes, boolean flag, String msg) {
		this.classes = classes;
		this.flag = flag;
		this.msg = msg;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
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
