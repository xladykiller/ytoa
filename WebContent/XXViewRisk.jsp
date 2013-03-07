<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="js/XXutils.js"></script>
<script type="text/javascript" src="js/XXViewRisk.js"></script>
<link href="css/XXPage.css" rel="stylesheet" type="text/css" />
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
#searchRiskTable td{
	height:25px;
}

#viewRisk{
	border-collapse: collapse;
	border: none;
	width:100%;
	margin:10px auto;
}
#viewRisk td{
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
					<th width="20%">投诉（事发）日期</th>
					<th width="20%">投诉人</th>
					<th width="35%">代理案号</th>
					<th width="20%">案件承办人</th>
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
<table id="viewRisk"  style="display: none;">
		<tr>
			<td width="140px">投诉（事发）日期</td>
			<td id="complaindate"></td>
		</tr>
		<tr>
			<td>投诉人</td>
			<td  id="complainant"></td>
		</tr>
		<tr>
			<td>投诉方式</td>
			<td id="complainway"></td>
		</tr>
		<tr>
			<td>联系电话</td>
			<td id="phone"></td>
		</tr>
		<tr>
			<td>代理案号</td>
			<td id="proxyid"></td>
		</tr>
		<tr>
			<td>案件承办人</td>
			<td  id="undertaker"></td>
		</tr>
		<tr>
			<td>投诉（事故）内容</td>
			<td  id="content"></td>
		</tr>
		<tr>
			<td>处理结果</td>
			<td  id="result"></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align:center;"><input type="button" value="返回" id="returnBtn"></td>
		</tr>
	</table>
</body>
</html>