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
import java.util.List;

@WebServlet("/editProduct")
public class EditProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        int id = Integer.parseInt(request.getParameter("id"));

        List<Product> productList = readwriteProduct.readFile();
        Product productToEdit = null;

        for (Product product : productList) {
            if (product.getId() == id) {
                productToEdit = product;
                break;
            }
        }

        if (productToEdit == null) {
            response.sendRedirect("admin-dashboard?error=ProductNotFound");
            return;
        }

        // Write the HTML form dynamically with the product data
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Edit Product</title>");
        out.println("<link rel='stylesheet' type='text/css' href='css/addProduct.css'>");
        out.println("</head>");

        out.println("<body>");
        out.println("<h1>Edit Product</h1>");
        out.println("<form action='editProduct' method='post'>");
        out.println("<input type='hidden' name='id' value='" + productToEdit.getId() + "'>");
        out.println("<label for='name'>Name:</label>");
        out.println("<input type='text' id='name' name='name' value='" + productToEdit.getName() + "' required><br>");

        out.println("<label for='price'>Price:</label>");
        out.println("<input type='number' id='price' name='price' value='" + productToEdit.getPrice() + "' step='0.01' required><br>");

        out.println("<label for='description'>Description:</label>");
        out.println("<textarea id='description' name='description' required>" + productToEdit.getDescription() + "</textarea><br>");

        out.println("<label for='img'>Image URL:</label>");
        out.println("<input type='text' id='img' name='img' value='" + productToEdit.getImage() + "' required><br>");

        out.println("<label for='category'>Category:</label>");
        out.println("<input type='text' id='category' name='category' value='" + productToEdit.getCategory() + "' required><br>");

        out.println("<label for='onSale'>On Sale:</label>");
        out.println("<input type='text' id='onSale' name='onSale'" + productToEdit.getSale() + "' required<br>");

        out.println("<label for='newArrival'>New Arrival:</label>");
        out.println("<input type='text' id='newArrival' name='newArrival'" + productToEdit.getNewArrival() + "' required<br>");

        out.println("<button type='submit'>Save Changes</button>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String img = request.getParameter("img");
        String category = request.getParameter("category");
        String onSale = request.getParameter("onSale");
        String newArrival = request.getParameter("newArrival");

        List<Product> productList = readwriteProduct.readFile();

        for (Product product : productList) {
            if (product.getId() == id) {
                product.setName(name);
                product.setPrice(price);
                product.setDescription(description);
                product.setImg(img);
                product.setCategory(category);
                product.setOnSale(onSale);
                product.setNewArrival(newArrival);
                break;
            }
        }

        readwriteProduct.writeProduct(productList);

        response.sendRedirect("admin-dashboard");
    }
}
