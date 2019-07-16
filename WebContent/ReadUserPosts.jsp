<%@ page import="com.jason.entity.Post" %>
<%@ page import="com.jason.entity.User" %>
<%@ page import="com.jason.biz.PostBiz" %>
<%@ page import="com.jason.biz.impl.PostBizImpl" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset=utf-8">
    <base href="<%=basePath%>">   
    <title>阅读某个用户的帖子</title>
    <link rel="stylesheet" href="css/demo.css">
	<link href="css/list_posts_style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-3.4.0.js"></script>

</head>  
<body>
<div id="header">
        <div id="header-content" class="clearfix">
            <div id="logo" class="v-center">
                <a href="index.jsp"><img src="img/logo.jpg" alt="logo"></a>
            </div>
            </div>
            </div>
  		<div id="content_panel">
		  <div id="column_w610">
			<div class="header_01">
			<div class="header_02">
			<%
			String name = request.getParameter("username");
			//System.out.print(name);
			PostBiz postsBiz = new PostBizImpl();
			List<Post> listPosts = postsBiz.getPostByUsername(name);
			System.out.print(listPosts);

				if(listPosts == null || listPosts.size() == 0) {
			%>
					<p>
						很干净,该用户还未发过帖子。
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
							<a href="postContent.jsp?postid=<%=posts.getPostid() %>">
									<%= posts.getTitle() %></a>
							</td>
<%-- 							<td class="posts_context"><%= posts.getContext() %></td>
 --%>							<td class="posts_date"><%= posts.getPostingtime() %></td>
						</tr>
			<%						
					}
				}
			%>
					</table>
					</div>
		  </div>
		</div>
		</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
