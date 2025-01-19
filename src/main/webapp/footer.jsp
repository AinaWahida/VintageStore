<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Footer</title>
  <!-- Link to CSS -->
  <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/footer.css">
</head>
<body>

<!-- Footer -->
<footer class="footer">
  <div class="footer-bottom">
    <p>&copy; <%= new java.util.Date().getYear() + 1900 %> Timeless Vintage E-commerce. All Rights Reserved.</p>
  </div>
</footer>

</body>
</html>
