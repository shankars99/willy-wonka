/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.DBStuff;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public interface DBStuffRemote {
    Connection conn = null;

    public Connection getConn(String table) throws ClassNotFoundException, SQLException;
    public boolean checkLogin(String usr, String pwd) throws SQLException;
    public boolean registerUser(String usr, String pwd, String email, String card) throws SQLException;
    public String getElementList(String type, boolean flag) throws SQLException;
    public void logPurchase(String name) throws SQLException;
    public String getPurchases(String name) throws SQLException;
    public boolean checkBought(String name, String prod_name) throws SQLException;
    public String getFriends(String name) throws SQLException;
}
