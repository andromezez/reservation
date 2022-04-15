/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model;

import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_DCostCenterRealisasi;
import com.wonokoyo.model.strukturdata.D_CostCenterRealisasi;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Indra
 */
public class DCostCenterRealisasiDb {
    private DBConnection dBConnection;
    private ResultSet hasilQuery;
    //private ResultSet rs testing;
    public DCostCenterRealisasiDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class DCostCenterRealisasiDb: " + ex);
        }
    }
    
    public String selectCustomRow(String sql){
          //"SELECT * FROM vm_hris_bpartnerlengkap WHERE kodecostcenter = '"+filterCostCenter+"'";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class DCostCenterRealisasiDb method selectCustomRow: " + ex);
        }
        
        String hasil = convertToGridExtJsFormat_DCostCenterRealisasi();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
        
    }
    
    public String insertOneRow(D_CostCenterRealisasi d_CostCenterRealisasi){        
        String sql = "INSERT INTO D_COSTCENTERREALISASI(KODEREALISASI,KODECOSTCENTER,USERID,APPROVAL,PRESENTASE,NOMINAL)" +
                "VALUES ('"+d_CostCenterRealisasi.KODEREALISASI +"', '"+ d_CostCenterRealisasi.KODECOSTCENTER +"', '"+ d_CostCenterRealisasi.USERID
                +"', '"+ d_CostCenterRealisasi.APPROVAL+"' , "+ d_CostCenterRealisasi.PRESENTASE +", "+ d_CostCenterRealisasi.NOMINAL+")";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data cost center realisasi telah tersimpan.", "");
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table D_COSTCENTERREALISASI in class DCostCenterRealisasiDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data cost center realisasi : "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public String updateOneRow(D_CostCenterRealisasi d_CostCenterRealisasi){
        String sql = "UPDATE D_COSTCENTERREALISASI SET APPROVAL = '" +d_CostCenterRealisasi.APPROVAL+ "', "
                +" PRESENTASE = "+  d_CostCenterRealisasi.PRESENTASE  +", "
                +" NOMINAL = " +d_CostCenterRealisasi.NOMINAL          
                +" WHERE KODEREALISASI = '"+d_CostCenterRealisasi.KODEREALISASI+"' and "
                +" KODECOSTCENTER = '"+d_CostCenterRealisasi.KODECOSTCENTER+"' and USERID = '"+d_CostCenterRealisasi.USERID+"'";
         String hasil = SuccessErrorMessege.generateMessege(true, "Data cost center realisasi telah terupdate.", "");               
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table D_COSTCENTERREALISASI in class DCostCenterRealisasiDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data cost center realisasi: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public String deleteRowByKodeRealisasi(String koderealisasi){
        String sql = "DELETE FROM D_COSTCENTERREALISASI WHERE KODEREALISASI = '"+koderealisasi+"'";
            String hasil = SuccessErrorMessege.generateMessege(true, "Data cost center realisasi telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error delete table D_COSTCENTERREALISASI in class DCostCenterRealisasiDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data cost center realisasi: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public String convertToGridExtJsFormat_DCostCenterRealisasi(){
        String hasil = GridExtJsFormat_DCostCenterRealisasi.rootElAwal;        
        try {
            while(hasilQuery.next()){
                   hasil+=GridExtJsFormat_DCostCenterRealisasi.rowElAwal;
                   hasil+=GridExtJsFormat_DCostCenterRealisasi.koderealisasiElAwal+(hasilQuery.getString("koderealisasi")==null?"":hasilQuery.getString("koderealisasi"))+GridExtJsFormat_DCostCenterRealisasi.koderealisasiElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterRealisasi.kodecostcenterElAwal+(hasilQuery.getString("kodecostcenter")==null?"":hasilQuery.getString("kodecostcenter"))+GridExtJsFormat_DCostCenterRealisasi.kodecostcenterElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterRealisasi.namacostcenterElAwal+
                   (hasilQuery.getString("namacostcenter")==null?"":(hasilQuery.getString("namacostcenter")).replace("&", "&amp;"))+
                   GridExtJsFormat_DCostCenterRealisasi.namacostcenterElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterRealisasi.useridElAwal+(hasilQuery.getString("userid")==null?"":hasilQuery.getString("userid"))+GridExtJsFormat_DCostCenterRealisasi.useridElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterRealisasi.kodebpElAwal+(hasilQuery.getString("kodebp")==null?"":hasilQuery.getString("kodebp"))+GridExtJsFormat_DCostCenterRealisasi.kodebpElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterRealisasi.nikElAwal+(hasilQuery.getString("nik")==null?"":hasilQuery.getString("nik"))+GridExtJsFormat_DCostCenterRealisasi.nikElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterRealisasi.namabpElAwal+(hasilQuery.getString("namabp")==null?"":hasilQuery.getString("namabp"))+GridExtJsFormat_DCostCenterRealisasi.namabpElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterRealisasi.approvalElAwal+(hasilQuery.getString("approval")==null?"":hasilQuery.getString("approval"))+GridExtJsFormat_DCostCenterRealisasi.approvalElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterRealisasi.presentaseElAwal+(Float.toString(hasilQuery.getFloat("presentase"))==null?"":hasilQuery.getFloat("presentase"))+GridExtJsFormat_DCostCenterRealisasi.presentaseElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterRealisasi.nominalElAwal+(Integer.toString(hasilQuery.getInt("nominal"))==null?"":hasilQuery.getInt("nominal"))+GridExtJsFormat_DCostCenterRealisasi.nominalElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterRealisasi.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_DCostCenterRealisasi : " + ex);
        }
        hasil+=GridExtJsFormat_DCostCenterRealisasi.rootElAkhir;
        return hasil;
    }
}
