/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;

import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_vm_hris_costcenter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eddy
 */
public class VMHRIS_costcenterDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;    
    public VMHRIS_costcenterDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class VMHRIS_costcenterDb: " + ex);
        }
    }    

    public String selectAllRow(){
        String sql = "SELECT * FROM VM_HRIS_COSTCENTER";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table VM_HRIS_COSTCENTER in class VMHRIS_costcenterDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_vm_hris_costcenter();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class VMHRIS_costcenterDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_vm_hris_costcenter();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String convertToGridExtJsFormat_vm_hris_costcenter(){
        String hasil = GridExtJsFormat_vm_hris_costcenter.rootElAwal;

        try {
            while(hasilQuery.next()){
                String namacc = hasilQuery.getString("namacostcenter");
                String cc = hasilQuery.getString("costcenter");
                if(namacc == null)
                    namacc = "";
                else
                   namacc = namacc.replace("&", "&amp;");

                if(cc == null)
                    cc = "";
                else
                   cc = cc.replace("&", "&amp;");

                   hasil+=GridExtJsFormat_vm_hris_costcenter.rowElAwal+
                        GridExtJsFormat_vm_hris_costcenter.kodecostcenterElAwal+hasilQuery.getString("kodecostcenter")+GridExtJsFormat_vm_hris_costcenter.kodecostcenterElAkhir+
                        GridExtJsFormat_vm_hris_costcenter.namacostcenterElAwal+namacc+GridExtJsFormat_vm_hris_costcenter.namacostcenterElAkhir+
                        GridExtJsFormat_vm_hris_costcenter.costcenterElAwal+cc+GridExtJsFormat_vm_hris_costcenter.costcenterElAkhir+
                        GridExtJsFormat_vm_hris_costcenter.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convert to GridExtJsFormat_vm_hris_costcenter : " + ex);
        }
        hasil+=GridExtJsFormat_vm_hris_costcenter.rootElAkhir;
        return hasil;
    }
}