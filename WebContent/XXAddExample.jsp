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
<script type="text/javascript" src="js/XXAddExample.js"></script>
</head>
<%
//判断添加案例（addExample）权限
TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
if(!Power.validate(ub.getPermission(), Power.addExample)){
	response.sendRedirect("result/noPermission.jsp");
	return;
}
%>
<body>
<div style="font-size: 12px;margin:0 0 10px 0;">
	标题：<input type="text" id="exampleTitle" name="exampleTitle" style="width:400px;"/>
	上传者：<input type="text" id="exampleAuthor" name="exampleAuthor" style="width:200px;"/>
</div>
	<textarea name="exampleContent" id="exampleContent"></textarea>
	<div align="right"  style="margin:10px 10px ;">
		<input type="button" id="addExampleBtn" value="提交" />
		<input type="button" value="重置" id="resetBtn" />
	</div>
<script>
    CKEDITOR.replace( 'exampleContent' );
</script>
</body>
</html>