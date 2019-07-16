package com.jason.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.alibaba.fastjson.JSONObject;
import com.jason.dao.impl.mysql.PostDaoImpl;

/**
 * Servlet implementation class Showposts
 */
@WebServlet("/ShowPosts")
public class ShowPosts extends HttpServlet {
	
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
			PostDaoImpl postDaoImpl=new PostDaoImpl();
			List<Map<String, Object>> posts=postDaoImpl.findallPosts();

			//System.out.println("转为json格式为"+JSONObject.toJSON(posts).toString());
			out.print(JSONObject.toJSON(posts).toString());
			out.flush();
			out.close();   
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

}
