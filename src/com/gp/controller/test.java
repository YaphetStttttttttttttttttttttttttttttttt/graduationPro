package com.gp.controller;

import java.util.Calendar;
import java.util.Date;

import com.gp.model.util.NowDate;

public class test {

	public static void main(String[] args) {
		/*
		int cp1 = Integer.valueOf("11000000000",2);
	//	int cp1_Int = Integer.valueOf(cp1);
		int cp2 = Integer.valueOf("00110000000",2);
	//	Integer cp2_Int = Integer.valueOf(cp2);
		System.out.println(cp2);
		String cp = Integer.toBinaryString(2147483647);
		System.out.println("处理前:" + cp + Long.MAX_VALUE);
		cp = toCPTime(cp);
		System.out.println("处理后:" + cp);
		for(int i = 0; i < cp.length(); i++) {
			if(i != 0 && i % 4 == 0) System.out.print(" ");
			System.out.print(cp.charAt(i));
		} 
		*/
		NowDate now = new NowDate();
		int year = now.getYear();
		int month = now.getMonth();
		int studentYear = 2016;//获取学生入学时间
		if(month < 7) {
			//2020年上学期 对应大year - 入学年份 下学期
			int grade = year - studentYear;
		}else {
			//2020年下学期 对应大year - 入学年份 + 1  上学期
			int grade = year - studentYear + 1;
		}
		System.out.println(year + "年" + month + "月");
		//2016年入学。2020年4月 = 2020年上期  -> 大四下
	}
	public static String toCPTime(String s) {
		if(s.length() < 11) return toCPTime("0" + s);
		else return s;
	}
}
