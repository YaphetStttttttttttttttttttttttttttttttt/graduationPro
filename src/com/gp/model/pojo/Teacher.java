package com.gp.model.pojo;

import com.gp.model.vo.Sex;

public class Teacher {
	private long id;
	private String name;
	private Sex sex;
	private Department deid;
	private String title;
	private long tel;
	private String e_mail;
	
	public Teacher() {}
	public Teacher(long id, String name, Sex sex, Department departments_de, String title, long tel,
			String e_mail) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.deid = departments_de;
		this.title = title;
		this.tel = tel;
		this.e_mail = e_mail;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public Department getDeid() {
		return deid;
	}
	public void setDeid(Department deid) {
		this.deid = deid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getTel() {
		return tel;
	}
	public void setTel(long tel) {
		this.tel = tel;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	
	
}
