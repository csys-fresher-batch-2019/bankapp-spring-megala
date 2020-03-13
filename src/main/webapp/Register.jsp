<!DOCTYPE html5>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<style type="text/css">
h3 {
	font-size: 30;
	font-style: italic;
}

body {
	background-size: 60em;
	background-attachment: fixed;
}

.a {
	font-family: cursive;
	font-size: 24px;
	color: #d7291f;
	color:blue;
}

.b {
	font-size: 14;
	font-style: italic;
	font-family: cursive;
	color: purple;
}

.c {
	font-size: 22px;
	font-style: italic;
	font-family: cursive;
	color: purple;
}
#accType{
font-family: cursive;
	font-size: 18px;
	color: blue;
}
input[type="text"] {
	font-family: cursive;
	font-size: 18px;
	color: blue;
}

input[type="password"] {
	font-family: cursive;
	font-size: 18px;
	color: blue;
}

input[type="number"] {
	font-family: cursive;
	font-size: 18px;
	color: blue;
}
</style>
<form action="RegisterServlet" class="a" method="post">
	<body class="a">
	<body background="assets/images/regi.jpg">
		Enter Name:
		<input type="text" name="name" placeholder="Enter Customer Name"
			required autofocus>
		<br />
		<br /> Enter Address:
		<input type="text" name="street" placeholder="Enter Customer Street"
			required>
		<br />
		<br /> Enter City:
		<input type="text" name="city" placeholder="Enter Customer City"
			required>
		<br />
		<br /> Enter Account Type:
		<select id="accType" name="accType">
			<option value="saving">saving</option>
			<option value="salaried">salaried</option>
		</select>
		<br />
		<br /> Enter mobile Number:
		<input type="text" name="mbleNo" placeholder="Enter mobile Number"
			required maxlength="10"pattern="\d{10}" >
		<br />
		<br /> Enter Email:
		<input type="email" name="email" placeholder="Enter Email id" required>
		<br />
		<br /> Enter password:
		<input type="password" id="psw" name="pass"
			pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
			title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
			required>
		<br />
		<br/>
		<%
			String errorMessage = (String) request.getAttribute("errormessage");
			if (errorMessage != null) {
		%>
		<font color="red" style="font-size: 26px;text-align: right;"><%=errorMessage%></font>
		<%
			}
		%>
		<br />
		<button type="submit" class="btn btn-primary" data-toggle="button"
			aria-pressed="false">Submit</button>
		<a href="User.jsp" class="c">Already Registered?</a>
		<br />
		<br />

	</body>
</form>
</body>
</html>