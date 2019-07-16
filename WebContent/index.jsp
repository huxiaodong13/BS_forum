<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.jason.entity.User" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8">
    <title>TechLand</title>
    <link rel="stylesheet" href="css/demo.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <script src="js/jquery-3.4.0.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/demo.js"></script>
    <script src="js/loginCheck.js"></script>
    <script src="js/registCheck.js"></script>
</head>
<body>
    <div id="header">
        <div id="header-content" class="clearfix">
            <div id="logo" class="v-center">
                <a href="index.jsp"><img src="img/logo.jpg" alt="logo"></a>
            </div>
            <div id="searchbar" class="v-center">
<!--            <input type="text" name="search" id="search">
 -->                
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
				您好,<%= user.getUsername() %>。
				<a href="doLogout.jsp">退出登录</a>
			<%
				}
			%>
            </div>
        </div>
    </div>
    <div id="register-dialog" hidden><!-- hidden使得网页最开始显示时，表格隐藏 -->
    		<div class="main-content clear">
    				<form id="regist">
    					<div class="control-group">
    						<label for="username">用户</label>
    						<input type="text" id="username" name="username" placeholder="请输入5~20位用户名">			
    					</div>
    					<div class="control-group">
    						<label for="password">密码</label>
    						<input type="password" id="password" name="password" placeholder="请输入8~16位英文、数字或符号的密码">					
    					</div>
						<div class="control-group">
							<label for="password2">重复密码</label>
							<input type="password" id="password2" name="password2" placeholder="请再次输入密码">
						</div>
						<div class="control-group">
							<label for="email">邮箱地址</label>
							<input type="email" id="email" name="email" placeholder="请输入你的邮箱地址">					
						</div>
						<div class="footer-content">
							<div class="btn-group">
								<button type="button" id="doRegist">注册</button>
								<button type="button"id="cancelRegist">取消</button>
							</div>
						</div>
					</form>
					<div class="prompt">
						<div id="username_prompt"></div>
						<div id="password_prompt"></div>
						<div id="password2_prompt"></div>
						<div id="email_prompt"></div>
					</div>
				</div>
    </div>
    <div id="login-dialog" hidden>
        <div class="main-content clear">
		    <form id="login">
		    	<div class="control-group">
		    		<label>用户</label>
		    		<input type="text" id="name" name="name" title="请填写用户名" value="">
				</div>
				<div class="control-group">
					<label>密码</label>
					<input type="password" id="pwd" name="pwd" title="请填写密码">
				</div>
				<div class="footer-content">
					<div class="btn-group">
						<button type="button" id="doLogin">登录</button>
						<button type="button" id="cancelLogin">取消</button>
					</div>
				</div>
		   </form>
		   <div class="prompt">
				<div id="name_prompt"></div>
				<div id="pwd_prompt"></div>
			</div>
		</div>
    </div>
    
    <jsp:include page="content.jsp"></jsp:include>
    
    <div id="nav_right">
		<ul>
			<li><a href="listPosts.jsp">个人中心</button></li>
			<li><a href="#">返回顶部</a></li>
		</ul>

	</div>
	
    <div id="footer">
        <div id="footer-content">
                <div id="footer-links">
                    <ul>
                        <li><a href="#">关于</a></li>
                        <li><a href="#">FAQ</a></li>
                        <li><a href="#">API</a></li>
                        <li><a href="#">我们的远景</a></li>
                        <li><a href="#">广告投放</a></li>
                        <li><a href="#">鸣谢</a></li>
                    </ul>
                </div>
                <div id="copyright" class="v-center h-center">
                    版权说明：四川师范大学计算机科学学院
                </div>
                <div id="update-date">
                    最后更新日期：2019年4月
                </div>
        </div>
    </div>

</body>
</html>