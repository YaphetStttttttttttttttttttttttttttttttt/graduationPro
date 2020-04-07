<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
 
<div  class="panel panel-primary" style="height:700px;width:1100px;">
  <div class="panel-heading" style="height:auto;">
   当前位置：管理员>课程信息管理
  </div>

<div class="btn-group" style=" width:25%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">课程编号</button>
      </span>
      <input type="text" class="form-control" >
    </div>
</div>
<div class="btn-group" style=" width:20%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">课程名称</button>
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
<button type="button" id="tjkcxx" class="btn btn-primary"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加课程信息
  
  </button>
</div>
  <div class="panel-body" style="height:1000px;" >
  
 
<table class="table table-striped table-bordered table-hover  table-condensed"style="position:absolute;width:1080px;margin-top:30px;">
  <thead>
     <th>课程编号</th>
     <th>课程名称</th>
     <th>课程性质</th>
     <th>学分</th>
     <th>学时</th>
     <th>开课学年</th>
     <th>开课学期</th>
     <th>所属院系</th>
     <th>所属专业</th>
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
        <td></td> 
        <td><center><button type="button" id="xiugai" class="btn btn-info"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>修改</button>
        <button type="button" id="shanchu" class="btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</button></center>
        </td>
     </tr>
  </tbody>
</table>

   <div id="haha"  style="display:none;background-color:LightCyan;margin-top:50px;margin-left:140px;position:absolute;width:800px;">
   <form name="myForm" action="demo_form.php" onsubmit="return validateForm()" method="post">
   <div class="col-lg-6"style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">课程编号</button>
      </span>
      <input id="cid" type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
<div class="col-lg-6"style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">课程名称</button>
      </span>
      <input id="cname"type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">课程性质</button>
      </span>
      <input id="type"type="text" class="form-control">
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">学分</button>
      </span>
      <input id="credit" type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">学时</button>
      </span>
      <input id="cycle" type="text" class="form-control"  >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">开课学年</button>
      </span>
      <input id="start_year" type="text" class="form-control"  >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">开课学期</button>
      </span>
      <input id="start_term"  type="text" class="form-control">
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">所属院系</button>
      </span>
      <input id="department" type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">所属专业</button>
      </span>
      <input id="discipline" type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <button type="button" id="queren" style="display:none;margin-top:50px; margin-left:350px;" class="btn btn-success" onclick="putFormData()">添加</button>
    <button type="button" id="xgai" style="display:none;margin-top:50px; margin-left:350px;" class="btn btn-success">修改</button>
    <button type="button" id="quxiao" style="margin-top:50px;" class="btn btn-warning">取消</button>
<div class="form-group" id="CourseExcelInput" class="col-sm-10" style="float: left; margin-left: 150px;margin-top: 35px;">
	通过excel表单添加：
	<input id="courseExcelFile" name="excelFile" type="file" class="form-control" style="width: 300px; display: inline;" />
	<input id="insertCourseExcelButton" type="button" class="btn btn-primary" onclick="inputFile()" style="width: 60px;height: 35px;" value="上传" />
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
		$("#xgai").hide();
		$("#queren").hide();
		$("#CourseExcelInput").hide();
	});
	$("#xiugai").click(function(){
		div.hide();
		$("#queren").hide();
		$("#CourseExcelInput").hide();
		$("#xgai").show();
		div.show();
	});
	$("#tjkcxx").click(function(){
		div.hide();
		$("#xgai").hide();
		$("#CourseExcelInput").show();
		$("#queren").show();
		div.show(); 
	});
	
});
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
function CoursevalidateForm() {
	var cid = document.getElementById("cid").value;
	var cname = document.getElementById("cname").value;
	var type = document.getElementById("type").value;
	var credit = document.getElementById("credit").value;
	var cycle = document.getElementById("cycle").value;
	var department = document.getElementById("department").value;
	var start_year = document.getElementById("start_year").value;
	var start_term = document.getElementById("start_term").value;
    var reg = /^[0-9]+$/; 
    if(cid == "" || !reg.test(cid) ){ 
    	alert('课程编号不能为空且只能为数字！'); 
    	return false; 
    } 
    var reg=/^[\u0391-\uFFE5]+$/; 
    if(cname == "" || !reg.test(cname) ){ 
    	alert('课程名称不能为空且只能为中文！');
    	return false; 
    } 
    var reg = /^必修课$&^选修课$&^限选课$&^任选课$&^公共课$/; 
    if(sex == '' || !reg.test(sex)){ 
    	alert('课程性质不能为空且只能为必修课，选修课,限选课,任选课,公共课');
    	return false; 
    }
    var reg = /^[0-9]+$/;
    if(credit == "" || !reg.test(credit) ){ 
    	alert('学分不能为空且只能为数字！'); 
    	return false; 
    } 
    var reg = /^[0-9]+$/;
    if(cycle == "" || !reg.test(cycle) ){ 
    	alert('学时不能为空且只能为数字！'); 
    	return false; 
    }
    var reg = /^[0-9]+$/;
    if(start_year == "" || !reg.test(start_year)  ){ 
    	alert('开课学年不能为空且只能为数字！'); 
    	return false; 
    }
    var reg = /^上学期$|^下学期$/;
    if(start_term == "" || !reg.test(start_term) ){ 
    	alert('开课学期不能为空且只能为上学期或下学期！'); 
    	return false; 
    }
   
    if(department == ""){
    	alert("所属院系不能为空");
    	return false;
    }
    return true
}
function putFormData(){
	var flag = CoursevalidateForm();
	if(flag){
		if(confirm("确定添加？")){
			var cid = document.getElementById("cid").value;
			var cname = document.getElementById("cname").value;
			var type = document.getElementById("type").value;
			var credit = document.getElementById("credit").value;
			var cycle = document.getElementById("cycle").value;
			var start_year = document.getElementById("start_year").value;
			var start_term = document.getElementById("start_term").value;
			var department =document.getElementById("department").value;
			var discipline = document.getElementById("discipline").value;
			alert(cid + cname + type + credit + cycle + start_year + start_term + department+ discipline);
			$.ajax("${pageContext.request.contextPath}/addCourse",// 发送请求的URL字符串。
					{
				type : "post", //  请求方式 POST或GET
				data:{
					'cid':cid,
					'cname':cname,
					'type':type,
					'credit':credit,
					'cycle':cycle,
					'start_year':start_year,
					'start_term':start_term,
					'department':department,
					'discipline':discipline
					
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
						$("#CourseExcelInput").hide();
					}
				},
				error : function(data){
					
				}
			});
		}
	}
}
</script>