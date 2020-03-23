package com.gp.mapper;

import org.apache.ibatis.annotations.Param;

public interface Select_courseMapper {
	public int add(@Param("sid") long sid, @Param("cid") long cid);
}
