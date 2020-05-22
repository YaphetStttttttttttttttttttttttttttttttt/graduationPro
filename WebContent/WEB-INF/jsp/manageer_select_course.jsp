<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
var allCount;//记录总数
var page;//总页数
var pageNumber = 1;//默认起始记录
var pageSize = 10;//每页记录数

$(document).ready(function () {
	show();
});
function getTotle(){
	var sid = document.getElementById("sid").value;
	var cpid = document.getElementById("cpid").value;
	$.ajax("${pageContext.request.contextPath}/manageerTotlePageStuList",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:{
			"sid":sid,
			"cpid":cpid
		},
		async:  false , // 默认设置下，所有请求均为异步请求。如果设置为false，则发送同步请求
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
	var sid = document.getElementById("sid").value;
	var cpid = document.getElementById("cpid").value;
	$.ajax("${pageContext.request.contextPath}/manageerStuList",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:{
			"sid":sid,
			"cpid":cpid,
			"pageNumber":$("#nowPage").val()
		},
		success : function(data) {
			$("#tableList").empty();
			var jsonObj = eval('(' + data + ')');
			tablePro(jsonObj["listStudents"]);
		},
		error : function(data){
			
		}
	});
	getTotle();
}
function tablePro(data, times){
	for(var index in data){
		var yearName = ['上学期','下学期'];
		var tp = data[index]["cpid"]["time_place1"]["schoolYear"] + "年" + yearName[data[index]["cpid"]["time_place1"]["schoolTerm"]["termNum"] - 1];
		var array = [data[index]["sid"]["id"], data[index]["cpid"]["id"]];
		var nowPage = $("#nowPage").val()
		html =  
		"<tr>" +
		"<td>" + tp + "</td>" + 
		"<td>" + data[index]["cpid"]["id"] + "</td>" + 
		"<td>" + data[index]["cpid"]["cid"]["name"] + "</td>" + 
		"<td>" + data[index]["sid"]["id"] + "</td>" + 
		"<td>" + data[index]["sid"]["name"] + "</td>" + 
		"<td><a href=\"javascript:void(0);\" onclick=\"updateTGrade(" + array + ")\">" + data[index]["tGrade"] + "</a></td>" + 
		"<td><a href=\"javascript:void(0);\" onclick=\"updateFGrade(" + array + ")\">" + data[index]["fGrade"] + "</a></td>" + 
		"<td><a href=\"javascript:void(0);\" onclick=\"updateSGrade(" + array + ")\">" + data[index]["sGrade"] + "</a></td>" + 
		"</tr>";
		$("#tableList").append(html);
	}
}
function selectSC(){
	show();
}
function updateFGrade(sid,cpid){
	var score=prompt("输入期末成绩","");
	$.ajax("${pageContext.request.contextPath}/manaUpdateFGrade",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:{
			"sid":sid,
			"cpid":cpid,
			"score":score
		},
		success : function() {
			show();
		}
	});
}
function updateSGrade(sid,cpid){
	var score=prompt("输入补考成绩","");
	$.ajax("${pageContext.request.contextPath}/manaUpdateSGrade",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:{
			"sid":sid,
			"cpid":cpid,
			"score":score
		},
		success : function() {
			show();
		}
	});
}
function updateTGrade(sid,cpid){
	var score=prompt("输入平时成绩","");
	$.ajax("${pageContext.request.contextPath}/manaUpdateTGrade",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:{
			"sid":sid,
			"cpid":cpid,
			"score":score
		},
		success : function() {
			show();
		}
	});
}
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
<div  class="panel panel-primary" style="height:700px;width:1100px;">
<div class="panel-heading" style="height:auto;">
	当前位置：管理员>选课信息管理
</div>
<div class="btn-group" style=" width:25%; height:10%;  float:left; margin-left:1%;">
	<div class="input-group">
		<span class="input-group-btn">
			<button class="btn btn-default" type="button">学号</button>
		</span>
		<input type="text" class="form-control" id="sid">
	</div>
</div>
<div class="btn-group" style=" width:20%; height:10%;  float:left; margin-left:1%;">
	<div class="input-group">
		<span class="input-group-btn">
			<button class="btn btn-default" type="button">课程编号</button>
		</span>
		<input type="text" class="form-control" id="cpid">
	</div>
</div>
<div class="btn-group" style=" width:2%; height:10%;  float:left; margin-left:12%;">
	<button type="button" class="btn btn-warning" onclick="selectSC()"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询</button>
</div>

<div class="form-group" id="Select_Course" class="col-sm-10" style="float: left; margin-left: 200px;margin-top: 35px;">
	通过excel表单添加：
	<input id="Select_CourseExcelFile" name="excelFile" type="file" class="form-control" style="width: 300px; display: inline;" />
	<input id="insertSelect_CourseExcelButton" type="button" class="btn btn-primary" onclick="inputSelect_Course()" style="width: 60px;height: 35px;" value="上传" />
	<input id="outPut" type="button" class="btn btn-primary" onclick="url()" style="width: 15%;height: 35px;" value="下载" />
</div>
<div class="panel-body" style="height:1000px;" >
<table class="table table-striped table-bordered table-hover  table-condensed">
<thead>
	<th>开课学期</th>
	<th>课程编号</th>
	<th>课程名称</th>
	<th>学号</th>
	<th>姓名</th>
	<th>平时成绩</th>
	<th>期末成绩</th>
	<th>补考成绩</th>
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
</div>
</div>