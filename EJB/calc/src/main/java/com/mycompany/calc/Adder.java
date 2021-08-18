/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calc;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shankar
 */
public class Adder extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();

        double val1 = Double.parseDouble(request.getParameter("val1"));
        double val2 = Double.parseDouble(request.getParameter("val2"));
        String op = request.getParameter("op");
        double res = 0.0;

        if ("add".equals(op)) {
            res = val1 + val2;
        } else if ("sub".equals(op)) {
            res = val1 - val2;
        } else if ("mul".equals(op)) {
            res = val1 * val2;
        } else {
            res = 1.0 * val1 / val2;
        }

        pw.println("Res = " + res);
    }
}

