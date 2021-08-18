package com.mycompany.rentroom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author shankar
 */
public class purchase extends HttpServlet {
    private Connection conn;

    public Connection listItems(HttpServletRequest request, HttpServletResponse response, Connection conn)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM room_det";
            ResultSet rs = stmt.executeQuery(sql);
            int ctr = 0;
            out.println("<form action='/onlineShop/checkout' method='get'>");
            out.println("Checkin  date : <input type=\"date\" id=\"checkin\" name=\"checkin\">");
            out.println("Checkout date : <input type=\"date\" id=\"checkout\" name=\"checkout\">");
            out.println("<table border> <tr><th>Num Beds</th> <th>Room Name</th> <th>Room Quantity</th> <th>Room Cost</th> <th>Buying</th></tr>");
            while (rs.next()) {
                String bed = rs.getString("room_bed");
                String name = rs.getString("room_name");
                String num = rs.getString("room_num");
                String cost = rs.getString("room_cost");
                
                String hide = cost+","+ name;
                
                out.println("<tr> <td>" + bed + "</td><td>" + name + "</td><td>" + num + "</td><td>" + cost);
                out.println(" Rs</td><td><input type='submit' name = 'hide' value= '" +hide +"'></td></tr>");
                ctr++;
            }
            out.println("</table>");
            out.println("<input type='hidden' name = 'counter' value = '"+ctr+"'</input></form>");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in select all");
        }
        return conn;
    }

    public Connection getConnection(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        try {
            String url = "jdbc:mysql://localhost/temp";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error occurred while connecting to database");
        }
        return conn;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {
            getConnection(response);
            listItems(request, response, conn);
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
