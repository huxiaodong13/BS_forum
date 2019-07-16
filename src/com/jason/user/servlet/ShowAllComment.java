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

import com.alibaba.fastjson.JSONObject;
import com.jason.dao.impl.mysql.CommentImpl;

/**
 * Servlet implementation class Showallcomment
 */
@WebServlet("/ShowAllComment")
public class ShowAllComment extends HttpServlet {

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
		
		int postid=Integer.parseInt(req.getParameter("postid"));
		//System.out.println("postid为"+postid);
		PrintWriter out = resp.getWriter();

		try {
			CommentImpl commentImpl=new CommentImpl();
			List<Map<String, Object>> comments=commentImpl.findallCommentsBypostid(postid);

			//System.out.println("(json)所有评论为"+JSONObject.toJSON(comments).toString());
			out.print(JSONObject.toJSON(comments).toString());
			out.flush();
			out.close();   

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}


}
