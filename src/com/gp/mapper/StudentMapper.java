package com.gp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gp.model.pojo.Student;

public interface StudentMapper {
	public int add(Student student); 
    
    public int getById(@Param("id") long id); 
	
    public void delete(int id); 
      
    public int update(Student student);  
        
    public List<Student> list();
     
    public int count();
    
    public List<Student> getStudent(@Param("cpid") long cpid);
}
