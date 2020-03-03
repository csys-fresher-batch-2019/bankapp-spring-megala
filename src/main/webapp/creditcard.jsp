<%@page import="com.megala.bankapp.domain.CreditCard"%>
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="com.megala.bankapp.dao.CreditCardDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>credit card</title>
<h5><jsp:include page="userLogin.jsp"></jsp:include></h5>
</head>
<body class="a">

	<%
		CreditCardDAO dao = DAOFactory.getCreditCardDAO();
		long acc = (Long) session.getAttribute("accNumber");
		List<CreditCard> c = dao.displayCreditCardsByAccNo(acc);
	%><center>
		<table border="1"
			style="border-color: navy; font-size: 30px; color: navy;">
			<thead class="a">
				<tr>
					<th>Card Id</th>
					<th>Card Number</th>
					<th>Card Pin</th>
					<th>Cvv Number</th>
					<th>Card Limit</th>
					<th>Expiry Date</th>
					<th>Available Balance</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (CreditCard card : c) {
				%>
				<tr>
					<td><%=card.getCreditcardId()%></td>
					<td><%=card.getCardNo()%></td>
					<td><%=card.getPin()%></td>
					<td><%=card.getCvvNo()%></td>
					<td><%=card.getLimitNo()%></td>
					<td><%=card.getExpiryDate()%></td>
					<td><%=card.getAvailableBalance()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
			</center>
		</table>
</body>
</html>