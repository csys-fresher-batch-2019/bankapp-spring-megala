package com.megala.bankapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.megala.bankapp.domain.CreditCard;
import com.megala.bankapp.service.CreditCardService;

@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	@Autowired
	private CreditCardService creditCardService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cardNo = request.getParameter("number");
		long cardNumber = Long.valueOf(cardNo);
		String pinNo = request.getParameter("pin");
		int pin = Integer.valueOf(pinNo);
		CreditCard creditCard = new CreditCard();
		creditCard.setCardNo(cardNumber);
		creditCard.setPin(pin);

		HttpSession sess = request.getSession();
		sess.setAttribute("card", cardNumber);

		sess.setAttribute("cardpin", pin);
		try {
			boolean check = CreditCardService.validateCreditCard(cardNumber, pin);
			System.out.println("valid credit card:" + check);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			creditCardService.checkLogin(creditCard);
			response.sendRedirect("creditCardPage.jsp");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
