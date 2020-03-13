package com.megala.bankapp.servlet;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/ListBeneficiaryDetailsServlet")
public class ListBeneficiaryDetailsServlet extends HttpServlet {
	@Autowired
	BeneficiaryService dao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Beneficiary> a = null;
		HttpSession session = request.getSession();
		Long obj1 = (Long) session.getAttribute("accNumber");

		String ifscCode = request.getParameter("ifsc");
		String obj = request.getParameter("acc");

		if (ifscCode != null && !"".equals(ifscCode.trim())) {
			long accNo = Long.valueOf(obj);
			try {
				a = dao.findByAccNoAndIfscCode(accNo, ifscCode);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else {
			try {
				a = dao.findByCusAccNum(obj1);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			System.out.println(a);

		}
		request.setAttribute("bene", a);
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewBeneficiary.jsp");
		dispatcher.forward(request, response);
	}
}
