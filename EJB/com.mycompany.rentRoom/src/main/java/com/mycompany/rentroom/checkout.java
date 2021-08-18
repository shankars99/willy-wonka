/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rentroom;

import javax.ejb.Stateful;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Vector;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author shankar
 */
public class checkout extends HttpServlet {

    Bill obj = new Bill();

    public void listItems(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        PrintWriter out = response.getWriter();
        String[] temp = request.getParameter("hide").split(",");
        String inDate = request.getParameter("checkin");
        String outDate = request.getParameter("checkout");
        
        String price = temp[0];
        String name = temp[1];
        Vector<Room> rooms = obj.addItem(name, price, inDate, outDate);
    
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(inDate);  
        Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(outDate);  
        long time_difference = date2.getTime() - date1.getTime();  
        long days_difference = TimeUnit.MILLISECONDS.toDays(time_difference) % 365; 
        
        int bill = 0;
        out.println("<table border = 1>"
                + "<tr> <td>Name</td> <td>Quantity</td><td>Cost</td><td>In</td><td>Out</td></tr>");
        for (Room room : rooms) {
            int total = room.getQuant() * room.getCost();
            out.println("<tr><td>" + room.getName() + "</td><td>" + room.getQuant() + "</td><td>"
                    + total + "Rs.</td><td>"+room.getEntryDate()+"</td><td>"+room.getExitDate()+"</td></tr>");
            bill += total;
        }
        out.println("</table>");
        out.println("Number of days = " + days_difference + "<br>");
        out.println("Bill = " + bill*days_difference + " <br> <a href = 'payment'> <input type = 'button' value = 'Click to pay'> </a>");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(checkout.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
