package com.gp.model.pojo;

public class CoursePlan {
	private long id;
	private Course cid;
	private Teacher tid;
	private TimeAndPlace time_place1;
	private TimeAndPlace time_place2;
	private TimeAndPlace time_place3;
	
	public CoursePlan() {}

	public CoursePlan(long id, Course cid, Teacher tid, TimeAndPlace time_place1, TimeAndPlace time_place2,
			TimeAndPlace time_place3) {
		super();
		this.id = id;
		this.cid = cid;
		this.tid = tid;
		this.time_place1 = time_place1;
		this.time_place2 = time_place2;
		this.time_place3 = time_place3;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Course getCid() {
		return cid;
	}

	public void setCid(Course cid) {
		this.cid = cid;
	}

	public Teacher getTid() {
		return tid;
	}

	public void setTid(Teacher tid) {
		this.tid = tid;
	}

	public TimeAndPlace getTime_place1() {
		return time_place1;
	}

	public void setTime_place1(TimeAndPlace time_place1) {
		this.time_place1 = time_place1;
	}

	public TimeAndPlace getTime_place2() {
		return time_place2;
	}

	public void setTime_place2(TimeAndPlace time_place2) {
		this.time_place2 = time_place2;
	}

	public TimeAndPlace getTime_place3() {
		return time_place3;
	}

	public void setTime_place3(TimeAndPlace time_place3) {
		this.time_place3 = time_place3;
	}
	
}
