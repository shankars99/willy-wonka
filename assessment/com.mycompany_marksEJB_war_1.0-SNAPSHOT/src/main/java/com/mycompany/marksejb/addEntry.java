/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.marksejb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shankar
 */
public class addEntry extends HttpServlet {

    private Connection conn;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            int age = Integer.parseInt(request.getParameter("age"));


            recordEntry obj = new recordEntry();
            int val = obj.checkUser(name, email);
            if( obj.checkUser(name, email) > 0){
                obj.enterUser(name, email, age);
                out.println("Insertion successful");
            
            }else{
                out.println("Account exists");
            
            }
                out.println("val = " + val);            
            out.println("<meta http-equiv=\"Refresh\" content=\"2.5; url='/marksEJB-1.0-SNAPSHOT/'\" />");
            conn.close();
        }
    }

    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(addEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
