<!DOCTYPE html5>
<html>
<head>
<title>login</title>
<br/>
<h2><center>Login</center></h2>
</head>
<style>
h2{
font-size: 40;
color: navy;
}
.a{
text-align: center;
font-family:cursive;
font-size:30;
}
.b{
font-size:20;
font-style: italic;
font-family: cursive;
color:purple;
}
input[type="number"] {
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
<form action="LoginServlet" class="a" >
<body>
<body background="assets/images/creditcard.jpg">
<br/>
Enter creditCardNo:<input type="number" name="number" pattern=".{16}"
placeholder="Enter CardNo" required autofocus>
<br/>
<br/>
Enter creditCardPin:<input type="password" name="pin" 
placeholder="Enter CardPin" required>
<br/>
<br/>
<button type ="submit" class="b">Submit</button>
</form>
</body>
</html>