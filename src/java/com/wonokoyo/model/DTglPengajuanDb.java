/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.utilities.datetime.OracleDateAndTimeStringFormat;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_DTglPengajuan;
import com.wonokoyo.model.strukturdata.D_TglPengajuan;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Bayu
 */
public class DTglPengajuanDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    //private ResultSet rs testing;
    public DTglPengajuanDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class DTglPengajuanDb: " + ex);
        }
    }
    public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class DTglPengajuanDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_DTglPengajuan();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String selectAllRow(){
        String sql =  "select KODETP,KODEPENGAJUAN ,to_char(TGLAWAL,'mm/dd/yyyy') TGLAWAL,to_char(TGLAKHIR,'mm/dd/yyyy') TGLAKHIR, NAMATARIF from D_TGLPENGAJUAN " ;

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table d_tglpengajuan in class DTglPengajuanDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_DTglPengajuan();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String convertToGridExtJsFormat_DTglPengajuan(){
        String hasil = GridExtJsFormat_DTglPengajuan.rootElAwal;
        try {
            while(hasilQuery.next()){
                   hasil+=GridExtJsFormat_DTglPengajuan.rowElAwal;
                   hasil+=GridExtJsFormat_DTglPengajuan.kodetpElAwal+(hasilQuery.getString("kodetp")==null?"":hasilQuery.getString("kodetp"))+GridExtJsFormat_DTglPengajuan.kodetpElAkhir;
                   hasil+=GridExtJsFormat_DTglPengajuan.kodepengajuanElAwal+(hasilQuery.getString("kodepengajuan")==null?"":hasilQuery.getString("kodepengajuan"))+GridExtJsFormat_DTglPengajuan.kodepengajuanElAkhir;
                   hasil+=GridExtJsFormat_DTglPengajuan.tglawalElAwal+
                           (hasilQuery.getString("tglawal")==null?"":hasilQuery.getString("tglawal")) +
                           GridExtJsFormat_DTglPengajuan.tglawalElAkhir;
                   hasil+=GridExtJsFormat_DTglPengajuan.tglakhirElAwal+
                           (hasilQuery.getString("tglakhir")==null?"":hasilQuery.getString("tglakhir"))+
                           GridExtJsFormat_DTglPengajuan.tglakhirElAkhir;
                   hasil+=GridExtJsFormat_DTglPengajuan.namatarifElAwal+
                           (hasilQuery.getString("namatarif")==null?"":hasilQuery.getString("namatarif"))+
                           GridExtJsFormat_DTglPengajuan.namatarifElAkhir;
                   hasil+=GridExtJsFormat_DTglPengajuan.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_DTglPengajuan : " + ex);
        }
        hasil+=GridExtJsFormat_DTglPengajuan.rootElAkhir;
        return hasil;
    }
    
    public String insertOneRow(D_TglPengajuan d_TglPengajuan){
        String sql = "INSERT INTO D_TGLPENGAJUAN(KODETP,KODEPENGAJUAN,TGLAWAL,TGLAKHIR,NAMATARIF)" +
                "VALUES ('"+d_TglPengajuan.KODETP +"', '"+ d_TglPengajuan.KODEPENGAJUAN +"', "+ 
                "TO_DATE(substr('"+ d_TglPengajuan.TGLAWAL +"',1,10),'"+OracleDateAndTimeStringFormat.dateFormat+"') , "
                +"TO_DATE(substr('"+ d_TglPengajuan.TGLAKHIR +"',1,10),'"+OracleDateAndTimeStringFormat.dateFormat+"'), "
                + "'"+d_TglPengajuan.NAMATARIF+"' ) ";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data detil tgl pengajuan telah tersimpan.", "");
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table D_TGLPENGAJUAN in class DTglPengajuanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data detil tgl pengajuan : "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(D_TglPengajuan d_TglPengajuan){
        String sql = "UPDATE D_TGLPENGAJUAN SET KODEPENGAJUAN = '"+ d_TglPengajuan.KODEPENGAJUAN+
                "', TGLAWAL =TO_DATE(substr('"+ d_TglPengajuan.TGLAWAL +"',1,10),'"+OracleDateAndTimeStringFormat.dateFormat+"') , "
                +" TGLAKHIR = TO_DATE(substr('"+ d_TglPengajuan.TGLAKHIR +"',1,10),'"+OracleDateAndTimeStringFormat.dateFormat+"'), "                     
                +" NAMATARIF = '"+d_TglPengajuan.NAMATARIF+"'"
                +" WHERE KODETP = '"+ d_TglPengajuan.KODETP+"'";
         String hasil = SuccessErrorMessege.generateMessege(true, "Data detil tgl pengajuan telah terupdate.", "");               
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table D_TGLPENGAJUAN in class DTglPengajuanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate detil tgl pengajuan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteRowByKodePengajuan(String kodepengajuan){
        String sql = "DELETE FROM D_TGLPENGAJUAN WHERE KODEPENGAJUAN = '"+kodepengajuan+"'";
            String hasil = SuccessErrorMessege.generateMessege(true, "Detil tgl pengajuan telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error delete table D_TGLPENGAJUAN in class DTglPengajuanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus detil tgl pengajuan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
}
