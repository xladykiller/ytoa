<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="datepicker/development-bundle/ui/jquery.ui.core.js" ></script>
<script type="text/javascript" src="datepicker/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="datepicker/development-bundle/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="js/XXAddSelfevaluation.js"></script>
<link  rel="stylesheet" type="text/css" href="datepicker/development-bundle/themes/base/jquery.ui.all.css"/>
<link  rel="stylesheet" type="text/css" href="datepicker/development-bundle/demos/demos.css"/>
<link  rel="stylesheet" type="text/css" href="css/table.css"/>
</head>
<body>

	
 <span style="font-size:12px;">日期： </span><input type="text" id="evaldate"  readonly="readonly">


	<table width="100%"  style="margin-top:10px;">
  <tr>
    <td colspan="2"  style="padding:5px 0;">缺勤：每项每次扣0.5元 </td>
    <td colspan="2">奖项：每项每天奖0.5元 </td>
    <td colspan="2">罚项：每项每次1元 </td>
  </tr>
  <tr>
    <td width="8%">迟到    </td>
    <td width="25%"><input type="checkbox"  name="qq" id="late" value="late"></td>
    <td width="8%">心情愉悦    </td>
    <td width="25%"><input type="checkbox"  name="jx"  id="happy" value="happy"></td>
    <td width="8%">工作失误    </td>
    <td width="26%"><input type="checkbox"   name="fx" id="mistake" value="mistake"></td>
  </tr>
  <tr>
    <td>早退    </td>
    <td><input type="checkbox"  name="qq" id="leave" value="leave"></td>
    <td>环境整洁    </td>
    <td><input type="checkbox"  name="jx"  id="tidy" value="tidy"></td>
    <td>擅离岗位    </td>
    <td><input type="checkbox"  name="fx" id="leaveseat" value="leaveseat"></td>
  </tr>
  <tr>
    <td>事假    </td>
    <td><input type="checkbox"  name="qq" id="absence" value="absence"></td>
    <td>办事迅速    </td>
    <td><input type="checkbox"   name="jx" id="speedy" value="speedy"></td>
    <td>旷&nbsp;工&nbsp;</td>
    <td><input type="checkbox" name="fx"  id="absentwork" value="absentwork"></td>
  </tr>
  <tr>
    <td>病假    </td>
    <td><input type="checkbox"  name="qq" id="sick" value="sick"></td>
    <td colspan="2">&nbsp;</td>
    <td>玩游戏</td>
    <td><input type="checkbox"  name="fx"  id="playgames" value="playgames"></td>
  </tr>
 
</table>

	<table>
		<tr>
			<td width="8%">考核结果</td>
			<td><textarea id="result" rows="8" cols="120" style="border: 0;"></textarea></td>
		</tr>
		<tr>
			<td>自我评价</td>
			<td><textarea id="content" rows="8" cols="120"  style="border: 0;"></textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" value="提交" id="addSelfevaluationBtn" />
				<input type="button" value="重置" id="resetBtn" /></td>
		</tr>
	</table>


</body>
</html>