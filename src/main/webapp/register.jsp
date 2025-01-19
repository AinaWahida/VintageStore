<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Vintage.co</title>
    <link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>
<div class="register-container">
    <h1>Register</h1>
    <form action="register" method="post" class="register-form">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>

        <button type="submit" class="register-button">Register</button>
    </form>

    <!-- Display error or success message -->
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        String successMessage = (String) request.getAttribute("successMessage");

        if (errorMessage != null) {
    %>
    <p class="message error"><%= errorMessage %></p>
    <%
        }
        if (successMessage != null) {
    %>
    <p class="message success"><%= successMessage %></p>
    <%
        }
    %>
</div>
</body>
</html>
