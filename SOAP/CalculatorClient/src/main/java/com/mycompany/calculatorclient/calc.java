/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculatorclient;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import org.me.calculator.CalculatorWS_Service;
import org.me.calculator.Factorial_Service;

/**
 *
 * @author shankar
 */
public class calc extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Factorial/Factorial.wsdl")
    private Factorial_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/CalculatorWS/CalculatorWS.wsdl")
    private CalculatorWS_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet calc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet calc at " + request.getContextPath() + "</h1>");
            //int i = Integer.parseInt((String) request.getParameter("i"));
            //int j = Integer.parseInt((String) request.getParameter("j"));
            double i = 0.0;
            String i_temp = request.getParameter("i");
            String j_temp = request.getParameter("j");
            
            if (i_temp.isEmpty()) {
                i = Double.parseDouble(j_temp);
            } else {
                i = Double.parseDouble(i_temp);
            }
            String op = (String) request.getParameter("op");

            double j = Double.parseDouble(j_temp);
            //out.println(i);
            //out.println(j);
            out.println(op);

            switch (op) {
                case "add":
                    
                    try { // Call Web Service Operation
                    org.me.calculator.CalculatorWS port = service.getCalculatorWSPort();
                    // TODO initialize WS operation arguments here
                    // TODO process result here
                    double result = port.add(i, j);
                    out.println("Result = " + result);
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
                break;
                case "sub":
                    try { // Call Web Service Operation
                    org.me.calculator.CalculatorWS port = service.getCalculatorWSPort();
                    // TODO initialize WS operation arguments here
                    // TODO process result here
                    double result = port.sub(i, j);
                    out.println("Result = " + result);
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
                break;
                case "div":
                    try { // Call Web Service Operation
                    org.me.calculator.CalculatorWS port = service.getCalculatorWSPort();
                    // TODO initialize WS operation arguments here
                    // TODO process result here
                    double result = port.div(i, j);
                    out.println("Result = " + result);
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
                break;
                case "mul":
                    try { // Call Web Service Operation
                    org.me.calculator.CalculatorWS port = service.getCalculatorWSPort();
                    // TODO initialize WS operation arguments here
                    // TODO process result here
                    double result = port.mul(i, j);
                    out.println("Result = " + result);
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
                break;
                case "root":
                    try { // Call Web Service Operation
                    org.me.calculator.CalculatorWS port = service.getCalculatorWSPort();
                    // TODO initialize WS operation arguments here
                    // TODO process result here
                    double result = port.root(i);
                    out.println("Result = " + result);
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
                break;

                case "prime":
                    try { // Call Web Service Operation
                    org.me.calculator.CalculatorWS port = service.getCalculatorWSPort();
                    // TODO initialize WS operation arguments here
                    // TODO process result here
                    java.lang.String result = port.prime((int) i);
                    out.println("Result = " + result);
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
                break;
                
                case "fact":
                    try { // Call Web Service Operation
                        org.me.calculator.Factorial port = service_1.getFactorialPort();
                        // TODO initialize WS operation arguments here
                        // TODO process result here
                        int result = port.factorial((int) i);
                        out.println("Result = "+result);
                    } catch (Exception ex) {
                        // TODO handle custom exceptions here
                    }

                    

            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
