package com.mycompany.onlineshop;

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
public class homePage extends HttpServlet {
    private Connection conn;

    public Connection listItems(HttpServletRequest request, HttpServletResponse response, Connection conn)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Bank";
            ResultSet rs = stmt.executeQuery(sql);

            out.println("<table border> <tr><th>Item Code</th> <th>Item Name</th> <th>Item Quantity</th> <th>Item Cost</th> </tr>");
            while (rs.next()) {
                String code = rs.getString("name");
                String name = rs.getString("num");
                String num = rs.getString("password");
                String cost = rs.getString("amount");

                out.println("<tr> <td>" + code + "</td><td>" + name + "</td><td>" + num + "</td><td>" + cost + "Rs</td></tr>");
            }
            out.println("</table>");
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
