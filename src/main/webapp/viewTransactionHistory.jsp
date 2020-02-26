
<!DOCTYPE html5>
 
 <%@page import="transaction.TransactionDAO"%>
<%@page import="transaction.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="factory.DAOFactory"%>
<html>
<head>
<title>Transaction Details</title>
<h5><jsp:include page="userLogin.jsp"></jsp:include></h5>
<h3><center>Transaction Details</center></h3>
</head>
<style>
h3{
font-size:large;
color:navy;
font-family:cursive;
font-size:30px;
}
body{
font-size:20;
}
input[type="number"] {
  font-family:cursive;
  font-size: 10px;
  color:blue;
}
input[type="text"] {
  font-family:cursive;
  font-size: 10px;
  color:blue;
}
</style>
<%Long obj=(Long)session.getAttribute("accNumber"); %>
<body>
</body>
<% 
TransactionDAO dao=DAOFactory.getTransactionDAO();
List<Transaction> c=dao.displayParTransaction(obj);
%>
<center>
<table border="1" style="border-color:maroon;font-size:20">
<center>
<thead><tr><th>Transaction id</th><th>Account Number</th><th>Beneficiary Account Number</th><th>Transaction Date</th><th>Transaction Amount</th><th>status</th></tr></thead>
<tbody>

<%
for(Transaction card:c){%>
<tr>
<td><%=card.getTransactionId()%></td>
<td><%=card.getAccNo()%></td>
<td><%=card.getBeneficiaryAccNo()%></td>
<td><%=card.getTransactionDate()%></td>
<td><%=card.getTransactionAmount()%></td>
<td><%=card.getStatus()%></td>

</tr>
<%}
%>
</tbody>
</center>
</table>
</center>
</html>