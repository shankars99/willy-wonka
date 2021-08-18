/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rentroom;

import javax.ejb.Stateful;
import javax.servlet.http.HttpServlet;
import java.util.Vector;

/**
 *
 * @author shankar
 */
@Stateful
public class Bill extends HttpServlet implements BillRemote  {

    static Vector<Room> rooms = new Vector<Room>();

    @Override
    public Vector<Room> addItem(String name, String price, String inDate, String outDate) {

        for(Room room : rooms){
            if(room.getName().equals(name))
            {
                room.setQuant( room.getQuant()+1 );
                return rooms;
            }
        }

        rooms.add(new Room(name,Integer.parseInt(price),1, inDate, outDate));                
        return rooms;
    }
    
}
