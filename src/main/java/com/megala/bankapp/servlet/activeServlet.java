package com.megala.bankapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.megala.bankapp.exception.ServiceException;
import com.megala.bankapp.service.AccountService;

@SuppressWarnings("serial")
@WebServlet("/activeServlet")
public class activeServlet extends HttpServlet {
	@Autowired
	AccountService accountDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String c = null;
		String obj = request.getParameter("accNo");
		long val = Long.valueOf(obj);
		String active = request.getParameter("status");
		int account = 0;
		try {
			account = accountDAO.active(val, active);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		if (account == 1) {
			c = "Account Updated";
		} else {
			c = "Account Failed to update";
		}
		request.setAttribute("output", c);
		RequestDispatcher dispatcher = request.getRequestDispatcher("activeAccount.jsp");
		dispatcher.forward(request, response);
	}
}
