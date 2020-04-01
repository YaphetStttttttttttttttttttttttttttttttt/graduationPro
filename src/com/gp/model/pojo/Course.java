package com.gp.model.pojo;

import java.time.Year;
import java.util.List;

import com.gp.model.vo.CourseType;
import com.gp.model.vo.SchoolTerm;

public class Course {
	private long id;
	private String name;
	private CourseType type;
	private float credit;
	private int cycle;
	private int startSyear;
	private SchoolTerm startTerm;
	private Department deid;
	private Discipline diid;
	
	List<CoursePlan> coursePlans;
	
	public Course() {}
	public Course(long id, String name, CourseType type, float credit, int cycle, int startSyear, SchoolTerm startTerm,
			Department deid, Discipline diid, List<CoursePlan> coursePlans) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.credit = credit;
		this.cycle = cycle;
		this.startSyear = startSyear;
		this.startTerm = startTerm;
		this.deid = deid;
		this.diid = diid;
		this.coursePlans = coursePlans;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CourseType getType() {
		return type;
	}
	public void setType(CourseType type) {
		this.type = type;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	public int getStartSyear() {
		return startSyear;
	}
	public void setStartSyear(int startSyear) {
		this.startSyear = startSyear;
	}
	public SchoolTerm getStartTerm() {
		return startTerm;
	}
	public void setStartTerm(SchoolTerm startTerm) {
		this.startTerm = startTerm;
	}
	public Department getDeid() {
		return deid;
	}
	public void setDeid(Department deid) {
		this.deid = deid;
	}
	public Discipline getDiid() {
		return diid;
	}
	public void setDiid(Discipline diid) {
		this.diid = diid;
	}
	public List<CoursePlan> getCoursePlans() {
		return coursePlans;
	}
	public void setCoursePlans(List<CoursePlan> coursePlans) {
		this.coursePlans = coursePlans;
	}
	
}