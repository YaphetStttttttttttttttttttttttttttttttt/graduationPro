<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
 
<div  class="panel panel-primary">
  <div class="panel-heading" style="height:140px;">
    <h1 >成绩查询系统</h1>当前位置：学生 ${student.username.name}<button type="button" class="btn btn-default btn-lg" style="float:right;">
  <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 注销
</button>
  </div>
  <div class="panel-body" style="height:550px;" >
 <div class="panel panel-default" style="margin:50px;">
  <div class="panel-heading"><font color="blue">我的应用</font></div>
  <div class="panel-body" style="height:400px; width:100%;">
   <div  class="sub1" style=" width:32%; height:100%;  float:left; margin-left:1%;">
 <div style="width:50%;height:100%;float:left;"><img src="images/zh.png" class="img-rounded" style="margin-top:30%;margin-left:25%;"></div>

<div  style="width:50%;height:100%;float:left;">
<p style="margin-top:30%;">个人账号管理</p>
<font color="Gray"><P>收藏人数：14145</P>
<span class="glyphicon glyphicon-align-left" aria-hidden="true"><a href="student info">详情</a>  </span>
<span class="glyphicon glyphicon-share-alt" ria-hidden="true">移除</span>
</font>
</div>
    
   </div>
<div class="sub1" style=" width:32%; height:100%;  float:left; margin-left:1%;">
 <div style="width:50%;height:100%;float:left;"><img src="images/cj.png" class="img-rounded" style="margin-top:30%;margin-left:25%;"></div>

<div  style="width:50%;height:100%;float:left;">
<p style="margin-top:30%;">学生成绩</p>
<font color="Gray"><P>收藏人数：11563</P>
<span class="glyphicon glyphicon-align-left" aria-hidden="true"><a href="scores">详情</a>  </span>
<span class="glyphicon glyphicon-share-alt" ria-hidden="true">移除</span>
</font>
</div>
</div>
<div class="sub1" style=" width:32%; height:100%;  float:left; margin-left:1%;">
<div style="width:50%;height:100%;float:left;"><img src="images/xk.png" class="img-rounded" style="margin-top:30%;margin-left:25%;"></div>

<div  style="width:50%;height:100%;float:left;">
<p style="margin-top:30%;">学生选课</p>
<font color="Gray"><P>收藏人数：11563</P>
<span class="glyphicon glyphicon-align-left" aria-hidden="true"><a href="choosec">详情</a></span>
<span class="glyphicon glyphicon-share-alt" ria-hidden="true">移除</span>
</font>
</div>
</div>

  </div>
</div>


  </div>
</div>