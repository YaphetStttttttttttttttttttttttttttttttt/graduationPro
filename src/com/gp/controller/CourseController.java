package com.gp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gp.pojo.Course;
import com.gp.service.CourseService;

@Controller
public class CourseController {
	@Autowired
	CourseService courseService;
	
	@RequestMapping("scores")
	public ModelAndView listCourse(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		long sid = (long)session.getAttribute("sid");
		List<Course> course = courseService.listCourse(sid);
		
		mav.addObject("allCourse",course);
		mav.setViewName("studentscore");
		return mav;
	}
	@RequestMapping("choosec")
	public ModelAndView listSelectCourse(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		long sid = (long)session.getAttribute("sid");
		List<Course> course = courseService.listSelectCourse(sid);
		mav.addObject("allSelectCourse",course);
		
		mav.setViewName("studentchoose");
		return mav;
	}
}
