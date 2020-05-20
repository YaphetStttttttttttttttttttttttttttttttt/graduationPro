package com.gp.model.pojo;

public class Classroom {
	private int id;
	private String name;
	private String detail;
	public Classroom() {
		// TODO Auto-generated constructor stub
	}
	public Classroom(int id, String name, String detail) {
		super();
		this.id = id;
		this.name = name;
		this.detail = detail;
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
