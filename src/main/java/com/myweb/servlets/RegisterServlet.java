package com.myweb.servlets;

import com.myweb.model.User;
import com.myweb.util.readwriteUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate inputs
        if (username == null || email == null || password == null || confirmPassword == null ||
                username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        ArrayList<User> userList = readwriteUser.readFileUser();

        // Check if email is already registered
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                request.setAttribute("errorMessage", "Email is already registered.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
        }

        // Generate new ID
        int newId = userList.size() + 1;

        // Create new user and save
        User newUser = new User(newId, username, email, password, "User");
        if (readwriteUser.writeUser(newUser)) {
            request.setAttribute("successMessage", "Registration successful! Please login.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "An error occurred during registration.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
