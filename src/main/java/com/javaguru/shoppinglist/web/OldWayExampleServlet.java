package com.javaguru.shoppinglist.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OldWayExampleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String param1 = request.getParameter("param1");

        // Set response content type
        response.setContentType("text/html");

        // Prepare output html
        PrintWriter out = response.getWriter();
        out.println("<h1>" + "Hello WWW world from Java!" + "</h1>");
        out.println("<h1>" + "Param 1 = " + param1 + "</h1>");
    }
}
