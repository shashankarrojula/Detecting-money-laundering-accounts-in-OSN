package com.voidmain.progaurd.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voidmain.progaurd.dao.DAO;
import com.voidmain.progaurd.dao.GetConnection;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username=request.getParameter("username").trim();
		String password=request.getParameter("password").trim();
		
		if(username.equals("admin") && password.equals("admin"))
		{
			response.sendRedirect("adminhome.jsp");
		}
		else
		{
			if(DAO.isValidUser(username, password))
			{
				try {
					
					PreparedStatement ps = GetConnection
							.getConnection()
							.prepareStatement(
									"insert into userloginhistory values(null,?,?)");

					
					ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
					ps.setString(2,username);
					
					ps.executeUpdate();
					
					request.getSession().setAttribute("username",username.toLowerCase());
					response.sendRedirect("home.jsp");
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
			else
			{
				response.sendRedirect("index.html?status=Invalid Username and Password");
			}
		}
	}
}
