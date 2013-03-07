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
<script type="text/javascript" src="js/XXViewSelfevaluation.js"></script>
<link href="css/XXPage.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="datepicker/development-bundle/themes/base/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css"
	href="datepicker/development-bundle/demos/demos.css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
</head>
<body>
	<div id="searchSelfEval">
		<div style="font-size: 12px;">
				开始日期：<input type="text" readonly="readonly" id="startdate">
				&nbsp;&nbsp;&nbsp;&nbsp;结束日期：<input type="text" readonly="readonly" id="enddate"> 
				&nbsp;&nbsp;&nbsp;&nbsp;<input id="searchSelfEvalBtn" type="button" value="查询">
		        <br />
		        <br/>
		        奖惩：<span id="prise"></span>
		</div>
		<table width="100%" id="searchSelfEvalTable" style="margin-top:10px;">
		<thead>
			<tr align="center">
				<th width="7%">日期</th>
				<th colspan="4">缺勤：每项每次扣0.5元</th>
				<th colspan="3">奖项：每项每天奖0.5元</th>
				<th colspan="4">罚项：每项每次1元</th>
				<th width="15%">考核结果</th>
				<th width="15%">自我评价</th>
			</tr>
			<tr align="center">
				<th>&nbsp;</th>
				<th>迟到</th>
				<th>早退</th>
				<th>事假</th>
				<th>病假</th>
				<th>心情愉悦</th>
				<th>环境整洁</th>
				<th>办事迅速</th>
				<th>工作失误</th>
				<th>擅离岗位</th>
				<th>旷工</th>
				<th>玩游戏</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
			</thead>
			<tbody style="line-height:120%;">
			
			</tbody>
		</table>

		<div  style="display:none;"  id="paging">
			<a href="#" id="first" name="arrow">&lt;&lt;</a> <a href="#"
				id="previous" name="arrow">&lt;</a>

			<div id="page" style="display: inline"></div>
			<a href="#" id="next" name="arrow">&gt;</a> <a href="#" id="last"
				name="arrow">&gt;&gt;</a> 共<span id="totalPageNum"></span>页
		</div>

	</div>

	<div id="viewSelfEval" style="display:none;">
			<div style="font-size: 12px;">
				日期： <span id="evaldate" ></span>
			</div>
		<table width="100%" style="margin-top:10px;">
			<tr>
				<td colspan="2" style="padding:5px 0;">缺勤：每项每次扣0.5元</td>
				<td colspan="2">奖项：每项每天奖0.5元</td>
				<td colspan="2">罚项：每项每次1元</td>
			</tr>
			<tr>
				<td width="8%" style="padding:5px 0;">迟到</td>
				<td width="25%" ><span id="late"></span></td>
				<td width="8%">心情愉悦</td>
				<td width="25%"><span id="happy"></span></td>
				<td width="8%">工作失误</td>
				<td width="26%"><span id="mistake"></span></td>
			</tr>
			<tr>
				<td style="padding:5px 0;">早退</td>
				<td><span id="leave"></span></td>
				<td>环境整洁</td>
				<td><span id="tidy"></span></td>
				<td>擅离岗位</td>
				<td><span id="leaveseat"></span></td>
			</tr>
			<tr>
				<td style="padding:5px 0;">事假</td>
				<td><span id="absence"></span></td>
				<td>办事迅速</td>
				<td><span id="speedy"></span></td>
				<td>旷&nbsp;工&nbsp;</td>
				<td><span id="absentwork"></span></td>
			</tr>
			<tr>
				<td style="padding:5px 0;">病假</td>
				<td><span id="sick"></span></td>
				<td colspan="2">&nbsp;</td>
				<td>玩游戏</td>
				<td><span id="playgames"></span></td>
			</tr>

		</table>

		<table>
			<tr>
				<td width="8%">考核结果</td>
				<td><textarea id="result" rows="9" cols="118" style="border: 0;" ></textarea></td>
			</tr>
			<tr>
				<td>自我评价</td>
				<td ><textarea id="content" rows="9" cols="118" style="border: 0;" ></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="返回"	id="returnBtn" /></td>
			</tr>
		</table>

	</div>

</body>
</html>