/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bayu
 */

package com.wonokoyo.model;

import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MJenis;
import com.wonokoyo.model.strukturdata.M_Jenis;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.CallableStatement;


public class MJenisDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    public MJenisDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class MJenisDb: " + ex);
        }
    }

    public String insertOneRow(String kodejenis, String namajenis){
        String tambahjenis = "INSERT INTO M_JENIS(KODEJENIS, NAMAJENIS)" +
                "VALUES ('"+kodejenis +"', '"+ namajenis +"')";
        /*String hasil = "{" +
                  "success: true," +                  
                  "errors: {"+
                      
                  "},"+
                    "successmsg: \"Data master jenis telah tersimpan.\""+
                  "}";*/
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master jenis telah tersimpan.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MJenis(kodejenis, namajenis, "insert");
            if( datakembar == null){
                dBConnection.getStmt().executeUpdate(tambahjenis);
            }else{
                dBConnection.isErrorProcessingDb=true;
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
                /*hasil = "{" +
                  "success: false," +
                  "errors: {"+

                  "},"+
                    //"errormsg: \"Error.\""+
                    "errormsg: \""+ datakembar +" telah ada sebelumnya.\""+
                  "}";*/
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table M_JENIS in class MJenisDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data master jenis: "+DBConnection.getOracleErrorSentence(ex));
            /*hasil = "{" +
                  "success: false," +
                  "errors: {"+
                      
                  "},"+
                    "errormsg: \"Tidak bisa menyimpan data master jenis.\""+
                  "}";*/
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(String kodejenis, String namajenis){
        String updatejenis = "UPDATE M_JENIS SET NAMAJENIS = '"+namajenis+"' "
                + "WHERE KODEJENIS = '"+kodejenis+"'";
         /*String hasil = "{" +
                  "success: true," +
                  "errors: {"+

                  "},"+
                    "successmsg: \"Data master jenis telah terupdate.\""+
                  "}";*/
         String hasil = SuccessErrorMessege.generateMessege(true, "Data master jenis telah terupdate.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MJenis(kodejenis, namajenis, "update");
            if( datakembar == null){
                dBConnection.getStmt().executeUpdate(updatejenis);
            }else{
                dBConnection.isErrorProcessingDb=true;
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
                /*hasil = "{" +
                  "success: false," +
                  "errors: {"+

                  "},"+
                    //"errormsg: \"Error.\""+
                    "errormsg: \""+ datakembar +" telah ada sebelumnya.\""+
                  "}";*/
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_JENIS in class MJenisDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data master jenis: "+DBConnection.getOracleErrorSentence(ex));
            /*hasil = "{" +
                  "success: false," +
                  "errors: {"+

                  "},"+
                    "errormsg: \"Tidak bisa mengupdate data master jenis.\""+
                  "}";*/
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteOneRow(String kodejenis){
        String deletejenis = "DELETE FROM M_JENIS WHERE KODEJENIS = '"+kodejenis+"'";
        /*String hasil = "{" +
                  "success: true," +                  
                  "errors: {"+
                      
                  "},"+
                    "successmsg: \"Data master jenis telah terhapus.\""+
                  "}";*/
            String hasil = SuccessErrorMessege.generateMessege(true, "Data master jenis telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(deletejenis);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_JENIS in class MJenisDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data master jenis: "+DBConnection.getOracleErrorSentence(ex));
            /*"{" +
                  "success: false," +
                  "errors: {"+
                      
                  "},"+
                    "errormsg: \"Tidak bisa menghapus data master jenis: "+getOracleErrorSentence(ex)+"\""+
                  "}";*/
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public M_Jenis selectOneRow(String kodejenis){
        String selectjenis = "SELECT * FROM M_JENIS WHERE kodejenis = '"+kodejenis+"'";
        M_Jenis mjenis = new M_Jenis();
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selectjenis);
            while(hasilQuery.next()){
                mjenis.KODEJENIS=hasilQuery.getString("KODEJENIS");
                mjenis.NAMAJENIS=hasilQuery.getString("NAMAJENIS");
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_JENIS in class MJenisDb method selectOneRow: " + ex);
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return mjenis;
    }
    public String selectAllRowToGridExtJsFormat_MJenis(){
        String selectjenis = "SELECT * FROM M_JENIS";
        String hasil = GridExtJsFormat_MJenis.rootElAwal;
        int n,i;
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selectjenis);
            while(hasilQuery.next()){
                hasil+=GridExtJsFormat_MJenis.rowElAwal+
                        GridExtJsFormat_MJenis.kodejenisElAwal+hasilQuery.getString("KODEJENIS")+GridExtJsFormat_MJenis.kodejenisElAkhir+
                        GridExtJsFormat_MJenis.namajenisElAwal+hasilQuery.getString("NAMAJENIS")+GridExtJsFormat_MJenis.namajenisElAkhir+
                       GridExtJsFormat_MJenis.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_JENIS in class MJenisDb method selectAllRow: " + ex);
        }
        hasil+=GridExtJsFormat_MJenis.rootElAkhir;
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
}
