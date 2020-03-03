<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remove Beneficiary</title>
<h5><jsp:include page="userLogin.jsp"></jsp:include></h5>
<h3>Remove Beneficiary</h3>
</head>
<style>
.a {
	font-size: 20;
	font-family: monospace;
	font-style: italic;
	color: navy;
	text-align: center;
}

input[type="number"] {
	font-family: cursive;
	font-size: 18px;
	color: blue;
}
</style>
<body class="a">
	<form action="remove.jsp">
		<br /> Enter AccNo:<input type="number" name="accNo"
			placeholder="Account Number" required autofocus> <br /> <br />
		<%String errorMessage=(String)request.getAttribute("output");
            if(errorMessage!=null)
            {%>
		<font color="red" style="font-size: 20px"><%=errorMessage %></font>
		<%} %>
		<br /> <br />
		<button type="submit" class="btn btn-primary" data-toggle="button"
			aria-pressed="false">Submit</button>
	</form>
</body>
</html>