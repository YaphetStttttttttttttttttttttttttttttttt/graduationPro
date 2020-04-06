package com.gp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.gp.model.pojo.Teacher;

public class test {
	private void mian() {
		// TODO Auto-generated method stub
		String a="[{\"id\":1235,\"name\":\"王五\",\"sex\":{\"num\":1,\"sex\":null},\"deid\":{\"id\":0,\"name\":\"计算机科学与工程学院\",\"tel\":null,\"deaddress\":null},\"title\":null,\"tel\":0,\"e_mail\":null},{\"id\":1236,\"name\":\"YYF\",\"sex\":{\"num\":1,\"sex\":null},\"deid\":{\"id\":0,\"name\":\"计算机科学与工程学院\",\"tel\":null,\"deaddress\":null},\"title\":null,\"tel\":0,\"e_mail\":null},{\"id\":1237,\"name\":\"路垚\",\"sex\":{\"num\":1,\"sex\":null},\"deid\":{\"id\":0,\"name\":\"计算机科学与工程学院\",\"tel\":null,\"deaddress\":null},\"title\":null,\"tel\":0,\"e_mail\":null},{\"id\":1238,\"name\":\"姜岑\",\"sex\":{\"num\":1,\"sex\":null},\"deid\":{\"id\":0,\"name\":\"计算机科学与工程学院\",\"tel\":null,\"deaddress\":null},\"title\":null,\"tel\":0,\"e_mail\":null},{\"id\":1239,\"name\":\"黄翔\",\"sex\":{\"num\":1,\"sex\":null},\"deid\":{\"id\":0,\"name\":\"计算机科学与工程学院\",\"tel\":null,\"deaddress\":null},\"title\":null,\"tel\":0,\"e_mail\":null},{\"id\":1240,\"name\":\"邹倚天\",\"sex\":{\"num\":1,\"sex\":null},\"deid\":{\"id\":0,\"name\":\"计算机科学与工程学院\",\"tel\":null,\"deaddress\":null},\"title\":null,\"tel\":0,\"e_mail\":null},{\"id\":1241,\"name\":\"谢彬恶心\",\"sex\":{\"num\":1,\"sex\":null},\"deid\":{\"id\":0,\"name\":\"计算机科学与工程学院\",\"tel\":null,\"deaddress\":null},\"title\":null,\"tel\":0,\"e_mail\":null}]";
		List<Teacher> list=JSONArray.parseArray(a,Teacher.class);
	}
}
