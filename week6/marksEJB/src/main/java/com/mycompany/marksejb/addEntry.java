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
            int marks[] = new int[4];

            for (int i = 1; i <= 4; i++) {
                marks[i - 1] = Integer.parseInt(request.getParameter("sub" + i));
            }
            markEntry obj = new markEntry();
            obj.enterMarks(name, marks);
            out.println("Insertion successful");
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
