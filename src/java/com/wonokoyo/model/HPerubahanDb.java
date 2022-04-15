/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;


import com.wonokoyo.model.utilities.datetime.OracleDateAndTimeStringFormat;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_HPerubahan;
import com.wonokoyo.model.strukturdata.H_Perubahan;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Indra
 */
public class HPerubahanDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    public HPerubahanDb(DBConnection dBConnection) {
         this.dBConnection = dBConnection;
    }

    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class HPerubahanDb: " + ex);
        }
    }
    public String insertOneRow(H_Perubahan perubahan){
        String sql = "INSERT INTO H_PENGAJUAN(KODEPENGAJUAN,KODEBP,JENISREALISASI,TANGGAL,VERSI,TOTALBIAYA,KODEORG,IE)" +
                "VALUES ('"+perubahan.KODEPENGAJUAN +"', '"+ perubahan.KODEBP +"', '"+ perubahan.JENISREALISASI
                +"', TO_DATE('"+ perubahan.TANGGAL +"','"+OracleDateAndTimeStringFormat.dateFormat+"') , "+ perubahan.VERSI +", "+ perubahan.TOTALBIAYA
                +", '"+ perubahan.KODEORG+"','"+perubahan.IE + "')";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data H_PERUBAHAN telah tersimpan.", "");
        try {
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table H_PENGAJUAN in class HPerubahanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data H_PENGAJUAN: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String updateOneRow(H_Perubahan perubahan){
        String sql = "UPDATE H_PENGAJUAN SET KODEBP = '"+perubahan.KODEBP+"', JENISREALISASI = '" +perubahan.JENISREALISASI+ "', "
                +" TANGGAL = TO_DATE('"+ perubahan.TANGGAL +"','"+OracleDateAndTimeStringFormat.dateFormat+"') , VERSI = " +perubahan.VERSI + " , "
                +" TOTALBIAYA = " + perubahan.TOTALBIAYA +", "
                +" KODEORG = '" +perubahan.KODEORG+"', "
                +" BATAL = "+perubahan.BATAL+", "
                +" IE ='" +perubahan.IE+"'"
                +" WHERE KODEPENGAJUAN = '"+perubahan.KODEPENGAJUAN+"'";
         String hasil = SuccessErrorMessege.generateMessege(true, "Data H_PERUBAHAN telah terupdate.", "");
        try {
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table H_PENGAJUAN in class HPerubahanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data H_PENGAJUAN: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String updateBatal(String KodePengajuan, int Batal){
        String sql = "UPDATE H_PENGAJUAN SET BATAL = "+Batal+""
                +" WHERE KODEPENGAJUAN = '"+KodePengajuan+"'";
         String hasil = SuccessErrorMessege.generateMessege(true, "Data H_PERUBAHAN telah terupdate.", "");
        try {
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table H_PENGAJUAN in class HPerubahanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data H_PENGAJUAN: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String deleteOneRow(H_Perubahan perubahan){
        String sql = "DELETE FROM H_PENGAJUAN WHERE KODEPENGAJUAN = '"+perubahan.KODEPENGAJUAN+"'";
            String hasil = SuccessErrorMessege.generateMessege(true, "Data H_PERUBAHAN telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error delete table H_PENGAJUAN in class HPerubahanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data H_PENGAJUAN: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String selectAllRow(){
        String sql = "SELECT distinct a.KODEPENGAJUAN, a.KODEBP, a.JENISREALISASI, to_char(a.TANGGAL,'MM/dd/yyyy') tanggal,"
                   + "b.versi, a.TOTALBIAYA, a.KODEORG,a.IE, c.NIK, c.namabp,c.NAMAPERUSAHAAN, c.NAMAORG, c.NAMAJABATAN "
                   + "FROM h_pengajuan a,(SELECT substr(kodepengajuan,0,14) kode,max(versi) versi from h_pengajuan group by  substr(kodepengajuan,0,14)) b, "
                   + "vm_hris_bpartnerlengkap c, m_acc d WHERE substr(a.kodepengajuan,0,14) = b.kode AND a.versi = b.versi "
                   + "AND a.kodebp=c.kodebp  and a.kodeorg = c.kodeorg AND a.batal=0 and a.kodepengajuan=d.kodetransaksi";
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table H_PENGAJUAN in class HPerubahanDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_HPerubahan();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
   
    public String convertToGridExtJsFormat_HPerubahan(){
        String hasil = GridExtJsFormat_HPerubahan.rootElAwal;
        try {
            while(hasilQuery.next()){
                   hasil+=GridExtJsFormat_HPerubahan.rowElAwal;
                   hasil+=GridExtJsFormat_HPerubahan.kodepengajuanElAwal+hasilQuery.getString("KODEPENGAJUAN")+GridExtJsFormat_HPerubahan.kodepengajuanElAkhir;
                   hasil+=GridExtJsFormat_HPerubahan.kodebpElAwal+hasilQuery.getString("KODEBP")+GridExtJsFormat_HPerubahan.kodebpElAkhir;
                   hasil+=GridExtJsFormat_HPerubahan.jenisrealisasiElAwal+hasilQuery.getString("JENISREALISASI")+GridExtJsFormat_HPerubahan.jenisrealisasiElAkhir;
                   hasil+=GridExtJsFormat_HPerubahan.tanggalElAwal+hasilQuery.getString("TANGGAL")+GridExtJsFormat_HPerubahan.tanggalElAkhir;
                   hasil+=GridExtJsFormat_HPerubahan.versiElAwal+hasilQuery.getInt("VERSI")+GridExtJsFormat_HPerubahan.versiElAkhir;
                   hasil+=GridExtJsFormat_HPerubahan.totalbiayaElAwal+hasilQuery.getInt("TOTALBIAYA")+GridExtJsFormat_HPerubahan.totalbiayaElAkhir;
                   hasil+=GridExtJsFormat_HPerubahan.kodeorgElAwal+hasilQuery.getString("KODEORG")+GridExtJsFormat_HPerubahan.kodeorgElAkhir;
                   hasil+=GridExtJsFormat_HPerubahan.nikElAwal+hasilQuery.getString("NIK")+GridExtJsFormat_HPerubahan.nikElAkhir;
                   hasil+=GridExtJsFormat_HPerubahan.namabpElAwal+hasilQuery.getString("NAMABP")+GridExtJsFormat_HPerubahan.namabpElAkhir;
                   hasil+=GridExtJsFormat_HPerubahan.namaperusahaanElAwal+hasilQuery.getString("NAMAPERUSAHAAN")+GridExtJsFormat_HPerubahan.namaperusahaanElAkhir;
                   hasil+=GridExtJsFormat_HPerubahan.namaorgElAwal+hasilQuery.getString("NAMAORG").replaceAll("&", "DAN")+GridExtJsFormat_HPerubahan.namaorgElAkhir;
                   hasil+=GridExtJsFormat_HPerubahan.IeElAwal+hasilQuery.getString("IE")+GridExtJsFormat_HPerubahan.IeElAkhir;
                   hasil+=GridExtJsFormat_HPerubahan.namajabatanElAwal+hasilQuery.getString("NAMAJABATAN")+GridExtJsFormat_HPerubahan.namajabatanElAkhir;
                   hasil+=GridExtJsFormat_HPerubahan.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_MAcc : " + ex);
        }
        hasil+=GridExtJsFormat_HPerubahan.rootElAkhir;
        return hasil;
    }
}
