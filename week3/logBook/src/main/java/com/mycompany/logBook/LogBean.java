package com.mycompany.logBook;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LogBean implements Serializable {

    private static final long serialVersionUID = 6081417964063918994L;

    public List<Log> getEntries() throws ClassNotFoundException, SQLException {

        Connection connect = null;

        String url = "jdbc:mysql://localhost:3306/test";

        String username = "root";
        String password = "";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection(url, username, password);
            // System.out.println("Connection established"+connect);

        } catch (SQLException ex) {
            System.out.println("in exec");
            System.out.println(ex.getMessage());
        }

        List<Log> entries = new ArrayList<Log>();
        PreparedStatement pstmt = connect
                .prepareStatement("select * from MESSAGES");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            Log car = new Log();
            car.setDate(rs.getString("Date"));
            car.setEmail(rs.getString("Email"));
            car.setName(rs.getString("Name"));
            car.setMessage(rs.getString("Message"));

            entries.add(car);

        }

        rs.close();
        pstmt.close();
        connect.close();

        return entries;

    }

}
