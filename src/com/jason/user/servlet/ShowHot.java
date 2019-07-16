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
@WebServlet("/ShowHot")
public class ShowHot extends HttpServlet {
	
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
			List<Map<String, Object>> post =posts.subList(0, 3);
			//System.out.println("真的只取了前三个吗");
			//System.out.println("转为json格式为"+JSONObject.toJSON(post).toString());
			out.print(JSONObject.toJSON(post).toString());//
			out.flush();
			out.close();   
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

}
