package com.myweb.servlets;

import com.myweb.model.Cart;
import com.myweb.model.Product;
import com.myweb.util.readwriteProduct;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the current session (do not create if it doesn't exist)
        HttpSession session = request.getSession(false);

        // Check if user is logged in
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get the product ID from the request parameter
        int productId = Integer.parseInt(request.getParameter("productId"));

        // Load the list of products
        ArrayList<Product> productList = readwriteProduct.readFile();

        // Find the product by ID
        Product selectedProduct = null;
        for (Product product : productList) {
            if (product.getId() == productId) {
                selectedProduct = product;
                break;
            }
        }

        if (selectedProduct != null) {
            // Get the cart from the session (if it exists)
            Cart cart = (Cart) session.getAttribute("cart");

            // If cart does not exist, create a new one
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }

            // Add the selected product to the cart
            cart.addProduct(selectedProduct);

            // Update the session with the updated cart
            session.setAttribute("cart", cart);

            // Optionally: Update the cart count
            session.setAttribute("cartCount", cart.getTotalItems());

            // Redirect to cart page to show updated cart
            response.sendRedirect("cart");
        } else {
            // If product not found, redirect to the homepage (or handle as error)
            response.sendRedirect("index.jsp");
        }
    }
}
