package com.mylogic;
 
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
 
/**
 * Session Bean implementation class Calc
 */
@Stateless
@LocalBean
public class Calc implements CalcRemote {
 
    /**
     * Default constructor. 
     */
    public Calc() {
        // TODO Auto-generated constructor stub
    }
 
    public double add(double a,double b){
    	return (a+b);
    }
    public double sub(double a,double b){
    	return (a-b);
    }
   public double mul(double a,double b){
    	return (a*b);
    }
   public double div(double a,double b){
    	return (1.0*a/b);
    }
   
}