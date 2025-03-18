package com.voidmain.progaurd.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voidmain.progaurd.dao.HibernateTemplate;
import com.voidmain.progaurd.entity.Service;

@WebServlet("/AddServiceServlet")
public class AddServiceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {

			Service service=new Service();

			Object object=HttpRequestParser.parseRequest(request, service);

			if(HibernateTemplate.addObject(object)==1)
			{
				response.sendRedirect("addservices.jsp?status=success");
			}
			else
			{
				response.sendRedirect("addservices.jsp?status=failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
