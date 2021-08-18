package com.mycompany.marketplace;

import com.mycompany.DBStuff.DBStuff;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/login")
public class Login extends HttpServlet {

    public static String messageDigest(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger num = new BigInteger(1, messageDigest);

            String hashtext = num.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void login(HttpServletRequest request, HttpServletResponse response, PrintWriter out, DBStuff db) throws SQLException {
        String usr = request.getParameter("usr");
        String pwd = request.getParameter("pwd");

        boolean loginStatus = db.checkLogin(usr, messageDigest(pwd));
        out.println("Login ? " + loginStatus);

        if (loginStatus) {
            setSessionLogin(request, usr);
        }
    }

    public void setSessionLogin(HttpServletRequest request, String usr) throws SQLException {
        HttpSession session = request.getSession();
        session.setAttribute("usr", usr);

    }

    public void signup(HttpServletRequest request, HttpServletResponse response, PrintWriter out, DBStuff db) throws SQLException {

        String card = request.getParameter("card");
        int cardLen = card.length();

        if (cardLen > 0) {
            String usr = request.getParameter("usr");
            String email = request.getParameter("email");
            String pwd = request.getParameter("pwd");

            boolean signUpStatus = db.registerUser(usr, messageDigest(pwd), email, card);
            out.println("Sign-Up ? " + signUpStatus);
            out.println("Sign-Up ? " + messageDigest(pwd));
            

        } else {
            getSignUpDets(out);
        }
    }

    public void getSignUpDets(PrintWriter out) {
        out.println("<center><form method = 'POST' action = 'login'>");
        out.println("Name:   <input type = 'text'     name = 'usr'   required> </br>"
                + "Password: <input type = 'password' name = 'pwd'   required> </br>"
                + "Email:    <input type = 'email'    name = 'email' required> </br>"
                + "Card:     <input type = 'card'     name = 'card'  required> </br>");
        out.println("<input type = 'submit' value = 'sign up'> </form></center>");
    }

    public void loginForm(PrintWriter out, String returnPage) {
        out.println("<form action = 'login?return=" + returnPage + "' method = 'POST' >"
                + "<br> Name: <input type = 'text' name = 'usr'>"
                + " Password: <input type = 'password' name = 'pwd'>"
                + "<input type = 'submit' name = 'sign-handler' value = 'login'>"
                + "<input type = 'submit' name = 'sign-handler' value = 'sign-up'> </form><br>");

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            String signHandler = request.getParameter("sign-handler");
            String returnPage = request.getParameter("return");
            if ("login".equals(signHandler)) {
                login(request, response, out, new DBStuff());
                out.println("<meta http-equiv='Refresh' content=\"0.5; url='" + returnPage + "'\" />");

            } else if ("logout".equals(signHandler)) {
                setSessionLogin(request, null);
                 out.println("<meta http-equiv='Refresh' content=\"0.5; url='" + returnPage + "'\" />");

            } else if ("signup".equals(signHandler)){
                out.println("signup");
                getSignUpDets(out);
            } else{
                 signup(request, response, out, new DBStuff());
                out.println("<meta http-equiv='Refresh' content=\"0.5; url='homepage'\" />");

            }
           
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try ( PrintWriter out = response.getWriter()) {
            loginForm(out, request.getParameter("return"));
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
