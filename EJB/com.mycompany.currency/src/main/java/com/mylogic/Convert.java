package com.mylogic;
 
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
 
/**
 * Session Bean implementation class Convert
 */
@Stateless
@LocalBean
public class Convert implements ConvertRemote {
 
    /**
     * Default constructor. 
     */
    public Convert() {
        // TODO Auto-generated constructor stub
    }
 
    public double toUSD(double a){
    	return (a / 73.37);
    }
    public double toYEN(double a){
    	return (a / 0.66);
    }
   public double toEUR(double a){
    	return (a / 86.28);
    }
}