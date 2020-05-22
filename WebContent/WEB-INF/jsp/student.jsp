<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
function exit(){
	console.log("123");
	if (confirm("确定退出？")) {
		$.ajax("${pageContext.request.contextPath}/stuExit",// 发送请求的URL字符串。
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
		case "#yhzx":
			pathn = "student_center";
			i = 0;
			break;
		case "#cjcx":
			pathn = "student_score";
			i = 1;
			break;
		case "#grxxgl":
			pathn = "student_info";
			i = 2;
			break;
		case "#xsxk":
			pathn = "student_select_course";
			i = 3;
			break;   
		default:
			pathn = "student_center";
			i = 0;
			break;
		}
		$("#content").load(pathn); //加载相对应的内容  
	}
	var sId = window.location.hash;
	loadInner(sId);
});
</script>
 
<div  class="panel panel-primary">
  <div class="panel-heading" style="height:140px;">
    <h1 >成绩查询系统</h1>${sessionScope.user.username.name }
    <button type="button" class="btn btn-default btn-lg" style="float:right;" onclick="exit()">
    	<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 注销
    </button>
  </div>
  <div class="panel-body" style="height:100%;" >
<div class="panel panel-primary" style="width:15%; height:550px;float:left;margin-left:1px;">
<div class="panel-heading" style="height:auto;">
   功能菜单
  </div>
        <ul class="list-group">
  <li class="list-group-item list-group-item-success" data-id="yhzx">用户中心</li>
  <li class="list-group-item list-group-item-info" data-id="cjcx">成绩查询</li>
  <li class="list-group-item list-group-item-warning" data-id="grxxgl">个人信息管理</li>
  <li class="list-group-item list-group-item-danger" data-id="xsxk">学生选课</li>
</ul>     
        </div>
        <div id="content" style="float:left;margin-left:1%;width:83%"></div>
  </div>
</div>