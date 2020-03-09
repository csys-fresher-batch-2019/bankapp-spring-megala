<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html5>
<html>
<head>
</head>
<h5><jsp:include page="loginpage.jsp"></jsp:include></h5>

<style>
.a {
	font-size: x-large;
	text-align: center;
	font-family: cursive;
	background-attachment: fixed;
	background-size: cover;
}
</style>
<%String name=(String)session.getAttribute("accName");
long acc=(Long)session.getAttribute("accNo");
%>
<body class="a">
<body background="assets/images/acc.jpg">
	<br />
	<br />
	<br /> Hai
	${accName}!!!
	<br /> Welcome to citiBank...
	<br /> Your account is created.
	<br /> Account Number is
	${accNo}
</body>
</html>