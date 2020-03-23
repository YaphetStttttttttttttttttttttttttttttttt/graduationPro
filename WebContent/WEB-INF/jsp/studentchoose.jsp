<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
});
function selectCourse(courseId){
	alert(courseId);
	$.ajax("${pageContext.request.contextPath}/selectCourse",// 发送请求的URL字符串。
			{
			dataType : "json", // 预期服务器返回的数据类型。
   			type : "post", //  请求方式 POST或GET
		    //contentType:"application/json", //  发送信息至服务器时的内容编码类型
		   // 发送到服务器的数据。
		   //data:JSON.stringify({id : 1, name : "Spring MVC企业应用实战"}),
		   data:{"cid":courseId},
		   async:  false , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
		   // 请求成功后的回调函数。
		   success :function(data){
			  // alert(data.size);
			  location.reload();
			  $("$errorInfo").html(data.error);
			  
			 },
		   // 请求出错时调用的函数
		   error:function(){
			   alert("数据发送失败");
		   }
	});
}
</script>

<div  class="panel panel-primary">
	<div class="panel-heading" style="height:140px;">
		<h1 >成绩查询系统</h1>当前位置：学生>学生选课 
		<button type="button" class="btn btn-default btn-lg" style="float:right;">
			<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 注销
		</button>
	</div>
	<div class="panel-body"  style="margin:50px; height:550px;">
		<table class="table table-striped table-bordered table-hover  table-condensed">
		<thead>
			<th>序号</th>
			<th>课程代码</th>
			<th>课程名称</th>
			<th>课程性质</th>
			<th>学分</th>
			<th></th>
		</thead>
		<tbody>
			<c:forEach items="${allSelectCourse}" var="course" varStatus="st">
				<tr>
					<td>${st.index} </td> 
					<td>${course.id} </td>  
					<td>${course.name} </td>  
					<td>${course.type} </td>
					<td>${course.credit} </td>  
					<td><center><button type="button" class="btn btn-primary" onclick="selectCourse(${course.id})">选课</button></center></td>   
				</tr>
			</c:forEach>
		</tbody>
		</table>
	</div>
	<font color="red" id="errorInfo"></font>
</div>