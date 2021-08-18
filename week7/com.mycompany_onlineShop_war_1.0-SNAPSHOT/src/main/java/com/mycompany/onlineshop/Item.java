/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlineshop;

/**
 *
 * @author shankar
 */
class Item {
    String name;
    int cost;
    int quant;
    
    public Item(){
        
    }

    public Item(String name, int cost, int quant){
        this.name = name;
        this.cost = cost;
        this.quant = quant;
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

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
    
}
