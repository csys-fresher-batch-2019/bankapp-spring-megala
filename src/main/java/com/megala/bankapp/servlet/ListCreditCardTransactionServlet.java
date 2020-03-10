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
import com.megala.bankapp.exception.DbException;

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
			try {
				c = dao.findByCardId(ccId);
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				c = dao.findAll();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("list", c);
		RequestDispatcher dispatcher = request.getRequestDispatcher("creditcardpaymentlist.jsp");
		dispatcher.forward(request, response);
	}

}
