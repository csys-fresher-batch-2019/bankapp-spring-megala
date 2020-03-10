
<%@page import="java.util.List"%>
<%@page import="com.megala.bankapp.factory.DAOFactory"%>
<%@page import="com.megala.bankapp.domain.Beneficiary"%>
<%@page import="com.megala.bankapp.dao.BeneficiaryDAO"%>
<html>
<head>
<title>Search by AccNo</title>
<h5><jsp:include page="userLogin.jsp"></jsp:include></h5>
</head>
<style>
.a {
	font-size: 20;
	font-family: monospace;
	font-style: italic;
	color: navy;
}
</style>
<body class="a">


	<%
		BeneficiaryDAO dao = DAOFactory.getBeneficiaryDAO();
			String obj = request.getParameter("name");
			List<Beneficiary> b = dao.findByName(obj);
	%><center>
		<table border="1"
			style="border-color: maroon; font-size: 20; font-family: monospace; font-style: italic; color: black;">
			<thead>
				<tr>
					<th>Beneficiary Name</th>
					<th>Account Number</th>
					<th>IFSC code</th>
				</tr>
			</thead>
			<tbody class="a">
				<%
					for (Beneficiary card : b) {
				%>
				<tr>
					<td><%=card.getBeneficiaryName()%></td>
					<td><%=card.getAccNo()%></td>
					<td><%=card.getiFSCCode()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
			</center>
		</table>
</body>
</html>