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
import com.gp.model.pojo.Discipline;
import com.gp.model.pojo.Teacher;
import com.gp.model.vo.ClassesVo;
import com.gp.service.ClassesService;
import com.gp.service.DisciplineService;
import com.gp.service.TeacherService;

@Controller
public class ClassesController {
	@Autowired
	ClassesService classesService;
	@Autowired
	DisciplineService disciplineService;
	@Autowired
	TeacherService teacherService;
	
	@RequestMapping("addClasses")
	public void addClass(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) throws IOException {
		
	}
	@RequestMapping("addClassesExcel")
	public void addClassExcel(@RequestParam("file") MultipartFile file,HttpServletResponse httpServletResponse) throws IOException {
		boolean rowFlag = true;
		String msg = "";
		List<Classes> listClasses = new ArrayList<Classes>();
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
				ClassesVo classesVo = new ClassesVo();
				classesVo = excelClassesDataPro(row, rowFlag);
				rowFlag = classesVo.isFlag();
				Classes c = classesVo.getClasses();
				msg = classesVo.getMsg();
				//结束一行数据的读取
				listClasses.add(c);
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
				int flag = classesService.addMany(listClasses);
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
	private ClassesVo excelClassesDataPro(Row row, boolean rowFlag) {
		ClassesVo classesVo = new ClassesVo();
		String msg = "";
		String className = "", diName = "", grade = "";
		int id = 0;
		long teacherId = 0;
		Cell cell = null;
		for(int i = 0; i < 5; i++) {
			cell = row.getCell(i);
			if(i == 0) {
				//读取excel第一列：班级ID
				if(cell != null && cell.getCellType() == CellType.NUMERIC) {
					id = (int)cell.getNumericCellValue();
				}else {
					rowFlag = false;
					msg = "班级id不能为空";
				}
			}else if (i == 1) {
				//读取excel第二列：班级名
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					className = cell.getStringCellValue();
				}else {
					rowFlag = false;
					msg = "班级名不能为空";
				}
			}else if (i == 2) {
				//读取excel第三列：所属专业
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					diName = cell.getStringCellValue();
				}else {
					rowFlag = false;
					msg = "班级所属专业不能为空";
				}
			}else if (i == 3) {
				//读取excel第四列：班主任ID
				if(cell != null && cell.getCellType() == CellType.NUMERIC ) {
					teacherId = (long)cell.getNumericCellValue();
				}
			}else {
				//第五列：年级
				if(cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().equals("") ) {
					grade = cell.getStringCellValue();
				}
			}
			if(!rowFlag) {
				msg = "第" + row.getRowNum() + "行数据出错：" + msg;
				break;
			}
		}
		if(!rowFlag) {
			classesVo.setFlag(rowFlag);
			classesVo.setMsg(msg);
		}else {
			classesVo = formClassesDatePro(id, className, diName, teacherId, grade);
			if(!classesVo.isFlag()) {
				msg = "第" + row.getRowNum() + "行数据：";
				classesVo.setMsg(msg + classesVo.getMsg());
			}
		}
		return classesVo;
	}
	private ClassesVo formClassesDatePro(int id, String name, String diName, long teacherId, String grade) {
		ClassesVo classesVo = new ClassesVo();
		String msg = "";
		boolean flag = true;
		Classes classes = new Classes();
		if(classesService.getByIdAName(id, name) == 1) {
			flag = false;
			msg += "已经存在相同的ID或班级名;";
		}else {
			classes.setId(id);
			classes.setName(name);
		}
		Integer diId = disciplineService.getIdByName(diName);
		if( diId == null) {
			flag = false;
			msg += "专业名不存在";
		}else {
			Discipline diid = new Discipline();
			diid.setId(diId.intValue());
			classes.setDiid(diid);
		}
		if(teacherId == 0) {
			Teacher tid = null;
			classes.setTid(tid);
		}else {
			if(teacherService.getCountById(teacherId) == 0) {
				flag = false;
				msg += "教师ID不存在";
			}else {
				Teacher tid = new Teacher();
				tid.setId(teacherId);
				classes.setTid(tid);
			}
		}
		if(flag) {
			classes.setGrade(grade);
			classesVo.setClasses(classes);
		}
		classesVo.setFlag(flag);
		classesVo.setMsg(msg);
		return classesVo;
	}
}
