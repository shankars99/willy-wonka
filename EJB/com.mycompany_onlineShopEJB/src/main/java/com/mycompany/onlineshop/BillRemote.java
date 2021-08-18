/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlineshop;

import java.util.Vector;
import javax.ejb.Remote;

/**
 *
 * @author shankar
 */
@Remote
public interface BillRemote {

    public Vector<Item> addItem(String name, String price);
}
