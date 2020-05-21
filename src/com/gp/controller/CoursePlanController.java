package com.gp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.gp.bean.ExcelValueBean;
import com.gp.bean.GlobalName;
import com.gp.model.pojo.Admin;
import com.gp.model.pojo.Classroom;
import com.gp.model.pojo.Course;
import com.gp.model.pojo.CoursePlan;
import com.gp.model.pojo.SelectCourse;
import com.gp.model.pojo.StudentUser;
import com.gp.model.pojo.Teacher;
import com.gp.model.pojo.TimeAndPlace;
import com.gp.model.vo.CoursePlanFormData;
import com.gp.model.vo.CoursePlanVo;
import com.gp.model.vo.GetExcelValue;
import com.gp.model.vo.SchoolTerm;
import com.gp.service.ClassroomService;
import com.gp.service.CoursePlanService;
import com.gp.service.CourseService;
import com.gp.service.SelectCourseService;
import com.gp.service.TeacherService;
import com.gp.service.TimeAndPlaceService;

@Controller
public class CoursePlanController {
	@Autowired
	CoursePlanService coursePlanService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	CourseService courseService;
	@Autowired
	ClassroomService classroomService;
	@Autowired
	TimeAndPlaceService tapService;
	@Autowired
	SelectCourseService scService;
	
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
			for (Row row : sheetAt) {
				// 4.读取每一行的单元格
				if(row.getRowNum() == 0) continue;
				//判断排课excel(共9列)这一行是否为空。为空就跳过这一行的读取
				if( ExcelValueBean.isBlankRow(row, 9) ) continue;
				//读取到错误数据跳出循环
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
		//System.out.println(s);
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(s);
	}
	/**
	 * @param row
	 * @param rowFlag
	 * @description 读取excel一行数据并处理
	 * @return
	 */
	private CoursePlanVo excelCoursePlanDataPro(Row row, boolean rowFlag) {
		CoursePlanVo coursePlanVo = new CoursePlanVo();
		String msg = "";
		String courseName = "", schoolTerm = "", weeks = "", timeAPlace1 = "", timeAPlace2 = "", timeAPlace3 = "";
		int schoolYear = 0;
		long teacherId = 0, coursePlanId = 0;
		Cell cell = null;
		GetExcelValue gev = new GetExcelValue();
		for(int i = 0; i < 9; i++) {
			cell = row.getCell(i);
			if(i == 0) {
				//读取第1列：排课编号
				//读取id 类型long 不可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_LONG, GlobalName.Necessary);
				if( !gev.isResult() ) {
					rowFlag = false;
					msg = "课程代码无法读取！";
				}else {
					coursePlanId = gev.getLongValue();
				}
			}else if(i == 1) {
				//读取excel第二列：课程名称 类型String 不可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_STRING, GlobalName.Necessary);
				if( !gev.isResult() ) {
					rowFlag = false;
					msg = "课程名无法读取！";
				}else {
					courseName = gev.getStringValue();
				}
			}else if (i == 2) {
				//读取excel第3列：授课教师 long 不可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_LONG, GlobalName.Necessary);
				if( !gev.isResult() ) {
					rowFlag = false;
					msg = "授课教师无法读取！";
				}else {
					teacherId = gev.getLongValue();
				}
			}else if (i == 3) {
				//读取excel第4列：上课年份 int 不可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_INT, GlobalName.Necessary);
				if( !gev.isResult() ) {
					rowFlag = false;
					msg = "开课学年无法读取！";
				}else {
					schoolYear = gev.getIntValue();
				}
			}else if (i == 4) {
				//读取excel第5列：上课学期 String 不可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_STRING, GlobalName.Necessary);
				if( !gev.isResult() ) {
					rowFlag = false;
					msg = "开课学期无法读取！";
				}else {
					schoolTerm = gev.getStringValue();
				}
			}else if (i == 5) {
				//读取excel第6列：上课时间(周) String 不可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_STRING, GlobalName.Necessary);
				if( !gev.isResult() ) {
					rowFlag = false;
					msg = "上课时间(周)无法读取！";
				}else {
					weeks = gev.getStringValue();
				}
			}else if (i == 6) {
				//读取excel第7列：上课时间及地点1 String 可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_STRING, GlobalName.unNecessary);
				if( !gev.isResult() ) {
					
				}else {
					timeAPlace1 = gev.getStringValue();
				}
			}else if (i == 7) {
				//读取excel第8列：上课时间及地点2 String 可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_STRING, GlobalName.unNecessary);
				if( !gev.isResult() ) {
					
				}else {
					timeAPlace2 = gev.getStringValue();
				}
			}else {
				//读取excel第9列：上课时间及地点3 String 可为空
				gev = ExcelValueBean.valueDeal(cell, GlobalName.resultType_STRING, GlobalName.unNecessary);
				if( !gev.isResult() ) {
					
				}else {
					timeAPlace3 = gev.getStringValue();
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
			SchoolTerm term = new SchoolTerm();
			term.setTermName(schoolTerm);
			CoursePlanFormData cpData = new CoursePlanFormData(coursePlanId, courseName, teacherId, schoolYear, term, weeks, timeAPlace1, timeAPlace2, timeAPlace3);
			coursePlanVo = formCoursePlanDataPro(cpData);
			if(!coursePlanVo.isFlag()) {
				msg = "第" + row.getRowNum() + "行数据：";
				coursePlanVo.setMsg(msg + coursePlanVo.getMsg());
			}
		}
		return coursePlanVo;
	}
	@ResponseBody
	@RequestMapping("studentSC")
//	public Object Manager_TeacherMana(HttpSession session, HttpServletRequest request) {
	public void studentSC(HttpSession session, HttpServletResponse httpServletResponse,HttpServletRequest request) throws IOException {
		StudentUser user = (StudentUser) session.getAttribute("user");
		int start = Integer.valueOf(request.getParameter("pageNumber"));
		//int size = Integer.valueOf(request.getParameter("Size"));
		List<CoursePlan> listCoursePlans = new ArrayList<CoursePlan>();
		JSONObject jsonObj = new JSONObject();
		//System.out.println(user != null);
		if(user != null) {
			listCoursePlans = coursePlanService.stuSelect(user.getUsername().getId(), (start - 1) * 10);
		}
		int i = 0;
		List<String> listTimes = new ArrayList<String>();
		for(CoursePlan cp : listCoursePlans) {
			//System.out.println(cp.getTime_place1().getId() + "\t" + cp.getTime_place2().getId() + "\t" + cp.getTime_place3().getId());
			String times = TimeIntToString(cp.getTime_place1(), 1) + TimeIntToString(cp.getTime_place2(), 2) + TimeIntToString(cp.getTime_place3(), 3);
			listTimes.add(times);
			i++;
		}
		jsonObj.put("listTimes", listTimes);
		jsonObj.put("listCoursePlans", listCoursePlans);
		String jsonString = JSONObject.toJSONString(jsonObj);
		
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(jsonString);
	}
	
	@RequestMapping("studentSelectCp")
	public void studentSelectCp(HttpSession session, HttpServletResponse httpServletResponse,HttpServletRequest request) throws IOException {
		String[] array = request.getParameterValues("tp[]");
		long cpid = Long.valueOf(request.getParameter("cpid"));
		StudentUser user = (StudentUser) session.getAttribute("user");
		String msg = "", s = "";
		int flag = 1;
		for(int i = 0; i < 3; i++) {
			TimeAndPlace tap = tapService.getById(Integer.valueOf(array[i]));
			flag = tapService.getStuSCFlag(tap, user.getUsername().getId());
			if(flag == 1) {
				msg = "课程时间冲突，无法选课.";
				s = "{\"success\":\"" + false +"\",\"msg\":\"" + msg + "\"}";
				break;
			}
		}
		if(flag == 0) {
			flag = scService.add(user.getUsername().getId(), cpid);
			//System.out.println(flag);
			msg = "选课成功";
			s = "{\"success\":\"" + true +"\",\"msg\":\"" + msg + "\"}";
		}
		httpServletResponse.setCharacterEncoding("utf8");
		httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
		httpServletResponse.getWriter().print(s);
	}
	@ResponseBody
	@RequestMapping("totlePageStuSC")
	public Object getStuSCTotlePage(HttpSession session) {
		StudentUser user = (StudentUser) session.getAttribute("user");
		int totle = 0;
		if(user != null) {
			totle = coursePlanService.stuSelectCount(user.getUsername().getId());
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totle", totle);
		return jsonObj;
	}
	/* a */
	/**
	 * @author 49305
	 * @param coursePlanId
	 * @param courseName
	 * @param teacherId
	 * @param startYear
	 * @param termName
	 * @param timeAPlace
	 * @return
	 * @description 处理CoursePlan格式的数据，包括jsp传来的表单数据，excel数据
	 */
	private CoursePlanVo formCoursePlanDataPro(CoursePlanFormData coursePlanData) {
		CoursePlanVo coursePlanVo = new CoursePlanVo();
		String msg = "";
		boolean flag = true;
		CoursePlan coursePlan = new CoursePlan();
		
		String courseName = coursePlanData.getName(), schoolTerm = coursePlanData.getSchoolTerm().getTermName();/* weeks = coursePlanData.getWeeks(), 
				timeAPlace1 = coursePlanData.getTime1(), timeAPlace2 = coursePlanData.getTime2(), timeAPlace3 = coursePlanData.getTime3();*/
		int schoolYear = coursePlanData.getSchoolYear();
		long teacherId = coursePlanData.getTid(), coursePlanId = coursePlanData.getId();
		
		if(coursePlanService.getCountById(coursePlanId) == 1) {
			flag = false;
			msg += "排课编号重复";
		}else {
			coursePlan.setId(coursePlanId);
		}
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
		if(schoolYear < 999) {
			flag = false;
			msg += "开课学年格式错误，只能输入4位数字";
		}else {
			
		}
		int termNum = isTrueTerm(schoolTerm);
		if(termNum == 0) {
			flag = false;
			msg += "开课学期错误[上学期，下学期];";
		}else {
			coursePlanData.getSchoolTerm().setTermNum(termNum);
		}
		if(flag) {
			boolean timeIsTrue = TimeAndPlacePro(coursePlan, coursePlanData);
			if(timeIsTrue) {
				/*
				 * if(coursePlanService.getCountByAll(coursePlan) == 1) { flag = false; msg +=
				 * "存在相同的课程安排"; }else { coursePlanVo.setCoursePlan(coursePlan); }
				 */
				coursePlanVo.setCoursePlan(coursePlan);
			}else {
				msg = "上课时间及地点冲突";
				flag = false;
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
	private boolean TimeAndPlacePro(CoursePlan cp, CoursePlanFormData coursePlanData) {
		boolean flag = true;
		String timeAPlace1 = coursePlanData.getTime1(), timeAPlace2 = coursePlanData.getTime2(), timeAPlace3 = coursePlanData.getTime3();
		List<TimeAndPlace> listTimes = new ArrayList<TimeAndPlace>();
		
		TimeAndPlace time1 = setData(coursePlanData, timeAPlace1);
		TimeAndPlace time2 = setData(coursePlanData, timeAPlace2);
		TimeAndPlace time3 = setData(coursePlanData, timeAPlace3);
		listTimes.add(time1);
		listTimes.add(time2);
		listTimes.add(time3);
		for(TimeAndPlace tap : listTimes) {
			//不为空的判断时间是否冲突
			if(tap != null) {
				if(tapService.getFlag(tap) != 0) {
					flag = false;
					return flag;
				}
			}
		}/*
		int add = tapService.addMany(listTimes);
		if(add != 0) {
			flag = true;
			for(TimeAndPlace tap : listTimes) {
				//不为空的判断时间是否冲突
				if(tap != null) {
					int id = tapService.getId(tap);
					tap.setId(id);
				}else {
					//时间为空的设置为默认1
					tap.setId(1);
				}
			}
		}*/
		cp.setTime_place1(time1);
		cp.setTime_place2(time2);
		cp.setTime_place3(time3);
		return flag;
	}
	private TimeAndPlace setData(CoursePlanFormData coursePlanData, String timeAndPlace) {
		TimeAndPlace time = null;
		if(timeAndPlace != null && !timeAndPlace.equals("")) {
			String weeks = coursePlanData.getWeeks();
			int schoolYear = coursePlanData.getSchoolYear();
			long tid = coursePlanData.getTid();
			Map<String, String> map = timeAndPlacePro(timeAndPlace);
			String single = map.get("single"), week = map.get("week"), times = map.get("times"), place = map.get("place");
			int singleInt = single(single);
			int weeksInt = weeksTime(weeks, singleInt);
			int weekInt = weekStringToInt(week);
			int classTimeInt = classTime(times);
			int placeInt = 0;
			Integer placeIntFlag = classroomService.getIdByName(place);
			Classroom classroom = new Classroom();
			if(placeIntFlag != null) {
				placeInt = placeIntFlag.intValue();
				classroom.setId(placeInt);
			}
			time = new TimeAndPlace(tid, weeksInt, weekInt, classTimeInt, classroom, schoolYear, coursePlanData.getSchoolTerm());/*
			time.setTid(tid);
			time.setWeeks(weeksInt);
			time.setWeek(weekInt);
			time.setTime(classTimeInt);
			time.setClassroom(classroom);
			time.setSchoolYear(schoolYear);
			time.setSchoolTerm(coursePlanData.getSchoolTerm());*/
		}
		return time;
	}
	//转换单双周为int
	public static int single(String single) {
		int singleInt = 0;
		for(int i = 0; i < single.length(); i++) {
			char ch = single.charAt(i);
			if(ch == '单') singleInt = 1;
			else if(ch == '双') singleInt = 2;
		}
		return singleInt;
	}
	//转换星期字符串为int
	public static int weekStringToInt(String week) {
		int weekInt = 0;
		for(int i = 0; i < week.length(); i++) {
			char ch = week.charAt(i);
			if( Character.isDigit(ch) ) {
				weekInt = ch - '0';
			} else if(ch == '一' || ch == '二' || ch == '三' || ch == '四' || ch == '五' || ch == '六' || ch == '天') {
				weekInt = weekChineseToint(ch);
			}
		}
		return weekInt;
	}
	//获取星期1-7并转换成int
	public static int weekChineseToint(char chinese) {
		int weekInt = 0;
		switch (chinese) {
		case '一': weekInt = 1; break;
		case '二': weekInt = 2; break;
		case '三': weekInt = 3; break;
		case '四': weekInt = 4; break;
		case '五': weekInt = 5; break;
		case '六': weekInt = 6; break;
		case '天': weekInt = 7; break;
		default: break;
		}
		return weekInt;
	}
	//将输入的上课时间x-y节转换成int类型
	public static int classTime(String time) {
		int classTimeInt = 0, beginTime = 0, endTime = 0;
		for(int i = 0; i < time.length(); i++) {
			char ch = time.charAt(i);
			if(beginTime == 0 && Character.isDigit(ch)) {
				beginTime = ch - '0';
				char ch2 = time.charAt(i + 1);
				if(Character.isDigit(ch2)) {
					beginTime = beginTime * 10 + ( ch2 - '0' );
				}
			}else if(beginTime != 0 && endTime == 0 && Character.isDigit(ch)) {
				endTime = ch - '0';
				char ch2 = time.charAt(i + 1);
				if(Character.isDigit(ch2)) {
					endTime = endTime * 10 + ( ch2 - '0' );
				}
			}
		}
		String bitString = "";
		for(int i = 0; i < 11; i++) {
			if(i >= beginTime - 1 && i <= endTime - 1) {
				bitString += '1';
			}else {
				bitString += '0';
			}
		}
		classTimeInt = Integer.valueOf(bitString, 2);
		return classTimeInt;
	}
	//将输入的x-y周转换成int类型
	public static int weeksTime(String weeks, int single) {
		//System.out.println(weeks + "\t" +single);
		int weeksTimeInt = 0, beginTime = 0, endTime = 0;
		for(int i = 0; i < weeks.length(); i++) {
			char ch = weeks.charAt(i);
			if(beginTime == 0 && Character.isDigit(ch)) {
				beginTime = ch - '0';
				char ch2 = weeks.charAt(i + 1);
				if(Character.isDigit(ch2)) {
					i++;
					beginTime = beginTime * 10 + ( ch2 - '0' );
				}
			}else if(beginTime != 0 && endTime == 0 && Character.isDigit(ch)) {
				endTime = ch - '0';
				char ch2 = weeks.charAt(i + 1);
				if(Character.isDigit(ch2)) {
					i++;
					endTime = endTime * 10 + ( ch2 - '0' );
				}
			}
		}
		//System.out.println(beginTime + "\t" + endTime);
		String bitString = "";
		for(int i = 0; i < 20; i++) {
			if(i >= beginTime - 1 && i <= endTime - 1) {
				if(single == 1 && i % 2 == 0) bitString += '1';
				else if(single == 2 && i % 2 == 1) bitString += '1';
				else if(single == 0) bitString += '1';
				else bitString += '0';
			}else {
				bitString += '0';
			}
		}
		//System.out.println(bitString);
		weeksTimeInt = Integer.valueOf(bitString, 2);
		return weeksTimeInt;
	}
	//获取输入的以分号隔开的上课时间，地点的信息
	public static Map<String, String> timeAndPlacePro(String timeAndPlace){
		Map<String, String> map = new HashMap<String, String>();
		String single = "", week = "", times = "", place = "";
	       int flag = 0;
	       for(int i = 0; i < timeAndPlace.length(); i++) {
	       	char ch = timeAndPlace.charAt(i);
	       	if(flag == 0 ) {
	       		if(ch == ';' || ch == '；') {
	       			flag++;
	       			continue;
	       		}
	       		single += ch;
	       	}else if(flag == 1) {
	       		if(ch == ';' || ch == '；') {
	       			flag++;
	       			continue;
	       		}
	       		week += ch;
	       	}else if(flag == 2) {
	       		if(ch == ';' || ch == '；') {
	       			flag++;
	       			continue;
	       		}
	       		times += ch;
	       	}else {
	       		if(ch == ';' || ch == '；') {
	       			flag++;
	       			continue;
	       		}
	       		place += ch;
	       	}
	    }
	    map.put("single", single);
	    map.put("week", week);
	    map.put("times", times);
	    map.put("place", place);
		return map;
	}
	public static String TimeIntToString(TimeAndPlace time, int index) {
		String timeString = "";
		String classroomString = time.getPlace().getName();
		int weeksStartNum = 0, weeksEndNum = 0, weeksNum = 0;
		int timesStartNum = 0, timesEndNum = 0;
		if(classroomString.equals("xxx")) {
			//==xxx为空时间地点..
		}else {
			//1.周转换为20位二进制。判断单双周。
			String weeks = Integer.toBinaryString(time.getWeeks());
			weeks = toWeeks(weeks);
			String times = Integer.toBinaryString(time.getTime());
			times = toTime(times);
			//System.out.println(weeks);
			for(int i = 0; i < weeks.length(); i++) {
				char ch = weeks.charAt(i);
				if(weeksStartNum == 0 && ch == '1') {
					weeksStartNum = i + 1;
					weeksNum ++;
				}else if(weeksStartNum != 0 && ch == '1') {
					weeksEndNum = i + 1;
					weeksNum ++;
				}
			}
			for(int i = 0; i < times.length(); i++) {
				char ch = times.charAt(i);
				if(timesStartNum == 0 && ch == '1') {
					timesStartNum = i + 1;
				}else if(timesStartNum != 0 && ch == '1') {
					timesEndNum = i + 1;
				}
			}
			if(index == 1) {
				timeString = weeksStartNum + "-" + weeksEndNum + "周;";
			}else {
				timeString = ";";
			}
			if(weeksNum < (weeksEndNum - weeksStartNum + 1)) {
				if(weeksStartNum % 2 == 0) {
					timeString += "双周,";
				}else {
					timeString += "单周,";
				}
			}else {
				
			}
			String[] weekArgs = {"","一","二","三","四","五","六","天"};
			timeString = timeString + "星期" + weekArgs[time.getWeek()] + timesStartNum + "-" + timesEndNum + "节," + time.getPlace().getName();
		}
		return timeString;
	}
	public static String toTime(String s) {
		if(s.length() < 11) return toTime("0" + s);
		else return s;
	}
	public static String toWeeks(String s) {
		if(s.length() < 20) return toWeeks("0" + s);
		else return s;
	}
}
