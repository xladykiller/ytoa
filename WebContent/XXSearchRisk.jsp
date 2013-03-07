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
<script type="text/javascript" src="js/XXSearchRisk.js"></script>
<link rel="stylesheet" type="text/css" href="css/XXPage.css" />
<link rel="stylesheet" type="text/css"
	href="datepicker/development-bundle/themes/base/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css"
	href="datepicker/development-bundle/demos/demos.css" />
</head>
<style>
#searchRiskTable{
	border-collapse: collapse;
	border: none;
	width:100%;
	margin:10px auto;
}
#searchRiskTable th{
	padding: 5px 2px ;
	background-color: #EEEEEE;
	font-weight: normal;
}
#searchRiskTable th,#searchRiskTable td{
	border: solid  black 1px;
	text-align: center;
	font-size: 12px;
}
table input[type=text]{
width:300px;
}
</style>
<body>
<div id="searchRisk">
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
		&nbsp;&nbsp;&nbsp;&nbsp;投诉人：<input type="text" id="searchComplainant" /> 
		&nbsp;&nbsp;&nbsp;&nbsp;代理案号：<input type="text" id="searchProxyid" /> 
		&nbsp;&nbsp;&nbsp;&nbsp;案件承办人：<input type="text" id="searchUndertaker" /> 
		&nbsp;&nbsp;&nbsp;&nbsp;<input id="searchRiskBtn" type="button" value="查询">
   </div>
		<table id="searchRiskTable">
			<thead>
				<tr>
					<th width="5%">编号</th>
					<th width="15%">投诉（事发）日期</th>
					<th width="15%">投诉人</th>
					<th width="30%">代理案号</th>
					<th width="15%">案件承办人</th>
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
	<table id="updateRiskTable" style="display:none; margin:10px 0 0 10px; font-size:12px;" >
		<tr>
			<td width="120px">投诉（事发）日期</td>
			<td><input type="text" id="updateComplainDate" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>投诉人</td>
			<td><input type="text" id="updateComplainant" /></td>
		</tr>
		<tr>
			<td>投诉方式</td>
			<td><input type="text" id="updateComplainWay" /></td>
		</tr>
		<tr>
			<td>联系电话</td>
			<td><input type="text" id="updatePhone" /></td>
		</tr>
		<tr>
			<td>代理案号</td>
			<td><input type="text" id="updateProxyid" disabled="disabled"/></td>
		</tr>
		<tr>
			<td>案件承办人</td>
			<td><input type="text" id="updateUndertaker"></td>
		</tr>
		<tr>
			<td>投诉（事故）内容</td>
			<td><textarea maxlength="1000" rows="8" cols="100"  id="updateContent"></textarea></td>
		</tr>
		<tr>
			<td>处理结果</td>
			<td><textarea maxlength="1000" rows="8" cols="100"  id="updateResult"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="button" value="提交"
				id="updateRiskBtn" /> <input type="button" value="返回"
				id="returnBtn"></td>
		</tr>
	</table>
</body>
</html>