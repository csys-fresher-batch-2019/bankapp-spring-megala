
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
	font-family: monospace;
	font-size: 26;
	font-style: italic;
	text-align: center;
}
</style>
<body class="a">
	<marquee>
		<h2>
			<p style="font-family: georgia; font-size: 26px; font-style: italic;">
				AVAILABLE BALANCE</p>
		</h2>
	</marquee>
	<%
		
		int c = (Integer)request.getAttribute("output");%>
		Rs.
		<%=c%>
</body>
</html>