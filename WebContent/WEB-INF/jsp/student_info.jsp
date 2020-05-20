<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	console.log("ajaxRequest");
	$.ajax("${pageContext.request.contextPath}/_studentInfo",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		success : function(data) {
			var jsonObj = eval('(' + data + ')');
			stuInfo(jsonObj["stuInfo"]);
		},
		error : function(data){
			
		}
	});
});
function stuInfo(data){
	document.getElementById("sid").value = data["id"];
	document.getElementById("sname").value = data["name"];
	if(data["sex"]["num"] == 1){
		document.getElementById("sex").value = "男";
	}else{
		document.getElementById("sex").value = "女";
	}
	document.getElementById("classes").value = data["cid"]["name"];
	document.getElementById("age").value = data["age"];
	document.getElementById("tel").value = data["tel"];
	document.getElementById("address").value = data["address"];
	document.getElementById("e_mail").value = data["e_mail"];
}
$(function(){
	$("#project2").on("click", "li", function(){
		$("#sex").val($(this).text());
	});
});//下拉菜单选中的值赋值给input输入框！

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
					'sid':sid,
					'sname':sname,
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
						location.reload();
					}
				},
				error : function(data){
					
				}
			});
		}
	}
}

</script>
 
<div  class="panel panel-primary" style="height:550px;width:100%;">
  <div class="panel-heading" style="height:auto;">
   当前位置：学生>个人信息管理
 </div>
   <div class="panel-body"  style=" height:550px;">
   <div id="StudentFormData"  style="border-radius:30px;background-color:GhostWhite;margin-left:10%;margin-top:2%;position:absolute;width:60%;">
	
		<div class="input-group" style=" width:47%;   float:left; margin-left:2%;margin-top:2%;">
			<span class="input-group-btn">
			<button class="btn btn-default" type="button">学号</button>
			</span>
			<input id="sid" type="text" class="form-control" disabled="true">
		</div><!-- /input-group -->

	
		<div class="input-group" style=" width:47%;   float:left; margin-left:2%;margin-top:2%;">
			<span class="input-group-btn">
			<button class="btn btn-default" type="button">姓名</button>
			</span>
			<input id="sname" type="text" class="form-control" disabled="true">
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
    		<button type="button" class="btn btn-default">班级</button>
	    		    
	       </div>
    		<input id="classes" type="text" class="form-control">
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
   
    	<div class="input-group" style=" width:47%;float:left; margin-left:2%;margin-top:5%">
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">家庭地址</button>
    		</span>
    		<input id="address" type="text" class="form-control" >
    	</div><!-- /input-group -->
    	
    	<div class="input-group" style=" width:47%;float:left; margin-left:2%;margin-top:5%">
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">电子邮箱</button>
    		</span>
    		<input id="e_mail" type="text" class="form-control" >
    	</div><!-- /input-group -->
  

    <button type="button" id="StudentFormData_UpdateBtn" style="width:15%;  float:left; margin-left:40%;margin-top:8%" class="btn btn-success"   onclick="updateFormData()">修改</button>
    
   
   
</div>
  
  </div>
  </div>