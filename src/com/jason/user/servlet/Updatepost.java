package com.jason.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.jason.dao.impl.mysql.PostDaoImpl;
import com.jason.entity.Post;

/**
 * Servlet implementation class Updatepost
 */
@WebServlet("/UpDatePost")
public class Updatepost extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setCharacterEncoding("utf-8");
		int postid=Integer.parseInt(req.getParameter("postid")); //评论的帖子id
		String context=req.getParameter("context");//内容
		String title=req.getParameter("title");//标题
		System.out.println("title：   "+title+"   评论的posid：   "+postid+"   context:"+context);
		
		PrintWriter out = resp.getWriter();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		String strDate = sdf.format(date);
		PostDaoImpl postDaoImpl=new PostDaoImpl();
		Post post=new Post(postid, title, context, strDate);
		System.out.println(post);
		int hasdown = postDaoImpl.update(post);
		if(hasdown>0){
			System.out.println("修改语句执行成功");
			out.write("修改成功");
		} 
		else {
			out.write("修改失败");
		}
		
		out.flush();
		out.close();  
	
	}
}
