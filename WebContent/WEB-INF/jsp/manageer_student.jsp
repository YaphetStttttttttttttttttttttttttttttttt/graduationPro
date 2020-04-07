<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
 
<div  class="panel panel-primary" style="height:700px;width:1100px;">
  <div class="panel-heading" style="height:auto;">
   当前位置：管理员>学生信息管理
  </div>

<div class="btn-group" style=" width:25%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">学号</button>
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
        <button class="btn btn-default" type="button">班级</button>
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
  <button type="button" id="tjxsxx" class="btn btn-primary"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加学生信息
  
  </button>
</div>

  <div class="panel-body" style="height:1000px;" >
  
 
<table class="table table-striped table-bordered table-hover  table-condensed"style="position:absolute;width:1080px;margin-top:30px;">
  <thead>
     <th>学号</th>
     <th>姓名</th>
     <th>性别</th>
     <th>班级</th>
     <th>年龄</th>
     <th>联系电话	</th>
     <th>家庭地址</th>
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
        <td></td>  
        <td><center><button type="button" id="xiugai" class="btn btn-info"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>修改</button>
        <button type="button" id="shanchu" class="btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</button></center>
        </td> 
     </tr>
  </tbody>
</table>
   <div id="haha"  style="display:none;background-color:LightCyan;margin-top:70px;margin-left:140px;position:absolute;width:800px;">
   <form name="myForm" action="demo_form.php" onsubmit="return validateForm()" method="post">
  <div class="col-lg-6"style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">学号</button>
      </span>
      <input id="sid" type="text" class="form-control"   >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
<div class="col-lg-6"style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">姓名</button>
      </span>
      <input id="sname" type="text" class="form-control" >
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
  <div class="col-lg-6" style="margin-top:50px;" >
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">班级</button>
      </span>
      <input id="classes" type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">年龄</button>
      </span>
      <input  id="age" type="text" class="form-control" >
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
        <button class="btn btn-default" type="button" >家庭住址</button>
      </span>
      <input id="address" type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button" >电子邮箱</button>
      </span>
      <input id="e_mail" type="text" class="form-control" name="email" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <button type="button" id="queren" style="display:none;margin-top:50px; margin-left:350px;" class="btn btn-success" onclick="putFormData()">添加</button>
    <button type="button" id="xgai" style="display:none;margin-top:50px; margin-left:350px;" class="btn btn-success">修改</button>
    <button type="button" id="quxiao" style="margin-top:50px;" class="btn btn-warning">取消</button>
 <div class="form-group" id="StudentExcelInput" class="col-sm-10" style="float: left; margin-left: 150px;margin-top: 35px;">
 通过excel表单添加：
	<input id="StudentExcelFile" name="excelFile" type="file" class="form-control"style="width: 300px; display: inline;" />
	<input id="insertStudentExcelButton" type="button" class="btn btn-primary" onclick="inputStudent()" style="width: 60px;height: 35px;" value="上传" />
</div>
</form>
  </div>
  </div>
</div>
<script>
$(function(){
	var div = $("#haha");
	   $("#quxiao").click(function(){
	      div.hide();
	   });
	   $("#xiugai").click(function(){
	      div.show();
	   });
	  
	});
$(function(){
	var div = $("#haha");
	$("#quxiao").click(function(){
		div.hide();   
		$("#xgai").hide();
		$("#queren").hide();
		$("#StudentExcelInput").hide();
	});
	$("#xiugai").click(function(){
		div.hide(); 
		$("#queren").hide();
		$("#StudentExcelInput").hide();
		$("#xgai").show();
		div.show();
	});
	$("#tjxsxx").click(function(){
		div.hide(); 
		$("#xgai").hide();
		$("#StudentExcelInput").show();
		$("#queren").show();
		div.show(); 
	});
});
$(document).ready(function(){
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
			}
		},
		error : function(data){
			
		}
	});
}

function putFormData(){
	var flag = StudentvalidateForm();
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
			$.ajax("${pageContext.request.contextPath}/addTeacher",// 发送请求的URL字符串。
					{
				type : "post", //  请求方式 POST或GET
				data:{
					'sid':sid,
					'sname':sname,
					'sex':sex,
					'classes':classes,
					'age':age ,
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
function StudentvalidateForm() {
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
    	alert("所属班级不能为空");
    	return false;
    }
    return true
}



</script>
