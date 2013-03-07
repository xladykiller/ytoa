<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.yitengls.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>浙江易腾律师事务所办公管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />

<script type="text/JavaScript">
	var $ = function(id) {
		return document.getElementById(id);
	}

	function show_menu(num) {
		for (i = 0; i < 100; i++) {
			if ($('li0' + i)) {
				$('li0' + i).style.display = 'none';
				$('f0' + i).className = '';
			}
		}
		$('li0' + num).style.display = 'block';//触发以后信息块
		$('f0' + num).className = 'left02down01_xia_li';//触发以后TAG样式
	}

	function show_menuB(numB) {
		for (j = 0; j < 100; j++) {
			if (j != numB) {
				if ($('Bli0' + j)) {
					$('Bli0' + j).style.display = 'none';
					$('Bf0' + j).style.background = 'url(images/01.gif)';
				}
			}
		}
		if ($('Bli0' + numB)) {
			if ($('Bli0' + numB).style.display == 'block') {
				$('Bli0' + numB).style.display = 'none';
				$('Bf0' + numB).style.background = 'url(images/01.gif)';
			} else {
				$('Bli0' + numB).style.display = 'block';
				$('Bf0' + numB).style.background = 'url(images/02.gif)';
			}
		}
	}

	var temp = 0;
	function show_menuC() {
		if (temp == 0) {
			document.getElementById('LeftBox').style.display = 'none';
			document.getElementById('RightBox').style.marginLeft = '0';
			document.getElementById('Mobile').style.background = 'url(images/center.gif)';

			temp = 1;
		} else {
			document.getElementById('RightBox').style.marginLeft = '222px';
			document.getElementById('LeftBox').style.display = 'block';
			document.getElementById('Mobile').style.background = 'url(images/center0.gif)';

			temp = 0;
		}
	}
</script>
</head>
<%
	TuserBean user = (TuserBean) request.getSession().getAttribute(
			"user");
	String uname = null;
	String rolename = null;
	if (user != null) {
		uname = user.getUname();
		rolename = user.getRolename();
	}
%>
<body>
	<div class="header">
		<div class="header03"></div>
		<div class="header01"></div>
		<div class="header02">易腾律师事务所办公系统</div>
	</div>
	<div class="left" id="LeftBox">
		<div class="left01">
			<div class="left01_right"></div>
			<div class="left01_left"></div>
			<div class="left01_c"><%=rolename%>：<%=uname%></div>
		</div>
		<!-- ================================================================================================== -->	
		<div class="left02">
			<div class="left02top">
				<div class="left02top_right"></div>
				<div class="left02top_left"></div>
				<div class="left02top_c">用户信息管理</div>
			</div>
			
			<div class="left02down">
				<div class="left02down01">
					<a onclick="show_menuB(79)" href="javascript:;">
					<div id="Bf079"class="left02down01_img"></div>用户信息操作</a>
				</div>
				<div class="left02down01_xia noneBox" id="Bli079">
					<ul>
						<li onclick="show_menu(8)" id="f08"><a target="iframe" href="XXAddUser.jsp">&middot;添加用户</a></li>
						<li onclick="show_menu(9)" id="f09"><a target="iframe"  href="XXSearchUser.jsp">&middot;删除、修改用户</a></li>
					</ul>
				</div>
				<!-- <div class="left02down01">
					<a onclick="show_menuB(80)" href="javascript:;"><div id="Bf080"
							class="left02down01_img"></div>用户信息查询</a>
				</div>
				<div class="left02down01_xia noneBox" id="Bli080">
					<ul>
						<li onclick="show_menu(10)" id="f010"><a target="iframe"  href="XXSearchUser.jsp">&middot;精确查询</a></li>
						<li onclick="show_menu(11)" id="f011"><a href="#">&middot;组合条件查询</a></li>
					</ul>
				</div> -->
				<div class="left02down01">
					<a onclick="show_menuB(81)" href="javascript:;">
						<div id="Bf081" class="left02down01_img"></div> 用户密码管理
					</a>
				</div>
				<div class="left02down01_xia noneBox" id="Bli081">
					<ul>
						<li onclick="show_menu(12)" id="f012"><a target="iframe" href="testDatepiacker.jsp">&middot;找回密码</a></li>
						<li onclick="show_menu(13)" id="f013"><a target="iframe"  href="XXUpdatePsw.jsp">&middot;更改密码</a></li>
					</ul>
				</div>
			</div>
		</div>
		
	<!-- ================================================================================================== -->	
		<div class="left02">
			<div class="left02top">
				<div class="left02top_right"></div>
				<div class="left02top_left"></div>
				<div class="left02top_c">业务办理</div>
			</div>
			<div class="left02down">
				<div class="left02down01">
					<a onclick="show_menuB(80)" href="javascript:;"><div id="Bf080"
							class="left02down01_img"></div>案件信息查询</a>
				</div>
				<div class="left02down01_xia noneBox" id="Bli080">
					<ul>
						<li onclick="show_menu(10)" id="f010"><a target="iframe"
							href="SZCfuzzyquery.jsp">&middot;年份查询</a></li>
						<li onclick="show_menu(11)" id="f011"><a target="iframe" href="SZCcomplexquery.jsp">&middot;组合条件查询</a></li>
					</ul>
				</div>
				<div class="left02down01">
					<a onclick="show_menuB(81)" href="javascript:;">
						<div id="Bf081" class="left02down01_img"></div> 工作平台
					</a>
				</div>
				<div class="left02down01_xia noneBox" id="Bli081">
					<ul>
						<li onclick="show_menu(12)" id="f012"><a href="#">&middot;找回密码</a></li>
						<li onclick="show_menu(13)" id="f013"><a href="#">&middot;更改密码</a></li>
					</ul>
				</div>
			</div>
		</div>
		
		<!-- ================================================================================================== -->	
		<div class="left02">
			<div class="left02top">
				<div class="left02top_right"></div>
				<div class="left02top_left"></div>
				<div class="left02top_c">办公管理</div>
			</div>
			
			<div class="left02down">
				<div class="left02down01">
					<a onclick="show_menuB(82)" href="javascript:;"><div id="Bf082"
							class="left02down01_img"></div>规章制度管理</a>
				</div>
				<div class="left02down01_xia noneBox" id="Bli082">
					<ul>
						<li onclick="show_menu(14)" id="f014"><a target="iframe" href="XXAddRegulation.jsp">&middot;添加规章制度</a></li>
						<li onclick="show_menu(15)" id="f015"><a target="iframe" href="XXSearchRegulation.jsp">&middot;删除、修改规章制度</a></li>
						<li onclick="show_menu(51)" id="f051"><a target="iframe" href="XXViewRegulation.jsp">&middot;查看规章制度</a></li>
					</ul>
				</div>
				
				<div class="left02down01">
					<a onclick="show_menuB(83)" href="javascript:;"><div id="Bf083"
							class="left02down01_img"></div>通知管理</a>
				</div>
				<div class="left02down01_xia noneBox" id="Bli083">
					<ul>
						<li onclick="show_menu(16)" id="f016"><a target="iframe" href="XXAddInfo.jsp">&middot;添加通知</a></li>
						<li onclick="show_menu(17)" id="f017"><a target="iframe" href="XXSearchInfo.jsp">&middot;删除、修改通知</a></li>
						<li onclick="show_menu(52)" id="f052"><a target="iframe" href="XXViewInfo.jsp">&middot;查看通知</a></li>
					</ul>
				</div>
				
				<div class="left02down01">
					<a onclick="show_menuB(84)" href="javascript:;"><div id="Bf084"
							class="left02down01_img"></div>典型案例管理</a>
				</div>
				<div class="left02down01_xia noneBox" id="Bli084">
					<ul>
						<li onclick="show_menu(18)" id="f018"><a target="iframe" href="XXAddExample.jsp">&middot;添加案例</a></li>
						<li onclick="show_menu(19)" id="f019"><a target="iframe" href="XXSearchExample.jsp">&middot;删除、修改案例</a></li>
						<li onclick="show_menu(53)" id="f053"><a target="iframe" href="XXViewExample.jsp">&middot;查看案例</a></li>
					</ul>
				</div>
			</div>
		</div>
		
		
	<!-- ================================================================================================== -->
		<div class="left02">
			<div class="left02top">
				<div class="left02top_right"></div>
				<div class="left02top_left"></div>
				<div class="left02top_c">行政管理</div>
			</div>
			
			<div class="left02down">
				<div class="left02down01">
					<a onclick="show_menuB(85)" href="javascript:;"><div id="Bf085"
							class="left02down01_img"></div>公章使用管理</a>
				</div>
				<div class="left02down01_xia noneBox" id="Bli085">
					<ul>
						<li onclick="show_menu(30)" id="f030"><a target="iframe" href="XXAddStamp.jsp">&middot;填写公章使用表</a></li>
						<li onclick="show_menu(31)" id="f031"><a target="iframe" href="XXSearchStamp.jsp">&middot;删除、修改公章使用表</a></li>
						<li onclick="show_menu(32)" id="f032"><a target="iframe" href="XXViewStamp.jsp">&middot;查看公章使用表</a></li>
					</ul>
				</div>
				
				<div class="left02down01">
					<a onclick="show_menuB(86)" href="javascript:;"><div id="Bf086"
							class="left02down01_img"></div>风险事故管理</a>
				</div>
				<div class="left02down01_xia noneBox" id="Bli086">
					<ul>
						<li onclick="show_menu(33)" id="f033"><a target="iframe" href="XXAddRisk.jsp">&middot;填写风险事故表</a></li>
						<li onclick="show_menu(34)" id="f034"><a target="iframe" href="XXSearchRisk.jsp">&middot;删除、修改风险事故表</a></li>
						<li onclick="show_menu(35)" id="f035"><a target="iframe" href="XXViewRisk.jsp">&middot;查看风险事故表</a></li>
					</ul>
				</div>
				
				<div class="left02down01">
					<a onclick="show_menuB(87)" href="javascript:;"><div id="Bf087"
							class="left02down01_img"></div>员工考核管理</a>
				</div>
				<div class="left02down01_xia noneBox" id="Bli087">
					<ul>
						<li onclick="show_menu(36)" id="f036"><a target="iframe" href="XXAddSelfevaluation.jsp">&middot;填写自评考核表</a></li>
						<li onclick="show_menu(37)" id="f037"><a target="iframe" href="XXSearchSelfevaluation.jsp">&middot;删除、修改自评考核表</a></li>
						<li onclick="show_menu(38)" id="f038"><a target="iframe" href="XXViewSelfevaluation.jsp">&middot;查看自评考核表</a></li>
						<li onclick="show_menu(39)" id="f039"><a target="iframe" href="XXViewPrise.jsp">&middot;奖惩统计</a></li>
					</ul>
				</div>
			</div>
		</div>
		
<!-- ================================================================================================== -->		
		<div class="left02">
			<div class="left02top">
				<div class="left02top_right"></div>
				<div class="left02top_left"></div>
				<div class="left02top_c">系统管理</div>
			</div>
			
			<div class="left02down">
				<div class="left02down01">
					<a onclick="show_menuB(90)" href="javascript:;">
						<div id="Bf090" class="left02down01_img"></div>权限管理
					</a>
				</div>
				<div class="left02down01_xia noneBox" id="Bli090">
					<ul>
						<li onclick="show_menu(20)" id="f020"><a target="iframe"  href="XXAddPower.jsp">&middot;添加角色</a></li>
						<li onclick="show_menu(21)" id="f021"><a target="iframe"  href="XXUpdatePower.jsp">&middot;删除角色、修改权限</a></li>
					</ul>
				</div>
				
				<div class="left02down01">
					<a href="#"><div class="left02down01_img"></div>用户组管理</a>
				</div>
				
				<div class="left02down01">
					<a href="#"><div class="left02down01_img"></div>操作日志</a>
				</div>
			</div>
		</div>
		
	<!-- ================================================================================================== -->	

		
		

		 
<!-- ================================================================================================== -->			 
		<a href="LogOut"><div class="left01" id="logout">
				<div class="left03_right"></div>
				<div class="left01_left"></div>
				<div class="left03_c">安全退出</div>
			</div></a>
	</div>
	<div class="rrcc" id="RightBox">
		<div class="center" id="Mobile" onclick="show_menuC()"></div>
		<div class="right">
			<div class="noneBox" id="li010">
				<div class="right01">
					<img src="images/04.gif" /> 用户信息查询 &gt; <span>精确查询</span>
				</div>
			</div>
			<div class="noneBox" id="li011">
				<div class="right01">
					<img src="images/04.gif" /> 用户信息查询 &gt; <span>组合条件查询</span>
				</div>
			</div>
			<div class="noneBox" id="li012">
				<div class="right01">
					<img src="images/04.gif" /> 用户密码管理 &gt; <span>找回密码</span>
				</div>
			</div>
			<div class="noneBox" id="li013">
				<div class="right01">
					<img src="images/04.gif" /> 用户密码管理 &gt; <span>更改密码</span>
				</div>
			</div>
			<iframe name="iframe"  frameborder="0" width="100%" height="90%"
				src=""></iframe>

		</div>


	</div>
</body>
</html>