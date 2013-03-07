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
<script type="text/javascript" src="js/XXAddRisk.js"></script>
<link  rel="stylesheet" type="text/css" href="datepicker/development-bundle/themes/base/jquery.ui.all.css"/>
<link  rel="stylesheet" type="text/css" href="datepicker/development-bundle/demos/demos.css"/>
<style>
#proxyidList tr:HOVER{
background-color: #EBEBEB;
}
table input[type=text]{
width:300px;
}
</style>
</head>

<%
//判断添加风险（addRisk）权限
TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
if(!Power.validate(ub.getPermission(), Power.addRisk)){
	response.sendRedirect("result/noPermission.jsp");
	return;
}
%>
<body>
	<table style="margin:10px 0 0 10px; font-size:12px;">
		<tr>
			<td width="120px">投诉（事发）日期</td>
			<td ><input   type="text" id="complainDate" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>投诉人</td>
			<td><input type="text" id="complainant" /></td>
		</tr>
			<tr>
			<td>投诉方式</td>
			<td><input type="text" id="complainWay" /></td>
		</tr>
		<tr>
			<td>联系电话</td>
			<td><input  type="text" id="phone" /></td>
		</tr>
		<tr>
			<td>代理案号</td>
			<td><input  type="text" id="proxyid" /></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<table id="proxyidList"
					style="display: none; position: absolute; z-index: 10; background-color: white; border: 1px solid gray; width: 300px;">
				</table>
			</td>
		</tr>
		<tr>
			<td>案件承办人</td>
			<td><input  type="text" id="undertaker" /></td>
		</tr>
		<tr>
			<td>投诉（事故）内容</td>
			<td><textarea maxlength="1000" rows="8" cols="100"  id="content"></textarea></td>
		</tr>
		<tr>
			<td>处理结果</td>
			<td><textarea maxlength="1000" rows="8" cols="100"  id="result"></textarea></td>
		</tr>
		<tr align="right">
			<td colspan="2"><input type="button" value="提交" id="addRiskBtn" />
				<input type="button" value="重置" id="resetBtn" /></td>
		</tr>
	</table>
</body>
</html>