<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="com.megala.bankapp.domain.Transaction"%>
<%@page import="com.megala.bankapp.dao.TransactionDAO"%>
<html>
<head>
<h5><jsp:include page="userfeatures.jsp"></jsp:include></h5>
<h3>
	<center>Account Transaction History</center>
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
	font-size: x-large;
	color: black;
	font-family: monospace;
	font-style: italic;
}

.a {
	font-size: x-large;
	color: black;
	font-family: monospace;
	font-style: italic;
}

.c {
	font-style: italic;
	font-size: 14px;
	color: black;
}

input[type="number"] {
	font-family: monospace;
	font-style: italic;
	font-size: 16px;
	color: blue;
}
</style>
<body class="a">
	<form action="ListAccountTransactionServlet">
		Search <br /> Enter AccNo:<input type="number" name="accNo">
		<button type="submit" class="btn btn-primary" data-toggle="button"
			aria-pressed="false">Submit</button>
	</form>
	<center>
		<br />

		<table border="1"
			style="border-color: maroon; font-size: 24; font-style: italic; color: navy;">
			<thead class="b">
				<tr>
					<th>TransactionId</th>
					<th>AccountNo</th>
					<th>BeneficiaryAccNo</th>
					<th>TransactionDate</th>
					<th>TransactionAmount</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${account}" var="acc">

					<tr>
						<td>${acc.transactionId}</td>
						<td>${acc.accNo}</td>
						<td>${acc.beneficiaryAccNo}</td>
						<td>${acc.transactionDate}</td>
						<td>${acc.transactionAmount}</td>
						<td>${acc.status}</td>
				</c:forEach>
			
				
			</tbody>
		</table>
	</center>
</body>
</html>