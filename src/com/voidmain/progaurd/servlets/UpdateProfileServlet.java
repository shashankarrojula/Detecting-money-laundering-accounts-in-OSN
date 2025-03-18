package com.voidmain.progaurd.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voidmain.progaurd.dao.DAO;
import com.voidmain.progaurd.dao.HibernateTemplate;
import com.voidmain.progaurd.entity.User;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user=DAO.getUserById((String)request.getSession().getAttribute("username"));
		
		List<Object> objects=HttpRequestParser.requestHandler(request,user);

		user=(User)objects.get(0);

		List<String> photo=(ArrayList<String>)objects.get(1);
		user.setPhoto(photo.get(0));
		
		if(HibernateTemplate.updateObject(user)==1)
		{
			response.sendRedirect("editprofile.jsp?status=success");
		}
		else
		{
			response.sendRedirect("editprofile.jsp?status=failed");
		}
	}
}
