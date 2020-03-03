package com.megala.bankapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/bankLoginServlet")
public class bankLoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("name");
		String password = request.getParameter("pin");
		HttpSession sess = request.getSession();
		sess.setAttribute("name", userName);
		if (userName.equalsIgnoreCase("admin") && password.equals("admin")) {
			response.sendRedirect("homepage.jsp");
		} else {
			request.setAttribute("error message", "Invalid username or password");
			RequestDispatcher dispatcher = request.getRequestDispatcher("banklog.jsp");
			dispatcher.forward(request, response);
		}
	}

}
