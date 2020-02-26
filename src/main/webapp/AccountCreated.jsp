<!DOCTYPE html5>
<html>
<head>
</head>
<h5><jsp:include page="loginpage.jsp"></jsp:include></h5>

<style>
.a{
font-size: x-large;
text-align:center;
font-family: cursive;
}</style>
<%String name=(String)session.getAttribute("accName");
long acc=(Long)session.getAttribute("accNo");
%>
<body class="a">
<br/>
<br/>
<br/>
Hai <%=name %>!!!
<br/>
Welcome to citiBank...
<br/>
Your account is created.
<br/>
Account Number is <%=acc %>
</body>
</html>