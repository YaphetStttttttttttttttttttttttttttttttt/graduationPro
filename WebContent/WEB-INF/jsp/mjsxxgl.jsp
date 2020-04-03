<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
 
<div  class="panel panel-primary" style="height:700px;width:1100px;">
<div class="panel-heading" style="height:auto;">
当前位置：管理员>教师信息管理
</div>

<div class="btn-group" style=" width:25%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">教师编号</button>
      </span>
      <input type="text" class="form-control" >
    </div>
</div>
<div class="btn-group" style=" width:20%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">姓名</button>
      </span>
      <input type="text" class="form-control" >
    </div>
</div>
<div class="btn-group" style=" width:20%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">所属院系</button>
      </span>
      <input type="text" class="form-control" >
    </div>
</div>
<div class="btn-group" style=" width:2%; height:10%;  float:left; margin-left:1%;">
  <button type="button" class="btn btn-warning">
<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
</button>
</div>
<div class="btn-group" style=" width:auto; height:auto;  float:left; margin-left:15%;">
  <button type="button" id="tjjsxx" class="btn btn-primary"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加教师信息
  </button>
</div>

<div class="panel-body" style="height:1000px;" >

<table class="table table-striped table-bordered table-hover  table-condensed"style="position:absolute;width:1080px;margin-top:30px;">
  <thead>
     <th>教师编号</th>
     <th>姓名</th>
     <th>性别</th>
     <th>所属院系</th>
     <th>职称</th>
     <th>联系电话	</th>
     <th>电子邮箱</th>
     <th><center>操作</center></th>
   
  </thead>
  <tbody>
     <tr>
        <td></td>  
        <td></td>  
        <td></td>  
        <td></td>
        <td></td>  
        <td></td>  
        <td></td>    
        <td><center><button type="button" id="xiugai" class="btn btn-info"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>修改</button>
        <button type="button" id="shanchu" class="btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</button></center>
        </td> 
     </tr>
  </tbody>
</table>
<div id="haha"  style="display:none;background-color:LightCyan;margin-top:110px;margin-left:140px;position:absolute;width:800px;">
	<div class="col-lg-6"style="margin-top:50px;">
		<div class="input-group">
			<span class="input-group-btn">
			<button class="btn btn-default" type="button">教师编号</button>
			</span>
			<input id="tid" type="text" class="form-control" required="required">
		</div><!-- /input-group -->
	</div><!-- /.col-lg-6 -->
	<div class="col-lg-6"style="margin-top:50px;">
		<div class="input-group">
			<span class="input-group-btn">
			<button class="btn btn-default" type="button">姓名</button>
			</span>
			<input id="tname" type="text" class="form-control" required="required">
    	</div><!-- /input-group -->
    </div><!-- /.col-lg-6 -->
    <div class="col-lg-6" style="margin-top:50px;">
    	<div class="input-group">
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">性别</button>
    		</span>
    		<input id="sex" type="text" class="form-control" >
    	</div><!-- /input-group -->
    </div><!-- /.col-lg-6 -->
    <div class="col-lg-6" style="margin-top:50px;">
    	<div class="input-group">
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">所属院系</button>
    		</span>
    		<input id="department" type="text" class="form-control">
    	</div><!-- /input-group -->
    </div><!-- /.col-lg-6 -->
    <div class="col-lg-6" style="margin-top:50px;">
    	<div class="input-group">
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">职称</button>
    		</span>
    		<input id="title" type="text" class="form-control" >
    	</div><!-- /input-group -->
    </div><!-- /.col-lg-6 -->
    <div class="col-lg-6" style="margin-top:50px;">
    	<div class="input-group">
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">联系电话</button>
    		</span>
    		<input id="tel" type="text" class="form-control" >
    	</div><!-- /input-group -->
    </div><!-- /.col-lg-6 -->
    <div class="col-lg-6" style="margin-top:50px;">
    	<div class="input-group">
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">电子邮箱</button>
    		</span>
    		<input id="e_mail" type="text" class="form-control" >
    	</div><!-- /input-group -->
    </div><!-- /.col-lg-6 -->
    <button type="button" id="queren" style="display:none;margin-top:50px; margin-left:350px;" class="btn btn-success" onclick="putFormData()">添加</button>
    <button type="button" id="xgai" style="display:none;margin-top:50px; margin-left:350px;" class="btn btn-success">修改</button>
    <button type="button" id="quxiao" style="margin-top:50px;" class="btn btn-warning">取消</button>
    
    <div class="form-group" id="TeacherExcelInput" class="col-sm-10" style="display:none;float: left; margin-left: 150px;margin-top: 35px;">
		通过excel表单添加：
		<input id="TeacherExcelFile" name="excelFile" type="file" class="form-control" style="width: 300px; display: inline;" />
		<input id="insertTeacherExcelButton" type="button" class="btn btn-primary" onclick="inputTeacher()" style="width: 60px;height: 35px;" value="上传" />
	</div>
</div>

</div>
</div>
<script>
$(document).ready(function(){
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
				$("#haha").hide();   
				$("#xgai").hide();
				$("#queren").hide();
				$("#TeacherExcelInput").hide();
			}
		},
		error : function(data){
			
		}
	});
}
$(function(){
	var div = $("#haha");
	$("#quxiao").click(function(){
		div.hide();   
		$("#xgai").hide();
		$("#queren").hide();
		$("#TeacherExcelInput").hide();
	});
	$("#xiugai").click(function(){
		div.hide(); 
		$("#queren").hide();
		$("#TeacherExcelInput").hide();
		$("#xgai").show();
		div.show();
	});
	$("#tjjsxx").click(function(){
		div.hide(); 
		$("#xgai").hide();
		$("#TeacherExcelInput").show();
		$("#queren").show();
		div.show(); 
	});
});
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
			alert(tid + tname + sex + department + title + tel + e_mail);
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
						$("#haha").hide();   
						$("#xgai").hide();
						$("#queren").hide();
						$("#TeacherExcelInput").hide();
					}
				},
				error : function(data){
					
				}
			});
		}
	}
}
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
</script>