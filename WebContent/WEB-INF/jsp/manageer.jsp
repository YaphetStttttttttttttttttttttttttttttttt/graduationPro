<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>

 
<div  class="panel panel-primary">
  <div class="panel-heading" style="height:140px;">
    <h1 >成绩查询系统</h1>当前位置：管理员 <button type="button" class="btn btn-default btn-lg" style="float:right;">
  <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 注销
</button>
  </div>
  <div class="panel-body" style="height:800px;" >
<div class="panel panel-primary" style="width:15%; height:700px;float:left;margin-left:1px;">
<div class="panel-heading" style="height:auto;">
  <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 功能菜单
  </div>
        <ul class="list-group">
  <li class="list-group-item list-group-item-info" data-id="xsxxgl" style="height:80px;"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>学生信息管理</li>
  
  <li class="list-group-item list-group-item-warning" data-id="jsxxgl"style="height:80px;"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>教师信息管理</li>

  <li class="list-group-item list-group-item-success" data-id="bjxxgl"style="height:80px;"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>班级信息管理</li>
 
  <li class="list-group-item list-group-item-warning" data-id="kcapgl"style="height:80px;"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>课程安排管理</li>
  
  <li class="list-group-item list-group-item-success" data-id="kcxxgl"style="height:80px;"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>课程信息管理</li>
  
  <li class="list-group-item list-group-item-warning" data-id="yxxxgl"style="height:80px;"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>院系信息管理</li>
  
  <li class="list-group-item list-group-item-success" data-id="zyxxgl"style="height:80px;"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>专业信息管理</li>
 
  <li class="list-group-item list-group-item-warning" data-id="xkxxgl"style="height:80px;"><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>选课信息管理</li>

</ul>
            

        </div>
        <div id="content" style="float:left;margin-left:1px;"></div>
  </div>
</div>
<script>
    $(function() {

        $(".list-group").on("click", "li", function() {

            var sId = $(this).data("id"); //获取data-id的值
            window.location.hash = sId; //设置锚点

            loadInner(sId);

        });
        function loadInner(sId) {

            var sId = window.location.hash;

            var pathn, i;

            switch(sId) {
            case "#xsxxgl":

                pathn = "mxsxxgl";

                i = 0;

                break;
            case "#jsxxgl":

                pathn = "mjsxxgl";

                i = 1;

                break;
            case "#bjxxgl":

                pathn = "mbjxxgl";

                i = 2;

                break;
            case "#kcapgl":

                pathn = "mkcapgl";

                i = 3;

                break;
            case "#kcxxgl":

                pathn = "mkcxxgl";

                i = 4;

                break;
            case "#yxxxgl":

                pathn = "myxxxgl";

                i = 5;

                break;
            case "#zyxxgl":

                pathn = "mzyxxgl";
                
                i = 6;

                break;
            case "#xkxxgl":

                pathn = "mxkxxgl";

                i = 7;

                break;
         
            default:

                pathn = "mxsxxgl";

                i = 0;

                break;
            }
            $("#content").load(pathn); //加载相对应的内容
           
        }
        var sId = window.location.hash;

        loadInner(sId);

    });

</script>