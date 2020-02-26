<!DOCTYPE html>
<html lang="en">
<head>
  <title>citiBank</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  <style>
  .fakeimg {
    height: 200px;
    background: #6e7f96;
  }
  body{
  background-attachment: fixed;
  background-size: cover;
  }
  .b{
  font-size:22px;
  font-family: cursive;
  }
  </style>
</head>
<% Long user=(Long)session.getAttribute("card"); %>
<body class="b">
<body background="assets/images/credit.jpg">
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="index1.jsp">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
	<% if(user!=null){%>  
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="creditcardpayment.jsp">CreditCard payment</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="transactionHistory.jsp">Transaction History</a>
      </li>    
      <li>
      </li>
      <li>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="checkBalance.jsp">check Balance</a>
      </li> 
    </ul>
	<%}%>
    <ul class="navbar-nav ml-auto">
     
      <li class="nav-item">
        <a class="nav-link" href="index1.jsp">logout</a>
      </li> 
    </ul>
  </div>  
</nav>

<div class="container" style="margin-top:30px">
  <div class="row">
    <div class="col-sm-4">
     
 </div>
 </div>
 </div>
     
</body>
</html>
