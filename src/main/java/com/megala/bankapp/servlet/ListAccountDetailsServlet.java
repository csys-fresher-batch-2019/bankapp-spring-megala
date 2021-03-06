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

import com.megala.bankapp.domain.Account;
import com.megala.bankapp.exception.ServiceException;
import com.megala.bankapp.service.AccountService;

@SuppressWarnings("serial")
@WebServlet("/ListAccountDetailsServlet")
public class ListAccountDetailsServlet extends HttpServlet {
	@Autowired
	AccountService dao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Account> a = null;
		System.out.println(a);
		String obj = request.getParameter("number");
		if (obj != null && !"".equals(obj.trim())) {
			long val = Long.valueOf(obj);
			try {
				a = dao.findByAccNum(val);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else {
			try {
				a = dao.findAll();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("accountlist", a);
		RequestDispatcher dispatcher = request.getRequestDispatcher("accountList.jsp");
		dispatcher.forward(request, response);
	}
}
