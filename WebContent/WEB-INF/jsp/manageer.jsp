<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>

<div  class="panel panel-primary">
<div class="panel-heading" style="height:140px;">
	<h1 >成绩查询系统</h1>当前位置：管理员
	<button type="button" class="btn btn-default btn-lg" style="float:right;" onclick="exit()">
		<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 注销
	</button>
</div>
<div class="panel-body" style="height:100%;" >
<div class="panel panel-primary" style="width:15%; height:700px;float:left;margin-left:1%;">
	<div class="panel-heading" style="height:auto;">
		<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 功能菜单
	</div>
	<ul class="list-group">
	<li class="list-group-item list-group-item-info" data-id="_student" style="height:80px;"data-toggle="tooltip" data-placement="right" title="学生信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>学生信息管理</li>
	<li class="list-group-item list-group-item-warning" data-id="_teacher"style="height:80px;"data-toggle="tooltip" data-placement="right" title="教师信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>教师信息管理</li>
	<li class="list-group-item list-group-item-success" data-id="_class"style="height:80px;"data-toggle="tooltip" data-placement="right" title="班级信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>班级信息管理</li>
	<li class="list-group-item list-group-item-warning" data-id="_course_plan"style="height:80px;"data-toggle="tooltip" data-placement="right" title="课程安排管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>课程安排管理</li>
	<li class="list-group-item list-group-item-success" data-id="_course"style="height:80px;"data-toggle="tooltip" data-placement="right" title="课程信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>课程信息管理</li>
	<li class="list-group-item list-group-item-warning" data-id="_department"style="height:80px;"data-toggle="tooltip" data-placement="right" title="院系信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>院系信息管理</li>
	<li class="list-group-item list-group-item-success" data-id="_discipline"style="height:80px;"data-toggle="tooltip" data-placement="right" title="专业信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>专业信息管理</li>
	<li class="list-group-item list-group-item-warning" data-id="_select_course"style="height:80px;"data-toggle="tooltip" data-placement="right" title="选课信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>选课信息管理</li>
	</ul>
</div>
<div id="content" style="float:left;margin-left:1%;width:83%">
	<iframe frameborder="0" scrolling="no" style="width:100%;height:800px" src="" id="aa"></iframe>
</div>
</div>
</div>
<script>
function exit(){
	if (confirm("确定退出？")) {
		$.ajax("${pageContext.request.contextPath}/ExitBtn",// 发送请求的URL字符串。
				{
			type : "post", //  请求方式 POST或GET
			async: false,
			success : function(data) {
				location.href="index";
			},
			error : function(){
				
			}
		});
	}
}
$(function () { 
	$("[data-toggle='tooltip']").tooltip();
});
$(function() {
	$(".list-group").on("click", "li", function() {
		var sId = $(this).data("id"); //获取data-id的值
		sId = "manageer" + sId;
		$("iframe").attr("src",sId);
	});
});

</script>