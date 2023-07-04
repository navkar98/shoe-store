<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shoe Store</title>
</head>
<body>
	<h2>Login</h2>
	<div>
	    <form action="Login" method="post">
	        <label for="email">Email:</label>
	        <input type="email" id="email" name="email" required>
	        <br>
	        <label for="password">Password:</label>
	        <input type="password" id="password" name="password" required>
	        <br>
	        <button type="submit">Login</button>
	        
	    </form>
	    <button onclick="redirectToRegisterPage()">Register</button>
    </div>
</body>
<script>
	// Define the function to redirect to the new page
  function redirectToRegisterPage() {
    window.location.href = "register.jsp";
  }
</script>
</html>