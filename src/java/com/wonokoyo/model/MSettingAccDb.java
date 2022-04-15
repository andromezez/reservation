/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SK
 */

package com.wonokoyo.model;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MSettingAcc;
import com.wonokoyo.model.strukturdata.M_SettingAcc;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import java.sql.CallableStatement;
import oracle.jdbc.internal.OracleCallableStatement;

public class MSettingAccDb{
    private ResultSet hasilQuery;
    
    private DBConnection dBConnection;    
    public MSettingAccDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset: " + ex);
        }
    }

    public String insertOneRow(String kodeform, String accform, String editfinalapprove){
        String tambahsettingacc = "INSERT INTO M_SETTINGACC(KODEFORM, ACCFORM, EDITFINALAPPROVE)" +
                "VALUES ('"+kodeform +"', '"+ accform +"', '"+ editfinalapprove +"')";
        
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master setting acc telah tersimpan.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MSettingacc(kodeform);
            if(datakembar == null){
                dBConnection.getStmt().executeUpdate(tambahsettingacc);
            }else{
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);

            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table M_SETTINGACC in class MSettingAccDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data master setting acc: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(String kodeform, String accform, String editfinalapprove){
        String updatesettingacc = "UPDATE M_SETTINGACC SET ACCFORM = '"+accform+"', "
                + "EDITFINALAPPROVE='"+editfinalapprove+"' "
                + "WHERE KODEFORM = '"+kodeform+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master setting acc telah terupdate.", "");
        try {
            dBConnection.getStmt().executeUpdate(updatesettingacc);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_SETTINGACC in class MSettingAccDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data master setting acc: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteOneRow(String kodeform){
        String deletesettingacc = "DELETE FROM M_SETTINGACC WHERE KODEFORM = '"+kodeform+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master setting acc telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(deletesettingacc);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error delete table M_SETTINGACC in class MSettingAccDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data master setting acc: "+DBConnection.getOracleErrorSentence(ex));
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
            System.err.println("Error class MSettingAccDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MSettingAcc();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String selectAllRow(){
        String sql = "SELECT a.KODEFORM, b.namaform, a.accform, a.editfinalapprove  "
                + "FROM M_SETTINGACC a, M_MENU b "
                + "WHERE a.kodeform = b.kodeform(+)";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table in class MSettingAccDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MSettingAcc();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String convertToGridExtJsFormat_MSettingAcc(){
        String hasil = GridExtJsFormat_MSettingAcc.rootElAwal;
        try {
            while(hasilQuery.next()){
                hasil+=GridExtJsFormat_MSettingAcc.rowElAwal+
                        GridExtJsFormat_MSettingAcc.kodeformElAwal+hasilQuery.getString("KODEFORM")+GridExtJsFormat_MSettingAcc.kodeformElAkhir+
                        GridExtJsFormat_MSettingAcc.namaformElAwal+hasilQuery.getString("NAMAFORM")+GridExtJsFormat_MSettingAcc.namaformElAkhir+
                        GridExtJsFormat_MSettingAcc.accformElAwal+hasilQuery.getString("ACCFORM")+GridExtJsFormat_MSettingAcc.accformElAkhir+
                        GridExtJsFormat_MSettingAcc.editfinalapproveElAwal+hasilQuery.getString("EDITFINALAPPROVE")+GridExtJsFormat_MSettingAcc.editfinalapproveElAkhir+
                       GridExtJsFormat_MSettingAcc.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_MSettingAcc: " + ex);
        }
        hasil+=GridExtJsFormat_MSettingAcc.rootElAkhir;
        return hasil;
    }    
}
