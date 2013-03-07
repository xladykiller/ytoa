<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="js/XXutils.js"></script>
<script type="text/javascript" src="js/XXViewExample.js"></script>
<link href="css/ckeditcontents.css" rel="stylesheet" type="text/css" />
<link href="css/XXPage.css" rel="stylesheet" type="text/css" />
<style>
#searchExampleTable{
	border-collapse: collapse;
	border: none;
	width:100%;
	margin:10px auto;
}
#searchExampleTable th{
	padding: 5px 2px ;
	background-color: #EEEEEE;
	font-weight: normal;
}
#searchExampleTable th,#searchExampleTable td{
	border: solid  black 1px;
	text-align: center;
	font-size: 12px;
}
#searchExampleTable td{
	height:25px;
}
</style>
</head>
<body>
	<div id="searchExample">
		<span style="font-size:12px">标题：</span><input type="text" name="searchExampleTitle" id="searchExampleTitle" style="width:400px;">
		<input id="searchExampleBtn" type="button" value="查询">

		<table id="searchExampleTable">
			<thead>
				<tr>
					<th width="10%">编号</th>
					<th width="50%">标题</th>
					<th width="20%">修改日期</th>
					<th width="20%">作者</th>
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

	<div id="viewExample" style="display: none;">
		<div id="view">
			<div style="text-align: center">
				<h1 id="title"></h1>
				<br /> 作者：<span id="author"></span>&nbsp;&nbsp;&nbsp;&nbsp;修改日期：<span id="addtime"></span>
				<hr />
			</div>
			<div id="content"></div>
			<hr />
			<div align="center"  >
				<input type="button" value="返回" id="returnBtn">
			</div>
		</div>

	</div>
</body>
</html>