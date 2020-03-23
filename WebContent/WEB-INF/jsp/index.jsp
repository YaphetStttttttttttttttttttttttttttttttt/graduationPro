<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<title>成绩查询系统</title> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<link href="css/login2.css" rel="stylesheet" type="text/css" />
</head>
<body background="images/1.png "
      style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<h1>学生成绩查询系统<sup>2020</sup></h1>
<div class="login" style="margin-top:50px;">
	<div class="header">
		<div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
			<a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a><div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
		</div>
	</div>
	
	<!-- 提示信息 -->
	<font color="red">${error }</font>
	<div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">
		<!--登录-->
		<div class="web_login" id="web_login">
			<div class="login-box">
			<div class="login_form">
				<form action="login" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post">
					<input type="hidden" name="did" value="0"/>
					<input type="hidden" name="to" value="log"/>
					<div class="uinArea" id="uinArea">
						<label class="input-tips" for="u">帐号：</label>
						<div class="inputOuter" id="uArea">
							<input type="text" id="username" name="username" class="inputstyle"/>
						</div>
					</div>
					<div class="pwdArea" id="pwdArea">
						<label class="input-tips" for="p">密码：</label> 
						<div class="inputOuter" id="pArea">
							<input type="password" id="password" name="password" class="inputstyle"/>
						</div>
					</div>
					
					<div style="padding-left:50px;margin-top:20px;"><input type="submit" value="登 录" style="width:150px;" class="button_blue"/>
					</div>
				</form>
			</div>
			</div>
		</div>
		<!--登录end-->
	</div>
</div>
<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
</body></html>