/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rentroom;

/**
 *
 * @author shankar
 */
class Room {
    String name;
    int cost;
    int quant;
    String entryDate;
    String exitDate;

    public Room(){
        
    }

    public Room(String name, int cost, int quant, String entryDate, String exitDate){
        this.name = name;
        this.cost = cost;
        this.quant = quant;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
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
    
    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getExitDate() {
        return exitDate;
    }

    public void setExitDate(String exitDate) {
        this.exitDate = exitDate;
    }
    
}
