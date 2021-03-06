package com.megala.bankapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.megala.bankapp.domain.Beneficiary;
import com.megala.bankapp.exception.ServiceException;
import com.megala.bankapp.service.BeneficiaryService;

@SuppressWarnings("serial")
@WebServlet("/BeneficiaryServlet")
public class BeneficiaryServlet extends HttpServlet {
	@Autowired
	BeneficiaryService c;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cusAcc = request.getParameter("acc");
		long accountNo = Long.valueOf(cusAcc);
		String beneficiaryName = request.getParameter("name");
		String accNo = request.getParameter("accNo");
		long acc = Long.valueOf(accNo);
		String ifscNo = request.getParameter("ifsc");
		Beneficiary b = new Beneficiary();
		b.setCustomerAccNo(accountNo);
		b.setBeneficiaryName(beneficiaryName);
		b.setAccNo(acc);
		b.setiFSCCode(ifscNo);
		HttpSession sess = request.getSession();
		sess.setAttribute("beneName", beneficiaryName);
		int accNumber = 0;
		try {
			accNumber = c.addBene(b);
			if (accNumber == 1) {
				request.setAttribute("outputmessage", "Beneficiary successfully added");
				RequestDispatcher dispatcher = request.getRequestDispatcher("addBeneficiary.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("errormessage", "Beneficiary already exists");
				RequestDispatcher dispatcher = request.getRequestDispatcher("addBeneficiary.jsp");
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			request.setAttribute("outputmessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("addBeneficiary.jsp");
			dispatcher.forward(request, response);
		}

	}
}
