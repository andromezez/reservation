/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.utilities.datetime.OracleDateAndTimeStringFormat;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_SD_ItemPengajuan;
import com.wonokoyo.model.strukturdata.D_TglPengajuan;
import com.wonokoyo.model.strukturdata.SD_ItemPengajuan;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Bayu
 */
public class SD_ItemPengajuanDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;    
    public SD_ItemPengajuanDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class SD_ItemPengajuanDb: " + ex);
        }
    }
    public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class SD_ItemPengajuanDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_SD_ItemPengajuan();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public boolean isValidJamawalJamakhir(SD_ItemPengajuan[] sD_ItemPengajuan){
        boolean report = true;                    
            for (int i=0;i<sD_ItemPengajuan.length;i++){
                for(int j=0;j<sD_ItemPengajuan.length;j++){
                    if(i!=j){
                        if(sD_ItemPengajuan[i].KODEITEM.equals(sD_ItemPengajuan[j].KODEITEM)&&
                                sD_ItemPengajuan[i].KODETP.equals(sD_ItemPengajuan[j].KODETP)){
                            if( 
                                Integer.parseInt(sD_ItemPengajuan[j].JAMAWAL.replaceFirst(":", "")) > 
                                    Integer.parseInt(sD_ItemPengajuan[i].JAMAKHIR.replaceFirst(":", ""))
                            ){

                            }else if(Integer.parseInt(sD_ItemPengajuan[j].JAMAKHIR.replaceFirst(":", "")) < 
                                    Integer.parseInt(sD_ItemPengajuan[i].JAMAWAL.replaceFirst(":", ""))){

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
    public String selectAllRow(){
        String sql = "select a.kodetp,a.kodeitem,b.namaitem,a.jumlahawal,a.jumlahakhir,TO_CHAR(a.jamawal,'"+OracleDateAndTimeStringFormat.timeOnlyWithoutSec+"') jamawal, " +
	   "TO_CHAR(a.jamakhir,'"+OracleDateAndTimeStringFormat.timeOnlyWithoutSec+"') jamakhir ,a.durasi,a.biaya,a.antrian,a.keterangan, a.biayaperjam,a.jumlahpeserta " +
           "from sd_itempengajuan a, m_item b " +
           //"where a.kodetp = '" +pkodetp+ "' " +
           " where a.kodeitem = b.kodeitem(+)";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table in class SD_ItemPengajuanDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_SD_ItemPengajuan();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String convertToGridExtJsFormat_SD_ItemPengajuan(){
        String hasil = GridExtJsFormat_SD_ItemPengajuan.rootElAwal;
        try {
            while(hasilQuery.next()){
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.rowElAwal;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.kodetpElAwal+hasilQuery.getString("kodetp")+GridExtJsFormat_SD_ItemPengajuan.kodetpElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.kodeitemElAwal+(hasilQuery.getString("kodeitem")==null?"":hasilQuery.getString("kodeitem"))+GridExtJsFormat_SD_ItemPengajuan.kodeitemElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.namaitemElAwal+(hasilQuery.getString("namaitem")==null?"":hasilQuery.getString("namaitem")) +GridExtJsFormat_SD_ItemPengajuan.namaitemElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.jumlahawalElAwal+hasilQuery.getInt("jumlahawal") +GridExtJsFormat_SD_ItemPengajuan.jumlahawalElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.jumlahakhirElAwal+hasilQuery.getInt("jumlahakhir")+GridExtJsFormat_SD_ItemPengajuan.jumlahakhirElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.jamawalElAwal+(hasilQuery.getString("jamawal")==null?"":hasilQuery.getString("jamawal"))+GridExtJsFormat_SD_ItemPengajuan.jamawalElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.jamakhirElAwal+(hasilQuery.getString("jamakhir")==null?"":hasilQuery.getString("jamakhir"))+GridExtJsFormat_SD_ItemPengajuan.jamakhirElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.durasiElAwal+hasilQuery.getInt("durasi")+GridExtJsFormat_SD_ItemPengajuan.durasiElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.biayaElAwal+hasilQuery.getInt("biaya")+GridExtJsFormat_SD_ItemPengajuan.biayaElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.antrianElAwal+hasilQuery.getInt("antrian")+GridExtJsFormat_SD_ItemPengajuan.antrianElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.keteranganElAwal+(hasilQuery.getString("keterangan")==null?"":hasilQuery.getString("keterangan"))+GridExtJsFormat_SD_ItemPengajuan.keteranganElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.biayaperjamElAwal+hasilQuery.getInt("biayaperjam") +GridExtJsFormat_SD_ItemPengajuan.biayaperjamElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.jumlahpesertaElAwal+hasilQuery.getInt("jumlahpeserta")+GridExtJsFormat_SD_ItemPengajuan.jumlahpesertaElAkhir;
                   hasil+=GridExtJsFormat_SD_ItemPengajuan.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_SD_ItemPengajuan : " + ex);
        }
        hasil+=GridExtJsFormat_SD_ItemPengajuan.rootElAkhir;
        return hasil;
    }
    
    public String insertOneRow(SD_ItemPengajuan sD_ItemPengajuan){        
        String sql = "INSERT INTO SD_ITEMPENGAJUAN(KODETP,KODEITEM,JUMLAHAWAL,JUMLAHAKHIR,JAMAWAL,JAMAKHIR,"+
                "DURASI,BIAYA,ANTRIAN,KETERANGAN,BIAYAPERJAM,JUMLAHPESERTA)" +
                "VALUES ('"+sD_ItemPengajuan.KODETP +"', '"+ sD_ItemPengajuan.KODEITEM +"', "
                +sD_ItemPengajuan.JUMLAHAWAL+", "+sD_ItemPengajuan.JUMLAHAKHIR+", " +
                "TO_DATE('"+ sD_ItemPengajuan.JAMAWAL +"','"+OracleDateAndTimeStringFormat.timeOnlyWithoutSec+"') , "
                +"TO_DATE('"+ sD_ItemPengajuan.JAMAKHIR +"','"+OracleDateAndTimeStringFormat.timeOnlyWithoutSec+"'), "+
                sD_ItemPengajuan.DURASI +","+sD_ItemPengajuan.BIAYA+","+sD_ItemPengajuan.ANTRIAN+",'"+sD_ItemPengajuan.KETERANGAN+"'"
                +"," +sD_ItemPengajuan.BIAYAPERJAM+ "," + sD_ItemPengajuan.JUMLAHPESERTA+
                ") ";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data subdetil item pengajuan telah tersimpan.", "");
        try {
            /*DB_Procedure procedure = new DB_Procedure(dBConnection);
            String cek = procedure.cek_SDItemPengajuan(sD_ItemPengajuan.KODETP, sD_ItemPengajuan.KODEITEM, sD_ItemPengajuan.JAMAWAL, sD_ItemPengajuan.JAMAKHIR);
            if( cek == null){   */     
                dBConnection.getStmt().executeUpdate(sql);
            /*}else{
                dBConnection.isErrorProcessingDb=true;
                hasil = SuccessErrorMessege.generateMessege(false, "", cek);
            }*/
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table SD_ITEMPENGAJUAN in class SD_ItemPengajuanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data subdetil item pengajuan : "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    /*public String updateOneRow(SD_ItemPengajuan sD_ItemPengajuan){
        String sql ="update SD_ITEMPENGAJUAN set " +
              "KODEITEM='"+sD_ItemPengajuan.KODEITEM+"' , "+
              "JUMLAHAWAL="+sD_ItemPengajuan.JUMLAHAWAL+", "+
              "JUMLAHAKHIR="+sD_ItemPengajuan.JUMLAHAKHIR+", "+
              "JAMAWAL=TO_DATE('"+ sD_ItemPengajuan.JAMAWAL +"','"+OracleDateAndTimeStringFormat.dateAndTimeFormat+"'), "+
              "JAMAKHIR=TO_DATE('"+ sD_ItemPengajuan.JAMAKHIR +"','"+OracleDateAndTimeStringFormat.dateAndTimeFormat+"'), "+
              "DURASI="+sD_ItemPengajuan.DURASI+", "+
              "BIAYA="+sD_ItemPengajuan.BIAYA+", "+
              "ANTRIAN="+sD_ItemPengajuan.ANTRIAN+", "+
              "KETERANGAN ='"+sD_ItemPengajuan.KETERANGAN+"'  "+
              "where   KODETP  = '"+sD_ItemPengajuan.KODETP+"'";
         String hasil = SuccessErrorMessege.generateMessege(true, "Data subdetil item pengajuan telah terupdate.", "");               
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table SD_ITEMPENGAJUAN in class SD_ItemPengajuanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate subdetil item pengajuan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }*/

    public String deleteRowByKodeTp(String kodetp){
        String sql = "DELETE FROM SD_ITEMPENGAJUAN WHERE KODETP = '"+kodetp+"'";
            String hasil = SuccessErrorMessege.generateMessege(true, "subdetil item pengajuan telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error delete table SD_ITEMPENGAJUAN in class SD_ItemPengajuanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus subdetil item pengajuan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
}
