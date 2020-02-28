<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html5>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Account</title>
<h5><jsp:include page="userfeatures.jsp"></jsp:include></h5>
<h3>Update Account</h3>
</head>
<style>
.a{
font-size:20;
font-family:monospace;
font-style: italic;
color:navy;
text-align: center;
}
input[type="number"] {
  font-family:cursive;
  font-size: 18px;
  color:blue;
}</style>
<body class="a">
<form action="update.jsp">
<br/>
Enter AccNo:<input type="number" name="accNo" placeholder="Account Number" required autofocus>
<br/>
<br/>
Enter amount:<input type="number" name="price" placeholder="amount" required>
        <br/>
        <br/>
        <%String errorMessage=(String)request.getAttribute("output");
            if(errorMessage!=null)
            {%>
         <font color="red" style="font-size: 20px" ><%=errorMessage %></font>
         <%} %>
         <br/>
         <br/>
         <button type="submit" class="btn btn-primary" data-toggle="button" aria-pressed="false">
         Submit
         </button>
         </form>

</body>
</html>