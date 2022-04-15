/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;

import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_VMHRIS_bpartnerlengkap;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletOutputStream;

/**
 *
 * @author Eddy
 */
public class VMHRIS_bpartnerlengkapDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;    
    public VMHRIS_bpartnerlengkapDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class VMBPartnerLengkap: " + ex);
        }
    }
    public String selectCustomRow(String sql){
          //"SELECT * FROM vm_hris_bpartnerlengkap WHERE kodecostcenter = '"+filterCostCenter+"'";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error in class VMHRIS_bpartnerlengkapDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_vm_hris_bpartnerlengkap();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String selectAllRow(){
        String sql = "SELECT * FROM vm_hris_bpartnerlengkap";
       
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);            
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table vm_hris_bpartnerlengkap in class VMHRIS_bpartnerlengkapDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_vm_hris_bpartnerlengkap();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String convertToGridExtJsFormat_vm_hris_bpartnerlengkap(){
        String hasil = GridExtJsFormat_VMHRIS_bpartnerlengkap.rootElAwal;
        String namaorg,namaorg2="";        
        try {
            while(hasilQuery.next()){
                namaorg = hasilQuery.getString("namaorg");
                if(namaorg == null)
                    namaorg2 = "";
                else
                   namaorg2 = namaorg.replace("&", "&amp;");
                
                   hasil+=GridExtJsFormat_VMHRIS_bpartnerlengkap.rowElAwal+
                        GridExtJsFormat_VMHRIS_bpartnerlengkap.kodebpElAwal+hasilQuery.getString("kodebp")+GridExtJsFormat_VMHRIS_bpartnerlengkap.kodebpElAkhir+
                        GridExtJsFormat_VMHRIS_bpartnerlengkap.nikElAwal+(hasilQuery.getString("nik")==null?"":hasilQuery.getString("nik"))+GridExtJsFormat_VMHRIS_bpartnerlengkap.nikElAkhir+
                        GridExtJsFormat_VMHRIS_bpartnerlengkap.namabpElAwal+hasilQuery.getString("namabp")+GridExtJsFormat_VMHRIS_bpartnerlengkap.namabpElAkhir+
                        GridExtJsFormat_VMHRIS_bpartnerlengkap.kodeperusahaanElAwal+(hasilQuery.getString("kodeperusahaan")==null?"":hasilQuery.getString("kodeperusahaan"))+GridExtJsFormat_VMHRIS_bpartnerlengkap.kodeperusahaanElAkhir+
                        GridExtJsFormat_VMHRIS_bpartnerlengkap.namaperusahaanElAwal+(hasilQuery.getString("namaperusahaan")==null?"":hasilQuery.getString("namaperusahaan"))+GridExtJsFormat_VMHRIS_bpartnerlengkap.namaperusahaanElAkhir+
                        GridExtJsFormat_VMHRIS_bpartnerlengkap.kodeorgElAwal+(hasilQuery.getString("kodeorg")==null?"":hasilQuery.getString("kodeorg").replace("&", "&amp;"))+GridExtJsFormat_VMHRIS_bpartnerlengkap.kodeorgElAkhir+
                        GridExtJsFormat_VMHRIS_bpartnerlengkap.namaorgElAwal+namaorg2 +GridExtJsFormat_VMHRIS_bpartnerlengkap.namaorgElAkhir+
                        GridExtJsFormat_VMHRIS_bpartnerlengkap.kodejabatanElAwal+(hasilQuery.getString("kodejabatan")==null?"":hasilQuery.getString("kodejabatan"))+GridExtJsFormat_VMHRIS_bpartnerlengkap.kodejabatanElAkhir+
                        GridExtJsFormat_VMHRIS_bpartnerlengkap.namajabatanElAwal+(hasilQuery.getString("namajabatan")==null?"":hasilQuery.getString("namajabatan").replace("&", "&amp;"))+GridExtJsFormat_VMHRIS_bpartnerlengkap.namajabatanElAkhir+
                        GridExtJsFormat_VMHRIS_bpartnerlengkap.kodecostcenterElAwal+(hasilQuery.getString("kodecostcenter")==null?"":hasilQuery.getString("kodecostcenter"))+GridExtJsFormat_VMHRIS_bpartnerlengkap.kodecostcenterElAkhir+
                        GridExtJsFormat_VMHRIS_bpartnerlengkap.useridElAwal+(hasilQuery.getString("userid")==null?"":hasilQuery.getString("userid"))+GridExtJsFormat_VMHRIS_bpartnerlengkap.useridElAkhir+
                       GridExtJsFormat_VMHRIS_bpartnerlengkap.rowElAkhir;

            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convert to GridExtJsFormat_vm_hris_bpartnerlengkap : " + ex);
        }
        hasil+=GridExtJsFormat_VMHRIS_bpartnerlengkap.rootElAkhir;
        return hasil;
    }    
}
