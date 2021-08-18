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
@WebService(serviceName = "Factorial")
@Stateless()
public class Factorial {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "factorial")
    public int factorial(@WebParam(name = "i") int i) {
        //TODO write your implementation code here:
        int fact = 1;
        
        for(int j = 1; j< i; j++){
            fact = fact * j;
        }
        return fact;
    }

    /**
     * This is a sample web service operation
     */

}
