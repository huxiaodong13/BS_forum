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
import com.jason.dao.impl.mysql.CommentImpl;

/**
 * Servlet implementation class Findlastcomment
 */
@WebServlet("/FindLastComment")
public class FindLastComment extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");

		int postid = Integer.parseInt(req.getParameter("postid")) ;
		//System.out.println("postid"+postid);

		PrintWriter out = resp.getWriter();

		try {
			CommentImpl commentImpl=new CommentImpl();
			String username=commentImpl.findLastcomment(postid);
			//System.out.println("username"+username);
			String str = "{"+username+":\""+username+"\"}";
			Map<String, Object>map = new HashMap<String, Object>();
			map.put("username", username);
			out.print(JSONObject.toJSON(map).toString());
			out.flush();
			out.close();   
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}

