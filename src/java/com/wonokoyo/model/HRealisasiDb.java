/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;

import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_HRealisasi;
import com.wonokoyo.model.strukturdata.H_Realisasi;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Indra
 */
public class HRealisasiDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    public HRealisasiDb(DBConnection dBConnection) {
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

    public String insertOneRow(H_Realisasi realisasi){
        String sql = "INSERT INTO H_REALISASI(KODEREALISASI,KODEBP,KODEPENGAJUAN,JENISREALISASI,TANGGAL,TOTALBIAYA,IE)" +
                "VALUES ('"+ realisasi.KODEREALISASI +"','"+ realisasi.KODEBP +"', '"+ realisasi.KODEPENGAJUAN +"', '"+ realisasi.JENISREALISASI
                +"', TO_DATE('"+ realisasi.TGLREALISASI +"','mm/dd/yyyy') , "+ realisasi.TOTALBIAYA + ",'" + realisasi.IE +"')";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data H_REALISASI telah tersimpan.", "");
        try {
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table H_REALISASI in class HRealisasiDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data H_REALISASI: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public String updateOneRow(H_Realisasi realisasi){
        String sql = "UPDATE H_REALISASI SET KODEBP = '"+realisasi.KODEBP+"',KODEPENGAJUAN='"+realisasi.KODEPENGAJUAN+"', JENISREALISASI = '" +realisasi.JENISREALISASI+ "', "
                +" TANGGAL = TO_DATE('"+ realisasi.TGLREALISASI +"','mm/dd/yyyy') , TOTALBIAYA = " + realisasi.TOTALBIAYA 
                +", IE= '"+ realisasi.IE +"'   WHERE KODEREALISASI = '"+realisasi.KODEREALISASI+"'";
         String hasil = SuccessErrorMessege.generateMessege(true, "Data H_REALISASI telah terupdate.", "");
        try {
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table H_REALISASI in class HRealisasiDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data H_REALISASI: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public String deleteOneRow(H_Realisasi realisasi){
        String sql = "DELETE FROM H_REALISASI WHERE KODEREALISASI = '"+realisasi.KODEREALISASI+"'";
            String hasil = SuccessErrorMessege.generateMessege(true, "Data H_REALISASI telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error delete table H_REALISASI in class HRealisasiDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data H_REALISASI: "+DBConnection.getOracleErrorSentence(ex));
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
            System.err.println("Error class HRealisasi method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_HRealisasi();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public String selectAllRowToGridExtJsFormat_HRealisasi(){
        String sql = "SELECT a.koderealisasi,a.kodebp,a.kodepengajuan,a.jenisrealisasi,to_char(c.tanggal,'mm/dd/yyyy') tglPengajuan,"
                    + "to_char(a.tanggal,'mm/dd/yyyy') tglRealisasi,a.totalbiaya,b.namabp, b.namaperusahaan,b.nik,b.namaorg, "
                    + "b.namajabatan, c.versi from h_realisasi a, vm_hris_bpartnerlengkap b, h_pengajuan c "
                    + "where a.kodebp=b.kodebp and a.kodepengajuan=c.kodepengajuan";

        String hasil = GridExtJsFormat_HRealisasi.rootElAwal;
         try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
            while(hasilQuery.next()){

                hasil+=GridExtJsFormat_HRealisasi.rowElAwal+ //,,,,,
                        GridExtJsFormat_HRealisasi.koderealisasiElAwal+hasilQuery.getString("KODEREALISASI")+GridExtJsFormat_HRealisasi.koderealisasiElAkhir+
                        GridExtJsFormat_HRealisasi.kodebpElAwal+hasilQuery.getString("KODEBP")+GridExtJsFormat_HRealisasi.kodebpElAkhir+
                        GridExtJsFormat_HRealisasi.kodepengajuanElAwal+hasilQuery.getString("KODEPENGAJUAN")+GridExtJsFormat_HRealisasi.kodepengajuanElAkhir+
                        GridExtJsFormat_HRealisasi.jenisrealisasiElAwal+hasilQuery.getString("JENISREALISASI")+GridExtJsFormat_HRealisasi.jenisrealisasiElAkhir+
                        GridExtJsFormat_HRealisasi.tglpengajuanElAwal+hasilQuery.getString("TGLPENGAJUAN")+GridExtJsFormat_HRealisasi.tglpengajuanElAkhir+
                        GridExtJsFormat_HRealisasi.tglrealisasiElAwal+hasilQuery.getString("TGLREALISASI")+GridExtJsFormat_HRealisasi.tglrealisasiElAkhir+
                        GridExtJsFormat_HRealisasi.totalbiayaElAwal+hasilQuery.getInt("TOTALBIAYA")+GridExtJsFormat_HRealisasi.totalbiayaElAkhir+
                        GridExtJsFormat_HRealisasi.namabpElAwal+hasilQuery.getString("NAMABP")+GridExtJsFormat_HRealisasi.namabpElAkhir+
                        GridExtJsFormat_HRealisasi.namaperusahaanElAwal+hasilQuery.getString("NAMAPERUSAHAAN")+GridExtJsFormat_HRealisasi.namaperusahaanElAkhir+
                        GridExtJsFormat_HRealisasi.nikElAwal+hasilQuery.getString("NIK")+GridExtJsFormat_HRealisasi.nikElAkhir+
                        GridExtJsFormat_HRealisasi.IEElAwal+hasilQuery.getString("IE")+GridExtJsFormat_HRealisasi.IEElAkhir+
                        GridExtJsFormat_HRealisasi.namaorgElAwal+hasilQuery.getString("NAMAORG").replaceAll("&", "DAN")+GridExtJsFormat_HRealisasi.namaorgElAkhir+
                        GridExtJsFormat_HRealisasi.namajabatanElAwal+hasilQuery.getString("NAMAJABATAN")+GridExtJsFormat_HRealisasi.namajabatanElAkhir+
                        GridExtJsFormat_HRealisasi.versiElAwal+hasilQuery.getInt("VERSI")+GridExtJsFormat_HRealisasi.versiElAkhir+
                        GridExtJsFormat_HRealisasi.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table H_REALISASI in class HRealisasiDb method selectAllRow: " + ex);
        }
        hasil+=GridExtJsFormat_HRealisasi.rootElAkhir;
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public String convertToGridExtJsFormat_HRealisasi(){
        String namaorg,namaorg2="";
        String hasil = GridExtJsFormat_HRealisasi.rootElAwal;
        try {
            while(hasilQuery.next()){
                    namaorg = hasilQuery.getString("namaorg");
               if(namaorg == null)
               {namaorg2 = "";}
               else
               {namaorg2 = namaorg.replace("&", "&amp;");}
                   hasil+=GridExtJsFormat_HRealisasi.rowElAwal;
                   hasil+=GridExtJsFormat_HRealisasi.koderealisasiElAwal+hasilQuery.getString("KODEREALISASI")+GridExtJsFormat_HRealisasi.koderealisasiElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.kodebpElAwal+hasilQuery.getString("KODEBP")+GridExtJsFormat_HRealisasi.kodebpElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.kodepengajuanElAwal+hasilQuery.getString("KODEPENGAJUAN")+GridExtJsFormat_HRealisasi.kodepengajuanElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.jenisrealisasiElAwal+hasilQuery.getString("JENISREALISASI")+GridExtJsFormat_HRealisasi.jenisrealisasiElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.tglpengajuanElAwal+hasilQuery.getString("TGLPENGAJUAN")+GridExtJsFormat_HRealisasi.tglpengajuanElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.tglrealisasiElAwal+hasilQuery.getString("TGLREALISASI")+GridExtJsFormat_HRealisasi.tglrealisasiElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.totalbiayaElAwal+hasilQuery.getInt("TOTALBIAYA")+GridExtJsFormat_HRealisasi.totalbiayaElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.namabpElAwal+hasilQuery.getString("NAMABP")+GridExtJsFormat_HRealisasi.namabpElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.namaperusahaanElAwal+hasilQuery.getString("NAMAPERUSAHAAN")+GridExtJsFormat_HRealisasi.namaperusahaanElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.nikElAwal+hasilQuery.getString("NIK")+GridExtJsFormat_HRealisasi.nikElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.IEElAwal+hasilQuery.getString("IE")+GridExtJsFormat_HRealisasi.IEElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.namaorgElAwal+namaorg2+GridExtJsFormat_HRealisasi.namaorgElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.namajabatanElAwal+hasilQuery.getString("NAMAJABATAN")+GridExtJsFormat_HRealisasi.namajabatanElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.versiElAwal+hasilQuery.getInt("VERSI")+GridExtJsFormat_HRealisasi.versiElAkhir;
                   hasil+=GridExtJsFormat_HRealisasi.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_MAcc : " + ex);
        }
        hasil+=GridExtJsFormat_HRealisasi.rootElAkhir;
        return hasil;
    }
}
