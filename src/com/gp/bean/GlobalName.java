package com.gp.bean;

public class GlobalName {
	//excel表格返回值
	public static final int resultType_INT = 1;
	public static final int resultType_LONG = 2;
	public static final int resultType_STRING = 3;
	public static final int resultType_DOUBLE = 4;
	public static final int resultType_BOOLEAN = 5;
	public static final int resultType_FLOAT = 6;
	//excel表格数据在数据库是否为not null
	public static final boolean Necessary = true;
	public static final boolean unNecessary = false;
	//前端数据处理方式
	public static final int method_INSERT = 1;
	public static final int method_DELETE = 2;
	public static final int method_SELECT = 3;
	public static final int method_UPDATE = 4;
}
