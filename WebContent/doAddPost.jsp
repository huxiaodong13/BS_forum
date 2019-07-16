<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.jason.entity.User" %>
<%@ page import="com.jason.biz.PostBiz" %>
<%@ page import="com.jason.biz.impl.PostBizImpl" %>
<%@ page import="com.jason.entity.Post" %>

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
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>处理发表帖子页面</title>
    <meta charset=utf-8">   

</head>

<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String context = request.getParameter("content");
		String name = user.getUsername();
		System.out.print(name+"---  "+"title: "+title+"context:  "+context);
		if(title == null || title.trim().equals("")) {
			request.setAttribute("error", "帖子标题不能为空！");
			request.getRequestDispatcher("addPosts.jsp").forward(request, response);
			return;
		}

		if(context == null || context.trim().equals("")) {
			request.setAttribute("error", "内容不能为空！");
			request.getRequestDispatcher("addPosts.jsp").forward(request, response);
			return;
		}
		try {
			System.out.print("最终帖子是否添加成功？");
			PostBiz postsBiz = new PostBizImpl();
			Post posts = new Post(title,context);
			System.out.print(postsBiz.addPost(posts, name));
			if(postsBiz.addPost(posts, name)) {
				System.out.print("亲,你的帖子发表了");
				response.sendRedirect("addPostsSuccess.jsp");
				return ; 
			} else {
				System.out.print("帖子粗错啦,亲");
				request.getRequestDispatcher("addPostsFail.jsp").forward(
						request, response);
				return ;
			}
		} catch(Exception e) {
			System.out.print("抛异常");
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("addPostsFail.jsp").forward(
				request, response);
		}
	%>
</body>
</html>
