package com.myweb.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Example: Pass data to the JSP (optional)
        request.setAttribute("siteName", "Timeless.CO");
        request.getRequestDispatcher("/webapp/home.jsp").forward(request, response);
    }
}
