/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.calculator;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
/**
 *
 * @author shankar
 */
@WebService(serviceName = "CalculatorWS")
@Stateless()
public class CalculatorWS {
    /**
     * Web service operation
     */ 
    @WebMethod(operationName = "add")
    public double add(@WebParam(name = "i") double i, @WebParam(name = "j") double j) {
        //TODO write your implementation code here:
        double k = i + j;
        return k;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sub")
    public double sub(@WebParam(name = "i") double i, @WebParam(name = "j") double j) {
        //TODO write your implementation code here:
        return (i-j);
    }
    
    @WebMethod(operationName = "mul")
    public double mul(@WebParam(name = "i") double i, @WebParam(name = "j") double j) {
        //TODO write your implementation code here:
        return (i*j);
    }
    
    @WebMethod(operationName = "div")
    public double div(@WebParam(name = "i") double i, @WebParam(name = "j") double j) {
        //TODO write your implementation code here:
        return (1.0 * i/j);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "root")
    public double root(@WebParam(name = "i") double i) {
        //TODO write your implementation code here:
        return Math.sqrt(i);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "prime")
    public String prime(@WebParam(name = "i") int i) {
        
        for(int j = 2; j<i/2; j++){
            if( i%j == 0){
                return "Not prime";
            }
        }
        return "Prime";
    }
}
