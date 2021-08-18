package com.mycompany.marketplace;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mycompany.DBStuff.DBStuff;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/payment")
public class Payment extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        if (req.getSession().getAttribute("usr") == null) {
            pw.println("Have to login to view page");
            pw.println("<meta http-equiv='Refresh' content=\"1.5; url='login?return=payment'\" />");

        } else {
            try {
                DBStuff obj = new DBStuff();
                
                obj.logPurchase((String) req.getSession().getAttribute("usr"));
                pw.println("Payment successsful");
                pw.println("<meta http-equiv='Refresh' content=\"0.5; url='profile?option=purchases'\" />");

                
            } catch (SQLException ex) {
                Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
