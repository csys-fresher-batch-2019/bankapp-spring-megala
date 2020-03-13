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
@WebServlet("/updateAmountServlet")
public class updateAmountServlet extends HttpServlet {
	@Autowired
	AccountService dao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String obj = request.getParameter("accNo");
		long val = Long.valueOf(obj);
		String price = request.getParameter("price");
		int amount = Integer.valueOf(price);
		int account = 0;
		try {
			account = dao.updateAmount(val, amount);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		String status = null;
		if (account == 1) {
			status = "Amount Successfully added";
		} else {
			status = "Invalid Account Number!!";
		}
		request.setAttribute("output", status);
		RequestDispatcher dispatcher = request.getRequestDispatcher("updateAccount.jsp");
		dispatcher.forward(request, response);
	}
}
