<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<<script type="text/javascript">
var allCount;//记录总数
var page;//总页数
var pageNumber = 1;//默认起始记录
var pageSize = 10;//每页记录数
var selectFlag = false;
var deleteFlag = false;
var array = new Array();

$(document).ready(function () {
	console.log("ajaxRequest");
	$.ajax("${pageContext.request.contextPath}/_department",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		success : function(data) {
			var jsonObj = eval('(' + data + ')');
			tablePro(jsonObj["listDepartments"]);
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
	var id = "", name = "";
	if(selectFlag){
		id = document.getElementById("select_input_id").value;
		name = document.getElementById("select_input_name").value;
		
	}
	$.ajax("${pageContext.request.contextPath}/totlePageDepartment",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		async:  false , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
		data:{
			"id":id,
			"name":name
			
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
	var id = "", name = "";
	if(selectFlag){
		id = document.getElementById("select_input_id").value;
		name = document.getElementById("select_input_name").value;

	}
	$.ajax("${pageContext.request.contextPath}/getDepartments",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:{
			"id":id,
			"name":name,
			"pageNumber":$("#nowPage").val(),
			"Size":10
		},
		success : function(data) {
			$("#tableList").empty();
			tablePro(data);
		},
		error : function(data){
			
		}
	});
	getTotle();
}

function tablePro(data){
	var selected_num = 0;
	for(var index in data){
		var address,tel;
		if(data[index]["address"]==null){
			address = '';
		}else{
			address = data[index]["address"];
		}
		if(data[index]["tel"] == 0){
			tel = '';
		}else{
			tel = data[index]["tel"];
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
		"<td name=\"select\" " + td_style + "><input name=\"ids\" type=\"checkbox\" value='{\"id\":"+ data[index]["id"] +"}' " + input_checked + "></td>" + 
		"<td>"+ data[index]["id"] +"</td>" +
		"<td>"+ data[index]["name"] +"</td>" +
		"<td>"+ address +"</td>" +
		"<td>"+ tel +"</td>" +
		"<td>" +
			"<center>" +
			"<button type=\"button\" name=\"Update\" class=\"btn btn-info\" " + updata_style + " onclick='updateDepartment(\"" + data[index]["id"] + "\", \"" + data[index]["name"] + "\"," + 
			"\"" + address + "\", \"" + tel + "\")'>" + 
			"<span class=\"glyphicon glyphicon-wrench\" aria-hidden=\"true\"></span>修改" +
			"</button>" + 
			"<button type=\"button\" name=\"Delete\" class=\"btn btn-danger\" " + delete_style + " onclick=\"deleteDepartment(" + data[index]["id"] + ")\">" +
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
function selectDepartment(){
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
	var table = $("#DepartmentInfoTable");
	$("#DeleteDepartmentBtn").click(function(){
		deleteFlag = true;
		$("#DepartmentInfoTable").hide();
		$("#DepartmentFormData").hide();
		$("button[name='Update']").hide();
		$("#selectall").show();
		$("td[name='select']").show();
		$("button[name='Delete']").show();
		$("#MultiDelete").show();
		$("#DepartmentInfoTable").show();
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
		$("#DepartmentInfoTable").hide();
		$("button[name='Update']").show();
		$("#selectall").hide();
		$("td[name='select']").hide();
		$("button[name='Delete']").hide();
		$("#MultiDelete").hide();
		$("#DepartmentInfoTable").show();
	});
	$("#AddDepartmentBtn").click(function(){
		document.getElementById('deid').removeAttribute('disabled');
		setFormData("", "", "", "");
		$("#DepartmentFormData").hide();
		$("#DepartmentFormData_UpdateBtn").hide();
		$("#DepartmentExcelInput").show();
		$("#DepartmentFormData_ConfirmBtn").show();
		$("#DepartmentFormData").show(); 
	});
});
function setFormData(id, name, address, tel){
	document.getElementById("deid").value = id;
	document.getElementById("dename").value = name;
	document.getElementById("address").value = address;
	document.getElementById("tel").value = tel;
}
function updateDepartment(id, name, address, tel) {
	document.getElementById('deid').setAttribute('disabled', 'disabled')
	setFormData(id, name, address, tel);
	var div = $("#DEpartmentFormData");
	$("#DepartmentFormData").hide();
	$("#DepartmentFormData_ConfirmBtn").hide();
	$("#DepartmentExcelInput").hide();
	$("#DepartmentFormData_UpdateBtn").show();
	$("#DepartmentFormData").show();
}
function selectAll(choiceBtn) {
    var arr = document.getElementsByName("ids")
    for(var i=0;i<arr.length;i++){
        arr[i].checked=choiceBtn.checked
    }
}
$(function(){
	//TeacherFormData
	var div = $("#DepartmentFormData");
	$("#DepartmentFormData_CancelBtn").click(function(){
		$("#DepartmentFormData").hide();   
		$("#DepartmentFormData_UpdateBtn").hide();
		$("#DepartmentFormData_ConfirmBtn").hide();
		$("#DepartmentExcelInput").hide();
	});
	setInterval(function(){
		var arr = document.getElementsByName("ids");
		var nowPage = $("#nowPage").val();
        for(var i = 0; i<arr.length; i++){
            var checkbox = arr[i];
            if(checkbox.checked){
            	array[nowPage][i] = checkbox.value;
            //	console.log("11第" + nowPage + "页第" + i + "行value:" + array[nowPage][i]);
            }else{
            	array[nowPage][i] = 0;
            //	console.log("22第" + nowPage + "页第" + i + "行value:" + array[nowPage][i]);
            }
        }
    },500);
});
function inputDEpartment(){
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
				$("#DepartmentFormData").hide();   
				$("#DepartmentFormData_UpdateBtn").hide();
				$("#DepartmentFormData_ConfirmBtn").hide();
				$("#DepartmentExcelInput").hide();
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
			var deid = document.getElementById("deid").value;
			var dename = document.getElementById("dename").value;
			var address = document.getElementById("address").value;
			var tel =document.getElementById("tel").value;
			alert(deid + dename + address + tel);
			$.ajax("${pageContext.request.contextPath}/addDepartment",// 发送请求的URL字符串。
					{
				type : "post", //  请求方式 POST或GET
				data:{
					'deid':deid,
					'dename':dename,
					'address':address,
					'tel':tel
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
						$("#DepartmentFormData").hide();   
						$("#DepartmentFormData_UpdateBtn").hide();
						$("#DepartmentFormData_ConfirmBtn").hide();
						$("#DepartmentExcelInput").hide();
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
			var deid = document.getElementById("deid").value;
			var dename = document.getElementById("dename").value;
			var address = document.getElementById("address").value;
			var tel =document.getElementById("tel").value;
			alert(deid + dename + address + tel);
			$.ajax("${pageContext.request.contextPath}/updateDepartment",// 发送请求的URL字符串。
					{
				type : "post", //  请求方式 POST或GET
				data:{
					'deid':deid,
					'dename':dename,
					'address':address,
					'tel':tel
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
						$("#DepartmentFormData").hide();   
						$("#DepartmentFormData_UpdateBtn").hide();
						$("#DepartmentFormData_ConfirmBtn").hide();
						$("#DepartmentExcelInput").hide();
						show();
					}
				},
				error : function(data){
					
				}
			});
		}
	}
}
function deleteDepartment(id){
	if (confirm("确定删除？")) {
		$.ajax("${pageContext.request.contextPath}/deleteDepartment",// 发送请求的URL字符串。
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
			$.ajax("${pageContext.request.contextPath}/deleteDepartments",// 发送请求的URL字符串。
					{
				type : "post", //  请求方式 POST或GET
				data:{"array":selected},
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
	var deid = document.getElementById("deid").value;
	var dename = document.getElementById("dename").value;
	var address = document.getElementById("address").value;
	var tel =document.getElementById("tel").value;
    var reg = /^[0-9]+$/; 
    if(deid == "" || !reg.test(deid) ){ 
    	alert('院系编号不能为空且只能为数字！'); 
    	return false; 
    } 
    var reg=/^[\u0391-\uFFE5]+$/; 
    if(dename == "" || !reg.test(dename) ){ 
    	alert('院系名称不能为空且只能为中文！');
    	return false; 
    } 
    return true
}
</script>
 
<div  class="panel panel-primary" style="height:700px;width:100%;">
<div class="panel-heading" style="height:auto;">
当前位置：管理员>院系信息管理
</div>

<div class="panel-body" style="height:1000px;" >
<div class="btn-group" style=" width:15%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">院系编号</button>
      </span>
      <input type="text" class="form-control" id="select_input_id" onchange="inputChange()">
    </div>
</div>
<div class="btn-group" style=" width:15%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
      	<button class="btn btn-default" type="button">院系名称</button>
      </span>
      <input type="text" class="form-control" id="select_input_name" onchange="inputChange()">
    </div>
</div>

<div class="btn-group" style=" width:7%; height:10%;  float:left; margin-left:1%;">
  <button type="button" class="btn btn-warning" id="selectDepartmentBtn" onclick="selectDepartment()">
  <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
  </button>
</div>
<div class="btn-group" style=" width:15%; height:10%;  float:left; margin-left:1%;">
  <button type="button" id="DeleteDepartmentBtn" class="btn btn-danger">
  <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除院系信息
  </button>
</div>
<div class="btn-group" style=" width:15%; height:10%;  float:left; margin-left:1%;">
  <button type="button" id="AddDepartmentBtn" class="btn btn-primary">
  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加院系信息
  </button>
</div>

<table id="DepartmentInfoTable" class="table table-striped table-bordered table-hover  table-condensed"style="position:absolute;width:95%;margin-top:4%;">
<thead>
	<th id="selectall"style="display:none;"><input type="checkbox" onclick="selectAll(this)" id="select_all_input">全选</th>
	<th>院系编号</th>
	<th>院系名称</th>
	<th>地址</th>
	<th>联系电话</th>
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

<div id="DepartmentFormData"  style="display:none;border-radius:30px;background-color:GhostWhite;margin-top:4%;margin-left:15%;position:absolute;width:60%;height:60%;">
	
		<div class="input-group" style=" width:47%;   float:left; margin-left:2%;margin-top:10%;">
			<span class="input-group-btn">
			<button class="btn btn-default" type="button">院系编号</button>
			</span>
			<input id="deid" type="text" class="form-control" >
		</div><!-- /input-group -->

	
		<div class="input-group" style=" width:47%;   float:left; margin-left:2%;margin-top:10%;">
			<span class="input-group-btn">
			<button class="btn btn-default" type="button">院系名称</button>
			</span>
			<input id="dename" type="text" class="form-control" >
    	</div><!-- /input-group -->
 
  
    	<div class="input-group"style=" width:47%;   float:left; margin-left:2%;margin-top:5%" >
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">地址</button>
    		</span>
    		<input id="address" type="text" class="form-control" >
    	</div><!-- /input-group -->
   
    	<div class="input-group" style=" width:47%;float:left;margin-left:2%;margin-top:5%">
    		<span class="input-group-btn">
    		<button class="btn btn-default" type="button">联系电话</button>
    		</span>
    		<input id="tel" type="text" class="form-control" >
    	</div><!-- /input-group -->
  
    <button type="button" id="DepartmentFormData_ConfirmBtn" style="display:none;width:15%;   float:left; margin-left:35%;margin-top:8%" class="btn btn-success" onclick="putFormData()">添加</button>
    <button type="button" id="DepartmentFormData_UpdateBtn" style="display:none;width:15%;  float:left; margin-left:35%;margin-top:8%" class="btn btn-success"   onclick="updateFormData()">修改</button>
    <button type="button" id="DepartmentFormData_CancelBtn" style="width:15%;   float:left; margin-left:5%;margin-top:8%" class="btn btn-warning">取消</button>
   
    <div class="form-group" id="DepartmentExcelInput" class="col-sm-10" style="display:none;float:left;margin-left:10%;margin-top:4%;">
	   通过excel表单添加：
	<input id="DepartmentExcelFile" name="excelFile" type="file" class="form-control" style="width:45%; display: inline;" />
	<input id="insertDepartmentExcelButton" type="button" class="btn btn-primary" onclick="inputDepartment()" style="width: 15%;height: 35px;" value="上传" />
	</div>
</div>
<div id="MultiDelete"style="display:none;margin-top:580px;margin-left:40%;">
	<button type="button" id="MultiDeleteBtn"  class="btn btn-danger"  onclick="deleteFormData()">批量删除</button>
	<button type="button" id="MultiDeleteCancelBtn" class="btn btn-warning"  >取消</button>
</div>

</div>
</div>