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

import com.megala.bankapp.domain.Transaction;
import com.megala.bankapp.dto.PaymentResponse;
import com.megala.bankapp.exception.ServiceException;
import com.megala.bankapp.service.TransactionService;

@SuppressWarnings("serial")
@WebServlet("/FundTransferServlet")
public class FundTransferServlet extends HttpServlet {
	@Autowired
	private TransactionService transactionService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acc = request.getParameter("no");
		long num = Long.valueOf(acc);
		String beneacc = request.getParameter("beneAccNo");
		long bene = Long.valueOf(beneacc);
		String transAmount = request.getParameter("amount");
		int amount = Integer.valueOf(transAmount);
		System.out.println("Name : " + num);
		System.out.println("Street : " + bene);
		System.out.println("City:" + amount);
		Transaction t = new Transaction();
		t.setAccNo(num);
		t.setBeneficiaryAccNo(bene);
		t.setTransactionAmount(amount);
		HttpSession sess = request.getSession();
		sess.setAttribute("accNo", acc);
		boolean result = false;
		PaymentResponse fund = null;
		try {
			fund = transactionService.fundTransaction(t);
			System.out.println(fund.getTransactionId());
			System.out.println(fund.isStatus());
			result = fund.isStatus();
			if (result) {
				response.sendRedirect("successfullPayment.jsp");

			} else {
				response.sendRedirect("paymentFailed.jsp");

			}
		} catch (ServiceException e) {
			request.setAttribute("errormessage", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("fundTransfer.jsp");
			dispatcher.forward(request, response);
		}

	}

}
