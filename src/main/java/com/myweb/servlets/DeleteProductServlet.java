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
import java.util.List;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        int id = Integer.parseInt(request.getParameter("id"));

        List<Product> productList = readwriteProduct.readFile();
        productList.removeIf(product -> product.getId() == id);

        readwriteProduct.writeProduct(productList);

        response.sendRedirect("admin-dashboard");
    }

}

