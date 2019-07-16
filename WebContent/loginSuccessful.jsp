<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.jason.entity.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录成功界面</title>
</head>
<body>
登录成功<br>
<%User user = (User)session.getAttribute("login"); %>
欢迎<%= user.getUsername() %>
<br>
<a href="index.jsp">返回登录前页面</a>
</body>
</html>