package com.mycompany.mainpages;

import com.mycompany.DBStuff.DBStuff;

import com.mycompany.billEJB.Bill;
import com.mycompany.billEJB.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

@WebServlet("/checkout")
public class Checkout extends HttpServlet {

    Bill obj = new Bill();

    public void listItems(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        PrintWriter out = response.getWriter();

        
        String[] temp = request.getParameter("product").split(",");
        String name = temp[0];
        String price = temp[1];
        
        DBStuff dbstuff = new DBStuff();
        
        if(dbstuff.checkBought((String) request.getSession().getAttribute("usr"), name)){
            out.println("Item already in cart<br><br>");
            
        }else{
            obj.addItem(name, price);
        }
        
        Vector<Item> cart = obj.getCart();
        int bill = 0;
        out.println("<table border = 1>"
                + "<tr> <td>Name</td> <td>Cost</td></tr>");
        for (Item item : cart) {
            out.println("<tr><td>" + item.getName() + "</td><td>"
                    + item.getCost() + " Rs.</td></tr>");
            bill += item.getCost();
        }
        out.println("</table>");
        out.println("<br>Bill = " + bill + " <br> <a href = 'payment'> <input type = 'button' value = 'Click to pay'> </a>");

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {
            listItems(request, response);
        } finally {
            out.close();
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Checkout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Checkout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
