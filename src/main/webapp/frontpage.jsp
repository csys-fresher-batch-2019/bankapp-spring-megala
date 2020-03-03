<!DOCTYPE html>
<html lang="en">
<head>
<title>citiBank</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<style>
.fakeimg {
	height: 200px;
	background: #6e7f96;
}

body {
	background-size: cover;
	background-attachment: fixed;
}

.a {
	font-size: x-large;
	font-family: monospace;
	font-style: italic;
	color: black;
}
</style>

</head>
<%
	String user = (String) session.getAttribute("email");
%>
<body class="a">
<body background="assets/images/userfront.png">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="BankLogin.jsp">Home</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<%
				if (user != null) {
			%>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="addBeneficiary.jsp">Add beneficiary</a></li>
				<li class="nav-item"><a class="nav-link"
					href="viewBeneficiary.jsp">Beneficiaries</a></li>
				<li></li>
				<li></li>
				<li class="nav-item"><a class="nav-link"
					href="fundTransfer.jsp">Fund Transfer</a></li>
				<li class="nav-item"><a class="nav-link"
					href="viewTransactionHistory.jsp">Transactions</a></li>
				<li class="nav-item"><a class="nav-link"
					href="accountCheckBalance.jsp">Balance</a></li>
			</ul>
			<%
				}
			%>

			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="BankLogin.jsp">Logout</a>
				</li>

			</ul>

		</div>
	</nav>
	<div class="container" style="margin-top: 30px">
		<div class="row">
			<div class="col-sm-4">
</body>
</body>
</html>
