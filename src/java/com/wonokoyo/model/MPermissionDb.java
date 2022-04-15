/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;
import com.wonokoyo.model.strukturdata.M_Permission;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MPermission;
/**
 *
 * @author Eddy
 */
public class MPermissionDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    //private ResultSet rs testing;
    public MPermissionDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset: " + ex);
        }
    }
    public String insertOneRow(String KODEFORM,String USERID,String PVIEW, String PADD,
                String PUPDATE,String PDELETE,String PPRINT,String BATAL_TRANS){
        String sql = "INSERT INTO M_PERMISSION(KODEFORM,USERID,PVIEW,PADD,PUPDATE,PDELETE,PPRINT,BATAL_TRANS)" +
                "VALUES ('"+KODEFORM +"', '"+ USERID +"', '"+ PVIEW +"', '"+ PADD + "', '"+ PUPDATE + "', '"+ PDELETE
                + "', '"+ PPRINT + "', '"+ BATAL_TRANS +"')";
        System.out.println(BATAL_TRANS);
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master permission telah tersimpan.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MPermission(USERID, KODEFORM);
            if(datakembar == null){
                dBConnection.getStmt().executeUpdate(sql);
            }else{
                dBConnection.isErrorProcessingDb=true;
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table M_PERMISSION in class MPermissionDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data master permission: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(String KODEFORM,String USERID,String PVIEW, String PADD,
                String PUPDATE,String PDELETE,String PPRINT,String BATAL_TRANS){
        String sql = "UPDATE M_PERMISSION SET PVIEW = '"+PVIEW+"', "
                + "PADD='"+PADD+"'," + "PUPDATE='"+PUPDATE+"',"
                + "PDELETE='"+PDELETE+"'," + "PPRINT='"+PPRINT+"', " + "BATAL_TRANS='" +BATAL_TRANS+"',"
                + " WHERE KODEFORM = '"+KODEFORM+"' and USERID ='"+USERID+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master permission telah terupdate.", "");
        try {
            dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_PERMISSION in class MPermissionDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data master permission: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    //public void deleteOneRow(String kodeform, String userid){
    public String deleteOneRow(String userid){
        String sql = "DELETE FROM M_PERMISSION WHERE USERID ='"+userid+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master permission telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error delete table M_PERMISSION in class MPermissionDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data master permission: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public M_Permission selectOneRow(String kodeform, String userid){
        String sql = "SELECT * FROM M_PERMISSION WHERE KODEFORM = '"+kodeform+"' and USERID ='"+userid+"'";
        M_Permission mpermission = new M_Permission();
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
            while(hasilQuery.next()){
                mpermission.KODEFORM=hasilQuery.getString("KODEFORM");
                mpermission.PADD=hasilQuery.getString("PADD");
                mpermission.PDELETE=hasilQuery.getString("PDELETE");
                mpermission.PPRINT=hasilQuery.getString("PPRINT");
                mpermission.PUPDATE=hasilQuery.getString("PUPDATE");
                mpermission.PVIEW=hasilQuery.getString("PVIEW");
                mpermission.BATAL_TRANS=hasilQuery.getString("BATAL_TRANS");
                mpermission.USERID=hasilQuery.getString("USERID");
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_PERMISSION in class MPermissionDb method selectOneRow: " + ex);
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return mpermission;
    }

    public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class M_PERMISSIONDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MPermission();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String selectAllRow(){
        String sql = "select a.kodeform, b.namaform, a.userid, a.pview, " +
            "a.padd, a.pupdate, a.pdelete, a.pprint, a.batal_trans "+
            "from m_permission a, m_menu b "+
            "where a.kodeform = b.kodeform ";
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table in class M_PERMISSIONDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MPermission();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String convertToGridExtJsFormat_MPermission(){
        String hasil = GridExtJsFormat_MPermission.rootElAwal;
        try {
            while(hasilQuery.next()){
                   hasil+=GridExtJsFormat_MPermission.rowElAwal;
                   hasil+=GridExtJsFormat_MPermission.kodeformElAwal+hasilQuery.getString("kodeform")+GridExtJsFormat_MPermission.kodeformElAkhir;
                   hasil+=GridExtJsFormat_MPermission.namaformElAwal+(hasilQuery.getString("namaform")==null?"":hasilQuery.getString("namaform"))
                            +GridExtJsFormat_MPermission.namaformElAkhir;
                   hasil+=GridExtJsFormat_MPermission.useridElAwal+(hasilQuery.getString("userid")==null?"":hasilQuery.getString("userid"))
                            +GridExtJsFormat_MPermission.useridElAkhir;
                   hasil+=GridExtJsFormat_MPermission.pviewElAwal+(hasilQuery.getString("pview")==null?"0":hasilQuery.getString("pview"))
                            +GridExtJsFormat_MPermission.pviewElAkhir;
                   hasil+=GridExtJsFormat_MPermission.paddElAwal+(hasilQuery.getString("padd")==null?"0":hasilQuery.getString("padd"))
                            +GridExtJsFormat_MPermission.paddElAkhir;
                   hasil+=GridExtJsFormat_MPermission.pupdateElAwal+(hasilQuery.getString("pupdate")==null?"0":hasilQuery.getString("pupdate"))
                            +GridExtJsFormat_MPermission.pupdateElAkhir;
                   hasil+=GridExtJsFormat_MPermission.pdeleteElAwal+(hasilQuery.getString("pdelete")==null?"0":hasilQuery.getString("pdelete"))
                            +GridExtJsFormat_MPermission.pdeleteElAkhir;
                   hasil+=GridExtJsFormat_MPermission.pprintElAwal+(hasilQuery.getString("pprint")==null?"0":hasilQuery.getString("pprint"))
                            +GridExtJsFormat_MPermission.pprintElAkhir;
                   hasil+=GridExtJsFormat_MPermission.bataltransElAwal+(hasilQuery.getString("batal_trans")==null?"0":hasilQuery.getString("batal_trans"))
                            +GridExtJsFormat_MPermission.bataltransElAkhir;
                   hasil+=GridExtJsFormat_MPermission.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_MPermission : " + ex);
        }
        hasil+=GridExtJsFormat_MPermission.rootElAkhir;
        return hasil;
    }
}
