package com.gp.pojo;

public class Department {
	private int id;
	private String name;
	private String address;
	private String tel;
	
	public Department() {}
	public Department(int id, String dename, String deaddress, String detel) {
		this.id = id;
		this.name = dename;
		this.address = deaddress;
		this.tel = detel;
	}
	public int getDeid() {
		return id;
	}
	public void setDeid(int id) {
		this.id = id;
	}
	public String getDename() {
		return name;
	}
	public void setDename(String dename) {
		this.name = dename;
	}
	public String getDeaddress() {
		return address;
	}
	public void setDeaddress(String deaddress) {
		this.address = deaddress;
	}
	public String getDetel() {
		return tel;
	}
	public void setDetel(String detel) {
		this.tel = detel;
	}
	
}
