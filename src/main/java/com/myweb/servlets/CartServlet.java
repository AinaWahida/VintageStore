package com.myweb.servlets;

import com.myweb.model.Product;
import com.myweb.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {
            // Redirect to login if user is not authenticated
            response.sendRedirect("login.jsp");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getTotalItems() == 0) {
            request.setAttribute("cartEmpty", true);
        } else {
            // Handle product removal if "remove" parameter is provided
            String removeProductIdStr = request.getParameter("remove");
            if (removeProductIdStr != null) {
                try {
                    int removeProductId = Integer.parseInt(removeProductIdStr);
                    cart.removeProductById(removeProductId);
                    session.setAttribute("cart", cart);
                    session.setAttribute("cartCount", cart.getTotalItems());
                    response.sendRedirect("cart");
                    return;
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Invalid product ID.");
                }
            }

            request.setAttribute("cart", cart);
            request.setAttribute("totalPrice", calculateTotalPrice(cart));
        }

        // Forward to JSP for rendering
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    private double calculateTotalPrice(Cart cart) {
        return cart.getProducts().stream().mapToDouble(Product::getPrice).sum();
    }
}
