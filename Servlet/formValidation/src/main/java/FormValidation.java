/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author shankar
 */
public class FormValidation extends HttpServlet {

    public static boolean isAlphabet(String str) {
        return ((str.equals("")) && (str != null) && (str.matches("^[a-zA-Z]*$")));
    }

    public static boolean isNumber(String str) {
        int n;
        boolean flag = false;
        try {
            n = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            flag = true;
        }
        return flag;
    }

    public static boolean isNotEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String country = req.getParameter("country");
        String zipcode = req.getParameter("zipcode");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String language = req.getParameter("language");
        String about = req.getParameter("about");

        boolean flag = true;
        String errParam = new String();

        if       (id.length() < 5 || id.length() > 12  || id.isBlank()){
            errParam = "id";
            flag = false;
        } else if(pwd.length() < 7 || pwd.length() > 12 || id.isBlank()){
            errParam = "pwd";
            flag = false;
        } else if(name.isBlank()   || isAlphabet(name)){
            errParam = "name";
            flag = false;
        } else if(zipcode.isBlank() || isNumber(zipcode)) {
            errParam = "zipcode";
            flag = false;
        } else if(isNotEmail(email) ){
            errParam = "email";
            flag = false;
        } else if(gender.isBlank()){
            errParam = "gender";
            flag = false;
        } else if(language.isBlank()) {
            errParam = "language";
            flag = false;
        }

        if( flag == false ){
            pw.println("Account creation unsuccessful");
            pw.println("\nError in parameter:" + errParam);
        } else{
            pw.println("Welcome " + name + " account creation successful");
        }
    }
}
