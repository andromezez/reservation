/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;

/**
 *
 * @author SK
 */
import com.wonokoyo.model.strukturdata.M_User;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MUser;
import java.sql.CallableStatement;
import oracle.jdbc.internal.OracleCallableStatement;

public class MUserDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;    
    public MUserDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class MUserDb: " + ex);
        }
    }

    public String insertOneRow(String userid,String kodebp,
            String pass, String aktif){
        String sql = "INSERT INTO M_USER(USERID, KODEBP, PASS,AKTIF)" +
                "VALUES ('"+userid +"', '"+ kodebp +"', '"+ userid +"', '"+ aktif +"')";
        
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master user telah tersimpan.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MUser(userid, kodebp, "insert");
            if(datakembar == null){
                dBConnection.getStmt().executeUpdate(sql);
            }else{
                dBConnection.isErrorProcessingDb=true;
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table M_USER in class MUserDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data master user: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(String userid,String kodebp,
            String pass, String aktif){
        String sql = "UPDATE M_USER SET KODEBP = '"+kodebp+"', "
                + "AKTIF = '"+aktif+"', PASS='"+pass+"' "
                + "WHERE USERID = '"+userid+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master user telah terupdate.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MUser(userid, kodebp, "update");
            if(datakembar == null){
                dBConnection.getStmt().executeUpdate(sql);
            }else{
                dBConnection.isErrorProcessingDb=true;
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_USER in class MUserDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data master user: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String updateOneRowForTransaction(String userid,String kodebp,String aktif){
        String sql = "UPDATE M_USER SET KODEBP = '"+kodebp+"', "
                + "AKTIF = '"+aktif+"' "
                + "WHERE USERID = '"+userid+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master user telah terupdate.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MUser(userid, kodebp, "update");
            if(datakembar == null){
                dBConnection.getStmt().executeUpdate(sql);
            }else{
                dBConnection.isErrorProcessingDb=true;
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_USER in class MUserDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data master user: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String deleteOneRow(String userid){
        String sql = "DELETE FROM M_USER WHERE USERID = '"+userid+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master user telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_USER in class MUserDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data master user: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public M_User selectOneRow(String userid, String pass){
        String sql = "SELECT distinct a.*, b.namabp FROM M_USER a, vm_hris_bpartnerlengkap b  WHERE a.USERID = '"+userid+"' and a.pass = '"+pass+"' and a.kodebp = b.kodebp ";
        M_User muser = new M_User();
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
            while(hasilQuery.next()){
                muser.USERID = hasilQuery.getString("USERID");
                muser.KODEBP=hasilQuery.getString("KODEBP");                
                muser.PASS = hasilQuery.getString("PASS");
                //muser.COSTCENTER=hasilQuery.getString("COSTCENTER");
                muser.AKTIF=hasilQuery.getString("AKTIF");
                muser.NAMABP =hasilQuery.getString("namabp");
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_USER in class MUserDb method selectOneRow: " + ex);
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return muser;
    }

    public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class M_USERDb method selectCustomRow: " + ex);
        }
        String hasil = selectAllRowToGridExtJsFormat_MUser();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String selectAllRow(){
        String sql = "select distinct a.userid,b.namabp,a.kodebp,b.nik,a.pass,a.aktif " +
            "FROM m_user a, vm_hris_bpartnerlengkap b  " +
            "WHERE a.kodebp = b.kodebp(+) ";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_USER in class M_USERDb method selectAllRow: " + ex);
        }
        String hasil = selectAllRowToGridExtJsFormat_MUser();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String selectAllRowToGridExtJsFormat_MUser(){
        /*String selectuser = "select distinct a.userid,b.namabp,a.kodebp,b.nik,a.costcenter,a.aktif " +
            "FROM m_user a, vm_hris_bpartnerlengkap b  " +
            "WHERE a.kodebp = b.kodebp(+) ";*/
        String hasil = GridExtJsFormat_MUser.rootElAwal;
        int n,i;
        try {
            //hasilQuery = dBConnection.getStmt().executeQuery(selectuser);
            while(hasilQuery.next()){
                String kodebp = hasilQuery.getString("KODEBP");
                //String costcenter = hasilQuery.getString("COSTCENTER");
                String namabp = hasilQuery.getString("NAMABP");
                String nik = hasilQuery.getString("NIK");
                if(kodebp==null){
                    kodebp = "";
                }
                /*if(costcenter==null){
                    costcenter = "";
                }*/
                if(nik==null){
                    nik = "";
                }
                if(namabp==null){
                    namabp = "";
                }

                hasil+=GridExtJsFormat_MUser.rowElAwal+
                        GridExtJsFormat_MUser.useridElAwal+hasilQuery.getString("USERID")+GridExtJsFormat_MUser.useridElAkhir+
                        GridExtJsFormat_MUser.namabpElAwal+namabp+GridExtJsFormat_MUser.namabpElAkhir+
                        GridExtJsFormat_MUser.kodebpElAwal+kodebp+GridExtJsFormat_MUser.kodebpElAkhir+
                        GridExtJsFormat_MUser.nikElAwal+nik+GridExtJsFormat_MUser.nikElAkhir+
                        //GridExtJsFormat_MUser.costcenterElAwal+costcenter+GridExtJsFormat_MUser.costcenterElAkhir+
                        GridExtJsFormat_MUser.aktifElAwal+hasilQuery.getString("AKTIF")+GridExtJsFormat_MUser.aktifElAkhir+
                       GridExtJsFormat_MUser.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_USER in class MUserDb method selectAllRow: " + ex);
        }
        hasil+=GridExtJsFormat_MUser.rootElAkhir;

        return hasil;
    }
}
