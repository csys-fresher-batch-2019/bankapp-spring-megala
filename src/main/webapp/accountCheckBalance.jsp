
<!DOCTYPE html5>
<%@page import="com.megala.bankapp.domain.Account"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="com.megala.bankapp.dao.AccountDAO"%>
<html>
<head>
<h5><jsp:include page="userLogin.jsp"></jsp:include></h5>
</head>
<style>
.a {
	font-family: cursive;
	font-size: 26;
	font-style: italic;
	text-align: center;
	font:bold;
}

h2 {
	font-family: sans-serif;
	font-size: 26;
	font-style: italic;
	text-align: center;
}
</style>
<body class="a">
<br/>
<br/>
<br/>
		<h2>AVAILABLE BALANCE</h2>
	<%
		int c = (Integer) request.getAttribute("output");
	%>
	Rs.
	<%=c%>
</body>
</html>