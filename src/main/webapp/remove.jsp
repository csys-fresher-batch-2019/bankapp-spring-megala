<%@page import="factory.DAOFactory"%>
<%@page import="beneficiary.BeneficiaryDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remove Beneficiary</title>
</head>
<body>
<%
BeneficiaryDAO dao=DAOFactory.getBeneficiaryDAO();
String obj=request.getParameter("accNo");
long val=Long.valueOf(obj);
long bene=dao.deleteBeneficiary(val);
if(bene==1){
	request.setAttribute("output", "Beneficiary Removed");
	RequestDispatcher dispatcher=request.getRequestDispatcher("activeAccount.jsp");
	dispatcher.forward(request, response);
}
else{
	request.setAttribute("output", "Invalid Account Number");
	RequestDispatcher dispatcher=request.getRequestDispatcher("activeAccount.jsp");
	dispatcher.forward(request, response);	
}
%>
</body>
</html>