package com.mycompany.visitperday;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.servlet.http.HttpServletResponse;

@Stateful
public class freq implements freqLocal {

    boolean visited = false;

    @Override
    public String getFreq(String day) {
        Connection conn = getConnection();
        String res = "";
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM VISITOR_FREQ";
            ResultSet rs = stmt.executeQuery(sql);

            res += "<table border> <tr><th>Day</th> <th>Visits</th></tr>";
            while (rs.next()) {
                String days = rs.getString("Day");
                int visits = rs.getInt("Visits");

                res += "<tr> <td>" + days + "</td><td>";

                if (days.equals(day) && visited == false) {
                    visits++;
                    addFreq(day, visits, conn);
                    visited = true;
                }

                res += visits + "</td>";

            }
            res += "</table>";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in select all");
        }

        return res;
    }

    @Override
    public void addFreq(String day, int visits,Connection conn) {
        Statement stmt = null;
        try {
            stmt = (Statement) conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(freq.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "UPDATE VISITOR_FREQ SET Visits='"+visits+"' WHERE Day = '" + day +"' ";
        
        try {
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(freq.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Connection getConnection() {
        Connection conn = null;
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
