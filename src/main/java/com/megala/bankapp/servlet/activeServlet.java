package com.megala.bankapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.megala.bankapp.dao.AccountDAO;
import com.megala.bankapp.exception.DbException;

@SuppressWarnings("serial")
@WebServlet("/activeServlet")
public class activeServlet extends HttpServlet {
	@Autowired
	AccountDAO dao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String c=null;
		String obj = request.getParameter("accNo");
		long val = Long.valueOf(obj);
		String active = request.getParameter("status");
		int account=0;
		try {
			account = dao.activeAccount(val, active);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (account == 1) {
			c="Account Updated";
		} else {
			c="Account Failed to update";
		}
		request.setAttribute("output", c);
		RequestDispatcher dispatcher = request.getRequestDispatcher("activeAccount.jsp");
		dispatcher.forward(request, response);
}
}
