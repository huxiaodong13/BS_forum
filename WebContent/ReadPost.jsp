<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.jason.biz.PostBiz" %>
<%@ page import="com.jason.biz.impl.PostBizImpl" %>
<%@ page import="com.jason.entity.Post" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.jason.entity.User" %>
<%
	User user = (User)session.getAttribute("login");
	if(user != null) {
	} else {
		response.sendRedirect("index.jsp");
		return ;
	}
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset=utf-8"> 
    <base href="<%=basePath%>">
    <title>阅读帖子</title>
    <meta http-equiv="description" content="阅读帖子">
    <link rel="stylesheet" href="css/demo.css">
    <link rel="stylesheet" href="css/temp.css">    
	<link href="css/read_posts_style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-3.4.0.js"></script>
	<script type="text/javascript" src="js/delCheck.js"></script>

  </head>
  
  <body>  
  	    <div id="header">
        <div id="header-content" class="clearfix">
            <div id="logo" class="v-center">
                <a href="index.jsp"><img src="img/logo.jpg" alt="logo"></a>
            </div>
            <%if(user != null) {%>
            <div id="login-links" class="v-center">
                <%= user.getUsername() %>。<a href="listPosts.jsp">返回个人中心</a>
            </div>
            <%} %>
        </div>
    </div>
  	
  	<div id="tempatemo_content_wrapper">
	  <div id="templatemo_content">
  	
  		<div id="content_panel_large">
		  <div id="column_w610_large">
			<div class="header_01">
				阅读帖子
			</div>
			<%
				String name = request.getParameter("username");
				String postid = request.getParameter("postid");
				int id = Integer.parseInt(postid);
				try{
					PostBiz postsBiz = new PostBizImpl();
					Post posts = postsBiz.findPostById(id);
					if(posts == null) {
			%>
						<p>未发过帖子。</p>
			<%
						return ;
					} else {
			%>
						<table id="posts_table">
							<tr>
								<td class="posts_title">
									<%= posts.getTitle() %>
								</td>
							</tr>
							<tr>
								<td class="posts_author_date">
									作者：<%= posts.getContext() %>
									时间：<%= posts.getPostingtime() %>
								</td>
							</tr>
							<tr>
								<td class="posts_content"><%= posts.getUsername() %></td>
							</tr>
							<tr>
						<td id="button_row" colspan="3">
							<button id="del">删除</button>
						</td></tr>
						</table>
						<div id="showtime"></div>
						
			<%
					}
				} catch (Exception e) {
			%>
					<p>粗错啦！帖子找不到啦。</p>
			<%
					return ;
				}
			%>
		  </div>
		   <!-- end of column w610 -->
		  
		  <div class="cleaner"></div>
		</div>
		<!-- end of content panel -->
		</div>
	  <!-- end of content -->
	</div>
	<!-- end of content wrapper -->
  </from>
  </body>
</html>
