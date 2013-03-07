<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="com.yitengls.bean.TuserBean,com.yitengls.utils.Power" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="js/XXAddRegulation.js"></script>
</head>
<%
//判断添加规章制度（addRegulation）权限
TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
if(!Power.validate(ub.getPermission(), Power.addRegulation)){
	response.sendRedirect("result/noPermission.jsp");
	return;
}
%>
<body>
<div style="font-size: 12px;margin:0 0 10px 0;">
	标题：<input type="text" id="regulationTitle" name="regulationTitle" style="width:400px"/>
	&nbsp;&nbsp;&nbsp;&nbsp;作者：<input type="text" id="regulationAuthor" name="regulationAuthor" style="width:200px"/>
</div>
	<textarea name="regulationContent" id="regulationContent"></textarea>
<div align="right"  style="margin:10px 10px ;">
	<input type="button" id="addRegulationBtn" value="提交" />
	<input type="button" value="重置" id="resetBtn" />
</div>
<script>
    CKEDITOR.replace( 'regulationContent' );
</script>
</body>
</html>