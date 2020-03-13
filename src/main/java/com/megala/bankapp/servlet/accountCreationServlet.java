package com.megala.bankapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.megala.bankapp.domain.Account;
import com.megala.bankapp.exception.ServiceException;
import com.megala.bankapp.service.AccountService;

@SuppressWarnings("serial")
@WebServlet("/accountCreationServlet")
public class accountCreationServlet extends HttpServlet {
	@Autowired
	private AccountService account;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cusId = request.getParameter("CustomerId");
		int Id = Integer.valueOf(cusId);
		String No = request.getParameter("accNo");
		long accNo = Long.valueOf(No);
		String accType = request.getParameter("accType");
		String amount = request.getParameter("balance");
		int price = Integer.valueOf(amount);
		Account acc = new Account();
		acc.setCustomerId(Id);
		acc.setAccNo(accNo);
		acc.setAccType(accType);
		acc.setAvailableBalance(price);
		try {
			account.addAccount(acc);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
