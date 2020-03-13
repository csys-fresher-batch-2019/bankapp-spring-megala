<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.megala.bankapp.domain.Transaction"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<h5><jsp:include page="userLogin.jsp"></jsp:include></h5>
</head>
<style>
h3 {
	font-family: monospace;
	font-style: italic;
}

input[type="number"] {
	font-family: monospace;
	font-size: 16px;
	color: blue;
	font-style: italic;
}

input[type="text"] {
	font-family: monospace;
	font-size: 16px;
	color: blue;
	font-style: italic;
}

.b {
	font-size: large;
	font-family: monospace;
	font-style: italic;
}
</style>
<body class="b">
	<center>
		<form action="FundTransferServlet">
			<%
				long obj = (Long) session.getAttribute("accNumber");
			%>
			<br /> Enter Account No:<input type="number" name="no"
				value="<%=obj%>" readonly> <br /> <br /> Enter BeneAcc No:<input
				type="number" name="beneAccNo"
				placeholder="Beneficiary Account Number" required> <br /> <br />
			Enter Amount:<input type="text" name="amount" placeholder="Amount"
				required> <br />
				<br/>
			<c:if test="${not empty errormessage}">
				<font color="red" style="font-style: italic;">
					${errormessage} 
			</c:if>
			<br /> <br />
			<button type="submit" class="btn btn-primary" data-toggle="button"
				aria-pressed="false">Submit</button>
			<br /> <br />
		</form>
	</center>
</body>
</html>