<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="com.gp.model.pojo.Teacher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
/* String listTeachers = request.getParameter("listInfo");
List<Teacher> list = JSONArray.parseArray(listTeachers,Teacher.class); */

%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>  
<script type="text/javascript">
$(document).ready(function () {
	console.log("ajaxRequest");
	$.ajax("${pageContext.request.contextPath}/_teacher",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		success : function(data) {
			var jsonObj = eval('(' + data + ')');
			tablePro(jsonObj["listTeachers"]);
			tabPro(jsonObj["listDepartments"]);
		},
		error : function(data){
			
		}
	});
});
function tabPro(data){
	for(var index in data){
		html = "<li><a href=\"#\">" + data[index]["name"] + "</a></li>";
		$("#project").append(html);
		$("#project1").append(html);
	}
}
function tablePro(data){
	for(var index in data){
		var title,tel,email,sex;
		if(data[index]["title"]==null){
			title = '';
		}else{
			title = data[index]["title"];
		}
		if(data[index]["tel"] == 0){
			tel = '';
		}else{
			tel = data[index]["tel"];
		}
		if(data[index]["e_mail"]==null){
			email = '';
		}else{
			email = data[index]["e_mail"];
		}
		if(data[index]["sex"]["num"]==1){
			sex = "男";
		}else{
			sex = "女";
		}
		html =  
		"<tr>" +
		"<td name=\"select\" style=\"display:none;\"><input name=\"ids\" type=\"checkbox\" value='{\"id\":"+ data[index]["id"] +"}'></td>" + 
		"<td>"+ data[index]["id"] +"</td>" +
		"<td>"+ data[index]["name"] +"</td>" +
		"<td>"+ sex +"</td>" + 
		"<td>"+ data[index]["deid"]["name"] +"</td>" + 
		"<td>"+ title +"</td>" +
		"<td>"+ tel +"</td>" +
		"<td>"+ email +"</td>" + 
		"<td>" +
			"<center>" +
			"<button type=\"button\" name=\"Update\" class=\"btn btn-info\" onclick='updateTeacher(\"" + data[index]["id"] + "\", \"" + data[index]["name"] + "\", \"" + sex + "\"," + 
			"\"" + data[index]["deid"]["name"] + "\", \"" + title + "\", \"" + tel + "\", \"" + email + "\")'>" + 
			"<span class=\"glyphicon glyphicon-wrench\" aria-hidden=\"true\"></span>修改" +
			"</button>" + 
			"<button type=\"button\" name=\"Delete\" class=\"btn btn-danger\" style=\"display:none;\" onclick=\"deleteTeacher(" + data[index]["id"] + ")\">" +
			"<span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\" ></span>删除" + 
			"</button>" + 
			"</center>" + 
		"</td>" + 
		"</tr>";
		$("#tableList").append(html);
	}
}
function selectTeacher(){
	var id = document.getElementById("select_input_id").value;
	var name = document.getElementById("select_input_name").value;
	var department = document.getElementById("select_input_department").value;
	
	$.ajax("${pageContext.request.contextPath}/getTeachers",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:{"id":id,"name":name,"dename":department},
		success : function(data) {
			console.log("sueecss");
			$("#tableList").empty();
			tablePro(data);
		},
		error : function(data){
			
		}
	});
}
$(function(){
	var table = $("#TeacherInfoTable");
	$("#DeleteTeacherBtn").click(function(){
		$("#TeacherInfoTable").hide();
		
		$("#TeacherFormData").hide();
		$("button[name='Update']").hide();
		$("#selectall").show();
		$("td[name='select']").show();
		$("button[name='Delete']").show();
		$("#MultiDelete").show();
		
		$("#TeacherInfoTable").show();
	});
	$("#MultiDeleteCancelBtn").click(function(){
		$("#TeacherInfoTable").hide();
		
		$("button[name='Update']").show();
		$("#selectall").hide();
		$("td[name='select']").hide();
		$("button[name='Delete']").hide();
		$("#MultiDelete").hide();
		
		$("#TeacherInfoTable").show();
	});
	$("#AddTeacherBtn").click(function(){
		document.getElementById('tid').removeAttribute('disabled')
		$("#TeacherFormData").hide();
		$("#TeacherFormData_UpdateBtn").hide();
		$("#TeacherExcelInput").show();
		$("#TeacherFormData_ConfirmBtn").show();
		$("#TeacherFormData").show(); 
	});
});
function updateTeacher(id, name, sex, department, title, tel, email) {
	document.getElementById('tid').setAttribute('disabled', 'disabled')
	document.getElementById("tid").value = id;
	document.getElementById("tname").value = name;
	document.getElementById("sex").value = sex;
	document.getElementById("department").value = department;
	document.getElementById("title").value = title;
	document.getElementById("tel").value = tel;
	document.getElementById("e_mail").value = email;
	var div = $("#TeacherFormData");
	$("#TeacherFormData").hide();
	$("#TeacherFormData_ConfirmBtn").hide();
	$("#TeacherExcelInput").hide();
	$("#TeacherFormData_UpdateBtn").show();
	$("#TeacherFormData").show();
}
function selectAll(choiceBtn) {
    var arr = document.getElementsByName("ids")
    for(var i=0;i<arr.length;i++){
        arr[i].checked=choiceBtn.checked
    }
}
$(function(){
	//TeacherFormData
	var div = $("#TeacherFormData");
	$("#TeacherFormData_CancelBtn").click(function(){
		$("#TeacherFormData").hide();   
		$("#TeacherFormData_UpdateBtn").hide();
		$("#TeacherFormData_ConfirmBtn").hide();
		$("#TeacherExcelInput").hide();
	});
});
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
				$("#TeacherFormData").hide();   
				$("#TeacherFormData_UpdateBtn").hide();
				$("#TeacherFormData_ConfirmBtn").hide();
				$("#TeacherExcelInput").hide();
				location.reload();
			}
		},
		error : function(data){
			
		}
	});
}
function putFormData(){
	var flag = validateForm();
	if(flag){
		if(confirm("确定添加？")){
			var tid = document.getElementById("tid").value;
			var tname = document.getElementById("tname").value;
			var sex = document.getElementById("sex").value;
			var department =document.getElementById("department").value;
			var title = document.getElementById("title").value;
			var tel = document.getElementById("tel").value;
			var e_mail = document.getElementById("e_mail").value;
			$.ajax("${pageContext.request.contextPath}/addTeacher",// 发送请求的URL字符串。
					{
				type : "post", //  请求方式 POST或GET
				data:{
					'tid':tid,
					'tname':tname,
					'sex':sex,
					'department':department,
					'title':title,
					'tel':tel,
					'e_mail':e_mail
				},
				contentType: "application/x-www-form-urlencoded",
				async:  false , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
				// 请求成功后的回调函数。
			//	dataType:"json",
				beforeSend:function(){
					console.log("正在进行，请稍候");
				},
				success : function(data) {
					var resq = eval("(" + data + ")");
					if(resq.success == "false"){
						alert(resq.msg);
					}else{
						alert("添加成功");
						$("#TeacherFormData").hide();   
						$("#TeacherFormData_UpdateBtn").hide();
						$("#TeacherFormData_ConfirmBtn").hide();
						$("#TeacherExcelInput").hide();
						location.reload();
					}
				},
				error : function(data){
					
				}
			});
		}
	}
}
function updateFormData(){
	var flag = validateForm();
	if(flag){
		if(confirm("确定修改？")){
			var tid = document.getElementById("tid").value;
			var tname = document.getElementById("tname").value;
			var sex = document.getElementById("sex").value;
			var department =document.getElementById("department").value;
			var title = document.getElementById("title").value;
			var tel = document.getElementById("tel").value;
			var e_mail = document.getElementById("e_mail").value;
			alert(tid + tname + sex + department + title + tel + e_mail);
			$.ajax("${pageContext.request.contextPath}/updateTeacher",// 发送请求的URL字符串。
					{
				type : "post", //  请求方式 POST或GET
				data:{
					'tid':tid,
					'tname':tname,
					'sex':sex,
					'department':department,
					'title':title,
					'tel':tel,
					'e_mail':e_mail
				},
				contentType: "application/x-www-form-urlencoded",
				async:  false , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
				// 请求成功后的回调函数。
			//	dataType:"json",
				beforeSend:function(){
					console.log("正在进行，请稍候");
				},
				success : function(data) {
					var resq = eval("(" + data + ")");
					if(resq.success == "false"){
						alert(resq.msg);
					}else{
						alert("修改成功");
						$("#TeacherFormData").hide();   
						$("#TeacherFormData_UpdateBtn").hide();
						$("#TeacherFormData_ConfirmBtn").hide();
						$("#TeacherExcelInput").hide();
						location.reload();
					}
				},
				error : function(data){
					
				}
			});
		}
	}
}
function deleteTeacher(id){
	if (confirm("确定删除？")) {
		$.ajax("${pageContext.request.contextPath}/deleteTeacher",// 发送请求的URL字符串。
				{
			type : "post", //  请求方式 POST或GET
			data:{"id":id},
			contentType: "application/x-www-form-urlencoded",
			success : function(data) {
				var resq = eval("(" + data + ")");
				if(resq.success == "false"){
					alert(resq.msg);
				}else{
					alert("删除成功");
					location.reload();
				}
			},
			error : function(data){
				
			}
		});
	}
}
function deleteFormData() {
	var ids = document.getElementsByName("ids");               
	var flag = false;
	var seleted = new Array();
	for(var i=0; i < ids.length; i++){
    	if(ids[i].checked){
    		seleted[i] = ids[i].value;
    		flag = true;
    	}
	}
	if(!flag){
		alert("请最少选择一项！");
	}
	else {
		if (confirm("确定删除？")) {
			$.ajax("${pageContext.request.contextPath}/deleteTeachers",// 发送请求的URL字符串。
					{
				type : "post", //  请求方式 POST或GET
				data:{"array":seleted},
				contentType: "application/x-www-form-urlencoded",
				success : function(data) {
					var resq = eval("(" + data + ")");
					if(resq.success == "false"){
						alert(resq.msg);
					}else{
						alert("删除成功");
					}
				},
				error : function(data){
					
				}
			});
		}
	}
}
/* function deleteForm(){
	var ids = document.getElementsByName("ids");               
	var flag = false ;
	var seleted = new Array();
	for(var i=0; i < ids.length; i++){
    	if(ids[i].checked){
    		seleted[i] = ids[i].value;
    	}
	}
	if(!flag){
    	alert("请最少选择一项！");
	}
} */
function validateForm() {
	var tid = document.getElementById("tid").value;
	var tname = document.getElementById("tname").value;
	var sex = document.getElementById("sex").value;
	var department = document.getElementById("department").value;
    var reg = /^[0-9]+$/; 
    if(tid == "" || !reg.test(tid) ){ 
    	alert('教师编号不能为空且只能为数字！'); 
    	return false; 
    } 
    var reg=/^[\u0391-\uFFE5]+$/; 
    if(tname == "" || !reg.test(tname) ){ 
    	alert('姓名不能为空且只能为中文！');
    	return false; 
    } 
    var reg = /^男$|^女$/; 
    if(sex == '' || !reg.test(sex)){ 
    	alert('性别不能为空且只能为男或女');
    	return false; 
    }
    if(department == ""){
    	alert("所属院系不能为空");
    	return false;
    }
    return true
}
$(function(){
	$("#project").on("click", "li", function(){
		 $("#department").val($(this).text());
	});
	$("#project1").on("click", "li", function(){
		$("#select_input_department").val($(this).text());
	});
	
});//下拉菜单选中的值赋值给input输入框！
</script>
<div  class="panel panel-primary" style="height:700px;width:100%;">
<div class="panel-heading" style="height:auto;">
当前位置：管理员>教师信息管理
</div>

<div class="panel-body" style="height:1000px;" >
<div class="btn-group" style=" width:15%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">姓名</button>
      </span>
      <input type="text" class="form-control" id="select_input_name">
    </div>
</div>
<div class="btn-group" style=" width:15%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">教师编号</button>
      </span>
      <input type="text" class="form-control" id="select_input_id">
    </div>
</div>
<div class="btn-group" style=" width:26%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false" type="button">所属院系<span class="caret"></span></button>
                      <ul id="project1" class="dropdown-menu" >
                      </ul>      
      </span>
      <input type="text" class="form-control" id="select_input_department" disabled="true">
    </div>
</div>
<div class="btn-group" style=" width:7%; height:10%;  float:left; margin-left:1%;">
  <button type="button" class="btn btn-warning" id="selectTeacherBtn" onclick="selectTeacher()">
  <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
  </button>
</div>
<div class="btn-group" style=" width:15%; height:10%;  float:left; margin-left:1%;">
  <button type="button" id="DeleteTeacherBtn" class="btn btn-danger">
  <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除教师信息
  </button>
</div>
<div class="btn-group" style=" width:15%; height:10%;  float:left; margin-left:1%;">
  <button type="button" id="AddTeacherBtn" class="btn btn-primary">
  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加教师信息
  </button>
</div>

<table id="TeacherInfoTable" class="table table-striped table-bordered table-hover  table-condensed"style="position:absolute;width:79%;margin-top:4%;">
<thead>
	<th id="selectall"style="display:none;"><input type="checkbox" onclick="selectAll(this)" >全选</th>
	<th>教师编号</th>
	<th>姓名</th>
	<th>性别</th>
	<th>所属院系</th>
	<th>职称</th>
	<th>联系电话	</th>
	<th>电子邮箱</th>
	<th><center>操作</center></th> 
</thead>
<tbody id="tableList">
	
</tbody>
</table>

<div id="TeacherFormData"  style="display:none;background-color:LightCyan;margin-top:4%;margin-left:15%;position:absolute;width:50%;">
	
		<div class="input-group" style=" width:49%; height:10%;  float:left; margin-left:1%;margin-top:2%;">
			<span class="input-group-btn">
			<button class="btn btn-default" type="button">教师编号</button>
			</span>
			<input id="tid" type="text" class="form-control" >
		</div><!-- /input-group -->

	
		<div class="input-group" style=" width:49%; height:10%;  float:left; margin-left:1%;margin-top:2%;">
			<span class="input-group-btn">
			<button class="btn btn-default" type="button">姓名</button>
			</span>
			<input id="tname" type="text" class="form-control" >
    	</div><!-- /input-group -->

    
    	<div class="input-group" style=" width:49%; height:10%;  float:left; margin-left:1%;margin-top:3%">
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">性别</button>
    		</span>
    		<input id="sex" type="text" class="form-control" >
    	</div><!-- /input-group -->
    
   
    		<div class="input-group" style=" width:49%; height:10%;  float:left; margin-left:1%;margin-top:3%">	
    		<div class="input-group-btn">
    		<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">所属院系<span class="caret"></span></button>
	    		    <ul id="project" class="dropdown-menu" >
	    		    
                    </ul>
	       </div>
    		<input id="department" type="text" class="form-control" disabled="true">
    	</div><!-- /input-group -->
   
    	<div class="input-group"style=" width:49%; height:10%;  float:left; margin-left:1%;margin-top:3%">
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">职称</button>
    		</span>
    		<input id="title" type="text" class="form-control" >
    	</div><!-- /input-group -->
   
    	<div class="input-group"style=" width:49%; height:10%;  float:left; margin-left:1%;margin-top:3%" >
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">联系电话</button>
    		</span>
    		<input id="tel" type="text" class="form-control" >
    	</div><!-- /input-group -->
   
    	<div class="input-group" style=" width:49%; height:10%;float:left; margin-right:50%;margin-left:1%;margin-top:3%">
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">电子邮箱</button>
    		</span>
    		<input id="e_mail" type="text" class="form-control" >
    	</div><!-- /input-group -->
  
    <button type="button" id="TeacherFormData_ConfirmBtn" style="display:none;width:20%;   float:left; margin-left:20%;margin-top:3%" class="btn btn-success" onclick="putFormData()">添加</button>
    <button type="button" id="TeacherFormData_UpdateBtn" style="display:none;width:20%;  float:left; margin-left:20%;margin-top:3%" class="btn btn-success"   onclick="updateFormData()">修改</button>
    <button type="button" id="TeacherFormData_CancelBtn" style="width:20%;   float:left; margin-left:20%;margin-top:3%" class="btn btn-warning">取消</button>
   
    <div class="form-group" id="TeacherExcelInput" class="col-sm-10" style="display:none;float:left;margin-left:5%;margin-top:3%;">
	   通过excel表单添加：
	<input id="TeacherExcelFile" name="excelFile" type="file" class="form-control" style="width:45%; display: inline;" />
	<input id="insertTeacherExcelButton" type="button" class="btn btn-primary" onclick="inputTeacher()" style="width: 15%;height: 35px;" value="上传" />
	</div>
</div>
<div id="MultiDelete"style="display:none;margin-top:580px;margin-left:40%;width:100%;">
	<button type="button" id="MultiDeleteBtn"  class="btn btn-danger"  onclick="deleteFormData()">批量删除</button>
	<button type="button" id="MultiDeleteCancelBtn" class="btn btn-warning"  >取消</button>
</div>

</div>
</div>