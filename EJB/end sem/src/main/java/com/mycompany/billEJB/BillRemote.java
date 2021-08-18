package com.mycompany.billEJB;

import java.util.Vector;
import javax.ejb.Remote;


@Remote
public interface BillRemote {

    public void addItem(String name, String price);
    public Vector<Item> getCart();
    public void emptyCart();
}
