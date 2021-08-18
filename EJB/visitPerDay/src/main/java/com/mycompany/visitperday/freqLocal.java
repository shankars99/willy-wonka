/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.visitperday;

import java.sql.Connection;
import javax.ejb.Remote;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shankar
 */
@Remote
public interface freqLocal {
    public String getFreq(String day);
    public void addFreq(String day, int visits, Connection conn );
    public Connection getConnection();
}
