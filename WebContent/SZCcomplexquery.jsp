<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/SZCcomplexquery.css" />
<link rel="stylesheet" type="text/css" href="css/XXPage.css" />
<link rel="stylesheet" type="text/css"  href="css/table.css" />
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/SZCcomplexquery.js"></script>
<script type="text/javascript" src="js/XXutils.js"></script>
</head>
<body>
	<div>
		年份<select id="yearSelect">
			<option value="">任意年</option>
			<option value="2013">2013</option>
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
			<option value="2018">2018</option>
			<option value="2019">2019</option>
			<option value="2020">2020</option>
			<option value="2021">2021</option>
			<option value="2022">2022</option>
			<option value="2023">2023</option>
			<option value="2024">2024</option>
			<option value="2025">2025</option>
			<option value="2026">2026</option>
			<option value="2027">2027</option>
			<option value="2028">2028</option>
		</select> 类型 <select id="typeSelect">
			<option value="1">诉讼、非诉讼、仲裁案件</option>
			<option value="2">法务体检、法律顾问、法务管家</option>
		</select> 编号 <input type="text" value="" id="numberBtn" class="inEdit" /> 主办人
		<select id="sponsorSelect">
			<option value="0">&nbsp;</option>
		</select> 协办人 <select id="coordinatorSelect">
			<option value="0">&nbsp;</option>
		</select> 模糊信息 <input type="text" value="" id="fuzzyBtn" class="inEdit" /> <input
			id="searchBtn" type="button" value="查询" />
	</div>
	<table>
		<thead>
			<tr id="th1">
				<th>序号</th>
				<th>受理日期</th>
				<th>委托人</th>
				<th>委托事项/案由</th>
				<th>咨询费</th>
				<th>委托日期</th>
				<th>代理案号</th>
				<th>关联当事人</th>
				<th>标的</th>
				<th>代理费</th>
				<th>管辖机关</th>
				<th>归档日期</th>
				<th>备注</th>
				<th>协办人</th>
				<th>主办人</th>
			</tr>
			<tr id="th2">
				<th>序号</th>
				<th>编号</th>
				<th>受聘期限</th>
				<th>聘请单位</th>
				<th>法定代表人</th>
				<th>联系人</th>
				<th>年收费</th>
				<th>续聘年度</th>
				<th>续聘年收费</th>
				<th>归档日期</th>
				<th>备注</th>
				<th>协办人</th>
				<th>主办人</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<div id="paging">
		<a href="#" id="first" name="arrow">&lt;&lt;</a> <a href="#"
			id="previous" name="arrow">&lt;</a>

		<div id="page" style="display: inline"></div>
		<a href="#" id="next" name="arrow">&gt;</a> <a href="#" id="last"
			name="arrow">&gt;&gt;</a> 共<span id="totalPageNum"></span>页
	</div>

</body>
</html>