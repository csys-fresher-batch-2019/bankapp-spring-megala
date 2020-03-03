<!DOCTYPE html5>
<%@page import="com.megala.bankapp.domain.CreditCardTransaction"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.dao.CreditCardTransactionDAO"%>
<html>
<head>
<title>Transaction History</title>
<h5><jsp:include page="creditCardPage.jsp"></jsp:include></h5>
<h2>
	<center>Transaction History</center>
</h2>

</head>
<style>
h2 {
	font-size: xx-large;
	color: navy;
	font-family: cursive;
}

.a {
	color: black;
}
</style>
<body class="a">

	<%
		CreditCardTransactionDAO dao = DAOFactory.getCreditCardTransactionDAO();
		Long obj = (Long) session.getAttribute("card");
		List<CreditCardTransaction> c = dao.displayTransactionHistory(obj);
	%><center>
		<table border="2"
			style="border-color: maroon; font-size: 25; color: navy;">
			<thead class="a">
				<tr>
					<th>TransactionId</th>
					<th>CardId</th>
					<th>Description</th>
					<th>TransactionDate</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (CreditCardTransaction card : c) {
				%>
				<tr>
					<td><%=card.getTransactionId()%></td>
					<td><%=card.getCardId()%></td>
					<td><%=card.getMerchantId()%></td>
					<td><%=card.getTransactionDate()%></td>
					<td><%=card.getStatus()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
			</center>
		</table>
</body>
</html>