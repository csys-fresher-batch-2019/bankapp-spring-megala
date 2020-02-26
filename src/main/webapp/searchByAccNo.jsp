<!DOCTYPE html5>
<%@page import="java.util.List"%>
<%@page import="factory.DAOFactory"%>
<%@page import="account.AccountDAO"%>
<%@page import="account.Account"%>
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
<body class="a">

<%
AccountDAO dao=DAOFactory.getAccountDAO();
String obj=request.getParameter("number");
long val=Long.valueOf(obj);
List<Account> account=dao.searchByAccountNo(val);
%><center>
<table border="1" style="border-color:maroon;font-size: 20;font-family: monospace;font-style: italic;color:black;"
>
<thead><tr><th>Customer Id</th><th>Account Number</th><th>Account Type</th><th>Available Balance</th></tr></thead>
<tbody class="a">
<%
for(Account acc:account){%>
<tr>
<td><%=acc.getCustomerId()%></td>
<td><%=acc.getAccNo()%></td>
<td><%=acc.getAccType()%></td>
<td><%=acc.getAvailableBalance()%></td>
</tr>
<%}
%>
</tbody>
</center>
</table>
</body>
</html>