<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html5>


<%@page import="java.util.ArrayList"%>
<%@page import="com.megala.bankapp.domain.Beneficiary"%>
<%@page import="com.megala.bankapp.dao.BeneficiaryDAO"%>
<%@page import="com.megala.bankapp.domain.CreditCardTransaction"%>
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="com.megala.bankapp.dao.CreditCardTransactionDAO"%>

<html>
<head>
<title>beneficiary Details</title>
<h5><jsp:include page="userLogin.jsp"></jsp:include></h5>
<h3>Beneficiary Details</h3>
</head>
<style>
h3 {
	color: navy;
	font-family: monospace;
	font-size: 22;
	font-style: italic;
	text-align: center;
}

.a {
	font-size: 20;
	font-family: monospace;
	font-style: italic;
	font-family: cursive;
}

.b {
	font-size: 20;
	font-family: monospace;
	font-style: italic;
	font-family: cursive;
	color: red;
	text-align: justify;
}

input[type="text"] {
	font-family: monospace;
	font-size: 16px;
	color: blue;
	font-style: italic;
	text-align: justify;
}

input[type="number"] {
	font-family: monospace;
	font-size: 16px;
	color: blue;
	font-style: italic;
	text-align: justify;
}
</style>

<body class="b">
	<form action="ListBeneficiaryDetailsServlet" class="b">
		Search <br /> Enter Ifsc Code:<input type="text" name="ifsc" required>
		<br /> Enter AccNo:<input type="number" name="acc" required>
		<button type="submit" class="b">Submit</button>
	</form>

	<div class="container">

		<table border="1"
			style="border-color: maroon; font-size: 20; font-style: italic; text-align: center;">

			<thead class="a">
				<tr>
					<th>Beneficiary Name</th>
					<th>Account Number</th>
					<th>IFSC code</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bene}" var="acc">

					<tr>
						<td>${acc.beneficiaryName}</td>
						<td>${acc.accNo}</td>
						<td>${acc.iFSCCode}</td>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>