package com.gp.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.gp.service.Select_courseService;

@Controller
public class Select_courseController {
	@Autowired
	Select_courseService scS;
	
	@RequestMapping("selectCourse")
	public void selectCourse(@RequestParam long cid,HttpServletResponse response, 
			HttpSession session) throws Exception {
//		ModelAndView mav = new ModelAndView();
		String error = null;
		int successFlag = 0;
		long sid = (long)session.getAttribute("sid");
		int result = scS.add(sid, cid);
		if(result < 1) {
			error = "选课失败！";
		}else {
			successFlag = 1;
		}
		String s=String.format("{\"success\":%d,\"error\":\"%s\"}",
				successFlag,error);
		response.getWriter().println(s);
	}
}
