package com.gp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

import com.gp.model.pojo.Course;
import com.gp.model.pojo.CoursePlan;
import com.gp.model.pojo.Teacher;
import com.gp.model.vo.CoursePlanVo;
import com.gp.model.vo.SchoolTerm;
import com.gp.service.CoursePlanService;
import com.gp.service.CourseService;
import com.gp.service.TeacherService;

@Controller
public class CoursePlanController {
	@Autowired
	CoursePlanService coursePlanService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	CourseService courseService;
	
	@RequestMapping("addCoursePlanExcel")
	public void addCoursePlanExcel(@RequestParam("file") MultipartFile file,HttpServletResponse httpServletResponse) throws IOException {
		boolean rowFlag = true;
		String msg = "";
		List<CoursePlan> coursePlans = new ArrayList<CoursePlan>();
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
				CoursePlanVo coursePlanVo = new CoursePlanVo();
				coursePlanVo = excelCoursePlanDataPro(row, rowFlag);
				rowFlag = coursePlanVo.isFlag();
				CoursePlan coursePlan = coursePlanVo.getCoursePlan();
				msg = coursePlanVo.getMsg();
				//结束一行数据的读取
				coursePlans.add(coursePlan);
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
				int flag = coursePlanService.addMany(coursePlans);
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
	
	private CoursePlanVo excelCoursePlanDataPro(Row row, boolean rowFlag) {
		CoursePlanVo coursePlanVo = new CoursePlanVo();
		String msg = "";
		String courseName = "", timeAPlace = "", startTerm = "";
		int startYear = 0;
		long teacherId = 0;
		Cell cell = null;
		for(int i = 0; i < 5; i++) {
			cell = row.getCell(i);
			if(i == 0) {
				//读取excel第一列：课程名称
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					courseName = cell.getStringCellValue();
				}else {
					rowFlag = false;
					msg = "课程名称不能为空";
				}
			}else if (i == 1) {
				//读取excel第二列：授课教师
				if(cell != null && cell.getCellType() == CellType.NUMERIC ) {
					teacherId = (long)cell.getNumericCellValue();
				}else {
					rowFlag = false;
					msg = "授课编号姓名不能为空";
				}
			}else if (i == 2) {
				//读取excel第三列：上课年份
				if(cell != null && cell.getCellType() == CellType.NUMERIC) {
					startYear = (int)cell.getNumericCellValue();
				}else {
					rowFlag = false;
					msg = "上课年份不能为空且仅能输入数字(年份)";
				}
			}else if (i == 3) {
				//读取excel第四列：上课学期
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					startTerm = cell.getStringCellValue();
				}else {
					rowFlag = false;
					msg = "上课学期不能为空且仅能输入[上学期，下学期]";
				}
			}else {
				//第五列：上课时间以及地点
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					timeAPlace = cell.getStringCellValue();
				}else {
					rowFlag = false;
					msg = "上课时间，地点不能为空";
				}
			}
			if(!rowFlag) {
				msg = "第" + row.getRowNum() + "行数据出错：" + msg;
				break;
			}
		}
		if(!rowFlag) {
			coursePlanVo.setFlag(rowFlag);
			coursePlanVo.setMsg(msg);
		}else {
			coursePlanVo = formCoursePlanDataPro(courseName, teacherId, startYear, startTerm, timeAPlace);
			if(!coursePlanVo.isFlag()) {
				msg = "第" + row.getRowNum() + "行数据：";
				coursePlanVo.setMsg(msg + coursePlanVo.getMsg());
			}
		}
		return coursePlanVo;
	}
	private CoursePlanVo formCoursePlanDataPro(String courseName, long teacherId, int startYear, String termName, String timeAPlace) {
		CoursePlanVo coursePlanVo = new CoursePlanVo();
		String msg = "";
		boolean flag = true;
		CoursePlan coursePlan = new CoursePlan();
		Long courseId = courseService.getIdByName(courseName);
		if(courseId == null) {
			flag = false;
			msg += "课程不存在";
		}else {
			Course cid = new Course();
			cid.setId(courseId.longValue());
			coursePlan.setCid(cid);
		}
		if(teacherService.getCountById(teacherId) == 0) {
			flag = false;
			msg += "教师不存在";
		}else {
			Teacher tid = new Teacher();
			tid.setId(teacherId);
			coursePlan.setTid(tid);
		}
		if(startYear < 999) {
			flag = false;
			msg += "开课学年格式错误，只能输入4位数字";
		}else {
			coursePlan.setStartYear(startYear);
		}
		int termNum = isTrueTerm(termName);
		if(termNum == 0) {
			flag = false;
			msg += "开课学期错误[上学期，下学期];";
		}else {
			SchoolTerm startTerm = new SchoolTerm();
			startTerm.setTermNum(termNum);
			coursePlan.setStartTerm(startTerm);
		}
		if(flag ) {
			coursePlan.setTimeAPlace(timeAPlace);
			if(coursePlanService.getByAll(coursePlan) == 1) {
				flag = false;
				msg += "存在相同的课程安排";
			}else {
				coursePlanVo.setCoursePlan(coursePlan);
			}
		}
		coursePlanVo.setFlag(flag);
		coursePlanVo.setMsg(msg);
		return coursePlanVo;
	}
	private int isTrueTerm(String term) {
		int termNum = 0;
		if(term.equals("上学期")) termNum = 1;
		if(term.equals("下学期")) termNum = 2;
		return termNum;
	}
}
