/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;

import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_VMFormNeedAcc;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eddy
 */
public class VMFormNeedAccDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;    
    public VMFormNeedAccDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class VMFormNeedAcc: " + ex);
        }
    }

    public String selectAllRow(){
        String sql = "SELECT * FROM VM_FORM_NEEDACC";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table VMFormNeedAcc in class VMFormNeedAccDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_VMFormNeedAcc();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String convertToGridExtJsFormat_VMFormNeedAcc(){
        //String sql = "SELECT * FROM VM_FORM_NEEDACC";
        String hasil = GridExtJsFormat_VMFormNeedAcc.rootElAwal;
        
        int n,i;
        try {
            //hasilQuery = dBConnection.getStmt().executeQuery(sql);
            while(hasilQuery.next()){
                hasil+=GridExtJsFormat_VMFormNeedAcc.rowElAwal+
                        GridExtJsFormat_VMFormNeedAcc.kodeformElAwal+hasilQuery.getString("kodeform")+GridExtJsFormat_VMFormNeedAcc.kodeformElAkhir+
                        GridExtJsFormat_VMFormNeedAcc.namaformElAwal+hasilQuery.getString("namaform")+GridExtJsFormat_VMFormNeedAcc.namaformElAkhir+
                       GridExtJsFormat_VMFormNeedAcc.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table VMFormNeedAcc in class VMFormNeedAccDb method selectAllRow: " + ex);
        }
        hasil+=GridExtJsFormat_VMFormNeedAcc.rootElAkhir;
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
}
