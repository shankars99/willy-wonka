package com.mycompany.logBook;


import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;

@ManagedBean
@RequestScoped
public class EntryBean {

    private String date;
    private String name;
    private String email;
    private String message;

    /**
     * Creates a new instance of GreetingsBean
     */
    public EntryBean() {
        System.out.println("Created GreetingsBean instance...");
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String addEntry() {

        return "addEntry";
    }

    public String addUser() throws ClassNotFoundException, SQLException {

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

        String date = sdf.format(dt);
        Connection connect = null;

        String url = "jdbc:mysql://localhost:3306/test";

        String username = "root";
        String password = "";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection(url, username, password);

        } catch (SQLException ex) {
            System.out.println("in exec");
            System.out.println(ex.getMessage());
        }
        PreparedStatement pstmt = connect
                .prepareStatement("INSERT INTO MESSAGES VALUES ('" + date + "','"
                        + name + "','" + email +"','"+ message + "')");
        int rs = pstmt.executeUpdate();

        pstmt.close();
        connect.close();

        return "index";
    }
}
