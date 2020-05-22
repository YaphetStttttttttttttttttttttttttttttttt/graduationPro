package com.gp.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gp.model.pojo.Admin;
import com.gp.model.pojo.StudentUser;
import com.gp.model.pojo.TeacherUser;
import com.gp.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService user;
	
	@RequestMapping("login")
    public ModelAndView listCategory(@RequestParam String username, @RequestParam String password, HttpSession session){
        ModelAndView mav = new ModelAndView();
        if(username.equals("") || username == null || password.equals("") || password == null) {
        	mav.addObject("error", "用户名和密码不能为空");
        	mav.setViewName("index");
        	return mav;
        }
        if(!isInteger(username)) {
        	if(user.alogin(username, password) == null) {
    			//验证失败
    			mav.addObject("error","用户名或密码错误");
            	mav.setViewName("forward:index");
            	return mav;
    		}else {
    			//验证管理员信息成功
    			Admin admin = user.alogin(username, password);
    			admin.setPassword(null);
    			session.setAttribute("user", admin);
    			mav.setViewName("manageer");
            	return mav;
    		}
        }else {
        	long uname = Long.valueOf(username);
        	if(user.slogin(uname, password) == null) {
            	if(user.tlogin(uname, password) == null) {
            		//验证失败
        			mav.addObject("error","用户名或密码错误");
                	mav.setViewName("forward:index");
                	return mav;
            	}else {
            		//验证教师信息成功
            		TeacherUser teacher = user.tlogin(uname, password);
            		teacher.setPassword(null);
            		teacher.getUsername().getSex().setSex( sexInt2String( teacher.getUsername().getSex().getNum() ) );
            		session.setAttribute("user", teacher);
            		mav.setViewName("teacher");
                	return mav;
            	}
            	
            }else {
            	//验证学生信息成功
            	StudentUser student = user.slogin(uname, password);
            	student.setPassword(null);
            	student.getUsername().getSex().setSex( sexInt2String( student.getUsername().getSex().getNum() ) );
            	session.setAttribute("user", student);
            	mav.setViewName("student");
            	return mav;
            }
        }
    }
	@RequestMapping("index")
	public String student() {
		return "index";
	}
	@RequestMapping("ExitBtn")
	public String exit(HttpSession session) {
		session.setAttribute("user", null);
		return "index";
	}
	private String sexInt2String(int num) {
		String sex = "";
		if(num == 1) {
			sex = "男";
		}else {
			sex = "女";
		}
		return sex;
	}
	public static boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        //整数返回true，否则返回false
        return pattern.matcher(str).matches();  
	}
}
