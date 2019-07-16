package com.jason.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jason.dao.UserDao;
import com.jason.dao.impl.mysql.UserDaoImpl;
import com.jason.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter pw=response.getWriter();
		HttpSession session = request.getSession();
		System.out.println("进入检查登录阶段");
		String username = request.getParameter("name").toLowerCase();
		String password = request.getParameter("pwd");
		UserDao userDao = new UserDaoImpl();
		
		int count = userDao.countUserByName(username);
		
		if(count == 0) {
			pw.write("no-such-user");
			System.out.println("不存在该用户");
		} else {
			User user = userDao.findUserByName(username);	
			
			if(user == null) {
				pw.write("error");
			} else {
				if(password.equals(user.getPassword())) {
					session.setAttribute("login", user);
					System.out.println("登录成功");
					pw.write("login-success");
				} else {
					pw.write("wrong-password");
					System.out.println("密码错误");
				}
			}
		}
		pw.close();
	}

}
