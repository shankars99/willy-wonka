/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.marksejb;

import java.sql.Connection;
import java.sql.SQLException;
import javax.ejb.Remote;

/**
 *
 * @author shankar
 */
@Remote
public interface markEntryRemote {
    public void enterMarks(String name, int[] marks);
}
