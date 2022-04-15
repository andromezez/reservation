/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MDPembebanan;
import com.wonokoyo.model.strukturdata.MD_Pembebanan;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author SK
 */
public class MDPembebananDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    //private ResultSet rs testing;
    public MDPembebananDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class MDPembebananDb: " + ex);
        }
    }

    public String insertOneRow(String kodepembebanan, String kodecostcenter){
        String tambahmdpembebanan = "INSERT INTO MD_PEMBEBANAN (KODEPEMBEBANAN, KODECOSTCENTER) " +
                "VALUES ('"+kodepembebanan +"', '"+ kodecostcenter +"')";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data detail pembebanan telah tersimpan.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MDPembebanan(kodepembebanan, kodecostcenter);
            if( datakembar == null){
                dBConnection.getStmt().executeUpdate(tambahmdpembebanan);
            }else{
                dBConnection.isErrorProcessingDb=true;
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table MDPembebanan in class MDPembebananDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data detail pembebanan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(String kodepembebanan, String kodecostcenter){
        String updatepembebanan = "UPDATE MD_PEMBEBANAN SET KODECOSTCENTER = '"+kodecostcenter+"' "
                + "WHERE KODEPEMBEBANAN = '"+kodepembebanan+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data detail pembebanan telah terupdate.", "");
        try {
            /*DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MJenis(kodejenis, namajenis, "update");
            if( datakembar == null){*/
                dBConnection.getStmt().executeUpdate(updatepembebanan);
            /*}else{
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
            }*/
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table MDPembebanan in class MDPembebananDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data detail pembebanan: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteOneRow(String kodepembebanan){
        String deletepembebanan = "DELETE FROM MD_PEMBEBANAN WHERE KODEPEMBEBANAN = '"+kodepembebanan+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data detail pembebanan telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(deletepembebanan);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table MDPembebanan in class MDPembebananDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data detail pembebanan: "+DBConnection.getOracleErrorSentence(ex));
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
            System.err.println("Error class MDPembebananDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MDPembebanan();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public MD_Pembebanan[] selectCustomRow2(String sql){
        MD_Pembebanan[] mD_Pembebanan=null;
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
            hasilQuery.last();
            mD_Pembebanan = new MD_Pembebanan[hasilQuery.getRow()];
            hasilQuery.beforeFirst();
            int i =0;
            while(hasilQuery.next()){          
                mD_Pembebanan[i] = new MD_Pembebanan();
                mD_Pembebanan[i].KODECOSTCENTER = hasilQuery.getString("kodecostcenter");
                mD_Pembebanan[i].KODEPEMBEBANAN = hasilQuery.getString("kodepembebanan");
                i++;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class MDPembebananDb method selectCustomRow2: " + ex);
        }catch(Exception ex){
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class MDPembebananDb method selectCustomRow2: " + ex);
            ex.printStackTrace();
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return mD_Pembebanan;
    }
    public boolean isDuplicateCostcenter(MD_Pembebanan[] mD_Pembebanan){
        boolean report = false;                    
            for (int i=0;i<mD_Pembebanan.length;i++){
                for(int j=0;j<mD_Pembebanan.length;j++){
                    if(i!=j){
                        if(mD_Pembebanan[i].KODECOSTCENTER.equals(mD_Pembebanan[j].KODECOSTCENTER)){
                            report = true;
                            break;
                        }
                    }
                }
                if(report){
                    break;
                }
            }
        return report;
    }
    public String selectAllRow(){
        String sql = "select distinct a.kodepembebanan,a.kodecostcenter,b.namacostcenter " +
                "from md_pembebanan a, vm_hris_costcenter b " +
                "where a.KODECOSTCENTER = b.KODECOSTCENTER (+) ";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table MDPembebanan in class MDPembebananDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MDPembebanan();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String convertToGridExtJsFormat_MDPembebanan(){
        String hasil = GridExtJsFormat_MDPembebanan.rootElAwal;
        try {
            while(hasilQuery.next()){
                   hasil+=GridExtJsFormat_MDPembebanan.rowElAwal;
                   hasil+=GridExtJsFormat_MDPembebanan.kodepembebananElAwal+hasilQuery.getString("kodepembebanan")+GridExtJsFormat_MDPembebanan.kodepembebananElAkhir;
                   hasil+=GridExtJsFormat_MDPembebanan.kodecostcenterElAwal+(hasilQuery.getString("kodecostcenter")==null?"":hasilQuery.getString("kodecostcenter"))
                            +GridExtJsFormat_MDPembebanan.kodecostcenterElAkhir;
                   hasil+=GridExtJsFormat_MDPembebanan.namacostcenterElAwal+
                           (hasilQuery.getString("namacostcenter")==null?"":(hasilQuery.getString("namacostcenter")).replace("&", "&amp;"))+
                           GridExtJsFormat_MDPembebanan.namacostcenterElAkhir;
                   hasil+=GridExtJsFormat_MDPembebanan.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_MDPembebanan : " + ex);
        }
        hasil+=GridExtJsFormat_MDPembebanan.rootElAkhir;
        return hasil;
    }
}
