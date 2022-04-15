/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bayu
 */

package com.wonokoyo.model;

import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MParameter;
import com.wonokoyo.model.strukturdata.M_Parameter;
import com.wonokoyo.model.utilities.validation.StringField;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.CallableStatement;


public class MParameterDb{
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    public MParameterDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class MParameterDb: " + ex);
        }
    }

    public String insertOneRow(String parname, String parvalue, String partype){
        String tambahparam = "INSERT INTO M_PARAMETER(PARNAME, PARVALUE, PARTYPE)" +
                "VALUES ('"+parname +"', '"+ parvalue +"', '"+ partype +"')";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master parameter telah tersimpan.", "");
        try {
            dBConnection.getStmt().executeUpdate(tambahparam);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table M_PARAMETER in class MParameterDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data master parameter: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(String parname, String parvalue, String partype){
        String updateparam = "UPDATE M_PARAMETER SET PARVALUE = '"+parvalue+"', "
                + "PARTYPE = '"+partype+"' "
                + "WHERE PARNAME = '"+parname+"'";
         String hasil = SuccessErrorMessege.generateMessege(true, "Data master parameter telah terupdate.", "");
        try {
            dBConnection.getStmt().executeUpdate(updateparam);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_PARAMETER in class MParameterDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data master parameter: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteOneRow(String parname){
        String deleteparam = "DELETE FROM M_PARAMETER WHERE PARNAME = '"+parname+"'";
        
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master parameter telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(deleteparam);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_PARAMETER in class MParameterDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data master parameter: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public M_Parameter selectOneRow(String parname){
        String selectparam = "SELECT * FROM M_PARAMETER WHERE parname = '"+parname+"'";
        M_Parameter mparameter = new M_Parameter();
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selectparam);
            while(hasilQuery.next()){
                mparameter.PARNAME=hasilQuery.getString("PARNAME");
                mparameter.PARVALUE=hasilQuery.getString("PARVALUE");
                mparameter.PARTYPE=hasilQuery.getString("PARTYPE");
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_PARAMETER in class MParameterDb method selectOneRow: " + ex);
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return mparameter;
    }
    public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class MParameterDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MParameter();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public M_Parameter[] selectCustomRow_ver2(String sql){
        M_Parameter[] hasil=null;
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
            hasilQuery.last();
            hasil = new M_Parameter[hasilQuery.getRow()];
            hasilQuery.beforeFirst();
            int i=0;
            while(hasilQuery.next()){
                hasil[i].PARNAME = hasilQuery.getString("parname");
                hasil[i].PARTYPE =hasilQuery.getString("partype");
                hasil[i].PARVALUE = hasilQuery.getString("parvalue");
                i++;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class MParameterDb method selectCustomRow_ver2: " + ex);
        }
        
        
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    
    public String convertToGridExtJsFormat_MParameter(){
        String hasil = GridExtJsFormat_MParameter.rootElAwal;
        try {
            while(hasilQuery.next()){
                   hasil+=GridExtJsFormat_MParameter.rowElAwal;
                   hasil+=GridExtJsFormat_MParameter.parnameElAwal+hasilQuery.getString("PARNAME")+GridExtJsFormat_MParameter.parnameElAkhir;
                   hasil+=GridExtJsFormat_MParameter.parvalueElAwal+(hasilQuery.getString("PARVALUE"))+GridExtJsFormat_MParameter.parvalueElAkhir;
                   hasil+=GridExtJsFormat_MParameter.partypeElAwal+(hasilQuery.getString("PARTYPE")) +GridExtJsFormat_MParameter.partypeElAkhir;
                   hasil+=GridExtJsFormat_MParameter.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_MParameter : " + ex);
        }
        hasil+=GridExtJsFormat_MParameter.rootElAkhir;
        return hasil;
    }
    public String selectAllRowToGridExtJsFormat_MParameter(){
        String selectparam = "SELECT * FROM M_PARAMETER";
        String hasil = GridExtJsFormat_MParameter.rootElAwal;
        int n,i;
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selectparam);
            while(hasilQuery.next()){
                String parvalue = hasilQuery.getString("PARVALUE");
                String partype = hasilQuery.getString("PARTYPE");
                if(parvalue==null){
                    parvalue = "";
                }
                if(partype==null){
                    partype = "";
                }

                hasil+=GridExtJsFormat_MParameter.rowElAwal+
                        GridExtJsFormat_MParameter.parnameElAwal+hasilQuery.getString("PARNAME")+GridExtJsFormat_MParameter.parnameElAkhir+
                        GridExtJsFormat_MParameter.parvalueElAwal+parvalue+GridExtJsFormat_MParameter.parvalueElAkhir+
                        GridExtJsFormat_MParameter.partypeElAwal+partype+GridExtJsFormat_MParameter.partypeElAkhir+
                       GridExtJsFormat_MParameter.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_PARAMETER in class MParameterDb method selectAllRow: " + ex);
        }
        hasil+=GridExtJsFormat_MParameter.rootElAkhir;
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
}
