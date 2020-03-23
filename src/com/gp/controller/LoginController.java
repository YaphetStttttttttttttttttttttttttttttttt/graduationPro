package com.gp.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gp.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService user;
	
	@RequestMapping("login")
    public ModelAndView listCategory(@RequestParam String username, @RequestParam String password, HttpSession session){
        ModelAndView mav = new ModelAndView();
        if(!isInteger(username)) {
        	if(user.alogin(username, password) != 1) {
    			//验证失败
    			mav.addObject("error","用户名或密码错误");
            	mav.setViewName("forward:index");
            	return mav;
    		}else {
    			//验证管理员信息成功
    			session.setAttribute("admin", username);
    			mav.setViewName("manageer");
            	return mav;
    		}
        }else {
        	long uname = Long.valueOf(username);
        	if(user.slogin(uname, password) != 1) {
            	if(user.tlogin(uname, password) != 1) {
            		//验证失败
        			mav.addObject("error","用户名或密码错误");
                	mav.setViewName("forward:index");
                	return mav;
            	}else {
            		//验证教师信息成功
            		session.setAttribute("tid", uname);
            		mav.setViewName("teacher");
                	return mav;
            	}
            	
            }else {
            	//验证学生信息成功
            	session.setAttribute("sid", uname);
            	mav.setViewName("student");
            	return mav;
            }
        }
    }
	@RequestMapping("index")
	public String student() {
		return "index";
	}
	@RequestMapping("2")
	public String teacher() {
		return "teacher";
	}
	@RequestMapping("3")
	public String admin() {
		return "manageer";
	}
	public static boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        //整数返回true，否则返回false
        return pattern.matcher(str).matches();  
}
}
