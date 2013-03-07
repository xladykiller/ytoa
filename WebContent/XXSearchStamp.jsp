<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<script type="text/javascript"
	src="datepicker/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="datepicker/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="datepicker/development-bundle/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="js/XXutils.js"></script>
<script type="text/javascript" src="js/XXSearchStamp.js"></script>
<link rel="stylesheet" type="text/css" href="css/XXPage.css" />
<link rel="stylesheet" type="text/css"
	href="datepicker/development-bundle/themes/base/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css"
	href="datepicker/development-bundle/demos/demos.css" />
<style>
#searchStampTable{
	border-collapse: collapse;
	border: none;
	width:100%;
	margin:10px auto;
}
#searchStampTable th{
	padding: 5px 2px ;
	background-color: #EEEEEE;
	font-weight: normal;
}
#searchStampTable th,#searchStampTable td{
	border: solid  black 1px;
	text-align: center;
	font-size: 12px;
}
</style>
</head>
<body>
	<div id="searchStamp">
		<div style="font-size: 12px;">
				年份：<select id="searchYear">
				<option value=""></option>
					<%
						int i = 2010;
						for (i = 2010; i <= 2030; i++) {
					%>
					<option value="<%=i%>"><%=i%></option>
					<%
						}
					%>
				</select> 
				&nbsp;&nbsp;&nbsp;&nbsp;使用人账号：<input type="text" id="searchPetitioner" /> 
				&nbsp;&nbsp;&nbsp;&nbsp;使用人姓名：<input type="text" id="searchUname" /> 
				&nbsp;&nbsp;&nbsp;&nbsp;使用事由：<input type="text" id="searchUseReason" /> 
				&nbsp;&nbsp;&nbsp;&nbsp;<input id="searchStampBtn" type="button" value="查询">
		</div>
		<table id="searchStampTable">
			<thead>
				<tr>
					<th width="5%">编号</th>
					<th width="10%">使用日期</th>
					<th width="10%">使用人账号</th>
					<th width="10%">使用人姓名</th>
					<th width="10%">文件数量</th>
					<th width="35%">使用事由</th>
					<th width="10%">删除记录</th>
					<th width="10%">修改记录</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		<div id="paging">
			<a href="#" id="first" name="arrow">&lt;&lt;</a> <a href="#"
				id="previous" name="arrow">&lt;</a>

			<div id="page" style="display: inline"></div>
			<a href="#" id="next" name="arrow">&gt;</a> <a href="#" id="last"
				name="arrow">&gt;&gt;</a> 共<span id="totalPageNum"></span>页
		</div>

	</div>

	<!--  style="display:none;" -->
	<table id="updateStampTable" style="display:none; margin:10px 0 0 10px; font-size:12px;">
		<tr>
			<td width="80px">使用日期</td>
			<td><input type="text" id="updateUseDate" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>使用人账号</td>
			<td><input type="text" id="updatePetitioner" readonly="readonly" disabled="disabled"/></td>
		</tr>
		<tr>
			<td>使用人姓名</td>
			<td><input type="text" id="updateUname" readonly="readonly" disabled="disabled"/></td>
		</tr>
		<tr>
			<td>文件数量</td>
			<td><input type="text" id="updateFileNum" /></td>
		</tr>
		<tr>
			<td>使用事由</td>
			<td><textarea maxlength="1000" rows="9" cols="100"  id="updateUseReason"></textarea></td>
		</tr>
		<tr>
			<td>备注</td>
			<td><textarea maxlength="1000" rows="9" cols="100"  id="updateMemo"></textarea></td>
		</tr>
		<tr>
			<td colspan="2"  align="right"><input type="button" value="提交"
				id="updateStampBtn" /> <input type="button" value="返回"
				id="returnBtn"></td>
		</tr>
	</table>

</body>
</html>