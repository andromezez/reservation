/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SK
 */

package com.wonokoyo.model;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MHKonfigurasiAcc;
import com.wonokoyo.model.strukturdata.MH_KonfigurasiAcc;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.CallableStatement;


public class MHKonfigurasiAccDb{
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    public MHKonfigurasiAccDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class MKonfigurasiAccDb: " + ex);
        }
    }

    public String insertOneRow(String kodeacc, String kodeform, Integer prioritas, String keterangan){
        String tambahkonfigurasi = "INSERT INTO MH_KONFIGURASIACC(KODEACC, KODEFORM, PRIORITAS, KETERANGAN)" +
                "VALUES ('"+kodeacc +"', '"+ kodeform +"', '"+ prioritas +"', '"+ keterangan +"')";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master konfigurasi acc telah tersimpan.", "");
        /*String hasil = "{" +
                  "success: true," +
                  "errors: {"+

                  "},"+
                    "successmsg: \"Data master konfigurasi acc telah tersimpan.\""+
                  "}";*/
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MHKonfAcc(kodeacc, kodeform, prioritas, "insert");
            if( datakembar == null){
                dBConnection.getStmt().executeUpdate(tambahkonfigurasi);
            }else{
                dBConnection.isErrorProcessingDb=true;
                System.err.println("Error insert table MH_KONFIGURASIACC in class MHKonfigurasiAccDb: "+datakembar);
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar+".");
                return hasil;
                /*hasil = "{" +
                  "success: false," +
                  "errors: {"+

                  "},"+
                    //"errormsg: \"Error.\""+
                    "errormsg: \""+ datakembar +".\""+
                  "}";*/
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table MH_KONFIGURASIACC in class MHKonfigurasiAccDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data master konfigurasi acc: "+DBConnection.getOracleErrorSentence(ex));
            /*hasil = "{" +
                  "success: false," +
                  "errors: {"+

                  "},"+
                    "errormsg: \"Tidak bisa menyimpan data master konfigurasi acc.\""+
                  "}";*/
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(String kodeacc, String kodeform, Integer prioritas, String keterangan){
        String updatekonfigurasi = "UPDATE MH_KONFIGURASIACC SET KODEFORM = '"+kodeform+"', "
                + "PRIORITAS='"+prioritas+"'," + "KETERANGAN='"+keterangan+"' "
                + "WHERE kodeacc = '"+kodeacc+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master konfigurasi acc telah terupdate.", "");
        /*String hasil = "{" +
                  "success: true," +
                  "errors: {"+

                  "},"+
                    "successmsg: \"Data master konfigurasi acc telah terupdate.\""+
                  "}";*/
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MHKonfAcc(kodeacc, kodeform, prioritas, "update");
            if( datakembar == null){
                dBConnection.getStmt().executeUpdate(updatekonfigurasi);
            }else{
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar+".");
                /*hasil = "{" +
                  "success: false," +
                  "errors: {"+

                  "},"+
                    //"errormsg: \"Error.\""+
                    "errormsg: \""+ datakembar +".\""+
                  "}";*/
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table MH_KONFIGURASIACC in class MHKonfigurasiAccDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data master konfigurasi acc: "+DBConnection.getOracleErrorSentence(ex));
            /*hasil = "{" +
                  "success: false," +
                  "errors: {"+

                  "},"+
                    "errormsg: \"Tidak bisa mengupdate data master konfigurasi acc.\""+
                  "}";*/
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteOneRow(String kodeacc){
        String deletekonfigurasi = "DELETE FROM MH_KONFIGURASIACC WHERE KODEACC = '"+kodeacc+"'";
        /*String hasil = "{" +
                  "success: true," +
                  "errors: {"+

                  "},"+
                    "successmsg: \"Data master konfigurasi acc telah terhapus.\""+
                  "}";*/
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master konfigurasi acc telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(deletekonfigurasi);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table MH_KONFIGURASIACC in class MHKonfigurasiAccDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data master konfigurasi acc: "+DBConnection.getOracleErrorSentence(ex));
            /*hasil = "{" +
                  "success: false," +
                  "errors: {"+

                  "},"+
                    "errormsg: \"Tidak bisa menghapus data master konfigurasi acc.\""+
                  "}";*/
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class MHKonfigurasiAccDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MHKonfigurasiAcc();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public MH_KonfigurasiAcc selectOneRow(String kodeform){
        String selectparam = "select count(kodeform) TOTAL from mh_konfigurasiacc where kodeform='"+kodeform+"'";
        MH_KonfigurasiAcc konfigurasi = new MH_KonfigurasiAcc();
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selectparam);
            while(hasilQuery.next()){
                konfigurasi.TOTAL = hasilQuery.getInt("TOTAL");
                
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table vm_hris_jabatankaryawan in class VMHRIS_jabatankaryawanDb method selectOneRow: " + ex);
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return konfigurasi;
    }
    public String convertToGridExtJsFormat_MHKonfigurasiAcc(){
        String hasil = GridExtJsFormat_MHKonfigurasiAcc.rootElAwal;
        try {
            while(hasilQuery.next()){
                String keterangan = hasilQuery.getString("KETERANGAN");
                if(keterangan==null){
                    keterangan = "";
                }
                hasil+=GridExtJsFormat_MHKonfigurasiAcc.rowElAwal+
                        GridExtJsFormat_MHKonfigurasiAcc.kodeaccElAwal+hasilQuery.getString("KODEACC")+GridExtJsFormat_MHKonfigurasiAcc.kodeaccElAkhir+
                        GridExtJsFormat_MHKonfigurasiAcc.kodeformElAwal+hasilQuery.getString("KODEFORM")+GridExtJsFormat_MHKonfigurasiAcc.kodeformElAkhir+
                        GridExtJsFormat_MHKonfigurasiAcc.namaformElAwal+hasilQuery.getString("NAMAFORM")+GridExtJsFormat_MHKonfigurasiAcc.namaformElAkhir+
                        GridExtJsFormat_MHKonfigurasiAcc.prioritasElAwal+hasilQuery.getString("PRIORITAS")+GridExtJsFormat_MHKonfigurasiAcc.prioritasElAkhir+
                        GridExtJsFormat_MHKonfigurasiAcc.keteranganElAwal+keterangan+GridExtJsFormat_MHKonfigurasiAcc.keteranganElAkhir+
                       GridExtJsFormat_MHKonfigurasiAcc.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_MHKonfigurasiAcc : " + ex);
        }
        hasil+=GridExtJsFormat_MHKonfigurasiAcc.rootElAkhir;
        return hasil;
    }

    public String selectAllRow(){
        String selectkonfigurasi = "select a.kodeacc, a.kodeform, b.namaform, a.prioritas, a.keterangan "
                + "FROM MH_KONFIGURASIACC a, m_menu b "
                + "WHERE a.kodeform = b.kodeform ";                        
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selectkonfigurasi);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table MH_KONFIGURASIACC in class MHKonfigurasiAccDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MHKonfigurasiAcc();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
}
