<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ page import="com.yitengls.bean.TuserBean,com.yitengls.utils.Power" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="js/XXutils.js"></script>
<script type="text/javascript" src="js/XXSearchUser.js"></script>
<link href="css/XXPage.css" rel="stylesheet" type="text/css" />

<style>
table input[type=text],table input[type=password]{
width:150px;
}
#searchUserTable{
	border-collapse: collapse;
	border: none;
	width:100%;
	margin:10px auto;
}
#searchUserTable th{
	padding: 5px 2px ;
	background-color: #EEEEEE;
	font-weight: normal;
}
#searchUserTable th,#searchUserTable td{
	border: solid  black 1px;
	text-align: center;
	font-size: 12px;
}
</style>
</head>
<%-- <%
//判断查询（searchUser）用户的权限
TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
if(!Power.validate(ub.getPermission(), Power.searchUser)){
	response.sendRedirect("result/noPermission.jsp");
	return;
}
%> --%>
<body>

	<div id="searchUser">
	<div style="font-size: 12px;">
		账号：<input type="text" name="textfield" id="account"> &nbsp;&nbsp;&nbsp;&nbsp;真实姓名：<input
			type="text" name="textfield" id="uname"> &nbsp;&nbsp;&nbsp;&nbsp;手机号码：<input
			type="text" name="textfield" id="cellphone"> &nbsp;&nbsp;&nbsp;&nbsp;角色： <select
			id="powerid">
			<option value="0">任意角色</option>
		</select>&nbsp;&nbsp;&nbsp;&nbsp;<input id="searchUserBtn" type="button" value="查询">
	</div>

		<table id="searchUserTable">
			<thead>
				<tr>
					<th width="5%">编号</th>
					<th width="20%">账号</th>
					<th width="20%">真实姓名</th>
					<th width="20%">手机号码</th>
					<th width="15%">角色</th>
					<th width="10%">删除用户</th>
					<th width="10%">修改用户</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		<div id="paging">
				<a href="#" id="first" name="arrow">&lt;&lt;</a>
				<a href="#" id="previous" name="arrow">&lt;</a>

				<div id="page" style="display:inline"></div>
				<a href="#" id="next" name="arrow">&gt;</a>
				<a href="#" id="last" name="arrow">&gt;&gt;</a>
				共<span id="totalPageNum"></span>页
		</div>
	</div>


	<table id="updateUserTable" style="display: none;margin:10px 0 0 10px; font-size:12px;">
		<tr>
			<td width="80px">用户名</td>
			<td><input type="text" id="updateAccount" disabled="disabled" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>真实姓名</td>
			<td><input type="text" id="updateUname" /></td>
		</tr>
		<tr>
			<td>手机号码</td>
			<td><input type="text" id="updateCellphone" /></td>
		</tr>
		<tr>
			<td>角色</td>
			<td><select id="updatePowerid">

			</select></td>
		</tr>
		<tr>
			<td>更改密码</td>
			<td><input type="password" id="updatePsw" /></td>
		</tr>
		<tr>
			<td>确认更改密码</td>
			<td><input type="password" id="updateRepsw" /></td>
		</tr>
		<tr align="right">
			<td colspan="2"><input type="button" value="提交"
				id="updateUserBtn" />
			<input type="button" value="返回"
				id="returnBtn" />	
			</td>
		</tr>
	</table>
</html>