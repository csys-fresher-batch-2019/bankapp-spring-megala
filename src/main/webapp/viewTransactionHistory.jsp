<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html5>

<%@page import="java.util.ArrayList"%>
<%@page import="com.megala.bankapp.dao.TransactionDAO"%>
<%@page import="com.megala.bankapp.domain.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<html>
<head>
<title>Transaction Details</title>
<h5><jsp:include page="userLogin.jsp"></jsp:include></h5>
<h3>
	<center>Transaction Details</center>
</h3>
</head>
<style>
h3 {
	font-size: large;
	color: navy;
	font-family: cursive;
	font-size: 30px;
}

body {
	font-size: 20;
	font-style: italic;
	font-family: cursive;
}

input[type="number"] {
	font-family: cursive;
	font-size: 10px;
	color: blue;
	font-style: italic;
	font-family: cursive;
}

input[type="text"] {
	font-family: cursive;
	font-size: 10px;
	color: blue;
	font-style: italic;
	font-family: cursive;
}
</style>
<body>
</body>
<div class="container">
	<table border="1"
		style="border-color: maroon; font-size: 20; color: black; font-style: italic; font-family: cursive">
		<thead>
			<tr>
				<th>Transaction id</th>
				<th>Account Number</th>
				<th>Beneficiary Account Number</th>
				<th>Transaction Date</th>
				<th>Transaction Amount</th>
				<th>status</th>
			</tr>

		</thead>
		<tbody>
			<c:forEach items="${fundTransfer}" var="fund">
				<tr>
					<td>${fund.transactionId}</td>
					<td>${fund.accNo}</td>
					<td>${fund.beneficiaryAccNo}</td>
					<td>${fund.transactionDate}</td>
					<td>${fund.transactionAmount}</td>
					<td>${fund.status}</td>
			</c:forEach>
		</tbody>
		<tbody>


		</tbody>

	</table>
</div>
</html>