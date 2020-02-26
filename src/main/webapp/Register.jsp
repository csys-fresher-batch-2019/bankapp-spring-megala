<!DOCTYPE html5>
<html>
   <head>
      <br/>
    
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
   </head>
   <style type="text/css">
   
      h3{
      font-size: 30;
      font-style:italic;
      }
      body{
      background-size:60em;
      background-attachment: fixed;
      }
      .a{
    
      font-family:cursive;
      font-size:24px;
      color:#d7291f;
      }
      .b{
      font-size:14;
      font-style: italic;
      font-family: cursive;
      color:purple;
      }
      .c{
      font-size:22px;
      font-style: italic;
      font-family: cursive;
      color:red;
      }
      input[type="text"] {
      font-family:cursive;
      font-size: 14px;
      color:blue;
      }
      input[type="password"] {
      font-family:cursive;
      font-size: 14px;
      color:blue;
      }
      input[type="number"] {
      font-family:cursive;
      font-size: 14px;
      color:blue;
      }
   </style>
   <form action="RegisterServlet" class="a" method="post">
      <body class="a" >
      <body background="assets/images/regi.jpg">
         <%String errorMessage=(String)request.getAttribute("errormessage");
            if(errorMessage!=null)
            {%>
         <font color="red" style="font-size: 18px" ><%=errorMessage %></font>
         <%} %>
         <br/>
         Enter Name:<input type="text" name="name"
            placeholder="Enter Customer Name" required autofocus>
         <br/>
         <br/>
         Enter Address:<input type="text" name="street" 
            placeholder="Enter Customer Street" required>
         <br/>
         <br/>
         Enter  City:<input type="text" name="city" 
            placeholder="Enter Customer City" required>
         <br/>
         <br/>
         Enter  Account Type:<input type="text" name="accType" 
            placeholder="Enter Account Type" required>(saving,salaried)
         <br/>
         <br/>
         Enter mobile Number:<input type="number" name="mbleNo" maxlength="10"
            placeholder="Enter mobile Number" required>
         <br/>
         <br/>
         Enter Email:<input type="text" name="email" 
            placeholder="Enter Email id" required>
         <br/>
         <br/>
         Enter password:<input type="password" id="psw" name="pass" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required>
         <br/>
         <br/>
         <button type="submit" class="btn btn-primary" data-toggle="button" aria-pressed="false">
         Submit
         </button>
         <br/>
         <br/>
         <a href="BankLogin.jsp" class="c">Back</a>
         <br/>
         <br/>
      </body>
   </form>
   </body>
</html>