<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
});
function inputFile(){
	var formData = new FormData();
	var name = $("#courseExcelFile").val();
	formData.append("file",$("#courseExcelFile")[0].files[0]);
	formData.append("name",name);//这个地方可以传递多个参数
	$.ajax("${pageContext.request.contextPath}/addCourses",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:formData,
		async:  false , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
		// 请求成功后的回调函数。
		processData : false,
		contentType : false,
		beforeSend:function(){
			console.log("正在进行，请稍候");
		},
		success : function(data) {
			var resq = eval("(" + data + ")");
			if(resq.success == "false"){
				alert(resq.msg);
			}else{
				alert("添加成功");
			}
		},
		error : function(data){
			
		}
	});
}
function inputCoursePlans(){
	var formData = new FormData();
	var name = $("#coursePlanExcelFile").val();
	formData.append("file",$("#coursePlanExcelFile")[0].files[0]);
	formData.append("name",name);//这个地方可以传递多个参数
	$.ajax("${pageContext.request.contextPath}/addCoursePlanExcel",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:formData,
		async:  false , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
		// 请求成功后的回调函数。
		processData : false,
		contentType : false,
		beforeSend:function(){
			console.log("正在进行，请稍候");
		},
		success : function(data) {
			var resq = eval("(" + data + ")");
			if(resq.success == "false"){
				alert(resq.msg);
			}else{
				alert("添加成功");
			}
		},
		error : function(data){
			
		}
	});
}
function inputTeacher(){
	var formData = new FormData();
	var name = $("#TeacherExcelFile").val();
	formData.append("file",$("#TeacherExcelFile")[0].files[0]);
	formData.append("name",name);//这个地方可以传递多个参数
	$.ajax("${pageContext.request.contextPath}/addTeacherExcel",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:formData,
		async:  false , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
		// 请求成功后的回调函数。
		processData : false,
		contentType : false,
		beforeSend:function(){
			console.log("正在进行，请稍候");
		},
		success : function(data) {
			var resq = eval("(" + data + ")");
			if(resq.success == "false"){
				alert(resq.msg);
			}else{
				alert("添加成功");
			}
		},
		error : function(data){
			
		}
	});
}
function inputClasses(){
	var formData = new FormData();
	var name = $("#ClassesExcelFile").val();
	formData.append("file",$("#ClassesExcelFile")[0].files[0]);
	formData.append("name",name);//这个地方可以传递多个参数
	$.ajax("${pageContext.request.contextPath}/addClassesExcel",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:formData,
		async:  false , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
		// 请求成功后的回调函数。
		processData : false,
		contentType : false,
		beforeSend:function(){
			console.log("正在进行，请稍候");
		},
		success : function(data) {
			var resq = eval("(" + data + ")");
			if(resq.success == "false"){
				alert(resq.msg);
			}else{
				alert("添加成功");
			}
		},
		error : function(data){
			
		}
	});
}
function inputStudent(){
	var formData = new FormData();
	var name = $("#StudentExcelFile").val();
	formData.append("file",$("#StudentExcelFile")[0].files[0]);
	formData.append("name",name);//这个地方可以传递多个参数
	$.ajax("${pageContext.request.contextPath}/addStudentExcel",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:formData,
		async:  false , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
		// 请求成功后的回调函数。
		processData : false,
		contentType : false,
		beforeSend:function(){
			console.log("正在进行，请稍候");
		},
		success : function(data) {
			var resq = eval("(" + data + ")");
			if(resq.success == "false"){
				alert(resq.msg);
			}else{
				alert("添加成功");
			}
		},
		error : function(data){
			
		}
	});
}
function inputDepartment(){
	var formData = new FormData();
	var name = $("#DepartmentExcelFile").val();
	formData.append("file",$("#DepartmentExcelFile")[0].files[0]);
	formData.append("name",name);//这个地方可以传递多个参数
	$.ajax("${pageContext.request.contextPath}/addDepartmentExcel",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:formData,
		async:  false , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
		// 请求成功后的回调函数。
		processData : false,
		contentType : false,
		beforeSend:function(){
			console.log("正在进行，请稍候");
		},
		success : function(data) {
			var resq = eval("(" + data + ")");
			if(resq.success == "false"){
				alert(resq.msg);
			}else{
				alert("添加成功");
			}
		},
		error : function(data){
			
		}
	});
}
</script>
</head>
<body>

<div class="form-group" id="thumbnailUploadContainer" class="col-sm-10" style="float: left; margin-right: 50px;">
	<input id="courseExcelFile" name="excelFile" type="file" class="form-control" style="width: 300px; display: inline;" />
	<input id="insertCourseExcelButton" type="button" onclick="inputFile()" style="width: 60px;height: 35px;" value="上传" />
</div>
<font color="red" id="errorInfo"></font>
<a href="addDepartment">123</a>
<div class="form-group" id="coursePlan" class="col-sm-10" style="float: left; margin-right: 50px;">
	<input id="coursePlanExcelFile" name="excelFile" type="file" class="form-control" style="width: 300px; display: inline;" />
	<input id="insertCoursePlanExcelButton" type="button" onclick="inputCoursePlans()" style="width: 60px;height: 35px;" value="上传" />
</div>
<div class="form-group" id="Teacher" class="col-sm-10" style="float: left; margin-right: 50px;">
	<input id="TeacherExcelFile" name="excelFile" type="file" class="form-control" style="width: 300px; display: inline;" />
	<input id="insertTeacherExcelButton" type="button" onclick="inputTeacher()" style="width: 60px;height: 35px;" value="上传" />
</div>
<div class="form-group" id="Classes" class="col-sm-10" style="float: left; margin-right: 50px;">
	<input id="ClassesExcelFile" name="excelFile" type="file" class="form-control" style="width: 300px; display: inline;" />
	<input id="insertClassesExcelButton" type="button" onclick="inputClasses()" style="width: 60px;height: 35px;" value="上传" />
</div>
<div class="form-group" id="Student" class="col-sm-10" style="float: left; margin-right: 50px;">
	<input id="StudentExcelFile" name="excelFile" type="file" class="form-control" style="width: 300px; display: inline;" />
	<input id="insertStudentExcelButton" type="button" onclick="inputStudent()" style="width: 60px;height: 35px;" value="上传" />
</div>
<div class="form-group" id="Department" class="col-sm-10" style="float: left; margin-right: 50px;">
	<input id="DepartmentExcelFile" name="excelFile" type="file" class="form-control" style="width: 300px; display: inline;" />
	<input id="insertDepartmentExcelButton" type="button" onclick="inputDepartment()" style="width: 60px;height: 35px;" value="上传" />
</div>
</body>
</html>