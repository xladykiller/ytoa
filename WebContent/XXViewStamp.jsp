<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="js/XXutils.js"></script>
<script type="text/javascript" src="js/XXViewStamp.js"></script>
<link href="css/XXPage.css" rel="stylesheet" type="text/css" />

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
#searchStampTable td{
	height:25px;
}

#viewStamp{
	border-collapse: collapse;
	border: none;
	width:100%;
	margin:10px auto;
}
#viewStamp td{
	text-indent:2em;
	border: solid  black 1px;
	text-align: left;
	font-size: 12px;
	height:25px;
	line-height:200%;
	padding:0 10px;"
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
					<th width="55%">使用事由</th>
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

<!-- style="display: none;" -->
<table id="viewStamp" style="display: none;">
		<tr>
			<td width="100px">使用日期</td>
			<td id="usedate"></td>
		</tr>
		<tr>
			<td>使用人账号</td>
			<td  id="petitioner"></td>
		</tr>
		<tr>
			<td>使用人姓名</td>
			<td id="uname"></td>
		</tr>
		<tr>
			<td>文件数量</td>
			<td id="filenum"></td>
		</tr>
		<tr>
			<td>使用事由</td>
			<td id="usereason" ></td>
		</tr>
		<tr>
			<td>备注</td>
			<td  id="memo"></td>
		</tr>
		<tr>
			<td colspan="2"  style="text-align:center;"><input type="button" value="返回" id="returnBtn"></td>
		</tr>
	</table>
</body>
</html>