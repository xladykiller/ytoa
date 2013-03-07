<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ page import="com.yitengls.bean.TuserBean,com.yitengls.utils.Power" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="js/XXUpdatePsw.js"></script>
<style>
*{font-size: 12px;}
table input[type=password]{
width:150px;
}
</style>
</head>
<%
//判断修改密码（updatePsw）权限
TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
if(!Power.validate(ub.getPermission(), Power.updatePsw)){
	response.sendRedirect("result/noPermission.jsp");
	return;
}
%>
<body>
	<table style="margin:10px 0 0 10px;">
		<tr>
			<td width="80px">旧密码</td>
			<td><input type="password" id="oldPsw" /><br /></td>
		</tr>
		<tr>
			<td>新密码</td>
			<td><input type="password" id="updatePsw" /><br /></td>
		</tr>
		<tr>
			<td>确认新密码</td>
			<td><input type="password" id="updateRepsw" />
		</tr>
		<tr align="right">
			<td colspan="2"><input type="button" value="提交"
				id="updatePswBtn" /> <input type="button" value="重置" id="resetBtn" />
			</td>
		</tr>
	</table>
</body>
</html>