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
import com.voidmain.progaurd.entity.Event;

@WebServlet("/AddEventServlet")
public class AddEventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {

			Event event=new Event();

			Object object=HttpRequestParser.parseRequest(request, event);

			if(HibernateTemplate.addObject(object)==1)
			{
				response.sendRedirect("addevents.jsp?status=success");
			}
			else
			{
				response.sendRedirect("addevents.jsp?status=failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
