<!DOCTYPE html5>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<br/>
</head>
<style>
h3{
font-size: 30;
font-style: italic;
font-family: cursive;
}
.a{
text-align: center;
font-family:cursive;
font-size:30;
color:white;
}
.b{
font-size:22;
font-style: italic;
font-family: cursive;
color:white;
}
.c{
font-size:20px;
font-style: italic;
font-family: cursive;
color:red;

}
 body{
      background-size:160%;
      background-attachment: fixed;
      }
input[type="text"] {
  font-family:cursive;
  font-size: 20px;
  color:blue;
}
input[type="password"] {
  font-family:cursive;
  font-size: 20px;
  color:blue;
}
</style>
<form action="UserLoginServlet" class="a" method="post">
<body background="assets/images/userlog.jpg">
<%String errorMessage=(String)request.getAttribute("error message");
if(errorMessage!=null)
{%>
<font color="red" style="font-size: 20px" ><%=errorMessage %></font>
<%} %>
<br/>
Enter MailId:<input type="text" name="name"
placeholder="Enter mail id" required autofocus>
<br/>
<br/>
Enter Password:<input type="password" name="pin" 
placeholder="Enter password" required>
<br/>
<br/>
<button type="submit" class="c" data-toggle="button" aria-pressed="false">
 Submit
</button><a href="Register.jsp" class="b">New User?</a><br/>
<a href="BankLogin.jsp" class="c">Back</a>
<br/>
<br/>

</body>
</form>
</body>
</html>