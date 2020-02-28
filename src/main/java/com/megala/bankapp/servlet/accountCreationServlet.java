package com.megala.bankapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megala.bankapp.dao.AccountDAO;
import com.megala.bankapp.domain.Account;
import com.megala.bankapp.factory.DAOFactory;

@SuppressWarnings("serial")
@WebServlet("/accountCreationServlet")
public class accountCreationServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cusId =request.getParameter("CustomerId");
		int Id = Integer.valueOf(cusId);
		String No = request.getParameter("accNo");
		long accNo=Long.valueOf(No);
		String accType = request.getParameter("accType");
		String amount = request.getParameter("balance");
		int price=Integer.valueOf(amount);
		
		System.out.println("CustomerId : "+Id);
		System.out.println("AccountNumber : "+accNo);
		System.out.println("AccountType:"+accType);
		System.out.println("Balance:"+price);
		Account acc=new Account();
		acc.setCustomerId(Id);
		acc.setAccNo(accNo);
		acc.setAccType(accType);
		acc.setAvailableBalance(price);
		AccountDAO a=DAOFactory.getAccountDAO();
		a.addAccount(acc);
	}

}
