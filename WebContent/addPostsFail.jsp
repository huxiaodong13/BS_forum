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
    
    <title>提交帖子失败</title>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<% 
  		String message = (String)request.getAttribute("message"); 
  	%>
  	提交帖子失败：<%= message %><br/>
  	<a href="addPosts.jsp">返回发布页面</a>
  </body>
</html>
