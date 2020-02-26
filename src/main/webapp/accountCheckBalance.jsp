
<!DOCTYPE html5>
<%@page import="account.Account"%>
<%@page import="factory.DAOFactory"%>
<%@page import="account.AccountDAO"%>
<html>
<head>
<h5><jsp:include page="userLogin.jsp"></jsp:include></h5>
</head>
<style>
.a{
font-family: monospace;
font-size: 26;
font-style: italic;
}
</style>
<body class="a">
<marquee><h2><p style = "font-family:georgia;font-size:26px;font-style:italic;">
    AVAILABLE BALANCE
     </p></h2>
</marquee>
<% 
AccountDAO dao = DAOFactory.getAccountDAO();
Long acc=(Long)session.getAttribute("accNumber");
int c=dao.displayBalance(acc);
%>
<center>Rs. <%=c %></center>
</body>
</html>