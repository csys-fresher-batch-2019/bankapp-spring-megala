<!DOCTYPE html5>
<%@page import="java.util.ArrayList"%>
<%@page import="com.megala.bankapp.domain.CreditCardTransaction"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.dao.CreditCardTransactionDAO"%>
<html>
<head>
<title>Transaction History</title>
<h5><jsp:include page="userfeatures.jsp"></jsp:include></h5>
</head>
<style>
.a {
	font-size: 20;
	font-family: monospace;
	font-style: italic;
	color: navy;
}
</style>
<body class="a">

	<%
	ArrayList<CreditCardTransaction> c = (ArrayList)request.getAttribute("creditList");

	%><center>
		<table border="1"
			style="border-color: maroon; font-size: 20; font-family: monospace; font-style: italic; color: black;">
			<thead>
				<tr>
					<th>TransactionId</th>
					<th>CardId</th>
					<th>Description</th>
					<th>TransactionDate</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody class="a">
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