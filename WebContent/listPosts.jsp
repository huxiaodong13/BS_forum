<%@ page import="com.jason.entity.Post" %>
<%@ page import="com.jason.entity.User" %>
<%@ page import="com.jason.biz.PostBiz" %>
<%@ page import="com.jason.biz.impl.PostBizImpl" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset=utf-8">   
    <title>查看个人帖子列表</title>
    <link rel="stylesheet" href="css/demo.css">
	<link href="css/list_posts_style.css" rel="stylesheet" type="text/css" />
	<script src="js/jquery-3.4.0.js"></script>
</head>  
<body>
<%	User user = (User)session.getAttribute("login"); %>
    <div id="header">
        <div id="header-content" class="clearfix">
            <div id="logo" class="v-center">
                <a href="index.jsp"><img src="img/logo.jpg" alt="logo"></a>
            </div>
            <%if(user != null) {%>
            <div id="login-links" class="v-center">
                <%= user.getUsername() %>。<a href="doLogout.jsp">退出登录</a>
            </div>
            <%} %>
        </div>
    </div>
  		<div id="content_panel">
		  <div id="column_w610">
			<div class="header_01">
				<%
					if(user == null){
						%><a href="index.jsp">请先登录</a>
					<%}else{
				%>
					<div class="button_02">
						<a href="addPosts.jsp">亲,可在这里添加帖子</a>
					</div>
				<%
					
				%>
			</div>
			<div class="header_02">
			<%
				PostBiz postsBiz = new PostBizImpl();
				List<Post> listPosts = postsBiz.getPostByUsername(user.getUsername());
				System.out.print(listPosts);

				if(listPosts == null || listPosts.size() == 0) {
			%>
					<p>
						很干净,没有帖子。
					</p>
			<%
				} else {
			%>
					<table id="posts_table">
			<%
					for(Post posts : listPosts) {
			%>
						<tr>
						<td class="posts_id"><%=posts.getPostid() %></td>
							<td class="posts_title">
								<a href="ReadPost.jsp?username=<%= user.getUsername() %>&postid=<%=posts.getPostid() %>">
									<%= posts.getTitle() %>
								</a>
							</td>
							<td class="posts_date"><%= posts.getPostingtime() %></td>
						</tr>
			<%						
					}
			%>
					</table>
			<%
				}
					}%>
					</div>
		  </div>
		</div>
		<!-- end of content panel -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
