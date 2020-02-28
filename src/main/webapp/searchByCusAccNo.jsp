<!DOCTYPE html5>
<%@page import="com.megala.bankapp.domain.Transaction"%>
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="com.megala.bankapp.dao.TransactionDAO"%>
<html>
<head>
<h5><jsp:include page="userfeatures.jsp"></jsp:include></h5>
</head>
<style>
.a{
font-size:20;
font-family:monospace;
font-style: italic;
color:navy;
}</style>
<body>
<%
TransactionDAO dao=DAOFactory.getTransactionDAO();
String obj=request.getParameter("accNo");
long val=Long.valueOf(obj);
List<Transaction> trans=dao.displayParTransaction(val);
%><center>
<table border="1" style="border-color:maroon;font-size: 20;font-family: monospace;font-style: italic;color:black"
>
<thead><tr><th>TransactionId</th><th>AccountNo</th><th>BeneficiaryAccNo</th><th>TransactionDate</th><th>TransactionAmount</th><th>Status</th></tr></thead>
<tbody class="a">
<%
for(Transaction acc:trans){%>
<tr>
<td><%=acc.getTransactionId()%></td>
<td><%=acc.getAccNo()%></td>
<td><%=acc.getBeneficiaryAccNo()%></td>
<td><%=acc.getTransactionDate()%></td>
<td><%=acc.getTransactionAmount()%></td>
<td><%=acc.getStatus()%></td>

</tr>
<%}
%>
</tbody>
</center>
</table>

</html>