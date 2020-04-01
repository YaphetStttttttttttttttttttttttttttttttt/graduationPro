package com.gp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gp.model.pojo.Course;
import com.gp.model.pojo.Department;
import com.gp.model.pojo.Discipline;
import com.gp.model.vo.CourseType;
import com.gp.model.vo.CourseVo;
import com.gp.model.vo.SchoolTerm;
import com.gp.service.CourseService;
import com.gp.service.DepartmentService;
import com.gp.service.DisciplineService;

@Controller
public class CourseController {
	@Autowired
	CourseService courseService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	DisciplineService disciplineService;
	/*
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
	}*/
	@RequestMapping("addCourses")
	public void addCourse(@RequestParam("file") MultipartFile file,HttpServletResponse httpServletResponse) throws IOException {
		boolean rowFlag = true;
		String msg = "";
		List<Course> listCourses = new ArrayList<Course>();
		try {
			// @RequestParam("file") MultipartFile file 是用来接收前端传递过来的文件
			// 1.创建workbook对象，读取整个文档
			InputStream inp = file.getInputStream();
			Workbook wb = WorkbookFactory.create(inp);
			// 2.读取页脚sheet
			Sheet sheetAt = wb.getSheetAt(0);
			// 3.循环读取某一行
			for (Row row : sheetAt) {
				// 4.读取每一行的单元格
				if(row.getRowNum() == 0) continue;
				if(!rowFlag) break;
				//处理这一行数据
				CourseVo courseVo = new CourseVo();
				courseVo = excelCourseDataPro(row, rowFlag);
				rowFlag = courseVo.isFlag();
				Course course = courseVo.getCourse();
				msg = courseVo.getMsg();
				//结束一行数据的读取
				listCourses.add(course);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = "";
		if(!rowFlag) {
			s = "{\"success\":\"" + rowFlag +"\",\"msg\":\"" + msg + "\"}";
		}else {
			try {
				int flag = courseService.addMany(listCourses);
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
	@RequestMapping("/{name}")
	public String commonHandler(@PathVariable String name) {
		return name;
	}
	public static boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        //整数返回true，否则返回false
        return pattern.matcher(str).matches();  
	}
	private int isTrueType(String type) {
		int typeNum = 0;
		switch (type) {
		case "必修课": typeNum = 1;break;
		case "选修课": typeNum = 2;break;
		case "限选课": typeNum = 3;break;
		case "任选课": typeNum = 4;break;
		case "公共课": typeNum = 5;break;
		default: break;
		}
		return typeNum;
	}
	private int isTrueTerm(String term) {
		int termNum = 0;
		if(term.equals("上学期")) termNum = 1;
		if(term.equals("下学期")) termNum = 2;
		return termNum;
	}
	private CourseVo formCourseDataPro(long id, String name, String typeName, 
			float credit, int cycle, int startSyear, String termName, String deName, String diName) {
		CourseVo courseVo = new CourseVo();
		String msg = "";
		boolean flag = true;
		Course course = new Course();
		if(courseService.getById(id) == 1) {
			flag = false;
			msg += "存在相同的id;";
		}else {
			course.setId(id);
		}//1.id
		if(courseService.getByName(name) == 1) {
			flag = false;
			msg += "存在相同的课程名;";
		}else {
			course.setName(name);
		}//2.name
		int typeNum = isTrueType(typeName);
		if(typeNum == 0) {
			flag = false;
			msg += "不存在的课程类型;";
		}else {
			CourseType type = new CourseType();
			type.setTypeNum(typeNum);
			course.setType(type);
		}//3.type	
		if(startSyear < 1 || startSyear > 4) {
			flag = false;
			msg += "开课学年错误[1,2,3,4];";
		}else {
			course.setStartSyear(startSyear);
		}//4.开课学年
		int termNum = isTrueTerm(termName);
		if(termNum == 0) {
			flag = false;
			msg += "开课学期错误[上学期，下学期];";
		}else {
			SchoolTerm startTerm = new SchoolTerm();
			startTerm.setTermNum(termNum);
			course.setStartTerm(startTerm);
		}//5.开课学期
		Integer deId = departmentService.getIdByName(deName);
		if(deId == null) {
			flag = false;
			msg += "院系名不存在;";
		}else {
			Department deid = new Department();
			deid.setId(deId.intValue());
			course.setDeid(deid);
		}
		if(!diName.equals("")) {
			Integer diId = disciplineService.getIdByName(diName);
			if(diId == null) {
				flag = false;
				msg += "专业名不存在;";
			}else {
				Discipline diid = new Discipline();
				diid.setId(diId.intValue());
				course.setDiid(diid);
			}
		}
		if(flag) {
			course.setCredit(credit);
			course.setCycle(cycle);
			courseVo.setCourse(course);
		}
		courseVo.setFlag(flag);
		courseVo.setMsg(msg);
		return courseVo;
	}
	private CourseVo excelCourseDataPro(Row row, boolean rowFlag) {
		CourseVo courseVo = new CourseVo();
		String msg = "";
		long id = 0;
		String name = "", typeName = "", termName = "", deName = "", diName = "";
		float credit = 0;
		int cycle = 0,startSyear = 0;
		Cell cell = null;
		for(int i =0; i < 9; i++) {
			cell = row.getCell(i);
			if(i == 0) {
				//读取的第一列数据为课程ID
				if(cell != null && cell.getCellType() == CellType.NUMERIC) {
					id = (long)cell.getNumericCellValue();		
				}else {
					//数据库里面这一条数据不能为空。此时为空，返回错误
					rowFlag = false;
					msg = "课程编号(id)不能为空";
				}
			}else if(i == 1) {
				//课程名称
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					name = cell.getStringCellValue();
				}else {
					//数据库里面这一条数据不能为空。此时为空，返回错误
					rowFlag = false;
					msg = "课程名称不能为空";
				}
			}else if(i == 2) {
				//课程类型
				//1:必修  2:选修 3:限选 4:任选 5:公选
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					typeName = cell.getStringCellValue();
				}else {
					//数据库里面这一条数据不能为空。此时为空，返回错误
					rowFlag = false;
					msg = "课程类型不能为空";
				}
			}else if(i == 3) {
				//学分
				if(cell != null && cell.getCellType() == CellType.NUMERIC) {
					credit = (float)cell.getNumericCellValue();
				}else {
					//数据库里面这一条数据不能为空。此时为空，返回错误
					rowFlag = false;
					msg = "课程学分不能为空";
				}
			}else if(i == 4) {
				//学时
				if(cell != null && cell.getCellType() == CellType.NUMERIC) {
					cycle = (int)cell.getNumericCellValue();
				}else {
					//数据库里面这一条数据不能为空。此时为空，返回错误
					rowFlag = false;
					msg = "课程学时不能为空";
				}
			}else if(i == 5) {
				//开课学年1,2,3,4
				if(cell != null && cell.getCellType() == CellType.NUMERIC) {
					startSyear = (int)cell.getNumericCellValue();
				}else {
					//数据库里面这一条数据不能为空。此时为空，返回错误
					rowFlag = false;
					msg = "课程开课学年不能为空";
				}
			}else if(i == 6) {
				//开课学期上，下
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					termName = cell.getStringCellValue();
				}else {
					//数据库里面这一条数据不能为空。此时为空，返回错误
					rowFlag = false;
					msg = "课程开课学期不能为空";
				}
			}else if(i == 7) {
				//课程所属院系
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("")) {
					deName = cell.getStringCellValue();
				}else {
					//数据库里面这一条数据不能为空。此时为空，返回错误
					rowFlag = false;
					msg = "课程所属院系不能为空";
				}
			}else {
				//课程所属专业
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					diName = cell.getStringCellValue();
				}
			}
			if(!rowFlag) {
				msg = "第" + row.getRowNum() + "行数据出错：" + msg;
				break;
			}
		}
		if(!rowFlag) {
			courseVo.setFlag(rowFlag);
			courseVo.setMsg(msg);
		}else {
			courseVo = formCourseDataPro(id, name, typeName, credit, cycle, startSyear, termName, deName, diName);
			if(!courseVo.isFlag()) {
				msg = "第" + row.getRowNum() + "行数据：";
				courseVo.setMsg(msg + courseVo.getMsg());
			}
		}
		return courseVo;
	}
}
