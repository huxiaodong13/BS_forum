package com.jason.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.dao.UserDao;
import com.jason.dao.impl.mysql.PostDaoImpl;
import com.jason.dao.impl.mysql.UserDaoImpl;

@WebServlet("/DelServlet")
public class DelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		String  title1=request.getParameter("title");
		
		  Pattern p = Pattern.compile("\\s*|\t|\r|\n"); 
		  Matcher m = p .matcher(title1);
		  String title = m.replaceAll("");
		//System.out.println("title为"+title);
		
		PrintWriter pw=response.getWriter();
		PostDaoImpl poatDao = new PostDaoImpl();
		int count = poatDao.delPostByTitle(title);
		
		if(count != 0) {
			pw.write("false");
		}else {
			pw.write("true");
		}
		pw.close();
	}
}
