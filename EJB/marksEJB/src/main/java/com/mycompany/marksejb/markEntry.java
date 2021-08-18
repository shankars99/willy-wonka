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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shankar
 */
@Stateless
public class markEntry implements markEntryRemote{
    Connection conn;
    
    @Override
    public void enterMarks(String name, int[] marks) {
        conn = getConnection();

        String st = "INSERT INTO marks VALUES ('" + name + "','"
                                + marks[0] + "','" + marks[1] + "','" + marks[2] 
                                + "','" + marks[3] +"')";
        
            
        try {
            String url = "jdbc:mysql://localhost:3306/test";
            
            String username = "root";
            String password = "";
            PreparedStatement pstmt = null;
            try {
                
                Class.forName("com.mysql.jdbc.Driver");
                
                conn = DriverManager.getConnection(url, username, password);
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(markEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                pstmt = conn
                        .prepareStatement(st);
            } catch (SQLException ex) {
                Logger.getLogger(markEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                int rs = pstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(markEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(markEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Connection getConnection() {

        try {
            String url = "jdbc:mysql://localhost/test";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
