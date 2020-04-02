package com.gp.model.vo;

public class SchoolTerm {
	private int termNum;
	private String termName;
	
	public SchoolTerm() {}
	public SchoolTerm(int termNum, String termName) {
		this.termNum = termNum;
		this.termName = termName;
	}
	public int getTermNum() {
		return termNum;
	}
	public void setTermNum(int termNum) {
		this.termNum = termNum;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	
}
