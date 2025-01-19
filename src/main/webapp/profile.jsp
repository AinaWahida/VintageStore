<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myweb.model.User" %>
<%@ page import="com.myweb.model.PurchaseHistory" %>
<%@ page import="com.myweb.util.readwritePurchaseHistory" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page session="true" %>
<%
    // Get the logged-in user from the session
    User user = (User) session.getAttribute("loggedInUser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Fetch all purchase history
    List<PurchaseHistory> allHistory = readwritePurchaseHistory.readFileHistory();

    // Filter purchase history for the logged-in user
    List<PurchaseHistory> userHistory = allHistory.stream()
            .filter(purchase -> purchase.getUserId()==(user.getId()))
            .collect(Collectors.toList());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile - Vintage.co</title>
    <link rel="stylesheet" type="text/css" href="css/profile.css">
</head>
<body>
<h1>Welcome, <%= user.getUsername() %>!</h1>
<p>User Name: <%= user.getUsername() %></p>
<p>Email: <%= user.getEmail() %></p>
<p>Password: <%= user.getPassword()%></p>

<h2>Purchase History</h2>
<%
    if (userHistory.isEmpty()) {
%>
<p>No purchase history available.</p>
<%
} else {
%>
<table border="1">
    <tr>
        <th>Product Name</th>
        <th>Address</th>
        <th>Payment Type</th>
        <th>Price</th>
        <th>Date</th>
    </tr>
    <%
        for (PurchaseHistory purchase : userHistory) {
    %>
    <tr>
        <td><%= purchase.getProduct() %></td>
        <td><%= purchase.getAddress() %></td>
        <td><%= purchase.getPaymentType() %></td>
        <td>$<%= purchase.getPrice() %></td>
        <td><%= purchase.getDate() %></td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>

<p>
   <a href="index.jsp">Home</a>
   <a href="logout">Logout</a> 
</p>
</body>
</html>
