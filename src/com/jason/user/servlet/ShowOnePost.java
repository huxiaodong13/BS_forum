package com.jason.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.jason.dao.impl.mysql.PostDaoImpl;

/**
 * Servlet implementation class ShowOnePost
 */
@WebServlet("/ShowOnePost")
public class ShowOnePost extends HttpServlet {
 
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
		
		//System.out.println("postid为"+req.getParameter("postid"));
		int postid=Integer.parseInt(req.getParameter("postid"));
		//System.out.println("postid为"+postid);
		PrintWriter out = resp.getWriter();

		try {
			PostDaoImpl postDaoImpl=new PostDaoImpl();
			Map<String, Object> post=postDaoImpl.findPostbyid(postid);
			//System.out.println("转为json格式为"+JSONObject.toJSON(post).toString());
			out.print(JSONObject.toJSON(post).toString());
			out.flush();
			out.close();   			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}

}
