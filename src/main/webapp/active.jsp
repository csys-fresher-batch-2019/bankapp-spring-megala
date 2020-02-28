<%@page import="com.megala.bankapp.domain.Account"%>
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="com.megala.bankapp.dao.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Active/Deactive account</title>
</head>
<body>
<%
AccountDAO dao=DAOFactory.getAccountDAO();
String obj=request.getParameter("accNo");
long val=Long.valueOf(obj);
String active=request.getParameter("status");
int account=dao.activeAccount(val, active);
if(account==1){
	request.setAttribute("output", "Account Updated");
	RequestDispatcher dispatcher=request.getRequestDispatcher("activeAccount.jsp");
	dispatcher.forward(request, response);
}
else{
	request.setAttribute("output", "Account Failed to update");
	RequestDispatcher dispatcher=request.getRequestDispatcher("activeAccount.jsp");
	dispatcher.forward(request, response);	
}
%>
</body>
</html>