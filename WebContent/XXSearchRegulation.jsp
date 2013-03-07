<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="js/XXutils.js"></script>
<script type="text/javascript" src="js/XXSearchRegulation.js"></script>
<link href="css/XXPage.css" rel="stylesheet" type="text/css" />
<style>
#searchRegulationTable{
	border-collapse: collapse;
	border: none;
	width:100%;
	margin:10px auto;
}
#searchRegulationTable th{
	padding: 5px 2px ;
	background-color: #EEEEEE;
	font-weight: normal;
}
#searchRegulationTable th,#searchRegulationTable td{
	border: solid  black 1px;
	text-align: center;
	font-size: 12px;
}
</style>
</head>
<body>
	<div id="searchRegulation">
		<span style="font-size:12px;">标题：</span><input type="text" name="searchRegulationTitle"
			id="searchRegulationTitle" style="width:400px"> <input id="searchRegulationBtn"
			type="button" value="查询">

		<table id="searchRegulationTable">
			<thead>
				<tr>
					<th width="5%">编号</th>
					<th width="40%">标题</th>
					<th width="15%">修改日期</th>
					<th width="20%">作者</th>
					<th width="10%">删除规章制度</th>
					<th width="10%">修改规章制度</th>
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

	<div id="updateRegulation" style="display: none; margin:0 0 10px 0;">
		<div  style="margin:0 0 10px 0;font-size:12px;">
			标题：<input type="text" id="updateRegulationTitle"
				name="updateRegulationTitle"  style="width:400px"/> &nbsp;&nbsp;&nbsp;&nbsp;作者：<input type="text"
				id="updateRegulationAuthor" name="updateRegulationAuthor" style="width:200px"/>
		</div>
		<textarea name="updateRegulationContent" id="updateRegulationContent"></textarea>
		<div  align="right"  style="margin:10px 10px ;">
			<input type="button" id="updateRegulationBtn" value="提交" /> <input
				type="button" value="返回" id="returnBtn">		
		</div>
	</div>

	<script>
		CKEDITOR.replace('updateRegulationContent');
	</script>
</body>
</html>