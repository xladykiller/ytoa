<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.yitengls.bean.TuserBean,com.yitengls.utils.Power" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="datepicker/development-bundle/ui/jquery.ui.core.js" ></script>
<script type="text/javascript" src="datepicker/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="datepicker/development-bundle/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="js/XXAddStamp.js"></script>
<link  rel="stylesheet" type="text/css" href="datepicker/development-bundle/themes/base/jquery.ui.all.css"/>
<link  rel="stylesheet" type="text/css" href="datepicker/development-bundle/demos/demos.css"/>
<style>
table input[type=text],table input[type=password]{
width:150px;
}
</style>
</head>

<%
//判断添加公章使用（addStamp）权限
TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
if(!Power.validate(ub.getPermission(), Power.addStamp)){
	response.sendRedirect("result/noPermission.jsp");
	return;
}
%>
<body>
<table style="margin:10px 0 0 10px;">
		<tr>
			<td width="80px"><span style="font-size:12px">使用日期</span></td>
			<td ><input   type="text" id="useDate" readonly="readonly"/></td>
		</tr>
		<tr>
			<td><span style="font-size:12px">使用人账号</span></td>
			<td><input type="text" id="petitioner" /></td>
		</tr>
			<tr>
			<td><span style="font-size:12px">使用人密码</span></td>
			<td><input type="password" id="psw" /></td>
		</tr>
		<tr>
			<td><span style="font-size:12px">文件数量</span></td>
			<td><input  type="text" id="fileNum" /></td>
		</tr>
		<tr>
			<td><span style="font-size:12px">使用事由</span></td>
			<td><textarea  maxlength="1000" rows="9" cols="100" id="useReason"></textarea></td>
		</tr>
		<tr>
			<td><span style="font-size:12px">备注</span></td>
			<td><textarea maxlength="1000" rows="9" cols="100"  id="memo"></textarea></td>
		</tr>
		
		<tr align="right">
			<td colspan="2"><input type="button" value="提交" id="addStampBtn" />
				<input type="button" value="重置" id="resetBtn" /></td>
		</tr>
	</table>
</body>
</html>