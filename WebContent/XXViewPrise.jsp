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
<script type="text/javascript" src="js/XXViewPrise.js"></script>
<link href="css/XXPage.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="datepicker/development-bundle/themes/base/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css"
	href="datepicker/development-bundle/demos/demos.css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
</head>
<body>
	<div id="searchEval">
		<div style="font-size: 12px;">
			开始日期：<input type="text" readonly="readonly" id="startdate">
			&nbsp;&nbsp;&nbsp;&nbsp;结束日期：<input type="text" readonly="readonly" id="enddate">
			&nbsp;&nbsp;&nbsp;&nbsp;员工账号：<input type="text" id="searchAccount"> 
			&nbsp;&nbsp;&nbsp;&nbsp;员工姓名：<input type="text" id="searchUname"> 
			&nbsp;&nbsp;&nbsp;&nbsp;<input id="searchPriseBtn" type="button" value="查询">
		</div>
		<table id="priseTable" style="width:50%;margin-top:10px;">
			<thead>
				<tr align="center">
					<th width="33%">账号</th>
					<th width="33%">姓名</th>
					<th width="33%">奖惩（元）</th>
				</tr>
			</thead>
			<tbody style="line-height:120%;">

			</tbody>
		</table>
	</div>
</body>
</html>