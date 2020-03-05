
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
<h3>
	<center>Beneficiary Details</center>
</h3>
</head>
<style>
h3 {
	color: navy;
	font-family: monospace;
	font-size: 22;
	font-style: italic;
}

.a {
	font-size: 20;
	font-family: monospace;
	font-style: italic;
	text-align:center;
	
}

.b {
	font-size: 16;
	font-family: monospace;
	font-style: italic;
}

input[type="number"] {
	font-family: monospace;
	font-size: 16x;
	color: blue;
	font-style: italic;
}

input[type="text"] {
	font-family: monospace;
	font-size: 16px;
	color: blue;
	font-style: italic;
}
</style>
<%
	Long obj = (Long) session.getAttribute("accNumber");
%>
<body class="a">
	<form action="ListBeneficiaryDetailsServlet">
		Search <br /> Enter Beneficiary Name:<input type="text" name="name">
		<button type="submit" class="b">Submit</button>
	</form>

<%
	ArrayList<Beneficiary> c =(ArrayList)request.getAttribute("bene"); 
%>
	<div class="container">

	<table border="1"
		style="border-color: maroon; font-size: 24; font-style: italic;text-align:center;">

		<thead class="a">
			<tr>
				<th>Beneficiary Name</th>
				<th>Account Number</th>
				<th>IFSC code</th>
				<th>Amount</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Beneficiary card : c) {
			%>
			<tr>
				<td><%=card.getBeneficiaryName()%></td>
				<td><%=card.getAccNo()%></td>
				<td><%=card.getiFSCCode()%></td>
				<td><%=card.getAmount()%></td>
				<td><%=card.getComments()%></td>

			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	</div>
	</body>
</html>