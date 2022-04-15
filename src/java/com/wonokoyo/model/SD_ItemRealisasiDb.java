/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model;

import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_SD_ItemRealisasi;
import com.wonokoyo.model.strukturdata.SD_ItemRealisasi;
import com.wonokoyo.model.utilities.datetime.OracleDateAndTimeStringFormat;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Indra
 */
public class SD_ItemRealisasiDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;    
    public SD_ItemRealisasiDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class SD_ItemRealisasiDb: " + ex);
        }
    }
   public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class SD_ItemPengajuanDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_SD_ItemRealisasi();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
   public boolean isValidJamawalJamakhir(SD_ItemRealisasi[] sD_ItemRealisasis){
        boolean report = true;
        for (int i=0;i<sD_ItemRealisasis.length;i++){
            for(int j=0;j<sD_ItemRealisasis.length;j++){
                if(i!=j){
                    if(sD_ItemRealisasis[i].KODEITEM.equals(sD_ItemRealisasis[j].KODEITEM)&&
                                sD_ItemRealisasis[i].KODETR.equals(sD_ItemRealisasis[j].KODETR)){
                        if( 
                            Integer.parseInt(sD_ItemRealisasis[j].JAMAWAL.replaceFirst(":", "")) > 
                                Integer.parseInt(sD_ItemRealisasis[i].JAMAKHIR.replaceFirst(":", ""))
                        ){

                        }else if(Integer.parseInt(sD_ItemRealisasis[j].JAMAKHIR.replaceFirst(":", "")) < 
                                Integer.parseInt(sD_ItemRealisasis[i].JAMAWAL.replaceFirst(":", ""))){

                        }else{
                            report = false;
                            break;
                        }
                    }
                }
            }
            if(report==false){
                break;
            }
        }
        return report;
    }
    public String insertOneRow(SD_ItemRealisasi sD_ItemRealisasi){        
        String sql = "INSERT INTO SD_ITEMREALISASI(KODETR,JUMLAHAKHIR,JAMAWAL,JAMAKHIR,DURASI,BIAYA,"+
                "STATUSSEBELUM,STATUSSESUDAH,KETERANGAN,KODEITEM,BIAYAPERJAM,JUMLAHPESERTA)" +
                "VALUES ('"+sD_ItemRealisasi.KODETR +"', "+ sD_ItemRealisasi.JUMLAHAKHIR +", "+ 
                "TO_DATE('"+ sD_ItemRealisasi.JAMAWAL +"','"+OracleDateAndTimeStringFormat.timeOnlyWithoutSec+"') , "
                +"TO_DATE('"+ sD_ItemRealisasi.JAMAKHIR +"','"+OracleDateAndTimeStringFormat.timeOnlyWithoutSec+"'), "+
                sD_ItemRealisasi.DURASI +","+sD_ItemRealisasi.BIAYA+",'"+sD_ItemRealisasi.STATUSSEBELUM+"','" +
                sD_ItemRealisasi.STATUSSESUDAH+ "','" +sD_ItemRealisasi.KETERANGAN+"','"+ sD_ItemRealisasi.KODEITEM +"'"+
                ","+sD_ItemRealisasi.BIAYAPERJAM+","+sD_ItemRealisasi.JUMLAHPESERTA+
                ") ";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data subdetil item realisasi telah tersimpan.", "");
        try {    
            dBConnection.getStmt().executeUpdate(sql);
        }catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table SD_ITEMREALISASI in class SD_ItemRealisasiDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data subdetil item realisasi : "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    /*public String updateOneRow(SD_ItemRealisasi sD_ItemRealisasi){
        String sql ="update SD_ITEMREALISASI set " +
              "KODEITEM='"+sD_ItemRealisasi.KODEITEM+"' , "+
              "JUMLAHAKHIR="+sD_ItemRealisasi.JUMLAHAKHIR+", "+
              "JAMAWAL=TO_DATE('"+ sD_ItemRealisasi.JAMAWAL +"','"+OracleDateAndTimeStringFormat.dateAndTimeFormat+"'), "+
              "JAMAKHIR=TO_DATE('"+ sD_ItemRealisasi.JAMAKHIR +"','"+OracleDateAndTimeStringFormat.dateAndTimeFormat+"'), "+
              "DURASI="+sD_ItemRealisasi.DURASI+", "+
              "BIAYA="+sD_ItemRealisasi.BIAYA+", "+
              "STATUSSEBELUM='"+sD_ItemRealisasi.STATUSSEBELUM+"' , "+
              "STATUSSESUDAH='"+sD_ItemRealisasi.STATUSSESUDAH+"' , "+  
              "KETERANGAN ='"+sD_ItemRealisasi.KETERANGAN+"'  "+
              "where   KODETR  = '"+sD_ItemRealisasi.KODETR+"'";
         String hasil = SuccessErrorMessege.generateMessege(true, "Data subdetil item realisasi telah terupdate.", "");               
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table SD_ITEMREALISASI in class SD_ItemRealisasiDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate subdetil item realisasi: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }*/
    
    public String deleteRowByKodeTr(String kodetr){
        System.out.println("kodetr "+kodetr);
        String sql = "DELETE FROM SD_ITEMREALISASI WHERE KODETR = '"+kodetr+"'";
            String hasil = SuccessErrorMessege.generateMessege(true, "subdetil item realisasi telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error delete table SD_ITEMREALISASI in class SD_ItemRealisasiDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus subdetil item realisasi: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public String convertToGridExtJsFormat_SD_ItemRealisasi(){
        String hasil = GridExtJsFormat_SD_ItemRealisasi.rootElAwal;
        try {
            while(hasilQuery.next()){
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.rowElAwal;
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.kodetrElAwal+hasilQuery.getString("kodetr")+GridExtJsFormat_SD_ItemRealisasi.kodetrElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.kodeitemElAwal+(hasilQuery.getString("kodeitem")==null?"":hasilQuery.getString("kodeitem"))+GridExtJsFormat_SD_ItemRealisasi.kodeitemElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.namaitemElAwal+(hasilQuery.getString("namaitem")==null?"":hasilQuery.getString("namaitem")) +GridExtJsFormat_SD_ItemRealisasi.namaitemElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.jumlahakhirElAwal+hasilQuery.getInt("jumlahakhir")+GridExtJsFormat_SD_ItemRealisasi.jumlahakhirElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.jamawalElAwal+(hasilQuery.getString("jamawal")==null?"":hasilQuery.getString("jamawal"))+GridExtJsFormat_SD_ItemRealisasi.jamawalElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.jamakhirElAwal+(hasilQuery.getString("jamakhir")==null?"":hasilQuery.getString("jamakhir"))+GridExtJsFormat_SD_ItemRealisasi.jamakhirElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.durasiElAwal+hasilQuery.getInt("durasi")+GridExtJsFormat_SD_ItemRealisasi.durasiElAkhir;                   
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.biayaElAwal+hasilQuery.getInt("biaya")+GridExtJsFormat_SD_ItemRealisasi.biayaElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.statussebelumElAwal+hasilQuery.getInt("statussebelum")+GridExtJsFormat_SD_ItemRealisasi.statussebelumElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.statussesudahElAwal+hasilQuery.getInt("statussesudah")+GridExtJsFormat_SD_ItemRealisasi.statussesudahElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.keteranganElAwal+(hasilQuery.getString("keterangan")==null?"":hasilQuery.getString("keterangan"))+GridExtJsFormat_SD_ItemRealisasi.keteranganElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.biayaperjamElAwal+hasilQuery.getInt("biayaperjam")+GridExtJsFormat_SD_ItemRealisasi.biayaperjamElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.jumlahpesertaElAwal+hasilQuery.getInt("jumlahpeserta")+GridExtJsFormat_SD_ItemRealisasi.jumlahpesertaElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemRealisasi.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_SD_ItemRealisasi : " + ex);
        }
        hasil+=GridExtJsFormat_SD_ItemRealisasi.rootElAkhir;
        return hasil;
    }
}
