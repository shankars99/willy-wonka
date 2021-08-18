/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.billEJB;

/**
 *
 * @author shankar
 */
public class Item {
    String name;
    int cost;
    
    public Item(){
        
    }

    public Item(String name, int cost){
        this.name = name;
        this.cost = cost;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
