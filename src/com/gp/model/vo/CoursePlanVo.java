package com.gp.model.vo;

import com.gp.model.pojo.CoursePlan;

public class CoursePlanVo {
	private CoursePlan coursePlan;
	private boolean flag;
	private String msg;
	
	public CoursePlanVo() {}
	public CoursePlanVo(CoursePlan coursePlan, boolean flag, String msg) {
		this.coursePlan = coursePlan;
		this.flag = flag;
		this.msg = msg;
	}
	public CoursePlan getCoursePlan() {
		return coursePlan;
	}
	public void setCoursePlan(CoursePlan coursePlan) {
		this.coursePlan = coursePlan;
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
