<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header with Dropdown</title>
    <!-- Link to CSS -->
    <link rel="stylesheet" type="text/css" href="css/header.css">
</head>
<body>
<header class="header">
    <!-- Logo -->
    <div class="logo">
        <span class="site-name">Timeless.CO</span>
    </div>

    <!-- Navigation Links -->
    <nav class="nav">
        <ul>
            <li><a href="on-sale">On Sale</a></li>
            <li><a href="new-arrivals">New Arrival</a></li>
        </ul>
    </nav>

    <!-- Search Box -->
    <div class="searchBox">
    <form action="search" method="post">
        <input type="text" name="searchTerm" placeholder="Search for items...">
        <button type="submit">Search</button>
    </form>
    </div>

    <!-- Profile and Cart -->
    <div class="user-actions">
        <a href="cart" class="cart">
            <i class="cart-icon">🛒</i>
            <span class="cart-count">
                <%= session.getAttribute("cartCount") != null ? session.getAttribute("cartCount") : 0 %>
            </span>
        </a>
        <a href="profile" class="profile">
            <i class="profile-icon">👤</i>
            Profile
        </a>
    </div>
</header>
</body>
</html>
