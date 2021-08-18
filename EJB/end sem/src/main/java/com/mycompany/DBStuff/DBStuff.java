package com.mycompany.DBStuff;

import com.mycompany.billEJB.Bill;
import com.mycompany.billEJB.Item;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBStuff implements DBStuffRemote {

    Connection conn = null;

    @Override
    public Connection getConn(String table) throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/" + table;

        String username = "root";
        String password = "";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url, username, password);

        } catch (SQLException ex) {
            System.out.println("in exec");
            System.out.println(ex.getMessage());
        }

        return conn;
    }

    @Override
    public boolean registerUser(String usr, String pwd, String email, String card) throws SQLException {
        boolean flag = false;
        String query = "INSERT INTO ACCOUNTS VALUES( '" + usr + "','" + pwd + "','" + email + "','" + card + "')";

        try {
            conn = getConn("MARKETPLACE");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBStuff.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreparedStatement pstmt = conn.prepareStatement(query);
        int rs = pstmt.executeUpdate();

        flag = rs > 0;

        conn.close();
        return flag;
    }

    public boolean checkLogin(String usr, String pwd) throws SQLException {
        boolean flag = false;
        String query = "SELECT * FROM  ACCOUNTS WHERE NAME = '" + usr + "' AND PWD = '" + pwd + "'";

        try {
            conn = getConn("MARKETPLACE");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBStuff.class.getName()).log(Level.SEVERE, null, ex);
        }

        Statement selectStmt = conn.createStatement();
        ResultSet rs = selectStmt
                .executeQuery(query);

        while (rs.next()) {
            flag = true;
        }

        conn.close();
        return flag;
    }

    @Override
    public String getElementList(String type, boolean flag) throws SQLException {
        String query = "SELECT * FROM  PRODUCTS WHERE TYPE = '" + type + "'";
        if (flag) {
            query += "AND DATE between date_sub(now(),INTERVAL 1 WEEK) and now()";
        }
        try {
            conn = getConn("MARKETPLACE");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBStuff.class.getName()).log(Level.SEVERE, null, ex);
        }

        Statement selectStmt = conn.createStatement();
        ResultSet rs = selectStmt.executeQuery(query);

        String str;

        str = "<hr><table border> <tr><th>Name</th> <th>Cost</th> <th>Add</th> </tr>";
        str += "<form action = 'checkout' method = 'POST'>";
        while (rs.next()) {
            String name = rs.getString("NAME");
            String cost = rs.getString("COST");
            String prod = name + "," + cost;

            str += "<tr> <td>" + name + "</td><td>" + cost + " Rs</td><td> <button name = 'product' type = 'submit' value = '" + prod + "'>+1</button></td></tr>";
        }
        str += "</form></table>";

        conn.close();
        return (str);
    }

    public void logPurchase(String name) throws SQLException {
        Bill obj = new Bill();

        Vector<Item> cart = obj.getCart();
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("yyyy-MM-dd");

        String date = sdf.format(dt);
        
        
        try {
            conn = getConn("MARKETPLACE");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBStuff.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        for (Item item : cart) {
            String query = "INSERT INTO PURCHASES VALUES( '" + date + "','" + name + "','" + item.getName() + "')";

            PreparedStatement pstmt = conn.prepareStatement(query);
            int rs = pstmt.executeUpdate();

        }
        conn.close();
    }
    
    public String getPurchases(String name) throws SQLException {
        String query = "SELECT * FROM  PURCHASES WHERE NAME = '" + name + "'";
        try {
            conn = getConn("MARKETPLACE");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBStuff.class.getName()).log(Level.SEVERE, null, ex);
        }

        Statement selectStmt = conn.createStatement();
        ResultSet rs = selectStmt.executeQuery(query);

        String str = "<hr>User Name : "+name+"<br><br>";
        
        str += "<table border> <tr><th>Date</th> <th>Name</th> </tr>";
        while (rs.next()) {
            String date = rs.getString("DATE");
            String prod_name = rs.getString("PROD_NAME");

            str += "<tr> <td>" + date + "</td><td>" + prod_name + "</td></tr>";
        }
        str += "</table>";

        conn.close();
        return (str); 
    }
    
    public String getFriends(String name) throws SQLException {
        String query = "SELECT * FROM  FRIENDS WHERE NAME = '" + name + "'";
        try {
            conn = getConn("MARKETPLACE");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBStuff.class.getName()).log(Level.SEVERE, null, ex);
        }

        Statement selectStmt = conn.createStatement();
        ResultSet rs = selectStmt.executeQuery(query);

        String str = "";

        while (rs.next()) {
            String friend = rs.getString("FRIEND");
              
             str += getPurchases(friend) + "<br>";
        }

        conn.close();
        return (str); 
    }
    
    public boolean checkBought(String name, String prod_name) throws SQLException {
        boolean flag = false;
        String query = "SELECT * FROM  PURCHASES WHERE NAME = '" + name + "' AND PROD_NAME = '" + prod_name + "'";

        try {
            conn = getConn("MARKETPLACE");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBStuff.class.getName()).log(Level.SEVERE, null, ex);
        }

        Statement selectStmt = conn.createStatement();
        ResultSet rs = selectStmt
                .executeQuery(query);

        while (rs.next()) {
            flag = true;
        }

        conn.close();
        return flag;
    }
}
