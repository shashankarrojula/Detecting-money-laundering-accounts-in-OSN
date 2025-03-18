package com.voidmain.progaurd.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voidmain.progaurd.dao.DAO;
import com.voidmain.progaurd.dao.GetConnection;
import com.voidmain.progaurd.dao.HibernateTemplate;
import com.voidmain.progaurd.entity.Transaction;

@WebServlet("/AddTransactionServlet")
public class AddTransactionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userName=(String)request.getSession().getAttribute("username");
		
		try {

			Transaction transaction=new Transaction();

			Object object=HttpRequestParser.parseRequest(request, transaction);
			
			
			transaction=(Transaction)object;
			
			transaction.setDate(new Date());
			transaction.setType("received");
			transaction.setUser(DAO.getUserById(userName));

			if(HibernateTemplate.addObject(object)==1)
			{
				PreparedStatement ps1 =GetConnection.getConnection()
						.prepareStatement("update user set amount=amount+? where username=?");
				
				ps1.setFloat(1,transaction.getAmount());
				ps1.setString(2,userName);
				
				ps1.executeUpdate();
				
				response.sendRedirect("transfermoney.jsp?status=success");
			}
			else
			{
				response.sendRedirect("transfermoney.jsp?status=failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
