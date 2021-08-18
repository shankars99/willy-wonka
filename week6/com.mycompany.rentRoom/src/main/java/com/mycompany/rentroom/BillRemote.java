/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rentroom;

import java.util.Vector;
import javax.ejb.Remote;

/**
 *
 * @author shankar
 */
@Remote
public interface BillRemote {

    public Vector<Room> addItem(String name, String price, String inDate, String outDate);
}
