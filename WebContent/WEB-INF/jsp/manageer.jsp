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
	<button type="button" class="btn btn-default btn-lg" style="float:right;">
		<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 注销
	</button>
</div>
<div class="panel-body" style="height:100%;" >
<div class="panel panel-primary" style="width:15%; height:700px;float:left;margin-left:1%;">
	<div class="panel-heading" style="height:auto;">
		<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 功能菜单
	</div>
	<ul class="list-group">
	<li class="list-group-item list-group-item-info" data-id="StudentMana" style="height:80px;"data-toggle="tooltip" data-placement="right" title="学生信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>学生信息管理</li>
	<li class="list-group-item list-group-item-warning" data-id="_teacher"style="height:80px;"data-toggle="tooltip" data-placement="right" title="教师信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>教师信息管理</li>
	<li class="list-group-item list-group-item-success" data-id="ClassMana"style="height:80px;"data-toggle="tooltip" data-placement="right" title="班级信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>班级信息管理</li>
	<li class="list-group-item list-group-item-warning" data-id="Course_planMana"style="height:80px;"data-toggle="tooltip" data-placement="right" title="课程安排管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>课程安排管理</li>
	<li class="list-group-item list-group-item-success" data-id="CourseMana"style="height:80px;"data-toggle="tooltip" data-placement="right" title="课程信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>课程信息管理</li>
	<li class="list-group-item list-group-item-warning" data-id="DepartmentMana"style="height:80px;"data-toggle="tooltip" data-placement="right" title="院系信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>院系信息管理</li>
	<li class="list-group-item list-group-item-success" data-id="DisciplineMana"style="height:80px;"data-toggle="tooltip" data-placement="right" title="专业信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>专业信息管理</li>
	<li class="list-group-item list-group-item-warning" data-id="Select_courseMana"style="height:80px;"data-toggle="tooltip" data-placement="right" title="选课信息管理"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>选课信息管理</li>
	</ul>
</div>
<div id="content" style="float:left;margin-left:1%;width:83%">
	<iframe frameborder="0" scrolling="no" style="width:100%;height:800px" src="" id="aa"></iframe>
</div>
</div>
</div>
<script>
$(function () { 
	$("[data-toggle='tooltip']").tooltip();
});
$(function() {
	$(".list-group").on("click", "li", function() {
		var sId = $(this).data("id"); //获取data-id的值
		sId = "manageer" + sId;
		$("iframe").attr("src",sId);
	});
	function loadInner(sId) {
		var sId = window.location.hash;
		var pathn, i;
		switch(sId) {
			case "#StudentMana":
				pathn = "manageer_student";
				i = 0;
				break;
			case "#_teacher":
				pathn = "manageer_teacher";
                i = 1;
                break;
            case "#ClassMana":
                pathn = "M-Class";
                i = 2;
                break;
            case "#Course_planMana":
                pathn = "M-Course_plan";
                i = 3;
                break;
            case "#CourseMana":
                pathn = "M-Course";
                i = 4;
                break;
            case "#DepartmentMana":
                pathn = "M-Department";
                i = 5;
                break;
            case "#DisciplineMana":
                pathn = "M-Discipline";
                i = 6;
                break;
            case "#Select_courseMana":
                pathn = "M-Select_course";
                i = 7;
                break;
            default:
                pathn = "M-Student";
                i = 0;
                break;
        }
	}
	var sId = window.location.hash;
	loadInner(sId);
});

</script>