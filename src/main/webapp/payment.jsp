<!DOCTYPE html >
<html>
<head>
</head>
<style>
h1{
font-family: serif;
font-size: larger;
}
body {
	font-family: cursive;
	font-size: x-large;
	font-style: italic;
	font-stretch: ultra-expanded;
	font-position:center;
}
</style>
<body>
<a href="creditcardpayment.jsp">CreditCard Payment</a>
<br/>
<br/>
<a href="checkBalance.jsp">Check Balance</a>
<br/>
<br/>
<a href="transactionHistory.jsp">Transaction History</a>
<br/>
<%
Long obj=(Long) session.getAttribute("card");
%>
</body>
</html>