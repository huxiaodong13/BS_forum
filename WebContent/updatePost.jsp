<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.jason.entity.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改帖子</title>
		<link rel="stylesheet" href="css/detail.css" />
    	<script src="js/jquery-3.4.0.js"></script>
	    <script src="js/detail.js"></script>
	
</head>
<body>	 
		<div id="modify"></div>
		<div id="modify_the_post">
			<table border="0" cellpadding="3" cellspacing="1" align="center">
				<tr>
					<td>
						修改帖子
					</td>
				</tr>
				<tr>
					<form name="" method="post">
						<td colspan="2">
							Title: <textarea type="text" rows="10" cols="40" name="title" id="titleUpdate">标题</textarea>
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
</body>
</html>