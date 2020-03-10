
<!DOCTYPE html5>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="com.megala.bankapp.dao.CreditCardDAO"%>
<html>
<head>
<h5><jsp:include page="creditCardPage.jsp"></jsp:include></h5>
</head>
<style>
.a {
	font-family: monospace;
	font-size: 20px;
	font-style: italic;
	color: red;
}
</style>
<body class="a">
		<marquee>
			<h2>
				<p
					style="font-family: georgia; font-size: 30px; font-style: italic; color: red;">
					AVAILABLE BALANCE</p>
			</h2>
		</marquee>
		<%
			CreditCardDAO dao = DAOFactory.getCreditCardDAO();
			Long cardNo = (Long) session.getAttribute("card");
			float c = dao.findBalance(cardNo);
		%>
		<br /> <br /> Rs.<%=c%>
</body>
</html>