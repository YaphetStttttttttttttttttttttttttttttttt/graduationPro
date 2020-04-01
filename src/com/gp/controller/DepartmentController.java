package com.gp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gp.model.pojo.Department;
import com.gp.service.DepartmentService;

@Controller
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping("addDepartment")
	public void add(HttpServletResponse httpServletResponse) throws IOException {
		Department department = new Department();
		department.setId(05);
		department.setName("数1统院");
		int flag = departmentService.add(department);/*
		String s = "{\"success\":\"" + flag == 1? true:false +"\",\"msg\":\"" + msg + "\"}";
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(s);*/
	}
	@RequestMapping("addListDepartment")
	public ModelAndView addMany() {
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
}
