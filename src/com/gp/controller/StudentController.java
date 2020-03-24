package com.gp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gp.pojo.Student;
import com.gp.service.StudentService;

@Controller
public class StudentController {
	@Autowired
    StudentService studentService;
 
    @RequestMapping("listStudent")
    public ModelAndView listCategory(){
        ModelAndView mav = new ModelAndView();
        List<Student> ss= studentService.list();
        // 放入转发参数
        mav.addObject("ss", ss);
        // 放入jsp路径
        mav.setViewName("listStudent");
        return mav;
    }
    //test.classpath
    @RequestMapping(value="{name}")
	public String commonHandler(@PathVariable String name) {
		return name;
	}

}
