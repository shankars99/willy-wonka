/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calc;

import javax.ejb.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
import java.io.IOException;
 
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
 

@WebServlet("/doadd")
public class doadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */	
    public doadd() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @EJB(lookup ="ejb:/AdditionEJB//Addition!com.mylogic.AdditionRemote")
    //private AdditionRemote abc;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String as=request.getParameter("a");
		int a=Integer.parseInt(as);
		as=request.getParameter("b");
		int b=Integer.parseInt(as);
		
		int result=0;
		
		try {
			//result=abc.add(a, b);
		}catch(Exception e){e.printStackTrace();}
 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Results for the addition</h3>");
		out.println("Result:"+result);		
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
 
}