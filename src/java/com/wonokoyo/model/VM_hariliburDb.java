/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_vm_harilibur;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author bayu
 */
public class VM_hariliburDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;    
    public VM_hariliburDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class VM_hariliburDb: " + ex);
        }
    }
    public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error in class VM_hariliburDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_VM_hariliburDb();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String selectAllRow(){
        String sql = "SELECT * FROM vm_harilibur "+
                "where kodelokasi=(select distinct parvalue from m_parameter where parname='kodelokasi_tarifitem')";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table in class VM_hariliburDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_VM_hariliburDb();
        closeHasilQuery();                                                              
        dBConnection.closeStmt();
        return hasil;
    }

    public String convertToGridExtJsFormat_VM_hariliburDb(){
        String hasil = GridExtJsFormat_vm_harilibur.rootElAwal;

        try {
            while(hasilQuery.next()){
                hasil+=GridExtJsFormat_vm_harilibur.rowElAwal;
                hasil+= GridExtJsFormat_vm_harilibur.keteranganElAwal+hasilQuery.getString("keterangan")+GridExtJsFormat_vm_harilibur.keteranganElAkhir;
                hasil+= GridExtJsFormat_vm_harilibur.tanggalElAwal+hasilQuery.getString("tanggal")+GridExtJsFormat_vm_harilibur.tanggalElAkhir;
                hasil+= GridExtJsFormat_vm_harilibur.kodelokasiElAwal+hasilQuery.getString("kodelokasi")+GridExtJsFormat_vm_harilibur.kodelokasiElAkhir;                
                hasil+= GridExtJsFormat_vm_harilibur.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convert to convertToGridExtJsFormat_VM_hariliburDb : " + ex);
        }
        hasil+=GridExtJsFormat_vm_harilibur.rootElAkhir;
        return hasil;
    }
}
