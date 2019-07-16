<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.jason.entity.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8">
<title>头界面</title>
</head>
<body>
<div id="header">
        <div id="header-content" class="clearfix">
            <div id="logo" class="v-center">
                <a href="index.jsp"><img src="img/logo.jpg" alt="logo"></a>
            </div>
            <div id="searchbar" class="v-center">
 				<input type="text" maxlength="20" id="search-text" class="search-text" placeholder="搜索..." />
				<input type="button" id="search-btn" class="search-btn" />
            </div>
            
            <div id="login-links" class="v-center">
            <%
				User user = (User)session.getAttribute("login");
				if(user == null) {
			%>
                <a href="#" id="btn-register">注册</a>
                <a href="#" id="btn-login">登陆</a>
                
                <%
				} else {
			%>
				你好，<%= user.getUsername() %>。
				<a href="doLogout.jsp">退出登录</a>
			<%
				}
			%>
            </div>
            
        </div>
    </div>
</body>
</html>