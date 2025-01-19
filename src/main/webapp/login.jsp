<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Vintage.co</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">

</head>
<body>
<div class="login-container">
    <h1>Login</h1>

    <!-- Login Form -->
    <form action="login" method="post" class="login-form">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" placeholder="Enter your email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="Enter your password" required>

        <button type="submit" class="login-button">Login</button>

    </form>

    <!-- Display error message -->
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
    <p style="color: red;"><%= errorMessage %></p>
    <%
        }
    %>

    <p style="text-align: center; margin-top: 10px;">
        Don't have an account? <a href="register.jsp">Sign Up</a>
    </p>
</div>
</body>
</html>
