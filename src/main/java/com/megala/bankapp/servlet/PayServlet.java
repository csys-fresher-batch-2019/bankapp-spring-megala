package com.megala.bankapp.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.megala.bankapp.domain.CreditCard;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.service.CreditCardService;

@SuppressWarnings("serial")
@WebServlet("/PayServlet")
public class PayServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@Autowired
	private CreditCardService creditCardService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		long card = Long.valueOf(name);
		String expirydate = request.getParameter("expirydate");
		LocalDate date = LocalDate.parse(expirydate);
		String cvv = request.getParameter("cvv");
		int cvvNo = Integer.valueOf(cvv);
		String pin = request.getParameter("pin");
		int pinNo = Integer.valueOf(pin);
		String merchantId = request.getParameter("id");
		String comments = request.getParameter("comments");
		String amount = request.getParameter("amount");
		float price = Float.parseFloat(amount);

		System.out.println("Name : " + name);
		System.out.println("expiryDate : " + expirydate);
		System.out.println("cvv:" + cvv);
		System.out.println("pin:" + pin);
		System.out.println("amount:" + amount);
		CreditCard creditCard = new CreditCard();
		creditCard.setCardNo(card);
		creditCard.setExpiryDate(LocalDate.parse(expirydate));
		creditCard.setCvvNo(cvvNo);
		creditCard.setPin(pinNo);

		try {
			boolean status = CreditCardService.validateCreditCard(card, date, cvvNo);
			System.out.println("validateCC status: " + status);

			boolean status1 = false;
			System.out.println(creditCard);
			PaymentResponse obj = creditCardService.pay(creditCard, price, merchantId, comments);
			System.out.println(obj);
			status1 = obj.isStatus();
			System.out.println(status1);
			if (status1) {
				request.setAttribute("output", "Payment successful");
				RequestDispatcher dispatcher = request.getRequestDispatcher("creditcardpayment.jsp");
				dispatcher.forward(request, response);

			} else {
				request.setAttribute("output", "Payment failed");
				RequestDispatcher dispatcher = request.getRequestDispatcher("creditcardpayment.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}
