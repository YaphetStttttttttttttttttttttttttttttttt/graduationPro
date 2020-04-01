package com.gp.model.pojo;

public class Classes {
	private int id;
	private String name;
	private Teacher tid;
	private String grade;
	private Discipline diid;
	
	public Classes() {}
	public Classes(int cid, String cname, Teacher tid, String grade, Discipline diid) {
		this.id = cid;
		this.name = cname;
		this.tid = tid;
		this.grade = grade;
		this.diid = diid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Teacher getTid() {
		return tid;
	}
	public void setTid(Teacher tid) {
		this.tid = tid;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Discipline getDiid() {
		return diid;
	}
	public void setDiid(Discipline diid) {
		this.diid = diid;
	}
	
	
}
