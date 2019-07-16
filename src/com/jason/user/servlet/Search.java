package com.jason.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.dao.PostDao;
import com.jason.dao.UserDao;
import com.jason.dao.impl.mysql.PostDaoImpl;
import com.jason.dao.impl.mysql.UserDaoImpl;
import com.jason.entity.Post;
import com.jason.entity.User;

@WebServlet("/Search")
public class Search extends HttpServlet{

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = (String)request.getParameter("key");
		System.out.println(key);
		if(key != null || key.trim() != "") {
			//如果搜索的是用户
			UserDao userDao = new UserDaoImpl();
			User user = userDao.findUserByName(key);
			PostDao postDao = new PostDaoImpl();
			Post post = postDao.getPostByTitle(key);
			if(user != null || post != null ) {
				if(user != null ) {
				    request.setAttribute("searchs", user);
				}else {
				    request.setAttribute("searchs", post);
				}
			}else {
			    request.setAttribute("searchs", "不存在该搜索内容");
			}
		}else {
		    request.setAttribute("searchs", "输入搜索内容为空");
		}
	    request.getRequestDispatcher("searchResult.jsp").forward(request, response);
	}

}
