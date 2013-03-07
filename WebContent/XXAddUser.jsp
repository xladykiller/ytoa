<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ page import="com.yitengls.bean.TuserBean,com.yitengls.utils.Power" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/XXAddUser.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="css/table.css" /> -->
</head>
<%
//判断addUser（添加用户）权限
TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
if(!Power.validate(ub.getPermission(), Power.addUser)){
	response.sendRedirect("result/noPermission.jsp");
	return;
}
%>
<style>
*{font-size: 12px;}
table input[type=text],table input[type=password]{
width:150px;
}
</style>
<body>
	<table style="margin:10px 0 0 10px;">
		<tr>
			<td width="80px">账号</td>
			<td><input type="text" id="account" /></td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input type="password" id="psw"  /></td>
		</tr>
		<tr>
			<td>确认密码</td>
			<td><input type="password" id="repsw" /></td>
		</tr>
		<tr>
			<td>真实姓名</td>
			<td><input type="text" id="uname" /></td>
		</tr>
		<tr>
			<td>角色</td>
			<td><select id="powerid">

			</select></td>
		</tr>
		<tr>
			<td>手机号码</td>
			<td><input type="text" id="cellphone" /></td>
		</tr>
		<tr align="right">
			<td colspan="2"><input type="button" value="提交" id="addUserBtn" />
				<input type="button" value="重置" id="resetBtn" /></td>
		</tr>
	</table>

</body>
</html>