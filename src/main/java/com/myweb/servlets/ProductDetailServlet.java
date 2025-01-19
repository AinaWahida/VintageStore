package com.myweb.servlets;

import com.myweb.model.Product;
import com.myweb.util.readwriteProduct;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/product-detail")
public class ProductDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the product ID from the query parameter
        String productIdParam = request.getParameter("id");

        // Set the content type to HTML
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Check if the product ID is valid
            if (productIdParam == null || productIdParam.isEmpty()) {
                out.println("<html><body><h3>Product ID is required.</h3></body></html>");
                return;
            }

            try {
                int productId = Integer.parseInt(productIdParam);  // Convert string to int
                Product product = readwriteProduct.getProductById(productId); // Fetch the product by ID

                // If the product is found, display its details
                if (product != null) {
                    out.println("<html><head><title>Product Details</title>");
                    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/productDetails.css\"></head>");
                    out.println("<body>");
                    out.println("<h1>Product details</h3>");
                    out.println("<h2>" + product.getName() + "</h1>");
                    out.println("<img src=\"images/" + product.getImage() + "\" alt=\"" + product.getName() + "\" class=\"product-image\" width=\"300\">");
                    out.println("<p><strong>Description:</strong> " + product.getDescription() + "</p>");
                    out.println("<p><strong>Price:</strong> $" + product.getPrice() + "</p>");

                    // Check if the user is logged in
                    HttpSession session = request.getSession(false); // False to avoid creating a new session
                    if (session != null && session.getAttribute("loggedInUser") != null) {
                        // User is logged in, allow them to add to cart
                        out.println("<form action=\"add-to-cart\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"action\" value=\"add\">");
                        out.println("<input type=\"hidden\" name=\"productId\" value=\"" + product.getId() + "\">");
                        out.println("<input type=\"submit\" value=\"Add to Cart\" class=\"add-to-cart\">");
                        out.println("</form>");
                    } else {
                        // User is not logged in, prompt them to login
                        out.println("<p><a href=\"login.jsp\">Add to cart.</a></p>");
                    }
                    out.println("</body></html>");
                } else {
                    // If no product is found with the provided ID, display an error
                    out.println("<html><body><h3>Product not found.</h3></body></html>");
                }
            } catch (NumberFormatException e) {
                // If the product ID is not a valid number, send a 400 error
                out.println("<html><body><h3>Invalid Product ID.</h3></body></html>");
            }
        }
    }
}
