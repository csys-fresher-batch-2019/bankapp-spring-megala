package com.megala.bankapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.megala.bankapp.dao.TransactionDAO;
import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.exception.DbException;

@SuppressWarnings("serial")
@WebServlet("/ListFundTransferDetailsServlet")
public class ListFundTransferDetailsServlet extends HttpServlet {
	@Autowired
	TransactionDAO dao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Transaction> a = null;
		System.out.println(a);
		HttpSession session = request.getSession();
		Long obj = (Long) session.getAttribute("accNumber");
		try {
			a = dao.findByAccNo(obj);
		} catch (DbException e) {
			e.printStackTrace();
		}
		request.setAttribute("fundTransfer", a);
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewTransactionHistory.jsp");
		dispatcher.forward(request, response);
	}
}
