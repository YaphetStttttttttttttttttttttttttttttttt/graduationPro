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
		String html ="<tr>\r\n" + 
				"		<td name=\"select\" style=\"display:none;\"><input name=\"ids\" type=\"checkbox\" value='{\"id\":${teacher.id}}'></td>\r\n" + 
				"		<td>${teacher.id }</td>\r\n" + 
				"		<td>${teacher.name }</td>\r\n" + 
				"		<td>${teacher.sex.num==1?'男':'女' }</td>\r\n" + 
				"		<td>${teacher.deid.name }</td>\r\n" + 
				"		<td>${teacher.title==null?'还未填写':teacher.title }</td>\r\n" + 
				"		<td>${teacher.tel==0?'还未填写':teacher.tel }</td>\r\n" + 
				"		<td>${teacher.e_mail==null?'还未填写':teacher.e_mail }</td>\r\n" + 
				"		<td>\r\n" + 
				"			<center>\r\n" + 
				"			<button type=\"button\" name=\"Update\" class=\"btn btn-info\" onclick='updateTeacher(\"${teacher.id}\", \"${teacher.name}\", \"${teacher.sex.num==1?'男':'女'}\", \r\n" + 
				"			\"${teacher.deid.name}\", \"${teacher.title==null?'':teacher.title}\", \"${teacher.tel==0?'':teacher.tel}\", \"${teacher.e_mail==null?'':teacher.e_mail}\")'>\r\n" + 
				"				<span class=\"glyphicon glyphicon-wrench\" aria-hidden=\"true\"></span>修改\r\n" + 
				"			</button>\r\n" + 
				"        	<button type=\"button\" name=\"Delete\" class=\"btn btn-danger\" style=\"display:none;\" onclick=\"deleteTeacher(${teacher.id})\">\r\n" + 
				"        		<span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\" ></span>删除\r\n" + 
				"        	</button>\r\n" + 
				"        	</center>\r\n" + 
				"        </td> \r\n" + 
				"		</tr>";
		
	}
}
