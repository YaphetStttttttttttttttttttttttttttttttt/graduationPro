package com.gp.model.vo;

public class CourseType {
	private int typeNum;
	private String typeName;
	
	public CourseType() {}
	public CourseType(int typeNum, String typeName) {
		this.typeNum = typeNum;
		this.typeName = typeName;
	}
	public int getTypeNum() {
		return typeNum;
	}
	public void setTypeNum(int typeNum) {
		this.typeNum = typeNum;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
