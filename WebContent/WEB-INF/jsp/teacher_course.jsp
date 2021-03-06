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
	$.ajax("${pageContext.request.contextPath}/totlePageStuList",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
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
	$.ajax("${pageContext.request.contextPath}/stuList",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:{
			"pageNumber":$("#nowPage").val(),
			"Size":10
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
		"<td>" + data[index]["cpid"]["cid"]["name"] + "</td>" + 
		"<td>" + data[index]["sid"]["id"] + "</td>" + 
		"<td>" + data[index]["sid"]["name"] + "</td>" + 
		"<td>" + data[index]["tGrade"] + "</td>" + 
		"<td><center><button type=\"button\" class=\"btn btn-primary\" onclick=\"updateTGrade(" + array + ")\">编辑</button></center></td>" +
		"</tr>";
		$("#tableList").append(html);
	}
}
function updateTGrade(sid,cpid){
	var score=prompt("输入平时成绩","");
	$.ajax("${pageContext.request.contextPath}/updateTGrade",// 发送请求的URL字符串。
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
</script>
<div  class="panel panel-primary" style="height:500px;width:1100px;">
<div class="panel-heading" style="height:auto;">
 当前位置：教师>课程查询
</div>
<div class="panel-body"  style="margin:50px; height:550px;">
<table class="table table-striped table-bordered table-hover  table-condensed">
<thead>
	<th>开课学期</th>
	<th>课程名称</th>
	<th>学号</th>
	<th>姓名</th>
	<th>平时成绩</th>
	<th>编辑成绩</th>
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