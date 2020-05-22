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
import com.gp.model.pojo.Admin;
import com.gp.model.pojo.SelectCourse;
import com.gp.model.pojo.StudentUser;
import com.gp.model.pojo.TeacherUser;
import com.gp.service.SelectCourseService;

@Controller
public class SelectCourseController {
	@Autowired
	SelectCourseService scService;
	
	
	@ResponseBody
	@RequestMapping("studentScore")
	public void studentScore(HttpSession session, HttpServletResponse httpServletResponse,HttpServletRequest request) throws IOException {
		StudentUser user = (StudentUser) session.getAttribute("user");
		int start = Integer.valueOf(request.getParameter("pageNumber"));
		//int size = Integer.valueOf(request.getParameter("Size"));
		List<SelectCourse> listSelectCourses = new ArrayList<SelectCourse>();
		JSONObject jsonObj = new JSONObject();
		//System.out.println(user != null);
		if(user != null) {
			listSelectCourses = scService.getStuScore(user.getUsername().getId(), (start - 1) * 10);
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
	@ResponseBody
	@RequestMapping("totlePageStuList")
	public Object totlePageStuList(HttpSession session) {
		TeacherUser user = (TeacherUser) session.getAttribute("user");
		int totle = 0;
		if(user != null) {
			totle = scService.getStuListCount(user.getUsername().getId());
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totle", totle);
		return jsonObj;
	}
	@ResponseBody
	@RequestMapping("manageerTotlePageStuList")
	public Object manageerTotlePageStuList(HttpSession session, HttpServletRequest request) throws IOException {
		long sid = 0, cpid = 0;
		Admin user = (Admin) session.getAttribute("user");
		int totle = 0;
		if(user != null) {
			if(request.getParameter("sid") != null && request.getParameter("sid") != "" && !request.getParameter("sid").equals("")) {
				sid = Long.valueOf(request.getParameter("id"));
			}
			if(request.getParameter("cpid") != null && request.getParameter("cpid") != "" && !request.getParameter("cpid").equals("")) {
				cpid = Long.valueOf(request.getParameter("cpid"));
			}
			totle = scService.getManageerStuListCount(sid, cpid);
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totle", totle);
		return jsonObj;
	}
	@ResponseBody
	@RequestMapping("manageerStuList")
	public void manageerStuList(HttpSession session, HttpServletResponse httpServletResponse,HttpServletRequest request) throws IOException {
		long sid = 0, cpid = 0;
		Admin user = (Admin) session.getAttribute("user");
		int start = Integer.valueOf(request.getParameter("pageNumber"));
		List<SelectCourse> listStudents = new ArrayList<SelectCourse>();
		JSONObject jsonObj = new JSONObject();
		if(user != null) {
			if(request.getParameter("sid") != null && request.getParameter("sid") != "" && !request.getParameter("sid").equals("")) {
				sid = Long.valueOf(request.getParameter("id"));
			}
			if(request.getParameter("cpid") != null && request.getParameter("cpid") != "" && !request.getParameter("cpid").equals("")) {
				cpid = Long.valueOf(request.getParameter("cpid"));
			}
			listStudents = scService.getManageerStuList(sid, cpid, (start - 1) * 10);
		}
		jsonObj.put("listStudents", listStudents);
		String jsonString = JSONObject.toJSONString(jsonObj);
		
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(jsonString);
	}
	@ResponseBody
	@RequestMapping("stuList")
	public void stuList(HttpSession session, HttpServletResponse httpServletResponse,HttpServletRequest request) throws IOException {
		TeacherUser user = (TeacherUser) session.getAttribute("user");
		int start = Integer.valueOf(request.getParameter("pageNumber"));
		List<SelectCourse> listStudents = new ArrayList<SelectCourse>();
		JSONObject jsonObj = new JSONObject();
		if(user != null) {
			listStudents = scService.getStuList(user.getUsername().getId(), (start - 1) * 10);
		}
		jsonObj.put("listStudents", listStudents);
		String jsonString = JSONObject.toJSONString(jsonObj);
		
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(jsonString);
	}
	@ResponseBody
	@RequestMapping("manaUpdateFGrade")
	public void manaUpdateFGrade(HttpSession session, HttpServletRequest request) throws IOException {
		Admin user = (Admin) session.getAttribute("user");
		long sid = Long.valueOf(request.getParameter("sid"));
		long cpid = Long.valueOf(request.getParameter("cpid"));
		int score = 0;
		if(user != null && request.getParameter("score") != null) {
			score = Integer.valueOf(request.getParameter("score"));
			int flag = scService.updateFGrade(sid, cpid, score);
		}
	}
	@ResponseBody
	@RequestMapping("manaUpdateSGrade")
	public void manaUpdateSGrade(HttpSession session, HttpServletRequest request) throws IOException {
		Admin user = (Admin) session.getAttribute("user");
		long sid = Long.valueOf(request.getParameter("sid"));
		long cpid = Long.valueOf(request.getParameter("cpid"));
		int score = 0;
		if(user != null && request.getParameter("score") != null) {
			score = Integer.valueOf(request.getParameter("score"));
			int flag = scService.updateSGrade(sid, cpid, score);
		}
	}
	@ResponseBody
	@RequestMapping("manaUpdateTGrade")
	public void manaUpdateTGrade(HttpSession session, HttpServletRequest request) throws IOException {
		Admin user = (Admin) session.getAttribute("user");
		long sid = Long.valueOf(request.getParameter("sid"));
		long cpid = Long.valueOf(request.getParameter("cpid"));
		int score = 0;
		if(user != null && request.getParameter("score") != null) {
			score = Integer.valueOf(request.getParameter("score"));
			int flag = scService.updateTGrade(sid, cpid, score);
		}
	}
	@ResponseBody
	@RequestMapping("updateTGrade")
	public void updateTGrade(HttpSession session, HttpServletRequest request) throws IOException {
		TeacherUser user = (TeacherUser) session.getAttribute("user");
		long sid = Long.valueOf(request.getParameter("sid"));
		long cpid = Long.valueOf(request.getParameter("cpid"));
		int score = 0;
		if(user != null && request.getParameter("score") != null) {
			score = Integer.valueOf(request.getParameter("score"));
			int flag = scService.updateTGrade(sid, cpid, score);
		}
	}
}
