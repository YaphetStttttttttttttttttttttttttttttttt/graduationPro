package com.gp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gp.bean.ExcelValueBean;
import com.gp.bean.GlobalName;
import com.gp.model.pojo.Admin;
import com.gp.model.pojo.Department;
import com.gp.model.pojo.StudentUser;
import com.gp.model.pojo.Teacher;
import com.gp.model.pojo.TeacherUser;
import com.gp.model.vo.GetExcelValue;
import com.gp.model.vo.Sex;
import com.gp.model.vo.TeacherVo;
import com.gp.service.ClassesService;
import com.gp.service.CoursePlanService;
import com.gp.service.DepartmentService;
import com.gp.service.TeacherService;

@Controller
public class TeacherController {
	@Autowired
	TeacherService teacherService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	ClassesService classesService;
	@Autowired
	CoursePlanService coursePlanService;
	
	@RequestMapping("addTeacher")
	public void addTeacher(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		Teacher teacher = getTeacherFormData(httpServletRequest);
		TeacherVo teacherVo = formTeacherDataPro(teacher, GlobalName.method_INSERT);
		String s = "", msg = "";
		if(!teacherVo.isFlag()) {
			s = "{\"success\":\"" + teacherVo.isFlag() +"\",\"msg\":\"" + teacherVo.getMsg() + "\"}";
		}else {
			try {
				int flag = teacherService.add(teacherVo.getTeacher());
				if(flag == 0) {
					msg = "插入数据库失败!";
					s = "{\"success\":\"" + "false" +"\",\"msg\":\"" + msg + "\"}";
				}else {
					msg = "添加成功!";
					s = "{\"success\":\"" + teacherVo.isFlag() +"\",\"msg\":\"" + msg + "\"}";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(s);
	}
	@RequestMapping("deleteTeacher")
	public void deleteTeacher(@RequestParam long id, HttpServletResponse httpServletResponse) throws IOException {
		String s ="", msg = "";
		Teacher teacher = new Teacher();
		teacher.setId(id);
		boolean deleteFlag = teacherCanDeleted(teacher);
		if(!deleteFlag) {
			msg = "教师无法删除。可能在排课或是班级中存在";
			s = "{\"success\":\"" + deleteFlag +"\",\"msg\":\"" + msg + "\"}";
		}else {
			try {
				int flag = teacherService.delete(teacher);
				if(flag == 0) {
					msg = "删除失败!";
					s = "{\"success\":\"" + "false" +"\",\"msg\":\"" + msg + "\"}";
				}else {
					msg = "删除成功!";
					s = "{\"success\":\"" + deleteFlag +"\",\"msg\":\"" + msg + "\"}";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(s);
	}
	@RequestMapping("deleteTeachers")
	public void deleteTeacher(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		String s = "", msg = "", jsonString = "[";
		//以数组形式从前端获取选取的checkbox的value
		//jsonString 将获取的value写成json字符串形式
		String[] array = httpServletRequest.getParameterValues("array[]");
		for(int i = 0; i < array.length; i++) {
			if(i > 0) {
				jsonString = jsonString + "," + array[i];
			}else {
				jsonString = jsonString + array[i];
			}
		}
		jsonString += "]";
		//将json字符串转换成list对象
		List<Teacher> listTeachers = JSONArray.parseArray(jsonString, Teacher.class);
		boolean deleteFlag = false;
		for(Teacher t : listTeachers) {
			//在这里判断list<>中每一个对象是否可以进行删除
			deleteFlag = teacherCanDeleted(t);
			//返回false就跳出循环
			if(!deleteFlag) {
				msg = "id为" + t.getId() + "的教师无法删除。可能在排课或是班级中存在";
				break;
			}
		}
		if(!deleteFlag) {
			s = "{\"success\":\"" + deleteFlag +"\",\"msg\":\"" + msg + "\"}";
		}else {
			try {
				int flag = teacherService.deleteMany(listTeachers);
				if(flag == 0) {
					msg = "删除失败!";
					s = "{\"success\":\"" + "false" +"\",\"msg\":\"" + msg + "\"}";
				}else {
					msg = "删除成功!";
					s = "{\"success\":\"" + deleteFlag +"\",\"msg\":\"" + msg + "\"}";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(s);
	}
	@ResponseBody
	@RequestMapping("_teacher")
//	public Object Manager_TeacherMana(HttpSession session, HttpServletRequest request) {
	public void Manager_TeacherMana(HttpSession session, HttpServletResponse httpServletResponse) throws IOException {
		Admin user = (Admin) session.getAttribute("user");
		List<Teacher> listTeachers = new ArrayList<Teacher>();
		List<Department> listDepartments = new ArrayList<Department>();
		JSONObject jsonObj = new JSONObject();
		//System.out.println(user != null);
		if(user != null) {
			listTeachers = teacherService.getAll();
			listDepartments = departmentService.get();
		}
		jsonObj.put("listTeachers", listTeachers);
		jsonObj.put("listDepartments", listDepartments);
		String jsonString = JSONObject.toJSONString(jsonObj);
		
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(jsonString);
	}
	@ResponseBody
	@RequestMapping("getTeachers")
	public Object getTeachers(HttpSession session, HttpServletRequest request) {
		long id = 0;
		int deid = 0;
		String name = "", deName = "";
		Admin user = (Admin) session.getAttribute("user");
		List<Teacher> listTeachers = new ArrayList<Teacher>();
	//	System.out.println(user != null);
		int start = Integer.valueOf(request.getParameter("pageNumber"));
		int size = Integer.valueOf(request.getParameter("Size"));
		if(user != null) {
			if(request.getParameter("id") != null && request.getParameter("id") != "" && !request.getParameter("id").equals("")) {
				id = Long.valueOf(request.getParameter("id"));
			}
			if(request.getParameter("name") != null && request.getParameter("name") != "" && !request.getParameter("name").equals("")) {
				name = request.getParameter("name");
			}
			if(request.getParameter("dename") != null && request.getParameter("dename") != "" && !request.getParameter("dename").equals("")) {
				deName = request.getParameter("dename");
				if(deName.trim().equals("所有学院")) {
					deid = 0;
				} else{
					deid = (int)departmentService.getIdByName(deName);
				}
			}
			listTeachers = teacherService.getBySelect(id, name, deid, (start - 1) * 10, size);
		}
		return listTeachers;
	}
	@ResponseBody
	@RequestMapping("totlePageTeacher")
	public Object getTeacherTotlePage(HttpSession session, HttpServletRequest request) {
		long id = 0;
		int deid = 0;
		String name = "", deName = "";
		Admin user = (Admin) session.getAttribute("user");
		if(user != null) {
			if(request.getParameter("id") != null && request.getParameter("id") != "" && !request.getParameter("id").equals("")) {
				id = Long.valueOf(request.getParameter("id"));
			}
			if(request.getParameter("name") != null && request.getParameter("name") != "" && !request.getParameter("name").equals("")) {
				name = request.getParameter("name");
			}
			if(request.getParameter("dename") != null && request.getParameter("dename") != "" && !request.getParameter("dename").equals("")) {
				deName = request.getParameter("dename");
				if(deName.trim().equals("所有学院")) {
					deid = 0;
				} else{
					deid = (int)departmentService.getIdByName(deName);
				}
			}
		}
		int totle = teacherService.totleCount(id, name, deid);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totle", totle);
		return jsonObj;
	}
	@RequestMapping("updateTeacher")
	public void updateTeacher(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		String s = "", msg = "";
		Teacher teacher = getTeacherFormData(httpServletRequest);
		TeacherVo teacherVo = formTeacherDataPro(teacher, GlobalName.method_UPDATE);
		if(!teacherVo.isFlag()) {
			s = "{\"success\":\"" + teacherVo.isFlag() +"\",\"msg\":\"" + teacherVo.getMsg() + "\"}";
		}else {
			try {
				int flag = teacherService.update(teacherVo.getTeacher());
				if(flag == 0) {
					msg = "修改失败!";
					s = "{\"success\":\"" + "false" +"\",\"msg\":\"" + msg + "\"}";
				}else {
					msg = "修改成功!";
					s = "{\"success\":\"" + teacherVo.isFlag() +"\",\"msg\":\"" + msg + "\"}";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(s);
	}
	@ResponseBody
	@RequestMapping("_teacherInfo")
	public void _teacherInfo(HttpSession session, HttpServletResponse httpServletResponse) throws IOException {
		TeacherUser user = (TeacherUser) session.getAttribute("user");
		JSONObject jsonObj = new JSONObject();
		if(user != null) {
			jsonObj.put("teacherInfo", user.getUsername());
		}
		String jsonString = JSONObject.toJSONString(jsonObj);
		
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(jsonString);
	}
	@RequestMapping("addTeacherExcel")
	public void addTeacherExcel(@RequestParam("file") MultipartFile file,HttpServletResponse httpServletResponse) throws IOException {
		boolean rowFlag = true;
		String msg = "";
		List<Teacher> listTeachers = new ArrayList<Teacher>();
		try {
			InputStream inp = file.getInputStream();
			Workbook wb = WorkbookFactory.create(inp);
			// 2.读取页脚sheet
			Sheet sheetAt = wb.getSheetAt(0);
			for(Row row : sheetAt) {
				if(row.getRowNum() == 0) continue;
				if( ExcelValueBean.isBlankRow(row, 7) ) continue;
				if(!rowFlag) break;
				TeacherVo teacherVo = new TeacherVo();
				teacherVo = excelTeacherDataPro(row, rowFlag);
				rowFlag = teacherVo.isFlag();
				msg = teacherVo.getMsg();
				Teacher teacher = teacherVo.getTeacher();
				listTeachers.add(teacher);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String s = "";
		if(!rowFlag) {
			s = "{\"success\":\"" + rowFlag +"\",\"msg\":\"" + msg + "\"}";
		}else {
			try {
				int flag = teacherService.addMany(listTeachers);
				if(flag == 0) {
					msg = "插入数据库失败!";
					s = "{\"success\":\"" + "false" +"\",\"msg\":\"" + msg + "\"}";
				}else {
					msg = "添加成功!";
					s = "{\"success\":\"" + rowFlag +"\",\"msg\":\"" + msg + "\"}";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println(s);
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(s);
	}
	//获取表单数据
	private Teacher getTeacherFormData(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
		httpServletRequest.setCharacterEncoding("UTF-8");
		Teacher teacher = new Teacher();
		Sex sex = new Sex();
		Department deid = new Department();
		
		teacher.setId( Long.valueOf( httpServletRequest.getParameter("tid") ) );
		teacher.setName( httpServletRequest.getParameter("tname") );
		sex.setSex( httpServletRequest.getParameter("sex") );
		teacher.setSex(sex);
		deid.setName( httpServletRequest.getParameter("department") );
		teacher.setDeid(deid);
		teacher.setTitle( httpServletRequest.getParameter("title") );
		String tel = httpServletRequest.getParameter("tel");
		if(tel == null || tel.equals("")) {
			
		}else {
			teacher.setTel( Long.valueOf(tel) );
		}
		
		teacher.setE_mail( httpServletRequest.getParameter("e_mail") );
		return teacher;	
	}
	/**
	 * @param teacher
	 * @return boolean
	 * 
	 */
	private boolean teacherCanDeleted(Teacher teacher) {
		//判断teacher是否能被删除。1.判断是否为班主任2.是否在排课中存在
		if(classesService.getCountByTid(teacher.getId()) > 0) {
			return false;
		}else {
			if(coursePlanService.getCountByTid(teacher.getId()) > 0) {
				return false;
			}else {
				return true;
			}
		}
	}
	private int isTrueSex(String sex) {
		int num = 0;
		if(sex.equals("男")) num = 1;
		if(sex.equals("女")) num = 2;
		return num;
	}
	//获取excel表格的数据
	private TeacherVo excelTeacherDataPro(Row row, boolean rowFlag) {
		TeacherVo teacherVo = new TeacherVo();
		String msg = "";
		String name = "", deName = "", sex = "", title = "", e_mail = "";
		long id = 0, tel = 0;
		Cell cell = null;
		GetExcelValue gev = new GetExcelValue();
		for(int i = 0; i < 7; i++) {
			cell = row.getCell(i);
			if(i == 0) {
				//读取id 类型long 不可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_LONG, GlobalName.Necessary);
				if( !gev.isResult() ) {
					rowFlag = false;
					msg = "教师编号无法读取！";
				}else {
					id = gev.getLongValue();
				}
			}else if(i == 1) {
				//读取excel第二列：姓名 类型String 不可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_STRING, GlobalName.Necessary);
				if( !gev.isResult() ) {
					rowFlag = false;
					msg = "教师姓名无法读取！";
				}else {
					name = gev.getStringValue();
				}
			}else if (i == 2) {
				//读取excel第三列：性别 类型String 不可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_STRING, GlobalName.Necessary);
				if( !gev.isResult() ) {
					rowFlag = false;
					msg = "教师性别无法读取！";
				}else {
					sex = gev.getStringValue();
				}
			}else if (i == 3) {
				//读取excel第四列：所属院系 类型 String 不可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_STRING, GlobalName.Necessary);
				if( !gev.isResult() ) {
					rowFlag = false;
					msg = "教师所属院系无法读取！";
				}else {
					deName = gev.getStringValue();
				}
			}else if (i == 4) {
				//读取excel第五列：职称 类型String 可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_STRING, GlobalName.unNecessary);
				if( !gev.isResult() ) {
					
				}else {
					title = gev.getStringValue();
				}
			}else if (i == 5) {
				//读取excel第六列：联系电话 类型long 可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_LONG, GlobalName.unNecessary);
				if( !gev.isResult() ) {
					
				}else {
					tel = gev.getLongValue();
				}
			}else {
				//第七列：邮箱 类型String 可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_STRING, GlobalName.unNecessary);
				if( !gev.isResult() ) {
					
				}else {
					e_mail = gev.getStringValue();
				}
			}
			if(!rowFlag) {
				msg = "第" + row.getRowNum() + "行数据出错：" + msg;
				break;
			}
		}
		if(!rowFlag) {
			teacherVo.setFlag(rowFlag);
			teacherVo.setMsg(msg);
		}else {
			Sex s = new Sex();
			s.setSex(sex);
			Department de = new Department();
			de.setName(deName);
			Teacher t = new Teacher(id, name, s, de, title, tel, e_mail);
			teacherVo = formTeacherDataPro(t, GlobalName.method_INSERT);
			if(!teacherVo.isFlag()) {
				msg = "第" + row.getRowNum() + "行数据：";
				teacherVo.setMsg(msg + teacherVo.getMsg());
			}
		}
		return teacherVo;
	}
	
	// 处理表单数据，以及excel获取后的数据 
	private TeacherVo formTeacherDataPro(Teacher t, int method_way) {
		TeacherVo teacherVo = new TeacherVo();
		long id = t.getId(), tel = t.getTel();
		String name = t.getName(), sex = t.getSex().getSex(), deName = t.getDeid().getName(), title = t.getTitle(), e_mail = t.getE_mail();
		String msg = "";
		boolean flag = true;
		Teacher teacher = new Teacher();
		if(method_way == GlobalName.method_INSERT) {
			if(teacherService.getCountById(id) == 1) {
				flag = false;
				msg += "教师编号重复";
			}else {
				teacher.setId(id);
			}
		}else if(method_way == GlobalName.method_UPDATE) {
			teacher.setId(id);
		}
		if(isTrueSex(sex) == 0) {
			flag = false;
			msg += "性别输入错误";
		}else {
			Sex tsex = new Sex();
			tsex.setNum(isTrueSex(sex));
			teacher.setSex(tsex);
		}
		Integer deId = departmentService.getIdByName(deName);
		if(deId == null) {
			flag = false;
			msg += "院系名错误";
		}else {
			Department deid = new Department();
			deid.setId( deId.intValue() );
			teacher.setDeid(deid);
		}
		if(flag) {
			teacher.setName(name);
			teacher.setTitle(title);
			teacher.setTel(tel);
			teacher.setE_mail(e_mail);
			teacherVo.setTeacher(teacher);
		}
		teacherVo.setFlag(flag);
		teacherVo.setMsg(msg);
		return teacherVo;
	}
}
