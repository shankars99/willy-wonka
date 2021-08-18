/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.question1;

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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leafl
 */
public class checking extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           /* out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet checking</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checking at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
           
           String name = request.getParameter("uname");
           String email = request.getParameter("email");
           String pwd = request.getParameter("pwd");
           
            Connection connect = null;
String url = "jdbc:mysql://localhost:3306/temp";
String username = "root";
String password = "";
Class.forName("com.mysql.jdbc.Driver");
connect = DriverManager.getConnection(url, username, password);

           PreparedStatement psmt = null;
           PreparedStatement ps = null;
           String s = "SELECT * FROM art";
           ps=connect.prepareStatement(s);
           ResultSet rs = ps.executeQuery();
           int flag = 0;
           while(rs.next())
           {
               String n = rs.getString("name");
               String e = rs.getString("email");
               String p = rs.getString("password");
               if(n.equals(name) && e.equals(email) && p.equals(pwd))
               {
                   flag =1;
                   break;
               }
               
           }
           if(flag==1)
               out.println("<h2>RECORD IS ALREADY PRESENT</h2>");
           else{
           String sql = "INSERT INTO art(name,email,password) VALUES(?,?,?)";
           psmt=connect.prepareStatement(sql);
            psmt.setString(1, name);
             psmt.setString(2, email);
             psmt.setString(3, pwd);
             psmt.executeUpdate();
             out.println("<h2>RECORD SUCCESSFULLY INSERTED</h2>");
              out.println("<body><table border ='2'>");
           out.println("<tr><th>NAME</th><th>E-MAIL ID</th><th>PASSWORD</th></tr>");
           ResultSet result = ps.executeQuery();
           while(result.next())
           {
               out.println("<tr><td>"+result.getString("name")+"</td><td>"+result.getString("email")+"</td><td>"+result.getString("password")+"</td></tr>");
           }
           out.println("</table></body>");
           }
           
           
          
           
           
        } finally {
            out.close();
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(checking.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(checking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(checking.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(checking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
