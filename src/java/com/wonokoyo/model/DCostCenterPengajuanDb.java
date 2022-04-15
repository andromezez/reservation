/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_DCostCenterPengajuan;
import com.wonokoyo.model.strukturdata.D_CostCenterPengajuan;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Eddy
 */
public class DCostCenterPengajuanDb {
    private DBConnection dBConnection;
    private ResultSet hasilQuery;
    //private ResultSet rs testing;
    public DCostCenterPengajuanDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class DCostCenterPengajuanDb: " + ex);
        }
    }
    public String insertOneRow(D_CostCenterPengajuan d_CostCenterPengajuan){        
        String sql = "INSERT INTO D_COSTCENTERPENGAJUAN(KODEPENGAJUAN,KODECOSTCENTER,USERID,APPROVAL,PRESENTASE,NOMINAL)" +
                "VALUES ('"+d_CostCenterPengajuan.KODEPENGAJUAN +"', '"+ d_CostCenterPengajuan.KODECOSTCENTER +"', '"+ d_CostCenterPengajuan.USERID
                +"', '"+ d_CostCenterPengajuan.APPROVAL+"' , "+ d_CostCenterPengajuan.PRESENTASE +", "+ d_CostCenterPengajuan.NOMINAL+")";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data cost center pengajuan telah tersimpan.", "");
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table D_COSTCENTERPENGAJUAN in class DCostCenterPengajuanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data cost center pengajuan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(D_CostCenterPengajuan d_CostCenterPengajuan){
        String sql = "UPDATE D_COSTCENTERPENGAJUAN SET APPROVAL = '" +d_CostCenterPengajuan.APPROVAL+ "', "
                +" PRESENTASE = "+  d_CostCenterPengajuan.PRESENTASE  +", "
                +" NOMINAL = " +d_CostCenterPengajuan.NOMINAL 
                +" WHERE KODEPENGAJUAN = '"+d_CostCenterPengajuan.KODEPENGAJUAN+"' and "
                +" KODECOSTCENTER = '"+d_CostCenterPengajuan.KODECOSTCENTER+"' and USERID = '"+d_CostCenterPengajuan.USERID+"'";
         String hasil = SuccessErrorMessege.generateMessege(true, "Data cost center pengajuan telah terupdate.", "");               
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table D_COSTCENTERPENGAJUAN in class DCostCenterPengajuanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data cost center pengajuan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteRowByKodePengajuan(String kodepengajuan){
        String sql = "DELETE FROM D_COSTCENTERPENGAJUAN WHERE KODEPENGAJUAN = '"+kodepengajuan+"'";
            String hasil = SuccessErrorMessege.generateMessege(true, "Data cost center pengajuan telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error delete table D_COSTCENTERPENGAJUAN in class DCostCenterPengajuanDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data cost center pengajuan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String selectCustomRow(String sql){
          //"SELECT * FROM vm_hris_bpartnerlengkap WHERE kodecostcenter = '"+filterCostCenter+"'";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class DCostCenterPengajuanDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_DCostCenterPengajuan();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String selectAllRow(){
        String sql = "select distinct a.kodepengajuan,a.kodecostcenter,b.namacostcenter,a.userid,c.kodebp, c.nik, c.namabp,a.approval,a.presentase,a.nominal " +
                "from d_costcenterpengajuan a, vm_hris_costcenter b, vm_hris_bpartnerlengkap c " +
                "where a.KODECOSTCENTER = b.KODECOSTCENTER (+) " +
                "and a.userid = c.userid (+)";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table d_costcenterpengajuan in class DCostCenterPengajuanDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_DCostCenterPengajuan();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String convertToGridExtJsFormat_DCostCenterPengajuan(){
        String hasil = GridExtJsFormat_DCostCenterPengajuan.rootElAwal;        
        try {
            while(hasilQuery.next()){
                   hasil+=GridExtJsFormat_DCostCenterPengajuan.rowElAwal;
                   hasil+=GridExtJsFormat_DCostCenterPengajuan.kodepengajuanElAwal+(hasilQuery.getString("kodepengajuan")==null?"":hasilQuery.getString("kodepengajuan"))+GridExtJsFormat_DCostCenterPengajuan.kodepengajuanElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterPengajuan.kodecostcenterElAwal+(hasilQuery.getString("kodecostcenter")==null?"":hasilQuery.getString("kodecostcenter"))+GridExtJsFormat_DCostCenterPengajuan.kodecostcenterElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterPengajuan.namacostcenterElAwal+
                           (hasilQuery.getString("namacostcenter")==null?"":(hasilQuery.getString("namacostcenter")).replace("&", "&amp;"))+
                           GridExtJsFormat_DCostCenterPengajuan.namacostcenterElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterPengajuan.useridElAwal+(hasilQuery.getString("userid")==null?"":hasilQuery.getString("userid"))+GridExtJsFormat_DCostCenterPengajuan.useridElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterPengajuan.kodebpElAwal+(hasilQuery.getString("kodebp")==null?"":hasilQuery.getString("kodebp"))+GridExtJsFormat_DCostCenterPengajuan.kodebpElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterPengajuan.nikElAwal+(hasilQuery.getString("nik")==null?"":hasilQuery.getString("nik"))+GridExtJsFormat_DCostCenterPengajuan.nikElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterPengajuan.namabpElAwal+(hasilQuery.getString("namabp")==null?"":hasilQuery.getString("namabp"))+GridExtJsFormat_DCostCenterPengajuan.namabpElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterPengajuan.approvalElAwal+(hasilQuery.getString("approval")==null?"":hasilQuery.getString("approval"))+GridExtJsFormat_DCostCenterPengajuan.approvalElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterPengajuan.presentaseElAwal+(Float.toString(hasilQuery.getFloat("presentase"))==null?"":hasilQuery.getFloat("presentase"))+GridExtJsFormat_DCostCenterPengajuan.presentaseElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterPengajuan.nominalElAwal+(Integer.toString(hasilQuery.getInt("nominal"))==null?"":hasilQuery.getInt("nominal"))+GridExtJsFormat_DCostCenterPengajuan.nominalElAkhir;
                   hasil+=GridExtJsFormat_DCostCenterPengajuan.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_DCostCenterPengajuan : " + ex);
        }
        hasil+=GridExtJsFormat_DCostCenterPengajuan.rootElAkhir;
        return hasil;
    }
}
