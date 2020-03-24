<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
 
<div  class="panel panel-primary">
  <div class="panel-heading" style="height:140px;">
    <h1 >成绩查询系统</h1> 当前位置：管理员
    <button type="button" class="btn btn-default btn-lg" style="float:right;">
  <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 注销
</button>
  </div>
  <div class="panel-body" style="height:550px;" >
 <div class="panel panel-default" style="margin:50px;">
  <div class="panel-heading"><font color="blue">我的应用</font></div>
  <div class="panel-body" style="height:400px; width:100%;">
   <div  class="sub1" style=" width:24%; height:100%;  float:left; margin-left:1%;">
 <div style="width:50%;height:100%;float:left;"><img src="images/glyxx.png" class="img-rounded" style="margin-top:30%;margin-left:25%;"></div>

<div  style="width:50%;height:100%;float:left;">
<p style="margin-top:30%;">管理员信息管理</p>
<font color="Gray">
<span class="glyphicon glyphicon-align-left" aria-hidden="true"><a href="m-manageer.jsp">详情</a>  </span>
<span class="glyphicon glyphicon-share-alt" ria-hidden="true">移除</span>
</font>
</div>
    
   </div>
<div class="sub1" style=" width:24%; height:100%;  float:left; margin-left:1%;">
 <div style="width:50%;height:100%;float:left;"><img src="images/xsxx.png" class="img-rounded" style="margin-top:30%;margin-left:25%;"></div>

<div  style="width:50%;height:100%;float:left;">
<p style="margin-top:30%;">学生信息管理</p>
<font color="Gray">
<span class="glyphicon glyphicon-align-left" aria-hidden="true"><a href="m-student.jsp">详情</a> </span>
<span class="glyphicon glyphicon-share-alt" ria-hidden="true">移除</span>
</font>
</div>
</div>
<div class="sub1" style=" width:24%; height:100%;  float:left; margin-left:1%;">
<div style="width:50%;height:100%;float:left;"><img src="images/jsxx.png" class="img-rounded" style="margin-top:30%;margin-left:25%;"></div>

<div  style="width:50%;height:100%;float:left;">
<p style="margin-top:30%;">教师信息管理</p>
<font color="Gray">
<span class="glyphicon glyphicon-align-left" aria-hidden="true"><a href="m-teacher.jsp">详情</a></span>
<span class="glyphicon glyphicon-share-alt" ria-hidden="true">移除</span>
</font>
</div>
</div>
<div class="sub1" style=" width:24%; height:100%;  float:left; margin-left:1%;">
<div style="width:50%;height:100%;float:left;"><img src="images/kcxxgl.png" class="img-rounded" style="margin-top:30%;margin-left:25%;"></div>

<div  style="width:50%;height:100%;float:left;">
<p style="margin-top:30%;">课程信息管理</p>
<font color="Gray">
<span class="glyphicon glyphicon-search" aria-hidden="true"><a href="m-coursecx.jsp">查询</a></span>
<span class="glyphicon glyphicon-plus" ria-hidden="true"><a href="m-coursetj.jsp">添加</a></span>
</font>
</div>
</div>

  </div>
</div>


  </div>
</div>