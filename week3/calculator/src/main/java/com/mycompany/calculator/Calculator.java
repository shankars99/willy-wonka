package com.mycompany.calculator;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped

public class Calculator {
    private int num1, num2;
    private double sum;
    private String operation;
    
    public Calculator(){
        
    }

    public String getOperation() {
        return operation;
    }
    
    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public double getSum() {
        return sum;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public void addNumber(ActionEvent e){
        sum = num1+num2;
        operation = "Sum";
    }
    
    public void subNumber(ActionEvent e){
        sum = num1-num2;
        operation = "Difference";
    }
    public void mulNumber(ActionEvent e){
        sum = num1*num2;
        operation = "Product";
    }
    
    public void divNumber(ActionEvent e){
        sum = 1.0*num1/num2;
        operation = "Quotient";
    }

}
