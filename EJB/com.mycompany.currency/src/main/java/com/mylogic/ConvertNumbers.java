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
 * Servlet implementation class ConvertNumbers
 */
@WebServlet("/ConvertNumbers")
public class ConvertNumbers extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConvertNumbers() {
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
        String op = request.getParameter("op");
        double result = 0.0;

        Convert obj = new Convert();

        if ("USD".equals(op)) {
            result = obj.toUSD(a);
        } else if ("EUR".equals(op)) {
            result = obj.toEUR(a);
        } else{
            result = obj.toYEN(a);
        }
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<h1>Converted</h1>");
        out.print("The result is : <b>" + result + "</b>");
    }

}
