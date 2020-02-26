<!DOCTYPE html5>
<html>
<head><br/>
<h2><center>Account Creation</center></h2>
</head>
<style>
.a{
text-align: center;
font-family:serif;
font-size:30;
color:highlighttext;
}
.b{
text-align: center;
font-family:serif;
font-size:20;
}
h2{
font-size: xx-large;
color:navy;
font-family:cursive;
}
input[type="number"] {
  font-family:cursive;
  font-size: 20px;
  color:teal;
}
input[type="text"] {
  font-family:cursive;
  font-size: 20px;
  color:teal;
}

</style>
<body style="background-color: infobackground;">

<form action="accountCreationServlet" class="a" method="post">
<br/>
Enter Customer Id:<input type="number" name="CustomerId" placeholder="Enter Customer Id" required>
<br/>
Enter Account No:<input type="number" name="accNo"
placeholder="Enter Account No" >
<br/>
Enter Account Type:<input type="text" name="accType" 
placeholder="Enter Account Type" >
<br/>
Enter Balance:<input type="number" name="balance" placeholder="Enter Balance">
<br/>
<br/>
<br/>
<!-- <body background ="assets/images/citibank.jpg" width="100%" height="100%">
</body>-->
<button type="submit" class="b">Submit</button>
</form>
</body>
</html>