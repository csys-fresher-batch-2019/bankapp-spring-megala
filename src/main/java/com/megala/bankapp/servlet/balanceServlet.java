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

import com.megala.bankapp.exception.ServiceException;
import com.megala.bankapp.service.AccountService;

@SuppressWarnings("serial")
@WebServlet("/balanceServlet")
public class balanceServlet extends HttpServlet {
	@Autowired
	AccountService accountDAO;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Long accNum = (Long) session.getAttribute("accNumber");
		int c = 0;
		try {
			c = accountDAO.findBalance(accNum);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		request.setAttribute("output", c);
		RequestDispatcher dispatcher = request.getRequestDispatcher("accountCheckBalance.jsp");
		dispatcher.forward(request, response);
	}
}
