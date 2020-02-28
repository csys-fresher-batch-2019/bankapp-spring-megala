<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="com.megala.bankapp.domain.Transaction"%>
<%@page import="com.megala.bankapp.dao.TransactionDAO"%>
<html>
<head>
<h5><jsp:include page="userfeatures.jsp"></jsp:include></h5>
<h3><center>Account Transaction History</center></h3>
</head>
<style>
h5{
font-size: x-large;
color:navy;
font-family:monospace;
font-style: italic;
}
h3{
font-size: xx-large;
color:navy;
font-family:monospace;
font-style: italic;
}
.b{
font-size:x-large;
color:black;
font-family:monospace;
font-style: italic;
}
.a{
font-size:x-large;
color:black;
font-family:monospace;
font-style: italic;
}
.c{
font-style: italic;
  font-size: 14px;
  color:black;
}
input[type="number"] {
font-family:monospace;
font-style: italic;
  font-size: 16px;
  color:blue;
}</style>
<body class="a">
<form action="searchByCusAccNo.jsp" >
Search
<br/>
Enter AccNo:<input type="number" name="accNo">
<button type="submit" class="btn btn-primary" data-toggle="button" aria-pressed="false">
         Submit
         </button></form>
<%TransactionDAO dao =DAOFactory.getTransactionDAO();
List<Transaction> t=dao.displayTransaction(); %>
<center>
<br/>

<table border="1" style="border-color:maroon;font-size:24;font-style: italic;color:navy;">
<thead class="b"><tr><th>TransactionId</th><th>AccountNo</th><th>BeneficiaryAccNo</th><th>TransactionDate</th><th>TransactionAmount</th><th>Status</th></tr></thead>
<tbody>
<%
for(Transaction trans:t){%>
<tr>
<td><%=trans.getTransactionId()%></td>
<td><%=trans.getAccNo()%></td>
<td><%=trans.getBeneficiaryAccNo()%></td>
<td><%=trans.getTransactionDate()%></td>
<td><%=trans.getTransactionAmount()%></td>
<td><%=trans.getStatus()%></td>
</tr>
<%}
%>
</tbody>
</table>
</center>
</body>
</html>