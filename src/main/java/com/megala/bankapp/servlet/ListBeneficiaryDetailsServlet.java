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

import com.megala.bankapp.dao.BeneficiaryDAO;
import com.megala.bankapp.domain.Beneficiary;
import com.megala.bankapp.exception.DbException;

@SuppressWarnings("serial")
@WebServlet("/ListBeneficiaryDetailsServlet")
public class ListBeneficiaryDetailsServlet extends HttpServlet {
	@Autowired
	BeneficiaryDAO dao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Beneficiary> a =null;
		HttpSession session = request.getSession();
		Long obj1 = (Long) session.getAttribute("accNumber");
		String obj = request.getParameter("name");
		if(obj!=null && !"".equals(obj.trim()))
		{
			try {
				a= dao.findByName(obj);
			} catch (DbException e) {
				e.printStackTrace();
			}
			System.out.println(a);
		}
		else
		{
			try {
				a=dao.findByCusAccNo(obj1);
			} catch (DbException e) {
				e.printStackTrace();
			}
			System.out.println(a);
			
		}
		request.setAttribute("bene", a);
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewBeneficiary.jsp");
		dispatcher.forward(request, response);
}
}
