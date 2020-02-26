<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html5>
<html>
<head>
<h2>BankApp</h2>
<h3>Registration</h3>
<form action="next.jsp">
Enter customer name:<input type="text" name="name" maxlength="16"
placeholder="Enter customer Name" required>
<br/>
 E-mail: <input type="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
placeholder="Enter Email" required> 
<br/>
  Password: <input type="password" name="pw" pattern=".{6,}" title="Six or more characters">

<br/>
Enter Date of birth:
<input type="date" name="dob"
min="2000-01-01" max="2020-02-03"
/>
<br/>
Enter no of tickets:
<input type="number" name="no_of_tickets"
min="1" max="5"/>
<br/>
<button type="submit">Submit</button>
</form>

<body>

</body>
</html>