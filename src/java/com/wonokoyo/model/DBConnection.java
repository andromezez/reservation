/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Bayu
 */
public class DBConnection {
    private static final String oracleDriver = "oracle.jdbc.OracleDriver";
    private Connection connection ;
    private static String oracleUrl = "jdbc:oracle:thin:@//andromezez.com:1521/XEPDB1";
    private String username="TDS", pass="1234";
    private Statement stmt ;
    public boolean isErrorProcessingDb = false;
    
    public DBConnection() {
        if(connection == null){
            try {
                Class.forName(oracleDriver);
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Error loading driver: " + ex);
            }
            try {                
                    connection = DriverManager.getConnection(oracleUrl, username, pass);
                   // connection.setAutoCommit(false);
            } catch (SQLException ex) {
                
                //Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Error create connection database: " + ex);
            }
        }
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }
    public void startTransaction(){
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            isErrorProcessingDb=true;
        }
    }
    public void endTransaction(){
        try {
            if(isErrorProcessingDb){
                connection.rollback();
            }else{
                connection.commit();
            }
        }
        catch (SQLException ex) {
        }
    }
    public void closeConn(){
        try {
            if (!connection.isClosed()){                              
                connection.close();
                connection = null;
            }
        } catch (SQLException ex) {
            System.err.println("Error close connection database: " + ex);
        }
    }
    protected void closeStmt(){
        try {
            if (getStmt() != null)
                this.getStmt().close();
        } catch (SQLException ex) {
            isErrorProcessingDb=true;
            System.err.println("Error close statement: " + ex);
        }
    }
    /**
     * @return the stmt
     */
    protected Statement getStmt() {
        try {
            this.stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            isErrorProcessingDb=true;
            System.err.println("Error create statement : " + ex);
        }
        return stmt;
    }
    static protected String getOracleErrorSentence(SQLException ex){
        if(ex.getErrorCode() == 2292)
            return "integrity constraint violated - child record found";
        else
            return "";
    }

    public ResultSet customSelect(String sql){
        try {
            return getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            isErrorProcessingDb=true;
            System.err.println("Error customSelect functino in class DBConnection: " + ex);
        }        
        this.closeStmt();
        return null;
    }
    
    
}
