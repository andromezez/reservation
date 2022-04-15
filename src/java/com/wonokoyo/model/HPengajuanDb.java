/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;
import com.wonokoyo.model.utilities.datetime.OracleDateAndTimeStringFormat;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_HPengajuan;
import com.wonokoyo.model.strukturdata.H_Pengajuan;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Eddy
 */
public class HPengajuanDb{
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    public HPengajuanDb(DBConnection dBConnection) {
         this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class HPengajuanDb: " + ex);
        }
    }
    
    public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class HPengajuan method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_HPengajuan();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public String insertOneRow(H_Pengajuan pengajuan){
        String sql = "INSERT INTO H_PENGAJUAN(KODEPENGAJUAN,KODEBP,JENISREALISASI,TANGGAL,VERSI,TOTALBIAYA,KODEORG,BATAL,IE)" +
                "VALUES ('"+pengajuan.KODEPENGAJUAN +"', '"+ pengajuan.KODEBP +"', '"+ pengajuan.JENISREALISASI
                +"', TO_DATE(substr('"+ pengajuan.TANGGAL +"',1,10),'"+OracleDateAndTimeStringFormat.dateFormat+"') , "+ pengajuan.VERSI +", "+ pengajuan.TOTALBIAYA
                +", '"+ pengajuan.KODEORG + "',"+pengajuan.BATAL+ ",'"+pengajuan.IE+"')";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data h_pengajuan telah tersimpan.", "");
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table H_Pengajuan in class HPengajuanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data H_Pengajuan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(H_Pengajuan pengajuan){
        String sql = "UPDATE H_PENGAJUAN SET KODEBP = '"+pengajuan.KODEBP+"', JENISREALISASI = '" +pengajuan.JENISREALISASI+ "', "
                +" TANGGAL = TO_DATE(substr('"+ pengajuan.TANGGAL +"',1,10),'"+OracleDateAndTimeStringFormat.dateFormat+"') , VERSI = " +pengajuan.VERSI + " , "
                +" TOTALBIAYA = " + pengajuan.TOTALBIAYA +", "
                +" KODEORG = '" +pengajuan.KODEORG+"' "
                +" WHERE KODEPENGAJUAN = '"+pengajuan.KODEPENGAJUAN+"'";
         String hasil = SuccessErrorMessege.generateMessege(true, "Data H_PENGAJUAN telah terupdate.", "");               
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table H_PENGAJUAN in class HPengajuanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data H_Pengajuan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteOneRow(H_Pengajuan pengajuan){
        String sql = "DELETE FROM H_PENGAJUAN WHERE KODEPENGAJUAN = '"+pengajuan.KODEPENGAJUAN+"'";
            String hasil = SuccessErrorMessege.generateMessege(true, "Data H_PENGAJUAN telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error delete table H_PENGAJUAN in class HPengajuanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data H_PENGAJUAN: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String selectAllRowToGridExtJsFormat_HPengajuan(){
        String sql = "SELECT a.KODEPENGAJUAN,a.KODEBP,a.JENISREALISASI, "+
                        "to_char(a.TANGGAL,'mm/dd/yyyy') TANGGAL,a.VERSI ,a.TOTALBIAYA,a.KODEORG,a.BATAL,a.IE, "
                      + "b.namabp, b.namaperusahaan,b.nik,b.namaorg "+
                        "FROM H_PENGAJUAN a, vm_hris_bpartnerlengkap b "+
                        "where a.versi = 0 " +
                        "and a.kodebp = b.kodebp and a.kodeorg = b.kodeorg";

        String hasil = GridExtJsFormat_HPengajuan.rootElAwal;
        int n,i;
        
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
            while(hasilQuery.next()){
                
                hasil+=GridExtJsFormat_HPengajuan.rowElAwal+ //,,,,,
                        GridExtJsFormat_HPengajuan.kodepengajuanElAwal+hasilQuery.getString("KODEPENGAJUAN")+GridExtJsFormat_HPengajuan.kodepengajuanElAkhir+
                        GridExtJsFormat_HPengajuan.kodebpElAwal+(hasilQuery.getString("KODEBP")==null?"":hasilQuery.getString("KODEBP"))+GridExtJsFormat_HPengajuan.kodebpElAkhir+
                        GridExtJsFormat_HPengajuan.jenisrealisasiElAwal+(hasilQuery.getString("JENISREALISASI")==null?"":hasilQuery.getString("JENISREALISASI"))+GridExtJsFormat_HPengajuan.jenisrealisasiElAkhir+
                        GridExtJsFormat_HPengajuan.tanggalElAwal+(hasilQuery.getString("TANGGAL")==null?"":hasilQuery.getString("TANGGAL"))+GridExtJsFormat_HPengajuan.tanggalElAkhir+
                        GridExtJsFormat_HPengajuan.versiElAwal+hasilQuery.getInt("VERSI")+GridExtJsFormat_HPengajuan.versiElAkhir+
                        GridExtJsFormat_HPengajuan.totalbiayaElAwal+hasilQuery.getInt("TOTALBIAYA")+GridExtJsFormat_HPengajuan.totalbiayaElAkhir+
                        GridExtJsFormat_HPengajuan.kodeorgElAwal+(hasilQuery.getString("KODEORG")==null?"":hasilQuery.getString("KODEORG"))+GridExtJsFormat_HPengajuan.kodeorgElAkhir+
                        GridExtJsFormat_HPengajuan.batalElAwal+hasilQuery.getInt("BATAL")+GridExtJsFormat_HPengajuan.batalElAkhir+
                        GridExtJsFormat_HPengajuan.IeElAwal+(hasilQuery.getString("IE")==null?"":hasilQuery.getString("IE"))+GridExtJsFormat_HPengajuan.IeElAkhir+
                        GridExtJsFormat_HPengajuan.useridElAwal+(hasilQuery.getString("USERID")==null?"":hasilQuery.getString("USERID"))+GridExtJsFormat_HPengajuan.useridElAkhir+
                        GridExtJsFormat_HPengajuan.namabpElAwal+(hasilQuery.getString("namabp")==null?"":hasilQuery.getString("namabp"))+GridExtJsFormat_HPengajuan.namabpElAkhir+
                        GridExtJsFormat_HPengajuan.namaperusahaanElAwal+(hasilQuery.getString("namaperusahaan")==null?"":hasilQuery.getString("namaperusahaan").replace("&", "&amp;"))+GridExtJsFormat_HPengajuan.namaperusahaanElAkhir+
                        GridExtJsFormat_HPengajuan.nikElAwal+(hasilQuery.getString("nik")==null?"":hasilQuery.getString("nik"))+GridExtJsFormat_HPengajuan.nikElAkhir+
                        GridExtJsFormat_HPengajuan.namaorgElAwal+(hasilQuery.getString("namaorg")==null?"":hasilQuery.getString("namaorg").replace("&", "&amp;"))+GridExtJsFormat_HPengajuan.namaorgElAkhir+
                       GridExtJsFormat_HPengajuan.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table H_PENGAJUAN in class HPengajuanDb method selectAllRow: " + ex);
        }
        hasil+=GridExtJsFormat_HPengajuan.rootElAkhir;
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public String convertToGridExtJsFormat_HPengajuan(){
        String namaorg,namaorg2=""; 
        String hasil = GridExtJsFormat_HPengajuan.rootElAwal;
        try {
            while(hasilQuery.next()){
                namaorg = hasilQuery.getString("namaorg");
                if(namaorg == null)
                {namaorg2 = "";}
                else
                {namaorg2 = namaorg.replace("&", "&amp;");}
                   hasil+=GridExtJsFormat_HPengajuan.rowElAwal;
                   hasil+=GridExtJsFormat_HPengajuan.kodepengajuanElAwal+hasilQuery.getString("KODEPENGAJUAN")+GridExtJsFormat_HPengajuan.kodepengajuanElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.kodebpElAwal+hasilQuery.getString("KODEBP")+GridExtJsFormat_HPengajuan.kodebpElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.jenisrealisasiElAwal+hasilQuery.getString("JENISREALISASI")+GridExtJsFormat_HPengajuan.jenisrealisasiElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.tanggalElAwal+hasilQuery.getString("TANGGAL")+GridExtJsFormat_HPengajuan.tanggalElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.totalbiayaElAwal+hasilQuery.getInt("TOTALBIAYA")+GridExtJsFormat_HPengajuan.totalbiayaElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.kodeorgElAwal+(hasilQuery.getString("KODEORG")==null?"":hasilQuery.getString("KODEORG"))+GridExtJsFormat_HPengajuan.kodeorgElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.batalElAwal+hasilQuery.getInt("BATAL")+GridExtJsFormat_HPengajuan.batalElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.versiElAwal+hasilQuery.getInt("VERSI")+GridExtJsFormat_HPengajuan.versiElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.IeElAwal+hasilQuery.getString("IE")+GridExtJsFormat_HPengajuan.IeElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.nikElAwal+hasilQuery.getString("NIK")+GridExtJsFormat_HPengajuan.nikElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.useridElAwal+hasilQuery.getString("USERID")+GridExtJsFormat_HPengajuan.useridElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.namabpElAwal+hasilQuery.getString("NAMABP")+GridExtJsFormat_HPengajuan.namabpElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.namaperusahaanElAwal+hasilQuery.getString("NAMAPERUSAHAAN")+GridExtJsFormat_HPengajuan.namaperusahaanElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.namaorgElAwal+namaorg2+GridExtJsFormat_HPengajuan.namaorgElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.namajabatanElAwal+hasilQuery.getString("NAMAJABATAN")+GridExtJsFormat_HPengajuan.namajabatanElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.totalElAwal+hasilQuery.getInt("TOTAL")+GridExtJsFormat_HPengajuan.totalElAkhir;
                   hasil+=GridExtJsFormat_HPengajuan.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_MAcc : " + ex);
        }
        hasil+=GridExtJsFormat_HPengajuan.rootElAkhir;
        return hasil;
    }
}
