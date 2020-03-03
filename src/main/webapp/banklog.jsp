<!DOCTYPE html5>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<br />
</head>
<style>
h3 {
	font-size: 30;
	font-style: italic;
	font-family: cursive;
}

body {
	background-size: cover;
	background-attachment: fixed;
}

.a {
	text-align: center;
	font-family: cursive;
	font-size: 30;
	color: red;
}

.b {
	font-size: 20;
	font-style: italic;
	font-family: cursive;
	color: purple;
}

.c {
	font-size: 19;
	font-style: italic;
	font-family: cursive;
	color: red;
}

input[type="text"] {
	font-family: cursive;
	font-size: 20px;
	color: blue;
}

input[type="password"] {
	font-family: cursive;
	font-size: 20px;
	color: blue;
}
</style>
<form action="bankLoginServlet" class="a" method="post">
	<body background="assets/images/adminl.jpg">
		<%String errorMessage=(String)request.getAttribute("error message");
if(errorMessage!=null)
{%>
		<font color="red" style="font-size: 20px"><%=errorMessage %></font>
		<%} %>
		<br /> Enter Username:
		<input type="text" name="name" placeholder="Enter Username" required
			autofocus>
		<br />
		<br /> Enter Password:
		<input type="password" name="pin" placeholder="Enter password"
			required>
		<br />
		<br />
		<button type="submit" class="btn btn-primary" data-toggle="button"
			aria-pressed="false">Submit</button>
		<br />
		<a href="BankLogin.jsp" class="c">Back</a>
		<br />
		<br />

	</body>
</form>
</body>
</html>