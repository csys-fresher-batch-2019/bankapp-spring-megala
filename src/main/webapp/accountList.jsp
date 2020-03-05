<!DOCTYPE html5>
<%@page import="java.util.ArrayList"%>
<%@page import="com.megala.bankapp.domain.Account"%>
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="com.megala.bankapp.dao.AccountDAO"%>
<html>
<head>
<title>Account Details</title>
<h5><jsp:include page="userfeatures.jsp"></jsp:include></h5>
<h3>
	<center>Account Details</center>
</h3>
</head>
<style>
h5 {
	font-size: x-large;
	color: navy;
	font-family: monospace;
	font-style: italic;
}

h3 {
	font-size: xx-large;
	color: navy;
	font-family: monospace;
	font-style: italic;
}

.b {
	font-size: 16;
	font-family: monospace;
	font-style: italic;
}

.a {
	font-size: 20;
	font-family: monospace;
	font-style: italic;
	color: black;
}

input[type="number"] {
	font-style: italic;
	font-size: 16px;
	color: blue;
}
</style>
<body class="a">
	<form action="ListAccountDetailsServlet">
		Search <br /> Enter AccNo:<input type="number" name="number">
		<button type="submit" class="btn btn-primary" data-toggle="button"
			aria-pressed="false">Submit</button>
	</form>
	<%
		
		ArrayList<Account> a = (ArrayList)request.getAttribute("accountList");

	%>
	<center>
		<table border="1"
			style="border-color: maroon; font-size: 20; font-family: monospace; font-style: italic; color: navy;">
			</center>

			<thead class="a">
				<tr>
					<th>CustomerId</th>
					<th>AccountNo</th>
					<th>AccountType</th>
					<th>AvailableBalance</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Account acc : a) {
				%>
				<tr>
					<td><%=acc.getCustomerId()%></td>
					<td><%=acc.getAccNo()%></td>
					<td><%=acc.getAccType()%></td>
					<td><%=acc.getAvailableBalance()%></td>
					<td><%=acc.getStatus()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
</body>
</html>