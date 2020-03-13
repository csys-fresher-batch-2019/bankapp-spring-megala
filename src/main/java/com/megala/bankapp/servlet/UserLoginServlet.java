package com.megala.bankapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.exception.ServiceException;
import com.megala.bankapp.service.CustomerService;

@SuppressWarnings("serial")
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	@Autowired
	private CustomerService cus;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mail = request.getParameter("name");
		String pass = request.getParameter("pin");
		System.out.println("mailId : " + mail);
		System.out.println("Password : " + pass);
		HttpSession sess = request.getSession();
		sess.setAttribute("email", mail);
		boolean status = false;
		PaymentResponse result = null;
		try {
			result = cus.login(mail, pass);
			status = result.isStatus();
			System.out.println(status);
			if (status) {
				System.out.println(result.getAccountNo());
				System.out.println(result.isStatus());
				sess.setAttribute("accNumber", result.getAccountNo());
				response.sendRedirect("frontpage.jsp");

			} else {
				request.setAttribute("errormessage", "Invalid MailId or Password");
				RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
				dispatcher.forward(request, response);

			}
		} catch (ServiceException e) {
			request.setAttribute("errormessage", "Invalid MailId or Password");
			RequestDispatcher dispatcher = request.getRequestDispatcher("User.jsp");
			dispatcher.forward(request, response);
			e.printStackTrace();
		}

	}

}
