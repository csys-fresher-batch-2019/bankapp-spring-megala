package com.chainsys;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import creditcard.CreditCard;
import creditcard.CreditCardService;

@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String cardNo=request.getParameter("number");
	long cardNumber=Long.valueOf(cardNo);
	String pinNo=request.getParameter("pin");
	int pin=Integer.valueOf(pinNo);
	CreditCard creditCard=new CreditCard();
	creditCard.setCardNo(cardNumber);
	creditCard.setPin(pin);
	
	HttpSession sess=request.getSession();
	sess.setAttribute("card",cardNumber);
	
	sess.setAttribute("cardpin",pin);
	try {
		boolean check=CreditCardService.validateCreditCard(cardNumber, pin);
		System.out.println("valid credit card:"+check);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	try{
		CreditCardService.checkLogin1(creditCard);
		response.sendRedirect("creditCardPage.jsp");
		
	}		
				catch(Exception e) {
	
		e.printStackTrace();
	}
	
	
	}
}

