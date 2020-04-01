package com.gp.model.pojo;

public class Admin {
	private String username;
	private String password;
	private String name;
	private int rlip;
	private int rltime;
	
	public Admin() {}
	public Admin(String username, String password, String aname, int rlip, int rltime) {
		this.username = username;
		this.password = password;
		this.name = aname;
		this.rlip = rlip;
		this.rltime = rltime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAname() {
		return name;
	}
	public void setAname(String aname) {
		this.name = aname;
	}
	public int getRlip() {
		return rlip;
	}
	public void setRlip(int rlip) {
		this.rlip = rlip;
	}
	public int getRltime() {
		return rltime;
	}
	public void setRltime(int rltime) {
		this.rltime = rltime;
	}
	
}
