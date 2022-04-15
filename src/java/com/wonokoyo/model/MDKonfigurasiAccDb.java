/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MDKonfigurasiAcc;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Eddy
 */
public class MDKonfigurasiAccDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    //private ResultSet rs testing;
    public MDKonfigurasiAccDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class MDKonfigurasiAccDb: " + ex);
        }
    }
    public String selectCustomRow(String sql){
          //"SELECT * FROM vm_hris_bpartnerlengkap WHERE kodecostcenter = '"+filterCostCenter+"'";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class MDKonfigurasiAccDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MDKonfigurasiAcc();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String selectAllRow(){
        //String sql = "select KODEACC, USERID from MD_KONFIGURASIACC";
        String sql = "select KODEACC, USERID, KODEJABATAN, DEFAULTACC, '' KETERANGAN from MD_KONFIGURASIACC";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table in class MDKonfigurasiAccDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MDKonfigurasiAcc();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String insertOneRow(String kodeacc, String userid, String kodejabatan,String defaultacc){
        String tambahmdkonfacc = "INSERT INTO MD_KONFIGURASIACC (KODEACC, USERID, KODEJABATAN, DEFAULTACC) " +
                "VALUES ('"+kodeacc +"', '"+ userid +"', '"+ kodejabatan +"', '"+ defaultacc +"')";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data detail konfigurasi acc telah tersimpan.", "");
        try {
            /*DB_Procedure procedure = new DB_Procedure();
            String datakembar = procedure.cekDataKembar_MJenis(kodepembebanan, kodecostcenter, "insert");
            if( datakembar == null){*/
                dBConnection.getStmt().executeUpdate(tambahmdkonfacc);
            /*}else{
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
            }*/
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table MD_KONFIGURASIACC in class MD_KONFIGURASIACCDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data detail konfigurasi acc: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(String kodeacc, String userid, String kodejabatan, String defaultacc){
        String updatemdkonfacc = "UPDATE MD_KONFIGURASIACC SET USERID = '"+userid+"', "
                + "KODEJABATAN='"+kodejabatan+"' "
                + "DEFAULTACC='"+defaultacc+"'"
                + "WHERE KODEACC = '"+kodeacc+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data detail konfigurasi acc telah terupdate.", "");
        try {
            /*DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MJenis(kodejenis, namajenis, "update");
            if( datakembar == null){*/
                dBConnection.getStmt().executeUpdate(updatemdkonfacc);
            /*}else{
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
            }*/
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table MD_KONFIGURASIACC in class MD_KONFIGURASIACCDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data detail konfigurasi acc: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteOneRow(String kodeacc){
        String deletemdkonfacc = "DELETE FROM MD_KONFIGURASIACC WHERE KODEACC = '"+kodeacc+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data detail konfigurasi acc telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(deletemdkonfacc);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table MD_KONFIGURASIACC in class MD_KONFIGURASIACCDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data detail konfigurasi acc: "+DBConnection.getOracleErrorSentence(ex));
            }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String convertToGridExtJsFormat_MDKonfigurasiAcc(){
        String hasil = GridExtJsFormat_MDKonfigurasiAcc.rootElAwal;
        try {
            while(hasilQuery.next()){
                String userid = hasilQuery.getString("userid");
                String kodebp = hasilQuery.getString("kodebp");
                String namabp = hasilQuery.getString("namabp");
                String kodejabatan = hasilQuery.getString("kodejabatan");
                String namajabatan = hasilQuery.getString("namajabatan");
                String defaultacc= hasilQuery.getString("defaultacc");
                String keterangan= hasilQuery.getString("keterangan");
                if(userid==null){
                    userid = "";
                }
                if(namabp==null){
                    namabp = "";
                }
                if(kodejabatan==null){
                    kodejabatan = "";
                }
                if(namajabatan==null){
                    namajabatan = "";
                }
                if(kodebp==null){
                    kodebp = "";
                }
                if(defaultacc==null)
                {
                    defaultacc="";
                }
                if(keterangan==null)
                {
                    keterangan="";
                }
                   hasil+=GridExtJsFormat_MDKonfigurasiAcc.rowElAwal;
                   hasil+=GridExtJsFormat_MDKonfigurasiAcc.kodeaccElAwal+hasilQuery.getString("kodeacc")+GridExtJsFormat_MDKonfigurasiAcc.kodeaccElAkhir;
                   hasil+=GridExtJsFormat_MDKonfigurasiAcc.useridElAwal+userid+GridExtJsFormat_MDKonfigurasiAcc.useridElAkhir;
                   hasil+=GridExtJsFormat_MDKonfigurasiAcc.kodebpElAwal+kodebp+GridExtJsFormat_MDKonfigurasiAcc.kodebpElAkhir;
                   hasil+=GridExtJsFormat_MDKonfigurasiAcc.namabpElAwal+namabp+GridExtJsFormat_MDKonfigurasiAcc.namabpElAkhir;
                   hasil+=GridExtJsFormat_MDKonfigurasiAcc.kodejabatanElAwal+kodejabatan+GridExtJsFormat_MDKonfigurasiAcc.kodejabatanElAkhir;
                   hasil+=GridExtJsFormat_MDKonfigurasiAcc.namajabatanElAwal+namajabatan+GridExtJsFormat_MDKonfigurasiAcc.namajabatanElAkhir;
                   hasil+=GridExtJsFormat_MDKonfigurasiAcc.defaultaccElAwal+defaultacc+GridExtJsFormat_MDKonfigurasiAcc.defaultaccElAkhir;
                   hasil+=GridExtJsFormat_MDKonfigurasiAcc.keteranganElAwal+keterangan+GridExtJsFormat_MDKonfigurasiAcc.keteranganElAkhir;
                   hasil+=GridExtJsFormat_MDKonfigurasiAcc.rowElAkhir;


            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_MDKonfigurasiAcc : " + ex);
        }
        hasil+=GridExtJsFormat_MDKonfigurasiAcc.rootElAkhir;
        return hasil;
    }
}
