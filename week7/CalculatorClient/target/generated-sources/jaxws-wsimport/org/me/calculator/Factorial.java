
package org.me.calculator;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Factorial", targetNamespace = "http://calculator.me.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Factorial {


    /**
     * 
     * @param i
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "factorial", targetNamespace = "http://calculator.me.org/", className = "org.me.calculator.Factorial_Type")
    @ResponseWrapper(localName = "factorialResponse", targetNamespace = "http://calculator.me.org/", className = "org.me.calculator.FactorialResponse")
    @Action(input = "http://calculator.me.org/Factorial/factorialRequest", output = "http://calculator.me.org/Factorial/factorialResponse")
    public int factorial(
        @WebParam(name = "i", targetNamespace = "")
        int i);

}