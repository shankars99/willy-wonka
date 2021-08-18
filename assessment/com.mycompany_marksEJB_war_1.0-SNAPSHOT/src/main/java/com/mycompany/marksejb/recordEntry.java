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
import java.sql.ResultSet;
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
public class recordEntry implements recordEntryRemote{
    Connection conn;
    
    @Override
    public void enterUser(String name, String email,int age) {
        String st = "INSERT INTO art VALUES ('" + name + "','"
                                + email + "','" + age + "')";
        
            
        try {
            String url = "jdbc:mysql://localhost:3306/temp";
            
            String username = "root";
            String password = "";
            PreparedStatement pstmt = null;
            try {
                
                Class.forName("com.mysql.jdbc.Driver");
                
                conn = DriverManager.getConnection(url, username, password);
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(recordEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                pstmt = conn
                        .prepareStatement(st);
            } catch (SQLException ex) {
                Logger.getLogger(recordEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                int rs = pstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(recordEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(recordEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public int checkUser(String name, String email) {
        boolean flag = false;
        String st = "SELECT COUNT(*) as total FROM art WHERE name = '" + name +"')";
        
            
        try {
            String url = "jdbc:mysql://localhost:3306/temp";
            
            String username = "root";
            String password = "";
            PreparedStatement pstmt = null;
            try {
                
                Class.forName("com.mysql.jdbc.Driver");
                
                conn = DriverManager.getConnection(url, username, password);
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(recordEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                pstmt = conn
                        .prepareStatement(st);
            } catch (SQLException ex) {
                Logger.getLogger(recordEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
               ResultSet rs = pstmt.executeQuery();
               rs.next();

               if( rs.getInt("total") == 0){
                   
               }
               return rs.getInt("total");
               
            } catch (SQLException ex) {
                Logger.getLogger(recordEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(recordEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 1;
    }
    
}
