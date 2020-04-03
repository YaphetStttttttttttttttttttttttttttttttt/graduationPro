<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
 
<div  class="panel panel-primary" style="height:700px;width:1100px;">
  <div class="panel-heading" style="height:auto;">
   当前位置：管理员>院系信息管理
  </div>

<div class="btn-group" style=" width:25%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">院系编号	</button>
      </span>
      <input type="text" class="form-control" >
    </div>
</div>
<div class="btn-group" style=" width:20%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">院系名称</button>
      </span>
      <input type="text" class="form-control" >
    </div>
</div>

<div class="btn-group" style=" width:2%; height:10%;  float:left; margin-left:12%;">
  <button type="button" class="btn btn-warning">查询</button>
</div>
<div class="btn-group" style=" width:auto; height:auto;  float:left; margin-left:25%;">
  <button type="button" id="tjxsxx">添加院系信息</button>
</div>

  <div class="panel-body" style="height:1000px;" >
  
 
<table class="table table-striped table-bordered table-hover  table-condensed"style="position:absolute;width:1080px;margin-top:30px;">
  <thead>
     <th>院系编号</th>
     <th>院系名称</th>
     <th>地址</th>
     <th>电话</th>
     <th><center>操作</center></th>
   
  </thead>
  <tbody>
     <tr>
        <td></td>
        <td></td>  
        <td></td>  
        <td></td>  
         
        <td><center><button type="button" id="xiugai">修改</button><button type="button" id="shanchu">删除</button></center></td> 
     </tr>
  </tbody>
</table>

  <div id="haha"  style="display:none;background-color:LightCyan;margin-top:110px;margin-left:140px;position:absolute;width:800px;">
   
  <div class="col-lg-6"style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">院系编号</button>
      </span>
      <input type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
<div class="col-lg-6"style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">院系名称</button>
      </span>
      <input type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">地址</button>
      </span>
      <input type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">电话</button>
      </span>
      <input type="text" class="form-control">
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <button type="button" id="queren" style="margin-top:50px;margin-left:350px;">确认</button>
  <button type="button" id="quxiao" style="margin-top:50px;">取消</button>
  </div>
   <div id="heihei"  style="display:none;background-color:LightCyan;margin-top:70px;margin-left:140px;position:absolute;width:800px;">
   <form name="myForm" action="demo_form.php" onsubmit="return validateForm()" method="post">
  <div class="col-lg-6"style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">院系编号</button>
      </span>
      <input type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
<div class="col-lg-6"style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">院系名称</button>
      </span>
      <input type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">地址</button>
      </span>
      <input type="text" class="form-control" >
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <div class="col-lg-6" style="margin-top:50px;">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">电话</button>
      </span>
      <input type="text" class="form-control">
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
  <input type="submit" value="添加" style="margin-top:50px;margin-left:350px; ">
  <button type="button" id="quxiao1" style="margin-top:50px;">取消</button>
  
 <div class="form-group" id="Student" class="col-sm-10" style="float: left; margin-left: 200px;">
 通过excel表单添加：
	<input id="StudentExcelFile" name="excelFile" type="file" class="form-control" style="width: 300px; display: inline;" />
	<input id="insertStudentExcelButton" type="button" onclick="inputStudent()" style="width: 60px;height: 35px;" value="上传" />
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
	var div = $("#heihei");
	   $("#quxiao1").click(function(){
	      div.hide();
	   });
	   $("#tjxsxx").click(function(){
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