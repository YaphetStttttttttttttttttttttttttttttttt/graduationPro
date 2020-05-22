package com.gp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.SelectCourse;

public interface SelectCourseMapper {
	public List<SelectCourse> getByStudent(@Param("sid") long sid);
	
	public int add(@Param("sid") long sid, @Param("cpid") long cpid);
	
	public int delete(@Param("sid") long sid, @Param("cpid") long cpid);
	
	public List<SelectCourse> getStuScore(@Param("sid") long sid, @Param("start") int start);
	
	public int getStuScoreCount(@Param("sid") long sid);
	
	public List<SelectCourse> getStuList(@Param("tid") long tid, @Param("start") int start);
	
	public int getStuListCount(@Param("tid") long tid);
	
	public int updateTGrade(@Param("sid") long sid,@Param("cpid") long cpid,@Param("score") int score);
}
