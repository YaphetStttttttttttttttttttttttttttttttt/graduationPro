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
import org.apache.poi.ss.usermodel.CellType;
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
import com.gp.bean.GlobalName;
import com.gp.model.pojo.Admin;
import com.gp.model.pojo.Classes;
import com.gp.model.pojo.Department;
import com.gp.model.pojo.Student;
import com.gp.model.pojo.StudentUser;
import com.gp.model.pojo.Teacher;
import com.gp.model.vo.Sex;
import com.gp.model.vo.StudentVo;
import com.gp.service.ClassesService;
import com.gp.service.SelectCourseService;
import com.gp.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	StudentService studentService;
	@Autowired
	ClassesService classesService;
	@Autowired
	SelectCourseService selectCourseSerivce;
	
	@RequestMapping("addStudent")
	public void addStudent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		Student student = getStudentFormData(httpServletRequest);
		//学号，姓名，性别，班级名，年龄，联系电话，家庭地址，电子邮箱
		StudentVo studentVo = formStudentDataPro(student, GlobalName.method_INSERT);
		String s = "", msg = "";
		if(!studentVo.isFlag()) {
			s = "{\"success\":\"" + studentVo.isFlag() +"\",\"msg\":\"" + studentVo.getMsg() + "\"}";
		}else {
			try {
				int flag = studentService.add(studentVo.getStudent());
				if(flag == 0) {
					msg = "插入数据库失败!";
					s = "{\"success\":\"" + "false" +"\",\"msg\":\"" + msg + "\"}";
				}else {
					msg = "添加成功!";
					s = "{\"success\":\"" + studentVo.isFlag() +"\",\"msg\":\"" + msg + "\"}";
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
	@RequestMapping("updateStudent")
	public void updateTeacher(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		String s = "", msg = "";
		Student student = getStudentFormData(httpServletRequest);
		StudentVo studentVo = formStudentDataPro(student, GlobalName.method_UPDATE);
		if(!studentVo.isFlag()) {
			s = "{\"success\":\"" + studentVo.isFlag() +"\",\"msg\":\"" + studentVo.getMsg() + "\"}";
		}else {
			try {
				int flag = studentService.update(studentVo.getStudent());
				if(flag == 0) {
					msg = "修改失败!";
					s = "{\"success\":\"" + "false" +"\",\"msg\":\"" + msg + "\"}";
				}else {
					msg = "修改成功!";
					s = "{\"success\":\"" + studentVo.isFlag() +"\",\"msg\":\"" + msg + "\"}";
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
	@RequestMapping("_studentInfo")
	public void _studentInfo(HttpSession session, HttpServletResponse httpServletResponse) throws IOException {
		StudentUser user = (StudentUser) session.getAttribute("user");
		JSONObject jsonObj = new JSONObject();
		if(user != null) {
			jsonObj.put("stuInfo", user.getUsername());
		}
		String jsonString = JSONObject.toJSONString(jsonObj);
		
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(jsonString);
	}
	@ResponseBody
	@RequestMapping("_student")
	public void Manager_TeacherMana(HttpSession session, HttpServletResponse httpServletResponse, HttpServletRequest request) throws IOException {
		long id = 0;
		int cid = 0, start = 1;
		String name = "", classes = "";
		Admin user = (Admin) session.getAttribute("user");
		List<Student> listStudents = new ArrayList<Student>();
		JSONObject jsonObj = new JSONObject();
		if(user != null) {
			if(request.getParameter("id") != null && request.getParameter("id") != "" && !request.getParameter("id").equals("")) {
				id = Long.valueOf(request.getParameter("id"));
			}
			if(request.getParameter("name") != null && request.getParameter("name") != "" && !request.getParameter("name").equals("")) {
				name = request.getParameter("name");
			}
			if(request.getParameter("classes") != null && request.getParameter("classes") != "" && !request.getParameter("classes").equals("")) {
				classes = request.getParameter("classes");
				if(classesService.getIdByName(classes) != null) {
					cid = (int)classesService.getIdByName(classes);
				}
			}
			if(request.getParameter("pageNumber") != null && request.getParameter("pageNumber") != "" && !request.getParameter("pageNumber").equals("")) {
				start = Integer.valueOf(request.getParameter("pageNumber"));
			}
			listStudents = studentService.getBySelect(id, name, cid, (start - 1) * 10);
		}
		jsonObj.put("listStudents", listStudents);
		String jsonString = JSONObject.toJSONString(jsonObj);
		
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(jsonString);
	}
	@ResponseBody
	@RequestMapping("totlePageStudent")
	public Object getStudentTotlePage(HttpSession session, HttpServletRequest request) {
		long id = 0;
		int cid = 0;
		String name = "", classes = "";
		Admin user = (Admin) session.getAttribute("user");
		if(user != null) {
			if(request.getParameter("id") != null && request.getParameter("id") != "" && !request.getParameter("id").equals("")) {
				id = Long.valueOf(request.getParameter("id"));
			}
			if(request.getParameter("name") != null && request.getParameter("name") != "" && !request.getParameter("name").equals("")) {
				name = request.getParameter("name");
			}
			if(request.getParameter("classes") != null && request.getParameter("classes") != "" && !request.getParameter("classes").equals("")) {
				classes = request.getParameter("classes");
				if(classesService.getIdByName(classes) != null) {
					cid = (int)classesService.getIdByName(classes);
				}
			}
		}
		int totle = studentService.totleCount(id, name, cid);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totle", totle);
		return jsonObj;
	}
	@ResponseBody
	@RequestMapping("getStudents")
	public void getStudent(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
	//	long cpid = session.getAttribute("cpid");
		long cpid = Long.valueOf(request.getParameter("cpid"));
		JSONObject jsonObj = new JSONObject();
		List<Student> listStudents = studentService.getStudent(cpid);
		jsonObj.put("listStudents", listStudents);
		String jsonString = JSONObject.toJSONString(jsonObj);
		
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(jsonString);
	}
	@RequestMapping("addStudentExcel")
	public void addStudentExcel(@RequestParam("file") MultipartFile file,HttpServletResponse httpServletResponse) throws IOException {
		boolean rowFlag = true;
		String msg = "";
		List<Student> listStudents = new ArrayList<Student>();
		try {
			InputStream inp = file.getInputStream();
			Workbook wb = WorkbookFactory.create(inp);
			// 2.读取页脚sheet
			Sheet sheetAt = wb.getSheetAt(0);
			for(Row row : sheetAt) {
				if(row.getRowNum() == 0) continue;
				if(!rowFlag) break;
				StudentVo studentVo = new StudentVo();
				studentVo = excelStudentDataPro(row, rowFlag);
				rowFlag = studentVo.isFlag();
				msg = studentVo.getMsg();
				Student student = studentVo.getStudent();
				listStudents.add(student);
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
				int flag = studentService.addMany(listStudents);
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
	@RequestMapping("deleteStudent")
	public void deleteStudent(@RequestParam long id, HttpServletResponse httpServletResponse) throws IOException {
		String s ="", msg = "";
		Student student = new Student();
		student.setId(id);
		boolean deleteFlag = studentCanDeleted(student);
		if(!deleteFlag) {
			msg = "学生无法删除。在选课表中存在";
			s = "{\"success\":\"" + deleteFlag +"\",\"msg\":\"" + msg + "\"}";
		}else {
			try {
				int flag = studentService.delete(student);
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
	@RequestMapping("deleteStudents")
	public void deleteStudents(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
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
		List<Student> listStudents = JSONArray.parseArray(jsonString, Student.class);
		boolean deleteFlag = false;
		for(Student student : listStudents) {
			//在这里判断list<>中每一个对象是否可以进行删除
			deleteFlag = studentCanDeleted(student);
			//返回false就跳出循环
			if(!deleteFlag) {
				msg = "id为" + student.getId() + "的学生无法删除。在选课表中存在";
				break;
			}
		}
		if(!deleteFlag) {
			s = "{\"success\":\"" + deleteFlag +"\",\"msg\":\"" + msg + "\"}";
		}else {
			try {
				int flag = studentService.deleteMany(listStudents);
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
	private boolean studentCanDeleted(Student student) {
		//判断teacher是否能被删除。1.判断是否为班主任2.是否在排课中存在
		if(selectCourseSerivce.getCountBySid(student.getId()) > 0) {
			return false;
		}else {
			return true;
		}
	}
	private Student getStudentFormData(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
		httpServletRequest.setCharacterEncoding("UTF-8");
		Student student = new Student();
		Sex sex = new Sex();
		Classes cid = new Classes();
		student.setId( Long.valueOf( httpServletRequest.getParameter("sid") ) );
		student.setName( httpServletRequest.getParameter("sname") );
		cid.setName(httpServletRequest.getParameter("classes"));
		student.setCid(cid);
		sex.setSex( httpServletRequest.getParameter("sex") );
		student.setSex(sex);
		student.setAge( Integer.valueOf( httpServletRequest.getParameter("age") ) );
		student.setTel( Long.valueOf(httpServletRequest.getParameter("tel") ) );
		student.setAddress( httpServletRequest.getParameter("address") );
		student.setE_mail( httpServletRequest.getParameter("e_mail") );
		return student;	
	}
	private int isTrueSex(String sex) {
		int num = 0;
		if(sex.equals("男")) num = 1;
		if(sex.equals("女")) num = 2;
		return num;
	}
	private StudentVo excelStudentDataPro(Row row, boolean rowFlag) {
		StudentVo studentVo = new StudentVo();
		String name = "", cName = "", sex = "", address = "", e_mail = "", msg = "";
		long id = 0, tel = 0;
		int age = 0;
		Cell cell = null;
		for(int i = 0; i < 8; i++) {
			cell = row.getCell(i);
			if(i == 0) {
				//读取excel第一列：学号
				if(cell != null && cell.getCellType() == CellType.NUMERIC) {
					id = (long)cell.getNumericCellValue();
				}else {
					rowFlag = false;
					msg = "学号不能为空";
				}
			}else if (i == 1) {
				//读取excel第二列：姓名
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					name = cell.getStringCellValue();
				}else {
					rowFlag = false;
					msg = "学生姓名不能为空";
				}
			}else if (i == 2) {
				//读取excel第三列：性别
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					sex = cell.getStringCellValue();
				}else {
					rowFlag = false;
					msg = "输入学生性别[男/女]";
				}
			}else if (i == 3) {
				//读取excel第四列：班级
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					cName = cell.getStringCellValue();
				}else {
					rowFlag = false;
					msg = "输入正确的学生班级";
				}
			}else if (i == 4) {
				//读取excel第五列：年龄
				if(cell != null && cell.getCellType() == CellType.NUMERIC) {
					age = (int)cell.getNumericCellValue();
				}
			}else if (i == 5) {
				//读取excel第六列：联系电话
				if(cell != null && cell.getCellType() == CellType.NUMERIC ) {
					tel = (long)cell.getNumericCellValue();
				}
			}else if (i == 6) {
				//读取excel第七列：家庭地址
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					address = cell.getStringCellValue();
				}
			}else {
				//第八列：邮箱
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					e_mail = cell.getStringCellValue();
				}
			}
			if(!rowFlag) {
				msg = "第" + row.getRowNum() + "行数据出错：" + msg;
				break;
			}
		}
		if(!rowFlag) {
			studentVo.setFlag(rowFlag);
			studentVo.setMsg(msg);
		}else {
			Classes cid = new Classes();
			cid.setName(cName);
			Sex sexNum = new Sex();
			sexNum.setSex(sex);
			Student student = new Student(id, name, cid, sexNum, age, tel, address, e_mail);
			studentVo = formStudentDataPro(student, GlobalName.method_INSERT);
			if(!studentVo.isFlag()) {
				msg = "第" + row.getRowNum() + "行数据：";
				studentVo.setMsg(msg + studentVo.getMsg());
			}
		}
		return studentVo;
	}
	//学号，姓名，性别，班级名，年龄，联系电话，家庭地址，电子邮箱
	private StudentVo formStudentDataPro(Student s, int method_way) {
		long id = s.getId(), tel = s.getTel();
		String name = s.getName(), sex = s.getSex().getSex(), e_mail = s.getE_mail(), address = s.getAddress(), cName = s.getCid().getName();
		int age = s.getAge();
		StudentVo studentVo = new StudentVo();
		String msg = "";
		boolean flag = true;
		Student student = new Student();
		if(method_way == GlobalName.method_INSERT) {
			if(studentService.getById(id) == 1) {
				flag = false;
				msg += "学号重复";
			}else {
				student.setId(id);
			}
		}else if(method_way == GlobalName.method_UPDATE) {
			student.setId(id);
		}
		if(isTrueSex(sex) == 0) {
			flag = false;
			msg += "性别输入错误";
		}else {
			Sex tsex = new Sex();
			tsex.setNum(isTrueSex(sex));
			student.setSex(tsex);
		}
		Integer cId = classesService.getIdByName(cName);
		if(cId == null) {
			flag = false;
			msg += "班级名错误";
		}else {
			Classes classes = new Classes();
			classes.setId( cId.intValue() );
			student.setCid(classes);
		}
		if(flag) {
			student.setName(name);
			student.setAge(age);
			student.setTel(tel);
			student.setE_mail(e_mail);
			student.setAddress(address);
			studentVo.setStudent(student);
		}
		studentVo.setFlag(flag);
		studentVo.setMsg(msg);
		return studentVo;
	}
}
