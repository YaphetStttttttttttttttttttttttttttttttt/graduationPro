<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>

<div  class="panel panel-primary">
	<div class="panel-heading" style="height:140px;">
		<h1 >成绩查询系统</h1>当前位置：学生>学生成绩 
		<button type="button" class="btn btn-default btn-lg" style="float:right;">
			<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 注销
		</button>
	</div>
	<div class="panel-body" style="margin:50px; height:550px;">本科生成绩
		<div class="progress">
		<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
			<span class="sr-only">60% Complete</span>
		</div>
		</div>
		<div class="dropdown" style="float:right;">
			<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				选择学期<span class="caret"></span>
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1" ">
				<li><a href="#">2016-2017上学期</a></li>
				<li><a href="#">2016-2017下学期</a></li>
				<li><a href="#">2017-2018上学期</a></li>
				<li><a href="#">2017-2018下学期</a></li>
			</ul>
		</div>
		<table class="table table-striped table-bordered table-hover  table-condensed">
		<thead>
			<th>序号</th>
			<th>学年学期</th>
			<th>课程名称</th>
			<th>绩点</th>
			<th>学分</th>
			<th>课程性质</th>
			<th>期末成绩</th>
			<th>补考成绩</th>
			<th>重修成绩</th>
		</thead>
		<tbody>
		<c:forEach items="${allCourse}" var="course" varStatus="st">
			<tr>
     		   	<td>${st.index}</td>  
     		   	<td>${course.start_time}${course.start_term}</td>  
     		   	<td>${course.name}</td>  
       	 		<td>0</td>
     	 	  	<td>${course.credit}</td>  
       	 		<td>${course.type}</td>
        		<td>${course.selectCourse.f_grade}</td>
        		<td>${course.selectCourse.s_grade}</td>
        		<td>${course.selectCourse.t_grade}</td> 
     		</tr>
     	</c:forEach>
     	</tbody>
     	</table>
     </div>
</div>