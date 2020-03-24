package com.gp.pojo;

public class Class {
	private int id;
	private String name;
	private Teacher tid;
	private String gread;
	private Discipline deid;
	
	public Class() {}
	public Class(int cid, String cname, Teacher c_teacher, String gread, Discipline departments_di) {
		this.id = cid;
		this.name = cname;
		this.tid = c_teacher;
		this.gread = gread;
		this.deid = departments_di;
	}
	public int getCid() {
		return id;
	}
	public void setCid(int cid) {
		this.id = cid;
	}
	public String getCname() {
		return name;
	}
	public void setCname(String cname) {
		this.name = cname;
	}
	public Teacher getC_teacher() {
		return tid;
	}
	public void setC_teacher(Teacher c_teacher) {
		this.tid = c_teacher;
	}
	public String getGread() {
		return gread;
	}
	public void setGread(String gread) {
		this.gread = gread;
	}
	public Discipline getDepartments_di() {
		return deid;
	}
	public void setDepartments_di(Discipline departments_di) {
		this.deid = departments_di;
	}
	
	
}
