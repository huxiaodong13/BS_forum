package com.jason.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.dao.UserDao;
import com.jason.dao.impl.mysql.UserDaoImpl;

/**
 * Servlet implementation class CheckUsernameServlet
 */
@WebServlet("/CheckUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUsernameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		String username = (String)request.getParameter("username");
		UserDao userDao = new UserDaoImpl();
		int count = userDao.countUserByName(username);
		
		if(count != 0) {
			pw.write("false");
		}else {
			pw.write("true");
		}
		pw.close();
	}
}
