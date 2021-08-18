package com.mycompany.mainpages;

import com.mycompany.DBStuff.DBStuff;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/homepage")
public class HomePage extends HttpServlet {

    public void login(PrintWriter out, HttpSession session) {
        if (session.getAttribute("usr") == null) {
            out.println("<form action = 'login?return=homepage' method = 'POST' >"
                    + "<br> Name: <input type = 'text' name = 'usr'>"
                    + " Password: <input type = 'password' name = 'pwd'>"
                    + "<input type = 'submit' name = 'sign-handler' value = 'login'>"
                    + "<input type = 'submit' name = 'sign-handler' value = 'signup'> </form><br>");

        }else{
            out.println("Hello, " + session.getAttribute("usr") + ". &nbsp"
                    + "<form action = 'login?return=homepage' method = 'POST'>"
                    + "<button name = 'sign-handler' type = 'submit' value = 'logout'>Logout</button><br>"
                    + "</form>"
                    + "<form action = 'profile' method = 'GET'>"
                    + "<button name = 'option' type = 'submit' value = 'purchases'>Purchases</button> &nbsp &nbsp"
                    + "<button name = 'option' type = 'submit' value = 'friends'>Friends</button><br>"
                    +"</form><hr><br>");
        }

    }

    public void homeBody(PrintWriter out) throws SQLException {
        listElements("GAME", out);
        out.println("<form action = 'catalogue' mothod = 'GET'>"
                + "<button type = 'submit' name = 'cont-type' value = 'GAME'>All Games</button></form><hr>");
        listElements("MOVIE", out);
        out.println("<form action = 'catalogue' mothod = 'GET'>"
                + "<button type = 'submit' name = 'cont-type' value = 'MOVIE'>All Movies</button></form><hr>");

    }

    public void listElements(String type, PrintWriter out) throws SQLException {

        DBStuff db = new DBStuff();

        out.println("NEW " + type + " RELEASES <br>");
        out.println(db.getElementList(type, true) + "<br>");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            login(out, request.getSession());
            homeBody(out);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
