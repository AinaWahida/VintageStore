package com.myweb.servlets;

import com.myweb.model.Product;
import com.myweb.util.readwriteProduct;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/admin-dashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch all products from the database or memory
        List<Product> products = readwriteProduct.readFile();

        // Set the response content type
        response.setContentType("text/html;charset=UTF-8");

        // Get the PrintWriter to write the response
        PrintWriter out = response.getWriter();

        // Start writing the HTML
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Admin Dashboard</title>");
        out.println("<link rel='stylesheet' type='text/css' href='css/admin-dashboard.css'>"); // Link to external CSS
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Admin Dashboard</h1>");
        out.println("<h2>Products</h2>");
        out.println("<p> <a href='addProduct'>Add New Product</a>");
        out.println("<a href='login.jsp'>Logout</a> </p>");
        out.println("<br><br>");

        // Check if there are products
        if (products != null && !products.isEmpty()) {
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Image</th>");
            out.println("<th>Price</th>");
            out.println("<th>Description</th>");
            out.println("<th>Category</th>");
            out.println("<th>On Sale</th>");
            out.println("<th>New Arrival</th>");
            out.println("<th>Actions</th>");
            out.println("</tr>");

            // Loop through products and display them
            for (Product product : products) {
                out.println("<tr>");
                out.println("<td>" + product.getId() + "</td>");
                out.println("<td><img src='images/" + product.getImage() + "' alt='" + product.getName() + "' width='100'></td>");
                out.println("<td>" + product.getPrice() + "</td>");
                out.println("<td>" + product.getDescription() + "</td>");
                out.println("<td>" + product.getCategory() + "</td>");
                out.println("<td>" + product.getSale() + "</td>");
                out.println("<td>" + product.getNewArrival() + "</td>");
                out.println("<td>");
                out.println("<a href='editProduct?id=" + product.getId() + "'>Edit</a> | ");
                out.println("<a href='deleteProduct?id=" + product.getId() + "'>Delete</a>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        } else {
            out.println("<p>No products available.</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
