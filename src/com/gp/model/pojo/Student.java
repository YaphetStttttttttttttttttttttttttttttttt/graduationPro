package com.gp.model.pojo;

import com.gp.model.vo.Sex;

public class Student {
	private long id;
	private String name;
	private Classes cid;
	private Sex sex;
	private int age;
	private long tel;
	private String address;
	private String e_mail;
	
	public Student() {}
	public Student(long id, String name, Classes class_id, Sex sex, int age, long tel, String address,
			String e_mail) {
		this.id = id;
		this.name = name;
		this.cid = class_id;
		this.sex = sex;
		this.age = age;
		this.tel = tel;
		this.address = address;
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
	public Classes getCid() {
		return cid;
	}
	public void setCid(Classes cid) {
		this.cid = cid;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getTel() {
		return tel;
	}
	public void setTel(long tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	
	
}
