<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet" type="text/css"
	href="static/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="static/css/wu.css" />
<link rel="stylesheet" type="text/css" href="static/css/icon.css" />
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
function checkLoginForm(){
	return $("#loginForm").form('enableValidation').form('validate');
}
</script> 

<script type="text/javascript">
$(function(){ 
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


<body class="login" > 
	
	<script type="text/javascript">
	var loginHiddenFlag = $('#login_hidden_flag').val();
	//console.info('-- loginHiddenFlag is ' + loginHiddenFlag);
	//console.info('-- document.location.href is ' + document.location.href );
	//console.info(loginHiddenFlag === "0");
	
	if( loginHiddenFlag === "0" ){
		//console.info('-- loginHiddenFlag action => 1 ' + document.location.href );
		//window.parent.document.location.href = document.location.href; 
		window.parent.document.location.href = ""; 
		//console.info('-- loginHiddenFlag action => 2' );
	}  
	</script>
	<input type="hidden" id="login_hidden_flag" value="0">
	<div class="login_m"> 
		<div class="login_logo">
			<img src="static/images/logo.png" width="196" height="80">
		</div>
		<form action="login" method="post" id="loginForm" onsubmit="return checkLoginForm()">
		
			<div class="login_boder">
				
				<div class="login_padding">
					<h2>用户名</h2><div class="erro-pass">${error}</div>
					<label> <input type="text" id="username"
						class="txt_input txt_input2 easyui-validatebox"
						placeholder="请输入用户名"
						data-options="required:true,validType:'length[1,10]',missingMessage:'用户名必填'" name="username">
					</label>
					<h2>密码</h2>
					<label> <input type="password" name="password"
						id="userpwd" class="txt_input easyui-validatebox"
						placeholder="请输入密码"
						data-options="required:true,validType:'length[1,10]',missingMessage:'请输入密码'">
					</label>
					<div class="rem_sub">
						<div class="rem_sub_l">
							<input type="checkbox" name="rememberMe" id="save_me"> <label
								for="checkbox">记住</label>
						</div>
						<label> <input type="submit" class="sub_button"
							id="button" value="登录" style="opacity: 0.7;">
						</label>
					</div>
				</div>
			</div>
		</form>
		<!--login_boder end-->
	</div>
</body>
</html>