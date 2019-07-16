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
import com.jason.entity.Comment;

/**
 * Servlet implementation class Insertcomment
 */
@WebServlet("/InsertComment")
public class InsertComment extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		
		int postid=Integer.parseInt(req.getParameter("postid")); //评论的帖子
		String context=req.getParameter("context");
		String username=req.getParameter("username");//评论的人
		//System.out.println("username"+username+"评论的posid"+postid);
		
		PrintWriter out = resp.getWriter();
		
		Comment comment=new Comment(postid, username, context);
		CommentImpl commentImpl=new CommentImpl();
		int hasdown=commentImpl.insert(comment);
		if(hasdown>0){
			out.write("插入成功");
		}
		else {
			out.write("插入失败");
		}
		
		out.flush();
		out.close();  	
	
	}

}
