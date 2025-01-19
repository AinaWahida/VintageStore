package com.myweb.servlets;

import com.myweb.model.User;
import com.myweb.util.readwriteUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ArrayList<User> userList = readwriteUser.readFileUser();

        User authenticatedUser = null;
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                authenticatedUser = user;
                break;
            }
        }

        if (authenticatedUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", authenticatedUser);

            // Check if the user is an admin
            if ("admin".equalsIgnoreCase(authenticatedUser.getRole())) {
                // Redirect to the Admin Dashboard if the user is an Admin
                response.sendRedirect("admin-dashboard");
            } else {
                // Redirect to the regular home page for normal users
                response.sendRedirect("index.jsp");
            }

        } else {
            request.setAttribute("errorMessage", "Invalid email or password. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
