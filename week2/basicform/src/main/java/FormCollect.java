/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shankar
 */
public class FormCollect extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        
        
        pw.println("Welcome " + fname + " login successful");
    }
}