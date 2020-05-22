<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="js/jquery.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	show();
});
function show() {
	var cpid = GetQueryValue("cpid");
	$.ajax("${pageContext.request.contextPath}/getStudents",// 发送请求的URL字符串。
			{
		type : "post", //  请求方式 POST或GET
		data:{
			"cpid":cpid
		},
		success : function(data) {
			$("#tableList").empty();
			var jsonObj = eval('(' + data + ')');
			tablePro(jsonObj["listStudents"]);
		},
		error : function(data){
			
		}
	});
}
function tablePro(data){
	console.log("123");
	for(var index in data){
		//var sexString = ['男','女'];
		//var sex = sexString[data[index]["sex"]["num"] - 1];
		html =  
		"<tr>" +
		"<td>" + data[index]["id"] + "</td>" + 
		"<td>" + data[index]["name"] + "</td>" + 
	//	"<td>" + sex + "</td>" + 
	//	"<td>" + data[index]["cid"]["name"] + "</td>" + 
		"</tr>";
		$("#tableList").append(html);
	}
}
function GetQueryValue(queryName) {
     var query = decodeURI(window.location.search.substring(1));
     var vars = query.split("&");
     for (var i = 0; i < vars.length; i++) {
     	var pair = vars[i].split("=");
     	if (pair[0] == queryName) { 
     		return pair[1]; 
     	}
     }
     return null;
}
</script>
<div  class="panel panel-primary" style="height:550px;width:100%;">
<div class="panel-body"  style="margin:50px; height:550px;">
<table class="table table-striped table-bordered table-hover  table-condensed">
<thead>
     <th>学号</th>
     <th>姓名</th>
</thead>
<tbody id="tableList">

</tbody>
</table>
</div>
</div>
