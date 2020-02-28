package com.megala.bankapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megala.bankapp.dao.BeneficiaryDAO;
import com.megala.bankapp.domain.Beneficiary;
import com.megala.bankapp.factory.DAOFactory;

@SuppressWarnings("serial")
@WebServlet("/BeneficiaryServlet")
public class BeneficiaryServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cusAcc = request.getParameter("acc");
		long accountNo=Long.valueOf(cusAcc);
		String beneficiaryName =request.getParameter("name");
		String accNo = request.getParameter("accNo");
		long acc=Long.valueOf(accNo);
		String ifscNo = request.getParameter("ifsc");
		Beneficiary b=new Beneficiary();
		b.setCustomerAccNo(accountNo);
		b.setBeneficiaryName(beneficiaryName);
		b.setAccNo(acc);
		b.setiFSCCode(ifscNo);
		HttpSession sess=request.getSession();
		sess.setAttribute("beneName", beneficiaryName);
		BeneficiaryDAO c=DAOFactory.getBeneficiaryDAO();
		int a=c.addBeneficiary(b);
		System.out.println(a);
		if(a==1) {
			request.setAttribute("output message", "Beneficiary successfully added");
			RequestDispatcher dispatcher=request.getRequestDispatcher("addBeneficiary.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("errormessage", "Beneficiary already exists");
			RequestDispatcher dispatcher=request.getRequestDispatcher("addBeneficiary.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
