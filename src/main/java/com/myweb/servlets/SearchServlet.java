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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the search term entered by the user
        String searchTerm = request.getParameter("searchTerm");

        // Redirect to the category page if no search term is provided
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            System.out.println("No product found.");
        }

        // Read all products from the CSV file (or database)
        ArrayList<Product> allProducts = readwriteProduct.readFile();

        // Create a list to store matching products
        List<Product> searchResults = new ArrayList<>();

        // Iterate over the products and check if the name or description contains the search term
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    product.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchResults.add(product);
            }
        }

        // Set the content type for the response
        response.setContentType("text/html;charset=UTF-8");

        // Create a PrintWriter to write the response
        PrintWriter out = response.getWriter();

        // Start the HTML content
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Search Results</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/category.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"category-page\">");
        out.println("<h2>Search Results for: " + searchTerm + "</h2>");

        out.println("<div class=\"product-cards\">");

        // Check if there are any search results and display them
        if (searchResults != null && !searchResults.isEmpty()) {
            for (Product product : searchResults) {
                out.println("<div class=\"product-card\">");
                out.println("<img src=\"images/" + product.getImage() + "\" alt=\"" + product.getName() + "\" class=\"product-image\">");
                out.println("<h3>" + product.getName() + "</h3>");
                out.println("<p>" + product.getDescription() + "</p>");
                out.println("<p class=\"price\">" + product.getPrice() + "</p>");
                out.println("<a href=\"product-detail?id=" + product.getId() + "\" class=\"view-details\">View Details</a>");
                out.println("</div>");
            }
        } else {
            // If no products are found, display a message
            out.println("<p>No products found matching your search.</p>");
        }

        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}

