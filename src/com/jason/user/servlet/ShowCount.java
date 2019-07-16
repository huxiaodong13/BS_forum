package com.jason.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSONObject;
import com.jason.dao.PostDao;
import com.jason.dao.UserDao;
import com.jason.dao.impl.mysql.CommentImpl;
import com.jason.dao.impl.mysql.PostDaoImpl;
import com.jason.dao.impl.mysql.UserDaoImpl;

/**
 * Servlet implementation class Showposts
 */
@WebServlet("/ShowCount")
public class ShowCount extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");

		PrintWriter out = resp.getWriter();

		try {
			//获取数量
			UserDao userDao = new UserDaoImpl();
			int userCount = userDao.countUser();
			//System.out.println(userCount);
			CommentImpl commentImpl=new CommentImpl();
			int commentCount = commentImpl.countComment();
			//System.out.println(commentCount);
			PostDao postDao = new PostDaoImpl();
			int postCount = postDao.countPosts();
			//System.out.println(postCount);
			String u = String.valueOf(userCount);
			Map<String,String> map = new HashMap<String,String>();
			map.put("userCount",String.valueOf(userCount));
			map.put("commentCount",String.valueOf(commentCount));
			map.put("postCount",String.valueOf(postCount));
			System.out.println("(json)所有数量为"+JSONObject.toJSON(map).toString());
			out.print(JSONObject.toJSON(map).toString());
			out.flush();
			out.close();
			//List<Map<String, Object>> count = [{userCount=userCount,commentCount=commentCount,postCount=postCount}];
			//String count = ["userCount":userCount];
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

}
