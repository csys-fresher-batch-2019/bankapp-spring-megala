<!DOCTYPE html5>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="com.megala.bankapp.dao.AccountDAO"%>
<%@page import="com.megala.bankapp.domain.Account"%>
<html>
<head>
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
		ArrayList<Account> account = (ArrayList) request.getAttribute("listAccount");
	%><center>
		<table border="1"
			style="border-color: maroon; font-size: 20; font-family: monospace; font-style: italic; color: black;">
			<thead>
				<tr>
					<th>Customer Id</th>
					<th>Account Number</th>
					<th>Account Type</th>
					<th>Available Balance</th>
				</tr>
			</thead>
			<tbody class="a">
				<%
					for (Account acc : account) {
				%>
				<tr>
					<td><%=acc.getCustomerId()%></td>
					<td><%=acc.getAccNo()%></td>
					<td><%=acc.getAccType()%></td>
					<td><%=acc.getAvailableBalance()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
			</center>
		</table>
</body>
</html>