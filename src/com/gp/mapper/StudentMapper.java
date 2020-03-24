package com.gp.mapper;

import java.util.List;

import com.gp.pojo.Student;

public interface StudentMapper {
	public int add(Student student); 
    
    public void delete(int id); 
        
    public String get(int id); 
      
    public int update(Student student);  
        
    public List<Student> list();
     
    public int count(); 
}
