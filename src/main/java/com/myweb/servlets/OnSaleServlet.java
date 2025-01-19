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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/on-sale")
public class OnSaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read all products from the CSV file
        ArrayList<Product> allProducts = readwriteProduct.readFile();

        // Filter products by the "Furniture" category
        List<Product> saleProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if ("yes".equalsIgnoreCase(product.getSale().trim())) {
                saleProducts.add(product);
            }
        }

        // Set content type to HTML
        response.setContentType("text/html");

        // Create a PrintWriter to write the response
        PrintWriter out = response.getWriter();

        // Start the HTML content
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>On Sale Products</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/category.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"category-page\">");
        out.println("<h2>On Sales Products</h2>");
        out.println("<div class=\"product-cards\">");

        // Check if the list is not empty and loop through each product
        if (saleProducts != null && !saleProducts.isEmpty()) {
            for (Product product : saleProducts) {
                out.println("<div class=\"product-card\">");
                out.println("<img src=\"images/" + product.getImage() + "\" alt=\"" + product.getName() + "\" class=\"product-image\">");
                out.println("<h3>" + product.getName() + "</h3>");
                out.println("<p>" + product.getDescription() + "</p>");
                out.println("<p class=\"price\">" + product.getPrice() + "</p>");
                out.println("<a href=\"product-detail?id=" + product.getId() + "\" class=\"view-details\">View Details</a>");
                out.println("</div>");
            }
        } else {
            out.println("<p>No products found.</p>");
        }

        // Close the HTML structure
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
