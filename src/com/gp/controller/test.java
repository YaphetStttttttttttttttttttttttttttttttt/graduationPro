package com.gp.controller;

import java.util.Calendar;
import java.util.Date;

import com.gp.model.pojo.TimeAndPlace;
import com.gp.model.util.NowDate;

public class test {

	public static void main(String[] args) {
		
		int cp1 = Integer.valueOf("11000000000",2);
	//	int cp1_Int = Integer.valueOf(cp1);
		int cp2 = Integer.valueOf("00110000000",2);
	//	Integer cp2_Int = Integer.valueOf(cp2);
		System.out.println(cp2);
		String cp = Integer.toBinaryString(cp2);
		System.out.println("处理前:" + cp);
		cp = toTime(cp);
		System.out.println("处理后:" + cp);
		for(int i = 0; i < cp.length(); i++) {
			if(i != 0 && i % 4 == 0) System.out.print(" ");
			System.out.print(cp.charAt(i));
		} 
		/*
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
		System.out.println(year + "年" + month + "月");*/
		//2016年入学。2020年4月 = 2020年上期  -> 大四下
	}
	public static String TimeIntToString(TimeAndPlace time, int index) {
		String timeString = "";
		String classroomString = time.getPlace().getName();
		int weeksStartNum = 0, weeksEndNum = 0, weeksNum = 0;
		int timesStartNum = 0, timesEndNum = 0;
		if(classroomString.equals("xxx")) {
			//==xxx为空时间地点..
		}else {
			//1.周转换为20位二进制。判断单双周。
			String weeks = Integer.toBinaryString(time.getWeeks());
			weeks = toWeeks(weeks);
			String times = Integer.toBinaryString(time.getTime());
			times = toTime(times);
			for(int i = 0; i < weeks.length(); i++) {
				char ch = weeks.charAt(i);
				if(weeksStartNum == 0 && ch == '1') {
					weeksStartNum = i + 1;
					weeksNum ++;
				}else if(weeksStartNum == 1 && ch == '1') {
					weeksEndNum = i + 1;
					weeksNum ++;
				}
			}
			for(int i = 0; i < times.length(); i++) {
				char ch = times.charAt(i);
				if(timesStartNum == 0 && ch == '1') {
					timesStartNum = i + 1;
				}else if(timesStartNum == 1 && ch == '1') {
					timesEndNum = i + 1;
				}
			}
			if(index == 1) {
				timeString = weeksStartNum + "-" + weeksEndNum + "周,";
			}else {
				timeString = ";";
			}
			if(weeksNum < (weeksEndNum - weeksStartNum)) {
				if(weeksStartNum % 2 == 0) {
					timeString += "双周,";
				}else {
					timeString += "单周,";
				}
			}else {
				
			}
			String[] weekArgs = {"","一","二","三","四","五","六","天"};
			timeString = timeString + "星期" + weekArgs[time.getWeek()] + timesStartNum + "-" + timesEndNum + "节," + time.getPlace().getName();
		}
		return timeString;
	}
	public static String toTime(String s) {
		if(s.length() < 11) return toTime("0" + s);
		else return s;
	}
	public static String toWeeks(String s) {
		if(s.length() < 20) return toWeeks("0" + s);
		else return s;
	}
}
