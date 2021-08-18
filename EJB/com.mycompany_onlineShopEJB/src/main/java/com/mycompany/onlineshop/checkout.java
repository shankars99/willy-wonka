/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlineshop;

import javax.ejb.Stateful;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author shankar
 */
public class checkout extends HttpServlet {

    Bill obj = new Bill();

    public void listItems(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String[] temp = request.getParameter("hide").split(",");
        String price = temp[0];
        String name = temp[1];
        Vector<Item> cart = obj.addItem(name, price);

        int bill = 0;
        out.println("<table border = 1>"
                + "<tr> <td>Name</td> <td>Quantity</td><td>Cost</td></tr>");
        for (Item item : cart) {
            int total = item.getQuant() * item.getCost();
            out.println("<tr><td>" + item.getName() + "</td><td>" + item.getQuant() + "</td><td>"
                    + total + "Rs.</td></tr>");
            bill += total;
        }
        out.println("</table>");
        out.println("Bill = " + bill + " <br> <a href = 'payment'> <input type = 'button' value = 'Click to pay'> </a>");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {
            listItems(request, response);
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }
}
