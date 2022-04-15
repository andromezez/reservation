/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model;

import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_DTglRealisasi;
import com.wonokoyo.model.strukturdata.D_TglRealisasi;
import com.wonokoyo.model.utilities.datetime.OracleDateAndTimeStringFormat;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Indra
 */
public class DTglRealisasiDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    //private ResultSet rs testing;
    public DTglRealisasiDb(DBConnection dBConnection) {
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
        String hasil = convertToGridExtJsFormat_DTglRealisasi();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public String insertOneRow(D_TglRealisasi d_TglRealisasi){
        String sql = "INSERT INTO D_TGLREALISASI(KODETR,KODEREALISASI,TGLAWAL,TGLAKHIR,NAMATARIF)" +
                "VALUES ('"+d_TglRealisasi.KODETR +"', '"+ d_TglRealisasi.KODEREALISASI +"', "+ 
                "TO_DATE(substr('"+ d_TglRealisasi.TGLAWAL +"',1,10),'"+OracleDateAndTimeStringFormat.dateFormat+"') , "
                +"TO_DATE(substr('"+ d_TglRealisasi.TGLAKHIR +"',1,10),'"+OracleDateAndTimeStringFormat.dateFormat+"'), "
                + "'"+d_TglRealisasi.NAMATARIF+"' ) ";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data detil tgl pengajuan telah tersimpan.", "");
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table D_TGLREALISASI in class DTglrealisasiDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data detil tglrealisasi : "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(D_TglRealisasi d_TglRealisasi){
        String sql = "UPDATE D_TGLPENGAJUAN SET KODEREALISASI = '"+ d_TglRealisasi.KODEREALISASI+
                "', TGLAWAL =TO_DATE(substr('"+ d_TglRealisasi.TGLAWAL +"',1,10),'"+OracleDateAndTimeStringFormat.dateFormat+"') , "
                +" TGLAKHIR = TO_DATE(substr('"+ d_TglRealisasi.TGLAKHIR +"',1,10),'"+OracleDateAndTimeStringFormat.dateFormat+"') , "
                +" NAMATARIF = '"+d_TglRealisasi.NAMATARIF+"'"
                +" WHERE KODETR = '"+ d_TglRealisasi.KODETR+"'";
         String hasil = SuccessErrorMessege.generateMessege(true, "Data detil tgl realisasi telah terupdate.", "");               
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table D_TGLREALISASI in class DTglRealisasiDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate detil tgl relisasi: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteRowByKodeRealisasi(String koderealisasi){
        String sql = "DELETE FROM D_TGLREALISASI WHERE KODEREALISASI = '"+koderealisasi+"'";
            String hasil = SuccessErrorMessege.generateMessege(true, "Detil tgl realisasi telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error delete table D_TGLREALISASI in class DTglRealisasiDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus detil tgl relisasi: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public String convertToGridExtJsFormat_DTglRealisasi(){
        String hasil = GridExtJsFormat_DTglRealisasi.rootElAwal;
        try {
            while(hasilQuery.next()){
                   hasil+=GridExtJsFormat_DTglRealisasi.rowElAwal;
                   hasil+=GridExtJsFormat_DTglRealisasi.kodetrElAwal+(hasilQuery.getString("kodetr")==null?"":hasilQuery.getString("kodetr"))+GridExtJsFormat_DTglRealisasi.kodetrElAkhir;
                   hasil+=GridExtJsFormat_DTglRealisasi.koderealisasiElAwal+(hasilQuery.getString("koderealisasi")==null?"":hasilQuery.getString("koderealisasi"))+GridExtJsFormat_DTglRealisasi.koderealisasiElAkhir;
                   hasil+=GridExtJsFormat_DTglRealisasi.tglawalElAwal+
                           (hasilQuery.getString("tglawal")==null?"":hasilQuery.getString("tglawal")) +
                           GridExtJsFormat_DTglRealisasi.tglawalElAkhir;
                   hasil+=GridExtJsFormat_DTglRealisasi.tglakhirElAwal+
                           (hasilQuery.getString("tglakhir")==null?"":hasilQuery.getString("tglakhir"))+
                           GridExtJsFormat_DTglRealisasi.tglakhirElAkhir;
                   hasil+=GridExtJsFormat_DTglRealisasi.namatarifElAwal+
                           (hasilQuery.getString("namatarif")==null?"":hasilQuery.getString("namatarif"))+
                           GridExtJsFormat_DTglRealisasi.namatarifElAkhir;
                   hasil+=GridExtJsFormat_DTglRealisasi.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_DTglRealisasi : " + ex);
        }
        hasil+=GridExtJsFormat_DTglRealisasi.rootElAkhir;
        return hasil;
    }
}
