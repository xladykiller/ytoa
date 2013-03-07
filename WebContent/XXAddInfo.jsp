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
<script type="text/javascript" src="js/XXAddInfo.js"></script>

</head>

<%
//判断添加通知（addInfo）权限
TuserBean ub = (TuserBean)request.getSession().getAttribute("user");
if(!Power.validate(ub.getPermission(), Power.addInfo)){
	response.sendRedirect("result/noPermission.jsp");
	return;
}
%>
<body>
	<form action="XXInfo" method="post" enctype="multipart/form-data">
		<div style="font-size: 12px;margin:0 0 10px 0;">
			标题：<input type="text" id="infoTitle" name="infoTitle" style="width:400px;"/>
			&nbsp;&nbsp;&nbsp;&nbsp;发布人：<input type="text" id="infoAuthor" name="infoAuthor" style="width:200px;"/>
		</div>
		<textarea name="infoContent" id="infoContent"></textarea>
		<input type="hidden" name="action" value="add"/>
		<div  align="right" style="font-size: 12px; margin:10px 10px ;">
			上传附件：<input type="file" name="attachment" value="">
		</div>
		<!-- <input type="submit" id="attachmentSubmitBtn"  value="点击添加"> -->
	</form>
	<div align="right"  style="margin:10px 10px ;">
		<input type="button" id="addInfoBtn" value="提交" />
		<input type="button" value="重置" id="resetBtn" />
	</div>
	
<script>
    CKEDITOR.replace( 'infoContent' );
</script>
</body>
</html>