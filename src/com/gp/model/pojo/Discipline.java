package com.gp.model.pojo;

public class Discipline {
	private int id;
	private String name;
	private Department deid;
	
	public Discipline() {}
	public Discipline(int id, String name, Department deid) {
		this.id = id;
		this.name = name;
		this.deid = deid;
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
	public Department getDeid() {
		return deid;
	}
	public void setDeid(Department deid) {
		this.deid = deid;
	}
	
}
