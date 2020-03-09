<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html5>


<%@page import="java.util.ArrayList"%>
<%@page import="com.megala.bankapp.domain.CreditCardTransaction"%>
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="com.megala.bankapp.dao.CreditCardTransactionDAO"%>
 
<html>
<head>
<title>CreditCardPaymentList</title>
<h5><jsp:include page="userfeatures.jsp"></jsp:include></h5>
<h3><center>CreditCard Transaction History</center></h3>
</head>
<style>
h5{
color:navy;
font-family:monospace;
font-style: italic;
}
.b{
font-size:14;
font-family:monospace;
font-style: italic;
}
.a{
font-size:20;
font-family:monospace;
font-style: italic;
color: navy;
}
input[type="number"] {

font-style: italic;
  font-size: 16px;
  color:blue;
}
body{
background-attachment: fixed;
background-size:110%; }
</style><body class="a">
<body background="assets/images/side.jpg">
<form action="ListCreditCardTransactionServlet">
<bold>Search</bold>
<br/>
Enter CardId:<input type="number" name="number">
<button type="submit" class="btn btn-primary" data-toggle="button" aria-pressed="false">
         Submit
         </button></form>
</body>
<div class="container">
<table border="1" style="border-color:maroon;font-size:20;font-family:monospace; italic;color:black">

<thead clas="c"><tr><th>TransactionId</th><th>CardId</th><th>Amount</th><th>Description</th><th>TransactionDate</th><th>Status</th></tr></thead>
<tbody class="a">
<c:forEach items="${list}" var="l">
			
		<tr>
			<td>${l.transactionId}</td>
			<td>${l.cardId}</td>
			<td>${l.amount}</td>
			<td>${l.merchantId}</td>
			<td>${l.transactionDate}</td>
			<td>${l.status}</td>
	</c:forEach>
			

</tbody>
</table>
</div>
</body>
</html>