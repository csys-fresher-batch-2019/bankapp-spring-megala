<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<h5><jsp:include page="userLogin.jsp"></jsp:include></h5>
</head>
<style>
.a {
	font-size: 20;
	font-family: monospace;
	font-style: italic;
	color: black;
}

input[type="number"] {
	font-style: italic;
	font-size: 16px;
	color: blue;
}

input[type="text"] {
	font-style: italic;
	font-size: 16px;
	color: blue;
}

.b {
	font-size: 16px;
	font-family: monospace;
	font-style: italic;
	color: blue;
}
</style>
<body>
	<center>
		<form action="BeneficiaryServlet">
			<br /> <br />
			<%long obj=(Long)session.getAttribute("accNumber"); %>

			<%String output=(String)request.getAttribute("output message");
if(output!=null)
{%>
			<font color="red" style="font-size: 25"><%=output %></font>
			<%} %>
			<br /> Enter Account Number:<input type="number" name="acc"
				value="<%=obj%>" readonly> <br /> <br /> Enter Beneficiary
			Name:<input type="text" name="name" placeholder="Beneficiary Name"
				required autofocus> <br /> <br /> Enter Account No:<input
				type="number" name="accNo" placeholder="Account Number" required>
			<br /> <br /> Enter IFSC Code:<input type="text" name="ifsc"
				placeholder="IFSC Code" required> <br /> <br />
			<%String errorMessage=(String)request.getAttribute("errormessage");
if(errorMessage!=null)
{%>
			<font color="red" style="font-size: 20"><%=errorMessage %></font>
			<%} %>
			<br />
			<button type="submit" class="btn btn-primary" data-toggle="button"
				aria-pressed="false">Submit</button>
			<br /> <br />
		</form>
	</center>
</body>
</html>