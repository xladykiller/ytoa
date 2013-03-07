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
<script type="text/javascript" src="js/XXSearchInfo.js"></script>
<link href="css/XXPage.css" rel="stylesheet" type="text/css" />
<style>
#searchInfoTable{
	border-collapse: collapse;
	border: none;
	width:100%;
	margin:10px auto;
}
#searchInfoTable th{
	padding: 5px 2px ;
	background-color: #EEEEEE;
	font-weight: normal;
}
#searchInfoTable th,#searchInfoTable td{
	border: solid  black 1px;
	text-align: center;
	font-size: 12px;
}
</style>
</head>
<body>
<div id="searchInfo">
		<span style="font-size:12px;">标题：</span><input type="text" name="searchInfoTitle"
			id="searchInfoTitle" style="width:400px"> <input id="searchInfoBtn"
			type="button" value="查询">

		<table id="searchInfoTable">
			<thead>
				<tr>
					<th width="5%">编号</th>
					<th width="40%">标题</th>
					<th width="15%">发布日期</th>
					<th width="20%">发布人</th>
					<th width="10%">删除通知</th>
					<th width="10%">修改通知</th>
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

	<div id="updateInfo" style="display: none;">
		<div  style="margin:0 0 10px 0;font-size:12px;">
			标题：<input type="text" id="updateInfoTitle"name="updateInfoTitle" style="width:400px" /> 
			发布人：<input type="text"	id="updateInfoAuthor" name="updateInfoAuthor" style="width:200px"/>
		</div>
		<textarea name="updateInfoContent" id="updateInfoContent"></textarea>
		<!-- <input type="hidden" name="action" value="update"/>
		修改附件：<input type="file" name="attachment" value=""> -->
		<div  align="right"  style="margin:10px 10px ;">
			<input type="button" id="updateInfoBtn" value="提交" />
		 	<input type="button" value="返回" id="returnBtn">
		 </div>
	</div>

	<script>
		CKEDITOR.replace('updateInfoContent');
	</script>
</body>
</html>