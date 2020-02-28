package com.megala.bankapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.service.CreditCardService;

@SuppressWarnings("serial")
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail =request.getParameter("name");
		String pass = request.getParameter("pin");
		System.out.println("Name : "+mail);
		System.out.println("Street : "+pass);
		HttpSession sess=request.getSession();
		sess.setAttribute("email",mail);
		boolean status=false;
		PaymentResponse result=CreditCardService.login(mail, pass);
		status=result.isStatus();
		if(status) {
			System.out.println(result.getAccountNo());
			System.out.println(result.isStatus());
			sess.setAttribute("accNumber",result.getAccountNo());
			response.sendRedirect("frontpage.jsp");

		}
		else {
			request.setAttribute("error message", "Invalid MailId or Password");
			RequestDispatcher dispatcher=request.getRequestDispatcher("User.jsp");
			dispatcher.forward(request, response);
			
		}

	}

}
