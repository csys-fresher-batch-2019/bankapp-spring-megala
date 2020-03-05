package com.megala.bankapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.megala.bankapp.dao.AccountDAO;
import com.megala.bankapp.dao.TransactionDAO;
import com.megala.bankapp.domain.Account;
import com.megala.bankapp.domain.Transaction;

@SuppressWarnings("serial")
@WebServlet("/ListAccountTransactionServlet")
public class ListAccountTransactionServlet extends HttpServlet {
	@Autowired
	TransactionDAO dao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Transaction> t =null;
		System.out.println(t);
		String obj = request.getParameter("accNo");
		if(obj!=null && !"".equals(obj.trim()))
		{
			long val = Long.valueOf(obj);
			t= dao.displayParTransaction(val);
		}
		else
		{
			t= dao.displayTransaction();
		}
		request.setAttribute("account", t);
		RequestDispatcher dispatcher = request.getRequestDispatcher("transaction.jsp");
		dispatcher.forward(request, response);
}
}
