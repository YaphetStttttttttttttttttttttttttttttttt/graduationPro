package com.gp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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

import com.gp.model.pojo.Department;
import com.gp.model.pojo.Teacher;
import com.gp.model.vo.Sex;
import com.gp.model.vo.TeacherVo;
import com.gp.service.DepartmentService;
import com.gp.service.TeacherService;

@Controller
public class TeacherController {
	@Autowired
	TeacherService teacherService;
	@Autowired
	DepartmentService departmentService;
	
	
	@RequestMapping("addTeacher")
	public void addTeacher(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		Teacher teacher = getTeacherFormData(httpServletRequest);
		TeacherVo teacherVo = formTeacherDataPro(teacher.getId(), teacher.getName(), teacher.getSex().getSex(), 
				teacher.getDeid().getName(), teacher.getTitle(), teacher.getTel(), teacher.getE_mail());
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
		System.out.println(s);
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(s);
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
	private Teacher getTeacherFormData(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
		httpServletRequest.setCharacterEncoding("UTF-8");
		Teacher teacher = new Teacher();
		Sex sex = new Sex();
		Department deid = new Department();
		System.out.println(httpServletRequest.getParameter("tid"));
		System.out.println(httpServletRequest.getParameter("tname"));
		teacher.setId( Long.valueOf( httpServletRequest.getParameter("tid") ) );
		teacher.setName( httpServletRequest.getParameter("tname") );
		sex.setSex( httpServletRequest.getParameter("sex") );
		teacher.setSex(sex);
		deid.setName( httpServletRequest.getParameter("department") );
		teacher.setDeid(deid);
		teacher.setTitle( httpServletRequest.getParameter("title") );
		String tel = httpServletRequest.getParameter("tel");
		if(tel ==null || tel.equals("")) {
			
		}else {
			teacher.setTel( Long.valueOf(tel) );
		}
		
		teacher.setE_mail( httpServletRequest.getParameter("e_mail") );
		return teacher;	
	}
	private int isTrueSex(String sex) {
		int num = 0;
		if(sex.equals("男")) num = 1;
		if(sex.equals("女")) num = 2;
		return num;
	}
	private TeacherVo excelTeacherDataPro(Row row, boolean rowFlag) {
		TeacherVo teacherVo = new TeacherVo();
		String msg = "";
		String name = "", deName = "", sex = "", title = "", e_mail = "";
		long id = 0, tel = 0;
		Cell cell = null;
		for(int i = 0; i < 7; i++) {
			cell = row.getCell(i);
			if(i == 0) {
				//读取excel第一列：教师ID
				if(cell != null && cell.getCellType() == CellType.NUMERIC) {
					id = (long)cell.getNumericCellValue();
				}else {
					rowFlag = false;
					msg = "教师编号不能为空";
				}
			}else if (i == 1) {
				//读取excel第二列：姓名
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					name = cell.getStringCellValue();
				}else {
					rowFlag = false;
					msg = "教师姓名不能为空";
				}
			}else if (i == 2) {
				//读取excel第三列：性别
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					sex = cell.getStringCellValue();
				}else {
					rowFlag = false;
					msg = "输入教师性别[男/女]";
				}
			}else if (i == 3) {
				//读取excel第四列：所属院系
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					deName = cell.getStringCellValue();
				}else {
					rowFlag = false;
					msg = "输入正确的教师所属院系";
				}
			}else if (i == 4) {
				//读取excel第五列：职称
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					title = cell.getStringCellValue();
				}
			}else if (i == 5) {
				//读取excel第六列：联系电话
				if(cell != null && cell.getCellType() == CellType.NUMERIC ) {
					tel = (long)cell.getNumericCellValue();
				}
			}else {
				//第七列：邮箱
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
			teacherVo.setFlag(rowFlag);
			teacherVo.setMsg(msg);
		}else {
			teacherVo = formTeacherDataPro(id, name, sex, deName, title, tel, e_mail);
			if(!teacherVo.isFlag()) {
				msg = "第" + row.getRowNum() + "行数据：";
				teacherVo.setMsg(msg + teacherVo.getMsg());
			}
		}
		return teacherVo;
	}
	private TeacherVo formTeacherDataPro(long id, String name,String sex, String deName, String title, long tel, String e_mail) {
		TeacherVo teacherVo = new TeacherVo();
		String msg = "";
		boolean flag = true;
		Teacher teacher = new Teacher();
		if(teacherService.getCountById(id) == 1) {
			flag = false;
			msg += "教师编号重复";
		}else {
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
