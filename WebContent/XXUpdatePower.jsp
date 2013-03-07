<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="js/XXUpdatePower.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css" />
<style>
#updatePowerTable input[type=text]{
width:150px;
}
#updatePowerTable input[type=checkbox]{
margin-left:20px;
}

#updatePowerTable{
	border-collapse: collapse;
	border: none;
	width:100%;
	
}

#updatePowerTable td{
	border: solid  black 1px;
	font-size: 12px;
	text-indent:2em;
	line-height:25px;
	text-align:left;
}
#updatePermission td{
text-align:left;
}

</style>
</head>
<body>
	<table id="powerTable">
		<thead>
			<tr>
				<th>编号</th>
				<th>角色名</th>
				<th>删除角色</th>
				<th>修改权限</th>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>

	<table id="updatePowerTable" style="display: none">
		<tr>
			<td width="100px" >编辑角色名</td>
			<td style="text-indent:0;"><input type="text" id="updateRolename" /></td>
		</tr>
		<tr>
			<td>编辑权限</td>
			<td>
				<!-- <div id="updatePermission">
				<input type="checkbox"  value="1" id="addUser"/>添加用户
				<input type="checkbox"  value="2" id="searchUser"/>查询用户
				<input type="checkbox"  value="3" id="delUser"/>删除用户
				<input type="checkbox"  value="4" id="updateUser"/>修改用户
				</div> -->
				<table id="updatePermission">
					<tr>
						<td width="100px;">用户</td>
						<td><input type="checkbox" value="1" id="updatePermission" />添加用户 <input
							type="checkbox" value="2" id="delUser" />删除用户 <input
							type="checkbox" value="3" id="updateUser" />修改用户 <input
							type="checkbox" value="4" id="searchUser" />查询用户 <input
							type="checkbox" value="5" id="updatePsw" />修改密码</td>
					</tr>
					<tr>
						<td>角色</td>
						<td><input type="checkbox" value="6" id="addPower" />添加角色 <input
							type="checkbox" value="7" id="delPower" />删除角色 <input
							type="checkbox" value="8" id="updatePower" />修改角色</td>
					</tr>
					<tr>
						<td>规章制度</td>
						<td><input type="checkbox" value="11" id="addRegulation" />添加规章
							<input type="checkbox" value="12" id="delRegulation" />删除规章 <input
							type="checkbox" value="13" id="updateRegulation" />修改规章 <input
							type="checkbox" value="14" id="searchRegulation" />查询规章</td>
					</tr>
					<tr>
						<td>通知</td>
						<td><input type="checkbox" value="16" id="addInfo" />添加通知 <input
							type="checkbox" value="17" id="delInfo" />删除通知 <input
							type="checkbox" value="18" id="updateInfo" />修改通知 <input
							type="checkbox" value="19" id="searchInfo" />查询通知 <input
							type="checkbox" value="20" id="downloadInfo"  style="display:none;"/><!-- 下载附件 -->
						</td>
					</tr>
					<tr>
						<td>典型案例</td>
						<td><input type="checkbox" value="21" id="addExample" />添加案例
							<input type="checkbox" value="22" id="delExample" />删除案例 <input
							type="checkbox" value="23" id="updateExample" />修改案例 <input
							type="checkbox" value="24" id="searchExample" />查询案例</td>
					</tr>
					<tr>
						<td>公章使用</td>
						<td><input type="checkbox" value="26" id="addStamp" />添加公章 <input
							type="checkbox" value="27" id="delStamp" />删除公章 <input
							type="checkbox" value="28" id="updateStamp" />修改公章 <input
							type="checkbox" value="29" id="searchStamp" />查询公章</td>
					</tr>
					<tr>
						<td>风险事故</td>
						<td><input type="checkbox" value="31" id="addRisk" />添加风险 <input
							type="checkbox" value="32" id="delRisk" />删除风险 <input
							type="checkbox" value="33" id="updateRisk" />修改风险 <input
							type="checkbox" value="34" id="searchRisk" />查询风险</td>
					</tr>
					<tr>
						<td>员工考核</td>
						<td><input type="checkbox" value="36" id="addEval" />添加考核 <input
							type="checkbox" value="37" id="delEval" />删除考核 <input
							type="checkbox" value="38" id="updateEval" />修改考核 <input
							type="checkbox" value="39" id="searchMyEval" />查询个人考核 <input
							type="checkbox" value="40" id="searchAllEval" />查询员工考核 <input
							type="checkbox" value="41" id="viewAllPrise" />查看员工奖惩统计</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;"><input type="button" value="提交"
				id="updatePowerBtn" /> <input type="button" value="返回"
				id="returnBtn" /></td>
		</tr>
	</table>

</body>
</html>