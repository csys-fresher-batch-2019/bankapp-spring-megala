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

import com.megala.bankapp.domain.CreditCardTransaction;
import com.megala.bankapp.exception.ServiceException;
import com.megala.bankapp.service.CreditCardService;

@SuppressWarnings("serial")
@WebServlet("/ListCreditCardTransactionServlet")
public class ListCreditCardTransactionServlet extends HttpServlet {
	@Autowired

	CreditCardService dao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<CreditCardTransaction> c = null;

		System.out.println(c);

		String cardId = request.getParameter("number");
		if (cardId != null && !"".equals(cardId.trim())) {
			Integer ccId = Integer.parseInt(cardId);
			try {
				c = dao.findCardDetailsByCardId(ccId);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else {
			try {
				c = dao.findAllCardDetails();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("list", c);
		RequestDispatcher dispatcher = request.getRequestDispatcher("creditcardpaymentlist.jsp");
		dispatcher.forward(request, response);
	}

}
