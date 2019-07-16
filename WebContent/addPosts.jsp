<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.jason.entity.User" %>

<%
	User user = (User)session.getAttribute("login");
	if(user != null) {
	} else {
		response.sendRedirect("index.jsp");
		return ;
	}
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加帖子</title>
    <meta charset=utf-8">   
	<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
	<link href="css/add_posts_form_style.css" rel="stylesheet" type="text/css" />
	<link href="css/demo.css" rel="stylesheet" type="text/css">
	<script src="js/jquery-3.4.0.js"></script>
    <script src="js/jquery-ui.js"></script>
  </head>
  
  <body>
    <div id="header">
        <div id="header-content" class="clearfix">
            <div id="logo" class="v-center">
                <a href="index.jsp"><img src="img/logo.jpg" alt="logo"></a>
            </div>
            <div id="login-links" class="v-center">
                <%= user.getUsername() %>。<a href="doLogout.jsp">退出登录</a>
            </div>
        </div>
    </div>  	

  		<div id="content_panel">
		  <div id="column_w610">
			<div class="header_01">
				添加帖子
			</div>
			<form id="add_posts_form" method="post" action="doAddPost.jsp" onsubmit="return check_add_posts_form(this);">
				<table>
					<tr>
						<td>标题：</td>
						<td><input type="text" id="title" name="title" title="请输入帖子标题"/></td>
					</tr>
					<%
				String msg = (String)request.getAttribute("error");
				if(msg != null) {
			%>
					<tr>
						<td colspan="2" class="error">
							<%= msg %>
						</td>
					</tr>
			<%
				}
			%>
					<tr>
						<td>内容：</td>
						<td><textarea id="content" name="content" rows="10" cols="60" title="请输入帖子内容"></textarea></td>
					</tr>
					<tr>
						<td id="button_row" colspan="3">
						<input type="submit" value="提交" />						
						</td>
					</tr>
				</table>
			</form>
			<div class="prompt">
				<div id="title_prompt"></div>
				<div id="content_prompt"></div>
			</div>
		  </div>
		   <!-- end of column w610 -->
		  
		  <div class="cleaner"></div>
		</div>
		<!-- end of content panel -->

  	<jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
