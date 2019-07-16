package com.jason.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.dao.UserDao;
import com.jason.dao.impl.mysql.UserDaoImpl;
import com.jason.entity.User;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username").toLowerCase();
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String[] b = {"img/head1.png", "img/head2.png", "img/head3.png","img/head4.png","img/head5.png","img/head6.png","img/head7.png","img/head8.png","img/heada.png","img/headb.png","img/headc.png","img/headd.png",};
		Random rand = new Random();
		int num = rand.nextInt(12);
		String photo = b[num];
		System.out.println(username+"  "+password+"  "+email);
		UserDao userDao = new UserDaoImpl();	
		System.out.println(photo);
		User user = new User(0,username,password,email,photo);
		int result = userDao.insertUser(user);
		if(result == 1) {
			System.out.println("插入成功");
			request.setAttribute("login",user);
			//request.getRequestDispatcher("registSuccess.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
