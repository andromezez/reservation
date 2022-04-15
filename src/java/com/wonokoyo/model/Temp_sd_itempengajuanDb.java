/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.utilities.datetime.OracleDateAndTimeStringFormat;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_SD_ItemPengajuan;
import com.wonokoyo.model.strukturdata.SD_ItemPengajuan;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author king peter
 */
public class Temp_sd_itempengajuanDb {
    
    private ResultSet hasilQuery;
    private DBConnection dBConnection;    
    public Temp_sd_itempengajuanDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class Temp_sd_itempengajuanDb: " + ex);
        }
    }
    public String selectCustomRow(String sql){
        try {            
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error in class Temp_sd_itempengajuanDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_Temp_sd_itempengajuanDb();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String selectAllRow(){
        String sql = "select a.kodetp,a.kodeitem,b.namaitem,a.jumlahawal,a.jumlahakhir,TO_CHAR(a.jamawal,'"+OracleDateAndTimeStringFormat.timeOnlyWithoutSec+"') jamawal, " +
	   "TO_CHAR(a.jamakhir,'"+OracleDateAndTimeStringFormat.timeOnlyWithoutSec+"') jamakhir ,a.durasi,a.biaya,a.antrian,a.keterangan, a.biayaperjam " +
           "from temp_sd_itempengajuan a, m_item b " +
           //"where a.kodetp = '" +pkodetp+ "' " +
           " where a.kodeitem = b.kodeitem(+)";
        try {            
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table in class Temp_sd_itempengajuanDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_Temp_sd_itempengajuanDb();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String convertToGridExtJsFormat_Temp_sd_itempengajuanDb(){
        String hasil = GridExtJsFormat_SD_ItemPengajuan.rootElAwal;
        try {
            while(hasilQuery.next()){
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.rowElAwal;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.kodetpElAwal+hasilQuery.getString("kodetp")+GridExtJsFormat_SD_ItemPengajuan.kodetpElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.kodeitemElAwal+(hasilQuery.getString("kodeitem")==null?"":hasilQuery.getString("kodeitem"))+GridExtJsFormat_SD_ItemPengajuan.kodeitemElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.namaitemElAwal+(hasilQuery.getString("namaitem")==null?"":hasilQuery.getString("namaitem")) +GridExtJsFormat_SD_ItemPengajuan.namaitemElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.jumlahawalElAwal+hasilQuery.getInt("jumlahawal") +GridExtJsFormat_SD_ItemPengajuan.jumlahawalElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.jumlahakhirElAwal+hasilQuery.getInt("jumlahakhir")+GridExtJsFormat_SD_ItemPengajuan.jumlahakhirElAkhir;
                   /*hasil+=GridExtJsFormat_SD_ItemPengajuan.jamawalElAwal+
                           OracleDateAndTimeStringFormat.convertJavaSQLTimestampToString(hasilQuery.getTimestamp("jamawal"), OracleDateAndTimeStringFormat.dateAndTimeFormat)+
                           GridExtJsFormat_SD_ItemPengajuan.jamawalElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.jamakhirElAwal+
                           OracleDateAndTimeStringFormat.convertJavaSQLTimestampToString(hasilQuery.getTimestamp("jamakhir"), OracleDateAndTimeStringFormat.dateAndTimeFormat)+
                           GridExtJsFormat_SD_ItemPengajuan.jamakhirElAkhir;*/
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.jamawalElAwal+(hasilQuery.getString("jamawal")==null?"":hasilQuery.getString("jamawal"))+GridExtJsFormat_SD_ItemPengajuan.jamawalElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.jamakhirElAwal+(hasilQuery.getString("jamakhir")==null?"":hasilQuery.getString("jamakhir"))+GridExtJsFormat_SD_ItemPengajuan.jamakhirElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.durasiElAwal+hasilQuery.getInt("durasi")+GridExtJsFormat_SD_ItemPengajuan.durasiElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.biayaElAwal+hasilQuery.getInt("biaya")+GridExtJsFormat_SD_ItemPengajuan.biayaElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.antrianElAwal+hasilQuery.getInt("antrian")+GridExtJsFormat_SD_ItemPengajuan.antrianElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.keteranganElAwal+(hasilQuery.getString("keterangan")==null?"":hasilQuery.getString("keterangan"))+GridExtJsFormat_SD_ItemPengajuan.keteranganElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.biayaperjamElAwal+hasilQuery.getInt("biayaperjam") +GridExtJsFormat_SD_ItemPengajuan.biayaperjamElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_Temp_sd_itempengajuanDb : " + ex);
        }
        hasil+=GridExtJsFormat_SD_ItemPengajuan.rootElAkhir;
        return hasil;
    }
}
