<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.jason.entity.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帖子详情</title>
		<link rel="stylesheet" href="css/detail.css" />
		<link rel="stylesheet" href="css/demo.css" />
    	<script src="js/jquery-3.4.0.js"></script>
	    <script src="js/detail.js"></script>
	
</head>
<body>
    <div id="header">
        <div id="header-content" class="clearfix">
            <div id="logo" class="v-center">
                <a href="index.jsp"><img src="img/logo.jpg" alt="logo"></a>
            </div>
        </div>
    </div>
    <!-- 默认一个楼层 -->
	<div id="content">
			<div id="title">
				<h4>标题</h4>
			</div>
			<div id="owner">
				<div class="clear">
					<div class="left">
						<p>楼主</p>
						<img src="img/head1.png" />
						<p>姓名</p>
					</div>
					<div class="right">
						<p>开心每一天</p>
					</div>
				</div>
				<div class="time">2019.1.1 12:00:00</div>
			</div>
		</div>
		
		 
		<!--修改帖子的弹出框  -->
		<div id="modify"></div>
		<div id="modify_the_post">
			<table border="0" cellpadding="3" cellspacing="1" align="center">
				<tr>
					<td class="update">
						修改帖子
					</td>
				</tr>
				<tr>
					<form name="" method="post">
						<td colspan="2">
							Title: <textarea type="text" rows="10" cols="40" name="title" id="titleUpdate">标题</textarea>
							<br>
							<br>
							Content:<textarea type="text" rows="10" cols="40" name="context" id="contentUpdate">内容</textarea>
							<br>
							<input type="button"  name="send" id="send" value="确认修改">
							<input type="button" name="exit" value="取消" onClick="closeme()">
						</td>
					</form>
				</tr>
			</table>
		</div>
<!-- 是否可修改帖子 -->
		<div id="sendpost">
				<textarea rows="5" cols="60" id="commenttext"></textarea>
			  	<%
			  			
			  		if (session.getAttribute("login") != null) {
					 	 User user = (User) session.getAttribute("login");
					 	String username=user.getUsername(); 
						//尚未登录
					    if (username == null){
		    	%>
		    	<% 
		    	
		         }else{  %>
		         	<input type="button" id="publish"  value="发表回复" username="<%=username %>" onclick="insertcomment()"/>
		         <% }
			  		}else{
			  			 %>
			  			 <input type="button" value="发表回复" onclick="alertlogin()"/>
			  			 <%
			  		}
						%>
		</div> 

</body>
</html>