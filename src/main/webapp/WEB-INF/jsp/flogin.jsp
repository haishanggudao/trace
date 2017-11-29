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
		*:focus {outline: none;} 
		.warp{
			width: 410px;
			margin: 80px auto 0;
			text-align: center;
		}
		.gloabInput{
			width: 345px;
			height: 52px;
			border: 1px solid #e7e7e7;
			border-radius: 5px;
			padding-left: 76px;
			margin-bottom: 15px;
		}
		.cssusername{
			background: url(static/images/usericon.png) no-repeat 0 0;
		}
		.csspassword{
			background: url(static/images/pwdicon.png) no-repeat 0 0;
		}
		.forgetPwd{
			text-align: right;
		}
		.forgetLink{
			color: #c0c0c0
		}
		.goBtn{
			width: 424px;
			height: 52px;
			background-color: #1658ac;
			border-radius: 5px;
			text-align: center;
			color: #fff;
			border: 0;
			margin-top: 20px;
		}
		footer{
			text-align: center;
			color: #999999;
			margin: 133px 0 66px 0;
			font-size: 12px;
		}
	</style>

<script type="text/javascript">
	function checkLoginForm() {
		$.cookie('redirectUrl', 'flogin', { expires: 7 }); // 存储 cookie 
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
	
	<header>
		<img src="static/images/banner.jpg" height="-1" width="100%" alt="">
	</header>
	<div class="warp">
		<form action="flogin" method="post" id="loginForm"	onsubmit="return checkLoginForm()">
			<input type="text" type="text" name="username"  id="username" placeholder="请输入用户名"
						data-options="required:true,missingMessage:'用户名必填'" class="easyui-validatebox cssusername gloabInput" placeholder="用户名">
			<input type="password" placeholder="密码" class="csspassword gloabInput easyui-validatebox" name="password" id="userpwd" 
			placeholder="请输入密码" data-options="required:true,missingMessage:'请输入密码'">
			
			<div class="forgetPwd"><a href="javascript:;"class="forgetLink">忘记密码?</a></div>
			<button class="goBtn" type="submit" class="loginbutton" id="button" value="登录" >登录</button>
			</form>
	</div>

	<footer>
		<div style="margin-bottom:15px">联系我们 | 公司介绍</div>
		<div>Copyright © 2014 上海质尊溯源电子科技有限公司 www.shianxian.cn All right Reserved.</div>
	</footer>
	
</body>
</html>