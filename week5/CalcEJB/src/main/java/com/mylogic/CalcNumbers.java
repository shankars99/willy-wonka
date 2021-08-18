/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mylogic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Servlet implementation class CalcNumbers
 */
@WebServlet("/CalcNumbers")
public class CalcNumbers extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcNumbers() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        double a = Double.parseDouble(request.getParameter("val_1"));
        double b = Double.parseDouble(request.getParameter("val_2"));
        String op = request.getParameter("op");
        double result = 0.0;

        Calc obj = new Calc();

        if ("add".equals(op)) {
            result = obj.add(a, b);
        } else if ("sub".equals(op)) {
            result = obj.sub(a, b);
        } else if ("mul".equals(op)) {
            result = obj.mul(a, b);
        } else {
            result = obj.div(a, b);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<h1>Result</h1>");
        out.print("The result is : <b>" + result + "</b>");
    }

}
