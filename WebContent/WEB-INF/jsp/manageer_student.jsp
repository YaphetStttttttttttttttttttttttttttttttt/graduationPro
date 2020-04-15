<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="com.gp.model.pojo.Teacher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>  
<script type="text/javascript">
var allCount;//记录总数
var page;//总页数
var pageNumber = 1;//默认起始记录
var pageSize = 10;//每页记录数
var selectFlag = false;
var deleteFlag = false;
var array = new Array();

$(document).ready(function () {
	console.log("ajaxRequest");
	$.ajax("${pageContext.request.contextPath}/_student",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		success : function(data) {
			var jsonObj = eval('(' + data + ')');
			tablePro(jsonObj["listStudents"]);
			
		},
		error : function(data){
			
		}
	});
	getTotle();
	initArray();
});


//初始化存储教师ID的数组
function initArray(){
	for(var i = 1; i<=page; i++){
		array[i] = new Array(10);
		for(var j = 0; j<10; j++){
			array[i][j] = 0;
		}
	}
}

function getTotle(){
	var id = "", name = "", classes = "";
	if(selectFlag){
		id = document.getElementById("select_input_id").value;
		name = document.getElementById("select_input_name").value;
		classes = document.getElementById("select_input_classes").value;
	}
	$.ajax("${pageContext.request.contextPath}/totlePageStudent",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:{
			"id":id,
			"name":name,
			"classes":classes
		},
		success : function(data) {
			allCount = data.totle;
            pageNum();//页面被加载默认的页数
            $("#totleRow").val(allCount);
		},
		error : function(data){
			
		}
	});
}
/**
 * 总页数
 * */
function pageNum() {
    if (allCount % 10 == 0) {
        page = parseInt(allCount / 10);
        $("#totlePage").val(page);
    } else {
        page = parseInt(allCount / 10) + 1;
        $("#totlePage").val(page);
    }
}
/**
 * 首页
 * */
function firstPage() {
    pageNumber = 1;
    pageSize = 10;
    $("#jumpPage").val("1");
    if ($("#nowPage").val() == 1) {
        alert("已经是首页了")
    }
    $("#nowPage").val("1");
	show();
}
/**
 * 上一页
 * */
function prePage() {
    if (pageSize > 10) {
        $("#nowPage").val(pageSize / 10 - 1);
        $("#jumpPage").val(pageSize / 10 - 1);
        pageSize = pageNumber - 1;
        pageNumber = pageNumber - 10;
		show();
    } else {
        alert("已经是首页了")
    }
}
/**
 * 下一页
 * */
function nextPage() {
    if (allCount > pageSize) {
        $("#nowPage").val(pageSize / 10 + 1);
        $("#jumpPage").val(pageSize / 10 + 1);
        pageNumber = pageNumber + 10;
        pageSize = pageNumber + 10 - 1;
       
		show();
    } else {
        alert("已经是最后一页了")
    }
}
/**
 * 跳转至第几页
 * */
function jump() {
    var page = parseInt($("#jumpPage").val());
    if (page <= parseInt($("#totlePage").val())) {
        pageSize = page * 10;
        pageNumber = (page - 1) * 10 + 1;
        $("#nowPage").val($("#jumpPage").val());
		show();
    } else {
        alert("该页不存在")
        $("#jumpPage").val($("#nowPage").val());

    }
}
/**
 * 最后一页
 **/
function lastPage() {
    pageNumber = parseInt($("#totlePage").val() - 1) * 10 + 1;
    pageSize = allCount;
    $("#jumpPage").val($("#totlePage").val());
    if ($("#nowPage").val() == $("#totlePage").val()) {
        alert("已经是最后一页了")
    }
    $("#nowPage").val($("#totlePage").val());
    show();
}
function show() {
	var id = "", name = "", classes = "";
	if(selectFlag){
		id = document.getElementById("select_input_id").value;
		name = document.getElementById("select_input_name").value;
		classes = document.getElementById("select_input_classes").value;
	}
	$.ajax("${pageContext.request.contextPath}/getStudents",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:{
			"id":id,
			"name":name,
			"cname":classes,
			"pageNumber":$("#nowPage").val(),
			"Size":10
		},
		success : function(data) {
			console.log("sueecss");
			$("#tableList").empty();
			tablePro(data);
		},
		error : function(data){
			
		}
	});
	getTotle();
}


function tablePro(data){
	for(var index in data){
		var title,tel,address,email,sex;
		if(data[index]["age"]==null){
			age = '';
		}else{
			title = data[index]["age"];
		}
		if(data[index]["tel"] == 0){
			tel = '';
		}else{
			tel = data[index]["tel"];
		}
		if(data[index]["address"] == 0){
			address = '';
		}else{
			address = data[index]["address"];
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
		
		var nowPage = $("#nowPage").val()
		var td_style = "style=\"display:none;\"";//默认是隐藏
		var input_checked = "";//默认是未选中
		var updata_style = "";//默认是显示
		var delete_style = "style=\"display:none;\"";//默认隐藏
		//当处于多选删除状态
		if(deleteFlag){
			td_style = "";//td显示
			updata_style = "style=\"display:none;\"";//修改按钮隐藏
			delete_style = "";//删除按钮显示
			for(var i = 0; i < array[nowPage].length; i++){
				//如果当前行被选中
				if(array[nowPage][i] == ("{\"id\":" + data[index]["id"] +"}")){
					input_checked = "checked=\"checked\"";//input被选中
					selected_num++;
				}
			}
		}
		html =  
		"<tr>" +
		"<td name=\"select\" style=\"display:none;\"><input name=\"ids\" type=\"checkbox\" value='{\"id\":"+ data[index]["id"] +"}'></td>" + 
		"<td>"+ data[index]["id"] +"</td>" +
		"<td>"+ data[index]["name"] +"</td>" +
		"<td>"+ sex +"</td>" + 
		"<td>"+ data[index]["cid"]["name"] +"</td>" + 
		"<td>"+ age +"</td>" +
		"<td>"+ tel +"</td>" +
		"<td>"+ address +"</td>" +
		"<td>"+ email +"</td>" + 
		"<td>" +
			"<center>" +
			"<button type=\"button\" name=\"Update\" class=\"btn btn-info\" onclick='updateStudent(\"" + data[index]["id"] + "\", \"" + data[index]["name"] + "\", \"" + sex + "\"," + 
			"\"" + data[index]["cid"]["name"] + "\", \"" + age + "\", \"" + tel + "\",\"" + adress + "\", \"" + email + "\")'>" + 
			"<span class=\"glyphicon glyphicon-wrench\" aria-hidden=\"true\"></span>修改" +
			"</button>" + 
			"<button type=\"button\" name=\"Delete\" class=\"btn btn-danger\" style=\"display:none;\" onclick=\"deleteStudent(" + data[index]["id"] + ")\">" +
			"<span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\" ></span>删除" + 
			"</button>" + 
			"</center>" + 
		"</td>" + 
		"</tr>";
		$("#tableList").append(html);
	}
	if(selected_num == 10) {
		$("#select_all_input").prop("checked",true);
	}
	else {
		$("#select_all_input").prop("checked",false);
	}
}
function inputChange(){
	selectFlag = false;
}
function selectStudent(){
	selectFlag = true;
	pageNumber = 1;
    pageSize = 10;
    $("#jumpPage").val("1");
    $("#nowPage").val("1");
    array.splice(1,page);
	show();
	initArray();
}
$(function(){
	var table = $("#StudentInfoTable");
	$("#DeleteStudentBtn").click(function(){
		deleteFlag = true;
		$("#StudentInfoTable").hide();
		$("#StudentFormData").hide();
		$("button[name='Update']").hide();
		$("#selectall").show();
		$("td[name='select']").show();
		$("button[name='Delete']").show();
		$("#MultiDelete").show();
		$("#StudentInfoTable").show();
	});
	$("#MultiDeleteCancelBtn").click(function(){
		deleteFlag = false;
		for(var i = 1; i <= page; i++){
			for(var j = 0; j < array[i].length; j++){
				array[i][j] = 0;
			}
		}
		$("#select_all_input").prop("checked",false);
		selectAll($("#select_all_input"));
		$("#StudentInfoTable").hide();
		$("button[name='Update']").show();
		$("#selectall").hide();
		$("td[name='select']").hide();
		$("button[name='Delete']").hide();
		$("#MultiDelete").hide();
		$("#StudentInfoTable").show();
	});
	$("#AddStudentBtn").click(function(){
		document.getElementById('sid').removeAttribute('disabled')
		setFormData("", "", "", "", "", "", "", "");
		$("#StudentFormData").hide();
		$("#StudentFormData_UpdateBtn").hide();
		$("#StudentExcelInput").show();
		$("#StudentFormData_ConfirmBtn").show();
		$("#StudentFormData").show(); 
	});
});
function updateStudent(id, name, sex, classes, age, tel, address, email) {
	document.getElementById('sid').setAttribute('disabled', 'disabled')
	setFormData(id, name, sex, classes, age, tel, address, email);
	var div = $("#StudentFormData");
	$("#StudentFormData").hide();
	$("#StudentFormData_ConfirmBtn").hide();
	$("#StudentExcelInput").hide();
	$("#StudentFormData_UpdateBtn").show();
	$("#StudentFormData").show();
}
function selectAll(choiceBtn) {
    var arr = document.getElementsByName("ids")
    for(var i=0;i<arr.length;i++){
        arr[i].checked=choiceBtn.checked
    }
}
$(function(){
	//StudentFormData
	var div = $("#StudentFormData");
	$("#StudentFormData_CancelBtn").click(function(){
		$("#StudentFormData").hide();   
		$("#StudentFormData_UpdateBtn").hide();
		$("#StudentFormData_ConfirmBtn").hide();
		$("#StudentExcelInput").hide();
	});
});
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
				$("#StudentFormData").hide();   
				$("#StudentFormData_UpdateBtn").hide();
				$("#StudentFormData_ConfirmBtn").hide();
				$("#StudentExcelInput").hide();
				show();
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
			var sid = document.getElementById("sid").value;
			var sname = document.getElementById("sname").value;
			var sex = document.getElementById("sex").value;
			var classes =document.getElementById("classes").value;
			var age = document.getElementById("age").value;
			var tel = document.getElementById("tel").value;
			var address = document.getElementById("address").value;
			var e_mail = document.getElementById("e_mail").value;
			alert(sid + sname + sex + classes + age + tel + address + e_mail);
			$.ajax("${pageContext.request.contextPath}/updateStudent",// 发送请求的URL字符串。
					{
				type : "post", //  请求方式 POST或GET
				data:{
					'sid':tid,
					'sname':tname,
					'sex':sex,
					'classes':classes,
					'age':age,
					'tel':tel,
					'address':address,
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
					//	alert(resq.msg);
					}else{
						alert("添加成功");
						$("#StudentFormData").hide();   
						$("#StudentFormData_UpdateBtn").hide();
						$("#StudentFormData_ConfirmBtn").hide();
						$("#StudentExcelInput").hide();
						show();
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
			var sid = document.getElementById("sid").value;
			var sname = document.getElementById("sname").value;
			var sex = document.getElementById("sex").value;
			var classes =document.getElementById("classes").value;
			var age = document.getElementById("age").value;
			var tel = document.getElementById("tel").value;
			var address = document.getElementById("address").value;
			var e_mail = document.getElementById("e_mail").value;
			alert(sid + sname + sex + classes + age + tel + address + e_mail);
			$.ajax("${pageContext.request.contextPath}/updateStudent",// 发送请求的URL字符串。
					{
				type : "post", //  请求方式 POST或GET
				data:{
					'sid':tid,
					'sname':tname,
					'sex':sex,
					'classes':classes,
					'age':age,
					'tel':tel,
					'address':address,
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
						$("#StudentFormData").hide();   
						$("#StudentFormData_UpdateBtn").hide();
						$("#StudentFormData_ConfirmBtn").hide();
						$("#StudentExcelInput").hide();
						show();
					}
				},
				error : function(data){
					
				}
			});
		}
	}
}
function deleteStudent(id){
	if (confirm("确定删除？")) {
		$.ajax("${pageContext.request.contextPath}/deleteStudent",// 发送请求的URL字符串。
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
					show();
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
	var selected = new Array();
	for(var i=1,num=0; i <= page; i++){
		for(var j=0; j < array[i].length; j++){
			if(array[i][j] != 0) {
				flag = true;
				selected[num++] = array[i][j];
			} 
		}
	}
	if(!flag){
		alert("请最少选择一项！");
	}
	else {
		if (confirm("确定删除？")) {
			$.ajax("${pageContext.request.contextPath}/deleteStudents",// 发送请求的URL字符串。
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
						show();
					}
				},
				error : function(data){
					
				}
			});
		}
	}
}
function validateForm() {
	var sid = document.getElementById("sid").value;
	var sname = document.getElementById("sname").value;
	var sex = document.getElementById("sex").value;
	var classes = document.getElementById("classes").value;
    var reg = /^[0-9]+$/; 
    if(sid == "" || !reg.test(sid) ){ 
    	alert('学号不能为空且只能为数字！'); 
    	return false; 
    } 
    var reg=/^[\u0391-\uFFE5]+$/; 
    if(sname == "" || !reg.test(sname) ){ 
    	alert('姓名不能为空且只能为中文！');
    	return false; 
    } 
    var reg = /^男$|^女$/; 
    if(sex == '' || !reg.test(sex)){ 
    	alert('性别不能为空且只能为男或女');
    	return false; 
    }
    if(classes == ""){
    	alert("班级不能为空");
    	return false;
    }
    return true
}
$(function(){

	$("#project2").on("click", "li", function(){
		selectFlag = false;
		$("#sex").val($(this).text());
	});
});//下拉菜单选中的值赋值给input输入框！



</script>
<div  class="panel panel-primary" style="height:700px;width:100%;">
<div class="panel-heading" style="height:auto;">
当前位置：管理员>学生信息管理
</div>

<div class="panel-body" style="height:1000px;" >
<div class="btn-group" style=" width:15%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">姓名</button>
      </span>
      <input type="text" class="form-control" id="select_input_name" onchange="inputChange()">
    </div>
</div>
<div class="btn-group" style=" width:15%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
      	<button class="btn btn-default" type="button">学号</button>
      </span>
      <input type="text" class="form-control" id="select_input_id" onchange="inputChange()">
    </div>
</div>
<div class="btn-group" style=" width:26%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
  	<span class="input-group-btn">
  	<button class="btn btn-default   type="button">班级
  	
  	</button>
  		
  	</span>
  	<input type="text" class="form-control" id="select_input_classes"  onchange="inputChange()">
    </div>
</div>
<div class="btn-group" style=" width:7%; height:10%;  float:left; margin-left:1%;">
  <button type="button" class="btn btn-warning" id="selectStudentBtn" onclick="selectStudent()">
  <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
  </button>
</div>
<div class="btn-group" style=" width:15%; height:10%;  float:left; margin-left:1%;">
  <button type="button" id="DeleteStudentBtn" class="btn btn-danger">
  <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除学生信息
  </button>
</div>
<div class="btn-group" style=" width:15%; height:10%;  float:left; margin-left:1%;">
  <button type="button" id="AddStudentBtn" class="btn btn-primary">
  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加学生信息
  </button>
</div>

<table id="StudentTable" class="table table-striped table-bordered table-hover  table-condensed"style="position:absolute;width:95%;margin-top:4%;">
<thead>
	<th id="selectall"style="display:none;"><input type="checkbox" onclick="selectAll(this)" >全选</th>
	<th>学号</th>
	<th>姓名</th>
	<th>性别</th>
	<th>班级</th>
	<th>年龄</th>
	<th>联系电话</th>
	<th>家庭住址</th>
	<th>电子邮箱</th>
	<th><center>操作</center></th> 
</thead>
<tbody id="tableList">
	
</tbody>
<tfoot >
    <tr>
        <td colspan="5" align="center">	
        	共<input style="width: 20px;" id="totleRow" disabled="disabled"/>条|每页10条|
            <span id="firstPage" style="color:red;font-weight:bold" onclick="firstPage()"><a href="#">首页</a></span>|
            <span id="prePage" style="color:red;font-weight:bold" onclick="prePage()"><a href="#">上一页</a></span>|
            <span id="nextPage" style="color:red;font-weight:bold" onclick="nextPage()"><a href="#">下一页</a></span>|
            <span id="lastPage" style="color:red;font-weight:bold" onclick="lastPage()"><a href="#">最后一页</a></span>|
            <span style="font-weight:bold">
            	当前第<input style="width: 20px;" id="nowPage" type="text" value="1" disabled="disabled"/>页
            </span>|
           	 	共<input style="width: 20px;" id="totlePage" disabled="disabled"/>页|
            <span style="color:bluefont-weight:bold">
            	跳转至第<input style="width: 20px;" id="jumpPage" type="text" value="1"/>页</span>
            <button onclick="jump()" class="btn btn-success">确定</button>
        </td>
    </tr>
    </tfoot>
</table>

<div id="StudentFormData"  style="display:none;border-radius:30px;background-color:GhostWhite;margin-top:4%;margin-left:15%;position:absolute;width:60%;height:60%;">
	
		<div class="input-group" style=" width:47%;   float:left; margin-left:2%;margin-top:10%;">
			<span class="input-group-btn">
			<button class="btn btn-default" type="button">学号</button>
			</span>
			<input id="sid" type="text" class="form-control" >
		</div><!-- /input-group -->

	
		<div class="input-group" style=" width:47%;   float:left; margin-left:2%;margin-top:10%;">
			<span class="input-group-btn">
			<button class="btn btn-default" type="button">姓名</button>
			</span>
			<input id="sname" type="text" class="form-control" >
    	</div><!-- /input-group -->

    
    	<div class="input-group" style=" width:47%;   float:left; margin-left:2%;margin-top:5%">
    		<div class="input-group-btn">
    		<button class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false" type="button">性别<span class="caret"></span></button>
    		<ul id="project2" class="dropdown-menu" >
	    		    <li><a href="#">男</a></li>
	    		    <li><a href="#">女</a></li>
                    </ul>
    		
    		</div>
    		<input id="sex" type="text" class="form-control" disabled="true">
    	</div><!-- /input-group -->
    
   
    		<div class="input-group" style=" width:47%;   float:left; margin-left:2%;margin-top:5%">	
    		<div class="input-group-btn">
    		<button type="button" class="btn btn-default" >班级</button>
	    		 
	    		   
	       </div>
    		<input id="classes" type="text" class="form-control" >
    	</div><!-- /input-group -->
   
    	<div class="input-group"style=" width:47%;   float:left; margin-left:2%;margin-top:5%">
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">年龄</button>
    		</span>
    		<input id="age" type="text" class="form-control" >
    	</div><!-- /input-group -->
   
    	<div class="input-group"style=" width:47%;   float:left; margin-left:2%;margin-top:5%" >
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">联系电话</button>
    		</span>
    		<input id="tel" type="text" class="form-control" >
    	</div><!-- /input-group -->
    	
    	<div class="input-group"style=" width:47%;   float:left; margin-left:2%;margin-top:5%" >
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">家庭住址</button>
    		</span>
    		<input id="address" type="text" class="form-control" >
    	</div><!-- /input-group -->
   
    	<div class="input-group" style=" width:47%;float:left;margin-left:2%;margin-top:5%">
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">电子邮箱</button>
    		</span>
    		<input id="e_mail" type="text" class="form-control" >
    	</div><!-- /input-group -->
  
    <button type="button" id="StudentFormData_ConfirmBtn" style="display:none;width:15%;   float:left; margin-left:35%;margin-top:8%" class="btn btn-success" onclick="putFormData()">添加</button>
    <button type="button" id="StudentFormData_UpdateBtn" style="display:none;width:15%;  float:left; margin-left:35%;margin-top:8%" class="btn btn-success"   onclick="updateFormData()">修改</button>
    <button type="button" id="StudentFormData_CancelBtn" style="width:15%;   float:left; margin-left:5%;margin-top:8%" class="btn btn-warning">取消</button>
   
    <div class="form-group" id="StudentExcelInput" class="col-sm-10" style="display:none;float:left;margin-left:10%;margin-top:4%;">
	   通过excel表单添加：
	<input id="StudentExcelFile" name="excelFile" type="file" class="form-control" style="width:45%; display: inline;" />
	<input id="insertStudentExcelButton" type="button" class="btn btn-primary" onclick="inputStudent()" style="width: 15%;height: 35px;" value="上传" />
	</div>
</div>
<div id="MultiDelete"style="display:none;margin-top:580px;margin-left:40%;">
	<button type="button" id="MultiDeleteBtn"  class="btn btn-danger"  onclick="deleteFormData()">批量删除</button>
	<button type="button" id="MultiDeleteCancelBtn" class="btn btn-warning"  >取消</button>
</div>

</div>
</div>