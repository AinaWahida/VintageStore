<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myweb.model.Cart" %>
<%@ page import="com.myweb.model.Product" %>
<%
  Cart cart = (Cart) request.getAttribute("cart");
  Double totalPrice = (Double) request.getAttribute("totalPrice");
  Boolean cartEmpty = (Boolean) request.getAttribute("cartEmpty");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Your Cart</title>
  <link rel="stylesheet" href="css/cart.css">
</head>
<body>
<h1>Your Cart</h1>
<%
  if (cartEmpty != null && cartEmpty) {
%>
<p>Your cart is empty.</p>
<a href="index.jsp">Continue Shopping</a>
<%
} else {
%>
<table>
  <tr>
    <th>Product</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Action</th>
  </tr>
  <%
    for (Product product : cart.getProducts()) {
  %>
  <tr>
    <td><%= product.getName() %></td>
    <td>$<%= String.format("%.2f", product.getPrice()) %></td>
    <td>1</td>
    <td><a href="cart?remove=<%= product.getId() %>">Remove</a></td>
  </tr>
  <%
    }
  %>
</table>
<p>Total Items: <%= cart.getTotalItems() %></p>
<p>Total Price: $<%= String.format("%.2f", totalPrice) %></p>
<p>
<a href="checkout">Proceed to Checkout</a>
<a href="index.jsp">Continue Shopping</a>
</p>
<%
  }
%>
</body>
</html>
