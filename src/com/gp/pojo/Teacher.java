package com.gp.pojo;

public class Teacher {
	private long id;
	private String name;
	private String sex;
	private Department deid;
	private String title;
	private String tel;
	private String e_mail;
	
	public Teacher() {}
	public Teacher(long id, String name, String sex, Department departments_de, String title, String tel,
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Department getDepartments_de() {
		return deid;
	}
	public void setDepartments_de(Department departments_de) {
		this.deid = departments_de;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	
	
}
