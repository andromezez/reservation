/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;

import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MItem;
import com.wonokoyo.model.strukturdata.M_Item;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.CallableStatement;
/**
 *
 * @author SK
 */

public class MItemDb{
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    public MItemDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class MItemDb: " + ex);
        }
    }
    
    public String insertOneRow(String kodeitem, String kodejenis, String namaitem, Integer durasiminsewa, Integer kapasitas,
            String keterangan, String aktif, String statusavailability, String pricechangeable){
        String tambahitem = "INSERT INTO M_ITEM(KODEITEM, KODEJENIS, NAMAITEM, " +
                "DURASIMINSEWA, KAPASITAS, KETERANGAN, AKTIF, STATUSAVAILABILITY, " +
                "PRICECHANGEABLE)" +
                "VALUES ('"+kodeitem +"', '"+ kodejenis +"', '"+ namaitem + "', "+
                durasiminsewa + ", "+ kapasitas +", '"+ keterangan + "', '"+
                aktif + "', '"+ statusavailability +"', '"+ pricechangeable +
                "')";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master item telah tersimpan.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MItem(kodeitem, namaitem, "insert");
            if( datakembar == null){
                dBConnection.getStmt().executeUpdate(tambahitem);
            }else{
                dBConnection.isErrorProcessingDb=true;
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
                }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb = true;
            System.err.println("Error insert table M_ITEM in class MItemDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data master item: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(String kodeitem, String kodejenis, String namaitem, Integer durasiminsewa, Integer kapasitas,
            String keterangan, String aktif, String statusavailability, String pricechangeable){
        String updateitem = "UPDATE M_ITEM SET KODEJENIS = '"+kodejenis+"', "
                + "NAMAITEM='"+namaitem+"'," + "DURASIMINSEWA="+durasiminsewa+", "
                + "KAPASITAS="+kapasitas+"," + "KETERANGAN='"+keterangan+"', "
                + "AKTIF='"+aktif+"'," + "STATUSAVAILABILITY='"+statusavailability+"', "
                + "PRICECHANGEABLE='"+pricechangeable+"' "
                + "WHERE KODEITEM = '"+kodeitem+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master item telah terupdate.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MItem(kodeitem, namaitem, "update");
            if(datakembar == null){
                dBConnection.getStmt().executeUpdate(updateitem);
            }else{
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
                }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb = true;
            System.err.println("Error update table M_ITEM in class MItemDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data master item: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteOneRow(String kodeitem){
        String deleteitem = "DELETE FROM M_ITEM WHERE KODEITEM = '"+kodeitem+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master item telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(deleteitem);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb = true;
            System.err.println("Error update table M_ITEM in class MItemDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data master item: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public M_Item selectOneRow(String kodeitem){
        String selectitem = "SELECT * FROM M_ITEM WHERE kodeitem = '"+kodeitem+"'";
        M_Item mitem = new M_Item();
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selectitem);
            while(hasilQuery.next()){
                mitem.KODEITEM=hasilQuery.getString("KODEITEM");
                mitem.KODEJENIS=hasilQuery.getString("KODEJENIS");
                mitem.NAMAITEM=hasilQuery.getString("NAMAITEM");
                mitem.DURASIMINSEWA=hasilQuery.getInt("DURASIMINSEWA");
                mitem.KAPASITAS=hasilQuery.getInt("KAPASITAS");
                mitem.KETERANGAN=hasilQuery.getString("KETERANGAN");
                mitem.AKTIF=hasilQuery.getString("AKTIF");
                mitem.STATUSAVAILABILITY=hasilQuery.getString("STATUSAVAILABILITY");
                mitem.PRICECHANGEABLE=hasilQuery.getString("PRICECHANGEABLE");
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb = true;
            System.err.println("Error select table M_ITEM in class MItemDb method selectOneRow: " + ex);
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return mitem;
    }

    public String selectAllRow(){
        String sql = "select a.kodeitem, a.kodejenis, b.namajenis, "
            + "a.namaitem, a.durasiminsewa, a.kapasitas, "
            + "a.keterangan, a.aktif, a.statusavailability, "
            + "a.pricechangeable "
            + "from m_item a, m_jenis b "
            + "where a.kodejenis = b.kodejenis and a.aktif='1'";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb = true;
            System.err.println("Error select table VM_HRIS_COSTCENTER in class VMHRIS_costcenterDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MItem();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class MItemDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MItem();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public M_Item selectCustomOneRow(String parname){
        String selectparam = "SELECT KODEJENIS FROM M_ITEM WHERE KODEITEM ='"+parname+"'";
        M_Item mitem = new M_Item();
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selectparam);
            while(hasilQuery.next()){
                mitem.KODEJENIS=hasilQuery.getString("KODEJENIS");
                            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class MItemDb method selectCustomOneRow: " + ex);
        }
      
        closeHasilQuery();
        dBConnection.closeStmt();
        return mitem;
    }
    
    public String convertToGridExtJsFormat_MItem(){
        //String selectitem = "SELECT * FROM M_ITEM";
        String hasil = GridExtJsFormat_MItem.rootElAwal;
        int n,i;
        try {
            //hasilQuery = getStmt().executeQuery(selectitem);
            while(hasilQuery.next()){
                String durasiminsewa = hasilQuery.getString("DURASIMINSEWA");
                String kapasitas = hasilQuery.getString("KAPASITAS");
                String keterangan = hasilQuery.getString("KETERANGAN");
                if(durasiminsewa==null){
                    durasiminsewa = "";
                }
                if(kapasitas==null){
                    kapasitas = "";
                }
                if(keterangan==null){
                    keterangan = "";
                }
                hasil+=GridExtJsFormat_MItem.rowElAwal+
                        GridExtJsFormat_MItem.kodeitemElAwal+hasilQuery.getString("KODEITEM")+GridExtJsFormat_MItem.kodeitemElAkhir+
                        GridExtJsFormat_MItem.kodejenisElAwal+hasilQuery.getString("KODEJENIS")+GridExtJsFormat_MItem.kodejenisElAkhir+
                        GridExtJsFormat_MItem.namajenisElAwal+hasilQuery.getString("NAMAJENIS")+GridExtJsFormat_MItem.namajenisElAkhir+
                        GridExtJsFormat_MItem.namaitemElAwal+hasilQuery.getString("NAMAITEM")+GridExtJsFormat_MItem.namaitemElAkhir+
                        GridExtJsFormat_MItem.durasiminsewaElAwal+durasiminsewa+GridExtJsFormat_MItem.durasiminsewaElAkhir+
                        GridExtJsFormat_MItem.kapasitasElAwal+kapasitas+GridExtJsFormat_MItem.kapasitasElAkhir+
                        GridExtJsFormat_MItem.keteranganElAwal+keterangan+GridExtJsFormat_MItem.keteranganElAkhir+
                        GridExtJsFormat_MItem.aktifElAwal+hasilQuery.getString("AKTIF")+GridExtJsFormat_MItem.aktifElAkhir+
                        GridExtJsFormat_MItem.statusavailabilityElAwal+hasilQuery.getString("STATUSAVAILABILITY")+GridExtJsFormat_MItem.statusavailabilityElAkhir+
                        GridExtJsFormat_MItem.pricechangeableElAwal+hasilQuery.getString("PRICECHANGEABLE")+GridExtJsFormat_MItem.pricechangeableElAkhir+
                       GridExtJsFormat_MItem.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb = true;
            System.err.println("Error select table M_ITEM in class MItemDb method selectAllRow: " + ex);
        }
        hasil+=GridExtJsFormat_MItem.rootElAkhir;
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
}
