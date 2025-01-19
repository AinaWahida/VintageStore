package com.myweb.servlets;

import com.myweb.model.Product;
import com.myweb.util.readwriteProduct;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Display the add product form
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Add Product</title>");
        out.println("<link rel='stylesheet' type='text/css' href='css/addProduct.css'>");
        out.println("</head>");

        out.println("<body>");
        out.println("<h1>Add Product</h1>");
        out.println("<form action='addProduct' method='post'>");

        out.println("<label for='name'>Name:</label>");
        out.println("<input type='text' id='name' name='name' required><br>");

        out.println("<label for='price'>Price:</label>");
        out.println("<input type='number' id='price' name='price' step='0.01' required><br>");

        out.println("<label for='description'>Description:</label>");
        out.println("<textarea id='description' name='description' required></textarea><br>");

        out.println("<label for='img'>Image URL:</label>");
        out.println("<input type='text' id='img' name='img' required><br>");

        out.println("<label for='category'>Category:</label>");
        out.println("<input type='text' id='category' name='category' required><br>");

        out.println("<label for='onSale'>On Sale(Yes/No):</label>");
        out.println("<input type='text' id='onSale' name='onSale' required<br>");

        out.println("<label for='newArrival'>New Arrival(Yes/No):</label>");
        out.println("<input type='text' id='newArrival' name='newArrival' required<br>");

        out.println("<button type='submit'>Add Product</button>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the form data
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String img = request.getParameter("img");
        String category = request.getParameter("category");
        String onSale = request.getParameter("onSale");
        String newArrival = request.getParameter("newArrival");

        // Read existing products
        List<Product> productList = readwriteProduct.readFile();
        int id = productList.size() + 1;

        // Create a new product object
        Product newProduct = new Product(id, name, price, description, img, category, onSale, newArrival);
        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setDescription(description);
        newProduct.setImg(img);
        newProduct.setCategory(category);
        newProduct.setOnSale(onSale);
        newProduct.setNewArrival(newArrival);

        // Add the new product to the list
        productList.add(newProduct);

        // Save the updated product list
        readwriteProduct.writeProduct(productList);

        // Redirect to the admin dashboard or another page
        response.sendRedirect("admin-dashboard");
    }
}
