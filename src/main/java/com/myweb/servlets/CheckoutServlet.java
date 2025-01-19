package com.myweb.servlets;

import com.myweb.model.Cart;
import com.myweb.model.Product;
import com.myweb.model.User;
import com.myweb.util.readwritePurchaseHistory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the current session (do not create if it doesn't exist)
        HttpSession session = request.getSession(false);
        response.setContentType("text/html;charset=UTF-8");

        // Get the cart from the session (if it exists)
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getTotalItems() == 0) {
            response.sendRedirect("cart"); // Redirect if the cart is empty
            return;
        }

        // Calculate the total price of the cart
        double totalPrice = 0;
        for (Product product : cart.getProducts()) {
            totalPrice += product.getPrice();
        }

        // Get the PrintWriter to write the response
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><title>Checkout</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/checkout.css\"></head>");

            out.println("<body>");
            out.println("<h1>Checkout</h1>");

            // Checkout form
            out.println("<form action='checkout' method='POST'>");

            // Shipping address section
            out.println("<h3>Shipping Address</h3>");
            out.println("<textarea name='address' rows='4' cols='50' required></textarea><br><br>");

            // Payment type section
            out.println("<h3>Payment Type</h3>");
            out.println("<select name='paymentType' required>");
            out.println("<option value='creditCard'>Credit Card</option>");
            out.println("<option value='paypal'>PayPal</option>");
            out.println("<option value='bankTransfer'>Bank Transfer</option>");
            out.println("</select><br><br>");

            // Cart Summary table
            out.println("<h3>Cart Summary</h3>");
            out.println("<table border='1'>");
            out.println("<tr><th>Product</th><th>Price</th><th>Quantity</th><th>Total</th></tr>");

            // Loop through cart products
            for (Product product : cart.getProducts()) {
                out.println("<tr>");
                out.println("<td>" + product.getName() + "</td>");
                out.println("<td>$" + product.getPrice() + "</td>");
                out.println("<td>1</td>"); // Assuming one item per product for simplicity
                out.println("<td>$" + product.getPrice() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            // Total price display
            out.println("<p>Total Price: $" + totalPrice + "</p>");

            // Submit button to complete the purchase
            out.println("<input type='submit' value='Complete Purchase'>");
            out.println("</form>");

            // Link to go back to cart
            out.println("<a href='cart'>Go Back to Cart</a>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the user input from the checkout form
        String address = request.getParameter("address");
        String paymentType = request.getParameter("paymentType");

        HttpSession session = request.getSession(false);
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getTotalItems() == 0) {
            response.sendRedirect("cart"); // Redirect if cart is empty
            return;
        }

        // Save the order (for simplicity, you can add it to a user's purchase history)
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            // Save the purchase history to CSV
            int userId = user.getId();
            if (readwritePurchaseHistory.savePurchaseToCSV(userId, cart, address, paymentType)) {
                System.out.println("Purchase saved successfully to CSV.");
            } else {
                System.err.println("Failed to save purchase to CSV.");
            }

            session.setAttribute("loggedInUser", user);
        }

        // Clear the cart after the purchase is complete
        session.setAttribute("cart", null);
        session.setAttribute("cartCount", 0);

        // Redirect to a confirmation page
        response.sendRedirect("checkoutConfirmation.jsp"); // Redirect to profile page to see the history
    }
}
