<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/table.css" />
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/SZCfuzzyquery.js"></script>
</head>
<style>
#setProxyidBox{
	margin:20px 0 0 30px;
}
#setProxyidBox div a{
text-decoration: none;
color:black;
}
#setProxyidBox div a:HOVER {
	color: gray;
}
#setProxyidBox div a:VISITED {
	color: black;
}
#updateBox{
	margin:20px 0 0 30px;
}
#updateBox table{
	width:auto;
	
}
#updateBox td{
text-align: left;
border:none;
}
</style>
<body>
	<div id="searchContainer">
		<div>
			年份：<select>
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
			</select><input id="searchBtn" type="button" value="年份查询" />
		</div>
		<div class="section">
			<table id="case1">
				<caption>诉讼、非诉讼、仲裁案件</caption>
				<thead>
					<tr>
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
				</thead>
				<tbody>
					<tr>
						<td>&nbsp;</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="section">
			<table id="case2">
				<caption>法务体检、法律顾问、法务管家</caption>
				<thead>
					<tr>
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
				<tbody>
					<tr>
						<td>&nbsp;</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="detailContainner" style="display: none;">
		操作菜单: <input style="background-color:#EEEEEE;" type="button" id="setNumBtn" value="设置案号/编号"> <input
			type="button" style="background-color:#EEEEEE;" id="updateBtn" value="修改"> <input type="button"
			id="delBtn" value="删除"> <input type="button" id="backBtn"
			value="返回">
		<div id="setProxyidBox" style="display:none;">
			<input type="text" style="width: 300px;" id="setProxyidBoxText"><input
				 id="saveProxyBtn" type="button" value="保存">
			<div>
				<a href="#">（）浙易律非诉字第号案件年月日</a><br> <a href="#">（）浙易律顾字第号案年月日</a><br>
				<a href="#">（）浙易律管字第号案年月日</a><br> <a href="#">（）浙易律检字第号案年月日</a><br>
				<a href="#">（）浙易律民字第号案年月日</a><br> <a href="#">（）浙易律外民字第号案年月日</a><br>
				<a href="#">（）浙易律外刑字第号案年月日</a><br> <a href="#">（）浙易律刑字第号案年月日</a><br>
				<a href="#">（）浙易律行字第号案年月日 </a><br> <a href="#">（）浙易律知字第号案年月日</a><br>
				<a href="#">（）浙易律执字第号案年月日</a><br> <a href="#">（）浙易律仲字第号案年月日</a><br>
			</div>
		</div>
		<div id="updateBox" style="display:none;">
		<table id="case1table">
		<tr><td>受理日期</td><td><input type="text" id="facceptdate" ></td></tr>
		<tr><td>委托人</td><td><input type="text" id="fconsignor" ></td></tr>
		<tr><td>委托事项/案由</td><td><input type="text" id="fcommitment" ></td></tr>
		<tr><td>咨询费</td><td><input type="text" id="fconsultfee" ></td></tr>
		<tr><td>委托日期</td><td><input type="text" id="fentrustdate" ></td></tr>
		<tr><td>代理案号</td><td><input readonly="readonly" type="text" id="fproxyid" ></td></tr>
		<tr><td>关联当事人</td><td><input type="text" id="frelate" ></td></tr>
		<tr><td>标的</td><td><input type="text" id="ftarget" ></td></tr>
		<tr><td>代理费</td><td><input type="text" id="fagencyfee" ></td></tr>
		<tr><td>管辖机关</td><td><input type="text" id="fgovadmin" ></td></tr>
		<tr><td>归档日期</td><td><input type="text" id="ffilingdate" ></td></tr>
		<tr><td>备注</td><td><input type="text" id="fmemo" ></td></tr>
		<tr><td>协办人</td><td><input type="text" id="fcoordinator" ></td></tr>
		<tr><td>主办人</td><td><input type="text" id="fsponsor" ></td></tr>
		<tr><td></td><td style="text-align:right;"><input type="button" id="saveUpdate1Btn" value="保存修改" /></td></tr>
		</table>
		<table id="case2table">
		<tr><td>编号</td><td><input type="text" id="number"/></td></tr>
		<tr><td>受聘期限</td><td><input type="text" id="engagedate"/></td></tr>
		<tr><td>聘请单位</td><td><input type="text" id="hostinstitution"/></td></tr>
		<tr><td>法定代表人</td><td><input type="text" id="legalrepresentative"/></td></tr>
		<tr><td>联系人</td><td><input type="text" id="contact"/></td></tr>
		<tr><td>年收费</td><td><input type="text" id="charge"/></td></tr>
		<tr><td>续聘年度</td><td><input type="text" id="recruityear"/></td></tr>
		<tr><td>续聘年收费</td><td><input type="text" id="recruitfee"/></td></tr>
		<tr><td>归档日期</td><td><input type="text" id="filingdate"/></td></tr>
		<tr><td>备注</td><td><input type="text" id="memo"/></td></tr>
		<tr><td>协办人</td><td><input type="text" id="coordinator"/></td></tr>
		<tr><td>主办人</td><td><input type="text" id="sponsor"/></td></tr>
		<tr><td/><td style="text-align:right;"><input type="button" id="saveUpdate2Btn" value="保存修改"/></td></tr>
		</table>
		</div>
	</div>
</body>
</html>