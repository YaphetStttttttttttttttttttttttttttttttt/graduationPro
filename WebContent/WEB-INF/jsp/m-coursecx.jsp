<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
 
<div  class="panel panel-primary">
  <div class="panel-heading" style="height:140px;">
    <h1 >成绩查询系统</h1>当前位置：管理员 >课程信息管理-查询<button type="button" class="btn btn-default btn-lg" style="float:right;">
  <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 注销
</button>
  </div>
  <center><h1><font style=""><font color="Gray">选择筛选条件</font></h1></center>
   <div class="panel-body"  style="margin-left:50px; margin-right:50px;margin-bottom:50px;height:550px;">


<div class="btn-group" style=" width:7%; height:10%;  float:left; margin-left:1%;">
  <button type="button" class="btn btn-info">学期</button>
  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu">
    <li><a href="#">上学期</a></li>
    <li><a href="#">下学期</a></li>
  </ul>
</div>
<div class="btn-group" style=" width:7%; height:10%;  float:left; margin-left:1%;">
  <button type="button" class="btn btn-info">院系</button>
  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu">
    <li><a href="#">计算机科学与工程学院</a></li>
    <li><a href="#">文学院</a></li>
    <li><a href="#">体院</a></li>
    <li><a href="#">数统院</a></li>
  </ul>
</div>
<div class="btn-group" style=" width:9%; height:10%;  float:left; margin-left:1%;">
  <button type="button" class="btn btn-info">课程性质</button>
  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul class="dropdown-menu">
    <li><a href="#">公共课</a></li>
    <li><a href="#">限选课</a></li>
    <li><a href="#">必修课</a></li>
    <li><a href="#">任选课</a></li>
  </ul>
</div>
<div class="btn-group" style=" width:25%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">老师</button>
      </span>
      <input type="text" class="form-control" >
    </div>
</div>
<div class="btn-group" style=" width:20%; height:10%;  float:left; margin-left:1%;">
  <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">学年</button>
      </span>
      <input type="text" class="form-control" >
    </div>
</div>
<div class="btn-group" style=" width:2%; height:10%;  float:left; margin-left:20%;">
  <button type="button" class="btn btn-warning">检索</button>
</div>

    <table class="table table-striped table-bordered table-hover  table-condensed">
  <thead>
     <th>学年</th>
     <th>学期</th>
     <th>课程编号</th>
     <th>课程名称</th>
     <th>学时</th>
     <th>学分</th>
     <th>课程性质</th>
     <th>所属院系</th>
     <th>上课时间</th>
     <th>上课地点</th>
     <th>授课教师</th>
 
   
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
        <td></td> 
        <td></td>  
        <td></td>  
        <td></td> 
     </tr>
 
     
  </tbody>
</table>
 
  </div>
  
  </div>