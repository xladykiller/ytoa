<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录--浙江易腾律师事务所办公管理系统</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<style type="text/css">
<!--

-->
</style>
</head>
<script type="text/javascript">
	function refreshSafeCode() {
		document.getElementById("safecodeimg").src = "SafeCode?t="
				+ new Date().getTime();
		$.post('GetRandServlet',{},function(data){
			document.getElementById('safecode').value=data;
		});

	}
</script>
<body>
	<table width="681" border="0" align="center" cellpadding="0"
		cellspacing="0" style="margin-top: 120px">
		<tr>
			<td width="353" height="259" align="center" valign="bottom"
				background="images/login/login_1.gif"><table width="90%"
					border="0" cellspacing="3" cellpadding="0">
					<tr>
						<td align="right" valign="bottom" style="color: #05B8E4">Power
							by <a href="#" target="_blank">zju</a> Copyright 2013
						</td>
					</tr>
				</table></td>
			<td width="195" background="images/login/login_2.gif"><table
					width="190" height="106" border="0" align="center" cellpadding="2"
					cellspacing="0">
					<form method="post" action="#" name="YTOA_Login">
						<tr>
							<td height="50" colspan="2" align="left">&nbsp;</td>
						</tr>
						<tr>
							<td width="60" height="30" align="left">登陆名称</td>
							<td><input name="account" type="TEXT" value="qq"
								style="background: url(images/login/login_6.gif) repeat-x; border: solid 1px #27B3FE; height: 20px; background-color: #FFFFFF"
								id="account" size="14"></td>
						</tr>
						<tr>
							<td height="30" align="left">登陆密码</td>
							<td><input name="psw" TYPE="PASSWORD" value="qq"
								style="background: url(images/login/login_6.gif) repeat-x; border: solid 1px #27B3FE; height: 20px; background-color: #FFFFFF"
								id="psw" size="16"></td>
						</tr>
						<tr>
							<td height="30">验 证 码</td>
							<td><input name="safecode" type="text" id="safecode" size="4"
								style="background: url(images/login/login_6.gif) repeat-x; border: solid 1px #27B3FE; height: 20px; background-color: #FFFFFF"
								maxlength="4"> <img id="safecodeimg" src="SafeCode"
								width="50" height="24" onclick="javascript:refreshSafeCode();" /></td>
						</tr>
						<tr>
							<td style="display: none;" id="tdinfo"  height="40" colspan="2" align="center"><img
								src="images/login/tip.gif" width="16" height="16"> 请勿非法登陆！</td>
						<tr>
							<td colspan="2" align="center"><input id="submitbtn" type="button"
								name="submit" onclick="javascript:void(0);" 
								style="background: url(images/login/login_5.gif) no-repeat"
								value=" 登  陆 "> <input type="reset" name="Submit" 
								style="background: url(images/login/login_5.gif) no-repeat"
								value=" 取  消 "></td>
						<tr>
							<td height="5" colspan="2"></td>
					</form>
				</table></td>
			<td width="133" background="images/login/login_3.gif">&nbsp;</td>
		</tr>
		<tr>
			<td height="161" colspan="3" background="images/login/login_4.gif"></td>
		</tr>
	</table>
</body>
</html>