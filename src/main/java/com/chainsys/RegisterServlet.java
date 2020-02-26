package com.chainsys;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import creditcard.CreditCardService;
import customer.Customer;
import customer.Register;


@SuppressWarnings("serial")
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cusName =request.getParameter("name");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String accTyp = request.getParameter("accType");
		String mbleNum = request.getParameter("mbleNo");
		long num=Long.valueOf(mbleNum);
		String emailId = request.getParameter("email");
		String password = request.getParameter("pass");
		System.out.println("Name : "+cusName);
		System.out.println("Street : "+street);
		System.out.println("City:"+city);
		System.out.println("Account Type:"+accTyp);
		System.out.println("Mobile Number:"+num);
		System.out.println("email:"+emailId);
		System.out.println("password:"+password);
		Customer c=new Customer();
		c.setName(cusName);
		c.setStreet(street);
		c.setCity(city);
		c.setAccType(accTyp);
		c.setMobileNo(num);
		c.setEmail(emailId);
		c.setPassword(password);
		boolean result=false;
		Register reg=CreditCardService.register(c);
		result=reg.isStatus();
		if(result) {
		System.out.println(reg.getAccNo());
		System.out.println(reg.isStatus());
		HttpSession sess=request.getSession();
		sess.setAttribute("accNo",reg.getAccNo());
		sess.setAttribute("accName", cusName);
		response.sendRedirect("AccountCreated.jsp");
			
		}
		else {
			request.setAttribute("errormessage", "Registration Failed");
			RequestDispatcher dispatcher=request.getRequestDispatcher("Register.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}

}
