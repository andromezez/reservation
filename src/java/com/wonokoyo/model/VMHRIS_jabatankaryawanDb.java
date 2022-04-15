/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model;

import com.wonokoyo.model.strukturdata.VM_Hris_JabatanKaryawan;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Indra
 */
public class VMHRIS_jabatankaryawanDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;    
    public VMHRIS_jabatankaryawanDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class VMjabatankaryawan: " + ex);
        }
    }
    
    public VM_Hris_JabatanKaryawan selectOneRow(String puserid){
        String selectparam = "select count(a.kodejabatan) TOTAL from vm_hris_jabatankaryawan a,m_user b,vm_hris_bpartnerlengkap c where a.tglakhirmenjabat is  null and b.userid='"+puserid+"' and b.kodebp=c.kodebp and a.nik=c.nik";
        VM_Hris_JabatanKaryawan jabatan = new VM_Hris_JabatanKaryawan();
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selectparam);
            while(hasilQuery.next()){
                jabatan.TOTAL = hasilQuery.getInt("TOTAL");
                
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table vm_hris_jabatankaryawan in class VMHRIS_jabatankaryawanDb method selectOneRow: " + ex);
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return jabatan;
    }
}
