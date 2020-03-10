<%@page import="com.megala.bankapp.domain.Account"%>
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="com.megala.bankapp.dao.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html5>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>account update</title>
</head>
<body>
	<%
		AccountDAO dao = DAOFactory.getAccountDAO();
			String obj = request.getParameter("accNo");
			long val = Long.valueOf(obj);
			String price = request.getParameter("price");
			int amount = Integer.valueOf(price);
			int account = dao.update(val, amount);
			if (account == 1) {
		request.setAttribute("output", "Amount Successfully added");
		RequestDispatcher dispatcher = request.getRequestDispatcher("activeAccount.jsp");
		dispatcher.forward(request, response);
			} else {
		request.setAttribute("output", "Invalid Account Number");
		RequestDispatcher dispatcher = request.getRequestDispatcher("activeAccount.jsp");
		dispatcher.forward(request, response);
			}
	%>
</body>
</html>