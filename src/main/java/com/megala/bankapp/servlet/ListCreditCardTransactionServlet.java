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

import com.megala.bankapp.dao.CreditCardTransactionDAO;
import com.megala.bankapp.domain.CreditCardTransaction;

@SuppressWarnings("serial")
@WebServlet("/ListCreditCardTransactionServlet")
public class ListCreditCardTransactionServlet extends HttpServlet {
	@Autowired

	CreditCardTransactionDAO dao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<CreditCardTransaction> c = null;
		
		System.out.println(c);
		
		
		String cardId = request.getParameter("number");
		if ( cardId !=null && !"".equals(cardId.trim())) {
			Integer ccId = Integer.parseInt(cardId);
			c = dao.displayTransactionHistoryByCardId(ccId);
		}
		else {
			c = dao.displayCreditCardPaymentList();
		}
		request.setAttribute("list", c);
		RequestDispatcher dispatcher = request.getRequestDispatcher("creditcardpaymentlist.jsp");
		dispatcher.forward(request, response);
	}

}
