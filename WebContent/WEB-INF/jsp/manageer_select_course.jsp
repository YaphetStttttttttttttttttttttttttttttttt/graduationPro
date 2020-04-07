<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
 
<div  class="panel panel-primary" style="height:700px;width:1100px;">
  <div class="panel-heading" style="height:auto;">
   当前位置：管理员>选课信息管理
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
        <button class="btn btn-default" type="button">课程编号</button>
      </span>
      <input type="text" class="form-control" >
    </div>
</div>
<div class="btn-group" style=" width:2%; height:10%;  float:left; margin-left:12%;">
  <button type="button" class="btn btn-warning"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询</button>
</div>
<div class="btn-group" style=" width:auto; height:auto;  float:left; margin-left:25%;">
   <button type="button" id="tjxkxx" class="btn btn-primary"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加选课信息
  </button>
</div>

  <div class="panel-body" style="height:1000px;" >
  
 
<table class="table table-striped table-bordered table-hover  table-condensed"style="position:absolute;width:1080px;margin-top:30px;">
  <thead>
     <th>学号</th>
     <th>课程编号</th>
     <th>授课教师</th>
     <th>上课时间及地点</th>
     <th>期末成绩</th>
     <th>补考成绩	</th>
     <th>重修成绩</th>
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

  
   <div id="haha"  style="display:none;background-color:LightCyan;margin-top:70px;margin-left:140px;position:absolute;width:800px;">
   <form name="myForm" action="demo_form.php" onsubmit="return validateForm()" method="post">
 <div class="col-lg-6"style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">学号</button>
      </span>
      <input type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
<div class="col-lg-6"style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">课程编号</button>
      </span>
      <input type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">授课教师</button>
      </span>
      <input type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">上课时间及地点</button>
      </span>
      <input type="text" class="form-control">
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">期末成绩</button>
      </span>
      <input type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">补考成绩</button>
      </span>
      <input type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">重修成绩</button>
      </span>
      <input type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <button type="button" id="queren" style="display:none;margin-top:50px; margin-left:350px;" class="btn btn-success" onclick="putFormData()">添加</button>
    <button type="button" id="xgai" style="display:none;margin-top:50px; margin-left:350px;" class="btn btn-success">修改</button>
    <button type="button" id="quxiao" style="margin-top:50px;" class="btn btn-warning">取消</button>
  
 <div class="form-group" id="Select_Course" class="col-sm-10" style="float: left; margin-left: 200px;margin-top: 35px;">
 通过excel表单添加：
	<input id="Select_CourseExcelFile" name="excelFile" type="file" class="form-control" style="width: 300px; display: inline;" />
	<input id="insertSelect_CourseExcelButton" type="button" class="btn btn-primary" onclick="inputSelect_Course()" style="width: 60px;height: 35px;" value="上传" />
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
		$("#Select_Course").hide();
	});
	$("#xiugai").click(function(){
		div.hide();
		$("#queren").hide();
		$("#Select_Course").hide();
		$("#xgai").show();
		div.show();
	});
	$("#tjxkxx").click(function(){
		div.hide();
		$("#xgai").hide();
		$("#Select_Course").show();
		$("#queren").show();
		div.show(); 
	});
	
});

$(document).ready(function(){
});

function inputSelect_Course(){
	var formData = new FormData();
	var name = $("#Select_CourseExcelFile").val();
	formData.append("file",$("#Select_CourseExcelFile")[0].files[0]);
	formData.append("name",name);//这个地方可以传递多个参数
	$.ajax("${pageContext.request.contextPath}/addSelect_CourseExcel",// 发送请求的URL字符串。
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