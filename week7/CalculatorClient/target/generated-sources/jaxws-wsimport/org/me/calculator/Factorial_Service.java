
package org.me.calculator;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "Factorial", targetNamespace = "http://calculator.me.org/", wsdlLocation = "http://localhost:8080/Factorial/Factorial?wsdl")
public class Factorial_Service
    extends Service
{

    private final static URL FACTORIAL_WSDL_LOCATION;
    private final static WebServiceException FACTORIAL_EXCEPTION;
    private final static QName FACTORIAL_QNAME = new QName("http://calculator.me.org/", "Factorial");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/Factorial/Factorial?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FACTORIAL_WSDL_LOCATION = url;
        FACTORIAL_EXCEPTION = e;
    }

    public Factorial_Service() {
        super(__getWsdlLocation(), FACTORIAL_QNAME);
    }

    public Factorial_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), FACTORIAL_QNAME, features);
    }

    public Factorial_Service(URL wsdlLocation) {
        super(wsdlLocation, FACTORIAL_QNAME);
    }

    public Factorial_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FACTORIAL_QNAME, features);
    }

    public Factorial_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Factorial_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Factorial
     */
    @WebEndpoint(name = "FactorialPort")
    public Factorial getFactorialPort() {
        return super.getPort(new QName("http://calculator.me.org/", "FactorialPort"), Factorial.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Factorial
     */
    @WebEndpoint(name = "FactorialPort")
    public Factorial getFactorialPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://calculator.me.org/", "FactorialPort"), Factorial.class, features);
    }

    private static URL __getWsdlLocation() {
        if (FACTORIAL_EXCEPTION!= null) {
            throw FACTORIAL_EXCEPTION;
        }
        return FACTORIAL_WSDL_LOCATION;
    }

}