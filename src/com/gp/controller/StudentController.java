package com.gp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartFile;

import com.gp.model.pojo.Classes;
import com.gp.model.pojo.Student;
import com.gp.model.vo.Sex;
import com.gp.model.vo.StudentVo;
import com.gp.service.ClassesService;
import com.gp.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	StudentService studentService;
	@Autowired
	ClassesService classesService;
	
	@RequestMapping("addStudent")
	public void addStudent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		Student student = getStudentFormData(httpServletRequest);
		//学号，姓名，性别，班级名，年龄，联系电话，家庭地址，电子邮箱
		StudentVo studentVo = formStudentDataPro(student.getId(), student.getName(), student.getSex().getSex(), student.getCid().getName(), 
				student.getAge(), student.getTel(), student.getAddress(), student.getE_mail() );
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
	private Student getStudentFormData(HttpServletRequest httpServletRequest) {
		Student student = new Student();
		Sex sex = new Sex();
		Classes classes = new Classes();
		student.setId( Long.valueOf( httpServletRequest.getParameter("id") ) );
		student.setName( httpServletRequest.getParameter("name") );
		classes.setName(httpServletRequest.getParameter("className"));
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
			studentVo = formStudentDataPro(id, name, sex, cName, age, tel, address, e_mail);
			if(!studentVo.isFlag()) {
				msg = "第" + row.getRowNum() + "行数据：";
				studentVo.setMsg(msg + studentVo.getMsg());
			}
		}
		return studentVo;
	}
	//学号，姓名，性别，班级名，年龄，联系电话，家庭地址，电子邮箱
	private StudentVo formStudentDataPro(long id, String name,String sex, String cName, int age, long tel, String address, String e_mail) {
		StudentVo studentVo = new StudentVo();
		String msg = "";
		boolean flag = true;
		Student student = new Student();
		if(studentService.getById(id) == 1) {
			flag = false;
			msg += "学号重复";
		}else {
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
