package com.gp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.gp.model.pojo.CoursePlan;
import com.gp.model.pojo.SelectCourse;
import com.gp.model.pojo.StudentUser;
import com.gp.service.SelectCourseService;

@Controller
public class SelectCourseController {
	@Autowired
	SelectCourseService scService;
	
	
	@ResponseBody
	@RequestMapping("studentScore")
//	public Object Manager_TeacherMana(HttpSession session, HttpServletRequest request) {
	public void studentScore(HttpSession session, HttpServletResponse httpServletResponse,HttpServletRequest request) throws IOException {
		StudentUser user = (StudentUser) session.getAttribute("user");
		int start = Integer.valueOf(request.getParameter("pageNumber"));
		//int size = Integer.valueOf(request.getParameter("Size"));
		List<SelectCourse> listSelectCourses = new ArrayList<SelectCourse>();
		JSONObject jsonObj = new JSONObject();
		//System.out.println(user != null);
		if(user != null) {
			listSelectCourses = scService.getStuScore(user.getUsername().getId());
		}
		jsonObj.put("listScores", listSelectCourses);
		String jsonString = JSONObject.toJSONString(jsonObj);
		
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(jsonString);
	}
	@ResponseBody
	@RequestMapping("totlePageStuScore")
	public Object totlePageStuScore(HttpSession session) {
		StudentUser user = (StudentUser) session.getAttribute("user");
		int totle = 0;
		if(user != null) {
			totle = scService.getStuScoreCount(user.getUsername().getId());
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totle", totle);
		return jsonObj;
	}
}
