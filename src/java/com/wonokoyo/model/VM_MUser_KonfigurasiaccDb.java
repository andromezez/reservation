/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;

import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_vm_muser_konfigurasiacc;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eddy
 */
public class VM_MUser_KonfigurasiaccDb{
    private ResultSet hasilQuery;
    private DBConnection dBConnection;    
    public VM_MUser_KonfigurasiaccDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class VM_MUser_Konfigurasiacc: " + ex);
        }
    }
    public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error in class VM_MUser_Konfigurasiacc method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_vm_muser_konfigurasiacc();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String selectAllRow(){
        String sql = "SELECT * FROM VM_HRIS_COSTCENTER";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table in class VM_MUser_Konfigurasiacc method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_vm_muser_konfigurasiacc();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String convertToGridExtJsFormat_vm_muser_konfigurasiacc(){
        String hasil = GridExtJsFormat_vm_muser_konfigurasiacc.rootElAwal;

        try {
            while(hasilQuery.next()){
                hasil+=GridExtJsFormat_vm_muser_konfigurasiacc.rowElAwal;
                hasil+= GridExtJsFormat_vm_muser_konfigurasiacc.useridElAwal+hasilQuery.getString("userid")+GridExtJsFormat_vm_muser_konfigurasiacc.useridElAkhir;
                hasil+= GridExtJsFormat_vm_muser_konfigurasiacc.passElAwal+hasilQuery.getString("pass")+GridExtJsFormat_vm_muser_konfigurasiacc.passElAkhir;
                hasil+= GridExtJsFormat_vm_muser_konfigurasiacc.kodeformElAwal+hasilQuery.getString("kodeform")+GridExtJsFormat_vm_muser_konfigurasiacc.kodeformElAkhir;
                hasil+= GridExtJsFormat_vm_muser_konfigurasiacc.prioritasElAwal+hasilQuery.getString("prioritas")+GridExtJsFormat_vm_muser_konfigurasiacc.prioritasElAkhir;
                hasil+= GridExtJsFormat_vm_muser_konfigurasiacc.kodejabatanElAwal+
                        (hasilQuery.getString("kodejabatan")==null?"":hasilQuery.getString("kodejabatan"))
                        +GridExtJsFormat_vm_muser_konfigurasiacc.kodejabatanElAkhir;
                hasil+= GridExtJsFormat_vm_muser_konfigurasiacc.namajabatanElAwal+hasilQuery.getString("namajabatan")+GridExtJsFormat_vm_muser_konfigurasiacc.namajabatanElAkhir;
                hasil+= GridExtJsFormat_vm_muser_konfigurasiacc.namabpElAwal+hasilQuery.getString("namabp")+GridExtJsFormat_vm_muser_konfigurasiacc.namabpElAkhir;
                hasil+= GridExtJsFormat_vm_muser_konfigurasiacc.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convert to GridExtJsFormat_vm_muser_konfigurasiacc : " + ex);
        }
        hasil+=GridExtJsFormat_vm_muser_konfigurasiacc.rootElAkhir;
        return hasil;
    }
}
