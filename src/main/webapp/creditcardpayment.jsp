<!DOCTYPE html5>
<html>
<head>
<title>Credit Card</title>
</head>
<h5><jsp:include page="creditCardPage.jsp"></jsp:include></h5>
<style>
.a {
	text-align: center;
	font-family: serif;
	font-size: 26;
	color: highlighttext;
}

.b {
	text-align: center;
	font-family: serif;
	font-size: 20;
}

.c {
	font-size: 22px;
	color: black;
}

input[type="number"] {
	font-family: cursive;
	font-size: 20px;
	color: teal;
}

input[type="password"] {
	font-family: cursive;
	font-size: 20px;
	color: teal;
}

input[type="date"] {
	font-family: cursive;
	font-size: 20px;
	color: teal;
}

input[type="text"] {
	font-family: cursive;
	font-size: 20px;
	color: teal;
}
</style>
<%
	Long obj = (Long) session.getAttribute("card");
	Integer obj1 = (Integer) session.getAttribute("cardpin");
%>
<form action="PayServlet" class="a" method="post">
	<body>
	<body background="assets/images/creditcard.jpg">
		<font style="font-family: cursive"> <%
 	String errorMessage = (String) request.getAttribute("output");
 	if (errorMessage != null) {
 %> <font color="red" style="font-size: 20px"><%=errorMessage%></font>
			<%
				}
			%>
		</font>
		<br /> Enter Card No:
		<input type="number" name="name" pattern=".{16}"
			placeholder="Enter card No" value="<%=obj%>" required>
		<br /> Enter expiry Date:
		<input type="date" name="expirydate" placeholder="Enter expiry date">
		<br /> Enter cvv:
		<input type="number" name="cvv" placeholder="Enter cvv">
		<br /> Enter pin:
		<input type="password" name="pin" placeholder="Enter pin number"
			value="<%=obj1%>">
		<br /> Enter merchantId:
		<input type="text" name="id" placeholder="Enter merchant Id" required>
		<br /> Enter comments:
		<input type="text" name="comments" placeholder="Enter comments"
			required>
		<br /> Enter amount:
		<input type="number" name="amount" placeholder="Enter amount" required>
		<br />
		<!-- <body background ="assets/images/citibank.jpg" width="100%" height="100%">
</body>-->
		<button type="submit" class="b">Submit</button>
		<br />
		<a href="creditCardPage.jsp" class="c">Back</a>
</form>
</body>
</html>