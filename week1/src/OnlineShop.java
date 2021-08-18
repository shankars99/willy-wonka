import java.sql.*;
import java.io.*;

public class OnlineShop {
    private Connection conn;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public Connection getCode(Connection conn) throws IOException {
        System.out.print("\nEnter the code of the item you wish to purchase:");
        int item_code = Integer.parseInt(br.readLine());

        System.out.print("Enter the quantity you wish to purchase:");
        int item_quant = Integer.parseInt(br.readLine());

        try {
            Statement stmt = conn.createStatement();
            Statement stmt1 = conn.createStatement();
            String sql = "SELECT * FROM store where item_code = " + item_code + "";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("item_name");
                int num = rs.getInt("item_num");
                int cost = rs.getInt("item_cost");

                int bought_items = Math.min(num, item_quant);
                System.out.println("You purchased " + bought_items + " " + name + " @Rs." + bought_items * cost);
                try {
                    String sql1 = "UPDATE store SET item_num = " + (num - bought_items) + " where item_code = "
                            + item_code;
                    stmt1.executeUpdate(sql1);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error in select all");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in select all");
        }
        return conn;
    }

    public Connection listItems(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM store";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\ncode\tname\tnum\tcost");
            while (rs.next()) {
                String code = rs.getString("item_code");
                String name = rs.getString("item_name");
                String num = rs.getString("item_num");
                String cost = rs.getString("item_cost");

                System.out.println(code + "\t" + name + "\t" + num + "\t" + cost);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in select all");
        }
        return conn;
    }

    public boolean loginVerification(Connection conn) throws IOException {
        System.out.print("\nEnter your username:");
        String usrname = br.readLine();

        System.out.print("Enter your password:");
        String pwd = br.readLine();

        boolean loginFlag = false;
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) AS login FROM login WHERE NAME = '" + usrname + "' AND PASSWORD = '" + pwd
                    + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if (rs.getInt("login") == 1) {
                    System.out.println("Login for " + usrname + " was successful");
                    loginFlag = true;
                } else {
                    System.out.println("Login for " + usrname + " was failure");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in select all");
        }
        return loginFlag;
    }

    public Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost/test";
            String username = "root";
            String password = "";

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Successful");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while connecting to database");
        }
        return conn;
    }


    public boolean getContinue() throws IOException{
        boolean flag = false;
        System.out.print("Do you wish to continue? (yes/no)");
        String inp = br.readLine();

        if( inp.compareToIgnoreCase("yes") == 0){
            flag = false;
        }
        else{
            flag = true;
        }

        return flag;
    }

    public static void main(String args[]) throws IOException {
        OnlineShop obj = new OnlineShop();
        Connection conn = obj.getConnection();

        while( !obj.loginVerification(conn) );
        do{
            obj.listItems(conn);
            obj.getCode(conn);
        }while( !obj.getContinue() );
    }
}