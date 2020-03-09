<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html5>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Activate Account</title>
<h5><jsp:include page="userfeatures.jsp"></jsp:include></h5>
<h3>
	<center>Active/Deactive Account</center>
</h3>
</head>
<style>
.a {
	font-size: 20;
	font-family: monospace;
	font-style: italic;
	color: navy;
	text-align:center;
}
#status{
font-family: cursive;
	font-size: 20px;
	color: blue;
}
input[type="text"] {
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
<body class="a">
	<form action="activeServlet">
		<br /> Enter AccNo:<input type="number" name="accNo"
			placeholder="Account Number" required autofocus> <br /> <br />
		Enter Status:<select id="status" name="status">
			<option value="active">Active</option>
			<option value="inactive">InActive</option>
		</select><br /> <br />
		<c:if test="${not empty output}">
		<font color="red" style="font-style: italic" >
${output}
</c:if>	
		<br /> 
		<br/>
		<button type="submit" class="btn btn-primary" data-toggle="button"
			aria-pressed="false">Submit</button>
	</form>
</body>
</html>