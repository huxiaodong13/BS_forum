<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset=utf-8">   
    
    <title>提交帖子成功</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function go_back() {
			var previous = document.referrer;
			window.location.href=previous;
		}
	</script>
  </head>
  
  <body>
  	提交帖子成功！<br/>
  	<a href="listPosts.jsp">返回帖子列表</a>
  </body>
</html>
