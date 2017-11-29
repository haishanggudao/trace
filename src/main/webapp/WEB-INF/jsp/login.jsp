<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>登录-上海食品安全信息追溯公共服务平台</title>
<link rel="shortcut icon" href="static/img/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="static/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="static/css/comme.css">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
html {
	background: url(static/images/background.jpg) repeat;
}
.wrap {
	width: 685px;
	margin: 150px auto 0;
	text-align: center;
}

.logoimg {
	width: 127px;
	height: 185px;
	background: url(static/images/logo.png) no-repeat;
	margin: 0 auto;
}

.company-name {
	font-size: 50px;
	margin-top: 50px;
}

.infotitle {
	font-size: 30px;
	margin-top: 12px;
}

.wrap .gent {
	color: #fff;
	font-family: 微软雅黑;
}

.fromlist {
	margin-top: 52px;
}

.gloab-input {
	text-align: center;
	width: 400px;
	height: 53px;
	border-radius: 10px;
	font-size: 16px;
	-moz-box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.21);
	-webkit-box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.21);
	box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.21);
	border: none;
}

.forget {
	width: 400px;
	margin: 15px auto;
	color: #fff;
	text-align: right;
}

.loginbutton {
	width: 414px;
	height: 65px;
	background: url(static/images/button.png) no-repeat;
	border: none;
	cursor: pointer;
	color: #fff;
	font-family: 微软雅黑;
	font-size: 16px;
}

.foot {
	text-align: center;
	color: #fff;
	margin-top: 60px;
}

.foot a {
	color: #fff
}

.foot .beian {
	margin-top: 15px;
}
.rem_sub_l{
	margin-top: 15px;
	margin-bottom: 15px;
}
</style>
<script type="text/javascript">
	function checkLoginForm() {
		$.cookie("redirectUrl", "login",{expires:10}); 
		return $("#loginForm").form('enableValidation').form('validate');
	}
</script>

<script type="text/javascript">
	$(function() {
		$('#login_hidden_flag').val("1");
		//$.ajax({
		//type: 'PUT',
		//contentType: 'application/json; charset=utf-8',
		//url: 'ws/addDeliveryOrder', 
		//data: ' {"deliveryQRCode":"1","userId":"11","goodList":[{"goodQRCodeId":"111"},{"goodQRCodeId":"112"}]}',
		//dataType: 'json',
		//success: function(data) {
		//        console.log("success ", data.response);
		//},
		//error: function(data) {
		//        console.log("error ", data.error);
		//    }           
		//});

	});
</script>
</head>


<body>

	<script type="text/javascript">
		var loginHiddenFlag = $('#login_hidden_flag').val();
		//console.info('-- loginHiddenFlag is ' + loginHiddenFlag);
		//console.info('-- document.location.href is ' + document.location.href );
		//console.info(loginHiddenFlag === "0");

		if (loginHiddenFlag === "0") {
			//console.info('-- loginHiddenFlag action => 1 ' + document.location.href );
			//window.parent.document.location.href = document.location.href; 
			window.parent.document.location.href = "";
			//console.info('-- loginHiddenFlag action => 2' );
		}
	</script>
	<input type="hidden" id="login_hidden_flag" value="0">

	<div class="wrap">
		<div class="logoimg"></div>
		<h1 class="company-name gent">上海食品安全信息追溯平台</h1>
		<p class="gent infotitle">企业信息录入子系统</p>
		<form action="login" method="post" id="loginForm"	onsubmit="return checkLoginForm()">
			<div class="fromlist">
				<input type="text" type="text" name="username"  id="username" placeholder="请输入用户名"
					data-options="required:true,missingMessage:'用户名必填'"
					class="gloab-input  easyui-validatebox"
					style="margin-bottom: 18px;"> 
				<input type="password" name="password"
						id="userpwd" class="gloab-input easyui-validatebox"
					placeholder="请输入密码" data-options="required:true,missingMessage:'请输入密码'">
				<div class="rem_sub" >
					<div class="rem_sub_l">
						<div  class="erro-pass" style="float: left;color:#DC143C;margin-left:145px">${error}</div>
						<div class="forget"><input type="checkbox" name="rememberMe" id="save_me">记住密码</div>
					</div>
					<label> <input type="submit" class="loginbutton" id="button" value="登录" >
					</label>
				</div>
			</div>
		</form>
	</div>
	<div class="foot">
		<a href="#">联系我们</a> | <a href="#">公司介绍</a>
		<div class="beian">Copyright © 2014 上海食品安全信息追溯公共服务平台
			www.shianxian.cn All right Reserved.</div>
	</div>
</body>
</html>