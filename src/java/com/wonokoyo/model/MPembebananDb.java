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
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MPembebanan;
import com.wonokoyo.model.strukturdata.M_Pembebanan;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.CallableStatement;
import oracle.jdbc.internal.OracleCallableStatement;

public class MPembebananDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    //private ResultSet rs testing;
    public MPembebananDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class MPembebananDb: " + ex);
        }
    }

    public String insertOneRow(String kodepembebanan, String namapembebanan){
        String tambahpembebanan = "INSERT INTO M_PEMBEBANAN(KODEPEMBEBANAN, NAMAPEMBEBANAN)" +
                "VALUES ('"+kodepembebanan +"', '"+ namapembebanan +"')";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master pembebanan telah tersimpan.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MPembebanan(kodepembebanan, namapembebanan, "insert");
            if( datakembar == null){
                dBConnection.getStmt().executeUpdate(tambahpembebanan);
            }else{
                dBConnection.isErrorProcessingDb=true;
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table M_PEMBEBANAN in class MPembebananDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data master pembebanan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(String kodepembebanan, String namapembebanan){
        String updatepembebanan = "UPDATE M_PEMBEBANAN SET NAMAPEMBEBANAN = '"+namapembebanan+"' "
                + "WHERE KODEPEMBEBANAN = '"+kodepembebanan+"'";
         String hasil = SuccessErrorMessege.generateMessege(true, "Data master pembebanan telah terupdate.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MPembebanan(kodepembebanan, namapembebanan, "update");
            if( datakembar == null){
                dBConnection.getStmt().executeUpdate(updatepembebanan);
            }else{
                dBConnection.isErrorProcessingDb=true;
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_PEMBEBANAN in class MPembebananDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data master pembebanan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteOneRow(String kodepembebanan){
        String deletepembebanan = "DELETE FROM M_PEMBEBANAN WHERE KODEPEMBEBANAN = '"+kodepembebanan+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master pembebanan telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(deletepembebanan);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_PEMBEBANAN in class MPembebananDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data master pembebanan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public M_Pembebanan selectOneRow(String kodepembebanan){
        String selectpembebanan = "SELECT * FROM M_PEMBEBANAN WHERE kodepembebanan = '"+kodepembebanan+"'";
        M_Pembebanan mpembebanan = new M_Pembebanan();
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selectpembebanan);
            while(hasilQuery.next()){
                mpembebanan.KODEPEMBEBANAN=hasilQuery.getString("KODEPEMBEBANAN");
                mpembebanan.NAMAPEMBEBANAN=hasilQuery.getString("NAMAPEMBEBANAN");
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_PEMBEBANAN in class MPembebananDb method selectOneRow: " + ex);
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return mpembebanan;
    }
    public String selectAllRowToGridExtJsFormat_MPembebanan(){
        String selectpembebanan = "SELECT * FROM M_PEMBEBANAN";
        String hasil = GridExtJsFormat_MPembebanan.rootElAwal;
        int n,i;
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selectpembebanan);
            while(hasilQuery.next()){
                hasil+=GridExtJsFormat_MPembebanan.rowElAwal+
                        GridExtJsFormat_MPembebanan.kodepembebananElAwal+hasilQuery.getString("KODEPEMBEBANAN")+GridExtJsFormat_MPembebanan.kodepembebananElAkhir+
                        GridExtJsFormat_MPembebanan.namapembebananElAwal+hasilQuery.getString("NAMAPEMBEBANAN")+GridExtJsFormat_MPembebanan.namapembebananElAkhir+
                       GridExtJsFormat_MPembebanan.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_PEMBEBANAN in class MPembebananDb method selectAllRow: " + ex);
        }
        hasil+=GridExtJsFormat_MPembebanan.rootElAkhir;
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
}
