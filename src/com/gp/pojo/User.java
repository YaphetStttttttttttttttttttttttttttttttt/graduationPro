package com.gp.pojo;

public class User {
	private String username;
	private String password;
	private String nickname;
	private String prp;
	private String pra;
	private int rlip;
	private int rltime;
	
	public User() {}
	public User(String username, String password, String nickname, String prp, String pra, int rlip, int rltime) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.prp = prp;
		this.pra = pra;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPrp() {
		return prp;
	}
	public void setPrp(String prp) {
		this.prp = prp;
	}
	public String getPra() {
		return pra;
	}
	public void setPra(String pra) {
		this.pra = pra;
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
