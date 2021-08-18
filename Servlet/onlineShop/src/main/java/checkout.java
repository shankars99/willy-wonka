/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class checkout extends HttpServlet {
    private Connection conn;

        public Connection calcPrice(HttpServletRequest request, HttpServletResponse response, Connection conn, int item_count)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM store";
            ResultSet rs = stmt.executeQuery(sql);
            int ctr = 0;
            int total_cost = 0;
            out.println("<form action='/onlineShop/payment' method=\"get\">");
            out.println("<table border> <tr> <th>Item Code</th> <th>Item Name</th> <th>Item Quantity</th> <th>Item Price</th><th>Item Total Price</th></tr>");
            while (rs.next()) {
                String code = rs.getString("item_code");
                String name = rs.getString("item_name");
                String num = rs.getString("item_num");
                int cost = rs.getInt("item_cost");
            
                int quant = 2;//Integer.parseInt(request.getParameter("item"+ctr));
                if(quant==0)
                    break;
                total_cost += cost * quant;
                out.println("<tr> <td>" + code + "</td><td>" + name + "</td><td>" + quant + "</td><td>" + cost + "</td><td>" + (cost * quant) + "</td></tr>");
                ctr++;
            }
            out.println("</table>");
            
            out.println("Total cost = " + total_cost);
            out.println("<br><input type='submit' value= 'Pay'> </form>");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in select all");
        }
        return conn;
    }
    
    public Connection getConnection(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        try {
            String url = "jdbc:mysql://localhost/test";
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
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        
        int item_count = Integer.parseInt(req.getParameter("counter"));
        try {
            getConnection(res);
            calcPrice(req, res, conn, item_count);
        } finally {
        }
        
        
    }
}
