/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;

import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MHistory;
import com.wonokoyo.model.strukturdata.M_History;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Indra
 */
public class MHistoryDb{
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    public MHistoryDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class MHistoryDb: " + ex);
        }
    }

    public String insertOneRow(String kodeform, String tabel, String pk1, String pk1value, String pk2, String pk2value,
                               String pk3, String pk3value, String keterangan, String createdby, String createddate, String updatedby,
                               String updateddate, String deletedby, String deleteddate){
        String tambahjenis = "INSERT INTO M_HISTORYFORM(KODEFORM, TABEL, PK1, PK1VALUE, PK2, PK2VALUE, PK3, PK3VALUE, KETERANGAN,"
                           + "CREATEDBY, CREATEDDATE, UPDATEDBY, UPDATEDDATE, DELETEDBY, DELETEDDATE)" +
                             "VALUES ('"+ kodeform +"', '"+ tabel +"', '"+ pk1 +"', '"+ pk1value +"', '"+ pk2 +"', '"+ pk2value
                             +"', '"+ pk3 +"', '"+ pk3value +"', '"+ keterangan +"', '"+ createdby +"', '"+ createddate
                             +"', '"+ updatedby +"', '"+ updateddate +"', '"+ deletedby +"', '"+ deleteddate +"')";
        /*String hasil = "{" +
                  "success: true," +
                  "errors: {"+

                  "},"+
                    "successmsg: \"Data master jenis telah tersimpan.\""+
                  "}";*/
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master history telah tersimpan.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MJenis(kodeform, tabel, "insert");
            if( datakembar == null){
                dBConnection.getStmt().executeUpdate(tambahjenis);
            }else{
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
                /*hasil = "{" +
                  "success: false," +
                  "errors: {"+

                  "},"+
                    //"errormsg: \"Error.\""+
                    "errormsg: \""+ datakembar +" telah ada sebelumnya.\""+
                  "}";*/
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table M_JENIS in class MJenisDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data master jenis: "+DBConnection.getOracleErrorSentence(ex));
            /*hasil = "{" +
                  "success: false," +
                  "errors: {"+

                  "},"+
                    "errormsg: \"Tidak bisa menyimpan data master jenis.\""+
                  "}";*/
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(String kodeform, String tabel, String pk1, String pk1value, String pk2, String pk2value,
                               String pk3, String pk3value, String keterangan, String createdby, String createddate, String updatedby,
                               String updateddate, String deletedby, String deleteddate){
        String updateitem = "UPDATE M_HISTORYFORM SET KODEFORM = '"+kodeform+"', "
                + "TABEL='"+tabel+"'," + "PK1="+pk1+", "
                + "PK1VALUE="+pk1value+"," + "PK2='"+pk2+"', "
                + "PK2VALUE='"+pk2value+"'," + "PK3='"+pk3+"', "
                + "PK3VALUE="+pk3value+"," + "KETERANGAN='"+keterangan+"', "
                + "CREATEDBY="+createdby+"," + "CREATEDDATE='"+createddate+"', "
                + "UPDATEDBY="+updatedby+"," + "UPDATEDDATE='"+updateddate+"', "
                + "DELETEDBY="+deletedby+"," + "DELETEDDATE='"+deleteddate+"', "
                + "WHERE KODEFORM = '"+kodeform+"'";
         /*String hasil = "{" +
                  "success: true," +
                  "errors: {"+

                  "},"+
                    "successmsg: \"Data master jenis telah terupdate.\""+
                  "}";*/
         String hasil = SuccessErrorMessege.generateMessege(true, "Data master history telah terupdate.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MJenis(kodeform, tabel, "update");
            if( datakembar == null){
                dBConnection.getStmt().executeUpdate(updateitem);
            }else{
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
                /*hasil = "{" +
                  "success: false," +
                  "errors: {"+

                  "},"+
                    //"errormsg: \"Error.\""+
                    "errormsg: \""+ datakembar +" telah ada sebelumnya.\""+
                  "}";*/
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_HISTORYFORM in class MHistoryDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data master history: "+DBConnection.getOracleErrorSentence(ex));
            /*hasil = "{" +
                  "success: false," +
                  "errors: {"+

                  "},"+
                    "errormsg: \"Tidak bisa mengupdate data master jenis.\""+
                  "}";*/
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteOneRow(String kodeform){
        String deleteitem = "DELETE FROM M_HISTORYFORM WHERE KODEFORM = '"+kodeform+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master history telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(deleteitem);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_HISTORYFORM in class MHistoryDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data master history: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String selectAllRow(){
        String selecthistory = "select kodeform, tabel, pk1, pk1value,pk2,pk2value, pk3,pk3value, keterangan,"
                + "createdby,To_Char(createddate,'mm/dd/yyyy') createddate, updatedby, To_Char(updateddate,'mm/dd/yyyy') updateddate,"
                + " deletedby, To_Char(deleteddate,'mm/dd/yyyy') deleteddate from M_HISTORYFORM";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selecthistory);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_History in class MHistoryDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MHistory();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public M_History selectOneRow(String kodeform){
        String selecthistory = "select kodeform, tabel, pk1, pk1value,pk2,pk2value, pk3,pk3value, keterangan,"
                + "createdby,To_Char(createddate,'mm/dd/yyyy') createddate, updatedby, To_Char(updateddate,'mm/dd/yyyy') updateddate,"
                + " deletedby, To_Char(deleteddate,'mm/dd/yyyy') deleteddate FROM M_HISTORYFORM WHERE kodeform = '"+kodeform+"'";
        M_History mhistory = new M_History();
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selecthistory);
            while(hasilQuery.next()){
                mhistory.KODEFORM=hasilQuery.getString("KODEFORM");
                mhistory.TABEL=hasilQuery.getString("TABEL");
                mhistory.PK1=hasilQuery.getString("PK1");
                mhistory.PK1VALUE=hasilQuery.getString("PK1VALUE");
                mhistory.PK2=hasilQuery.getString("PK2");
                mhistory.PK2VALUE=hasilQuery.getString("PK2VALUE");
                mhistory.PK3=hasilQuery.getString("KP3");
                mhistory.PK3VALUE=hasilQuery.getString("PK3VALUE");
                mhistory.KETERANGAN=hasilQuery.getString("KETERANGAN");
                mhistory.CREATEDBY=hasilQuery.getString("CREATEDBY");
                mhistory.CREATEDDATE=hasilQuery.getString("CREATEDDATE");
                mhistory.UPDATEDBY=hasilQuery.getString("UPDATEDBY");
                mhistory.UPDATEDDATE=hasilQuery.getString("UPDATEDDATE");
                mhistory.DELETEDBY=hasilQuery.getString("DELETEDBY");
                mhistory.DELETEDDATE=hasilQuery.getString("DELETEDDATE");
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_JENIS in class MJenisDb method selectOneRow: " + ex);
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return mhistory;
    }

      public String convertToGridExtJsFormat_MHistory(){
        String hasil = GridExtJsFormat_MHistory.rootElAwal;
        try {
            while(hasilQuery.next()){
                hasil+=GridExtJsFormat_MHistory.rowElAwal+
                        GridExtJsFormat_MHistory.kodeformElAwal+hasilQuery.getString("KODEFORM")+GridExtJsFormat_MHistory.kodeformElAkhir+
                        GridExtJsFormat_MHistory.tabelElAwal+hasilQuery.getString("TABEL")+GridExtJsFormat_MHistory.tabelElAkhir+
                        GridExtJsFormat_MHistory.pk1ElAwal+hasilQuery.getString("PK1")+GridExtJsFormat_MHistory.pk1ElAkhir+
                        GridExtJsFormat_MHistory.pk1valueElAwal+hasilQuery.getString("PK1VALUE")+GridExtJsFormat_MHistory.pk1valueElAkhir+
                        GridExtJsFormat_MHistory.pk2ElAwal+hasilQuery.getString("PK2")+GridExtJsFormat_MHistory.pk2ElAkhir+
                        GridExtJsFormat_MHistory.pk2valueElAwal+hasilQuery.getString("PK2VALUE")+GridExtJsFormat_MHistory.pk2valueElAkhir+
                        GridExtJsFormat_MHistory.pk3ElAwal+hasilQuery.getString("PK3")+GridExtJsFormat_MHistory.pk3ElAkhir+
                        GridExtJsFormat_MHistory.pk3valueElAwal+hasilQuery.getString("PK3VALUE")+GridExtJsFormat_MHistory.pk3valueElAkhir+
                        GridExtJsFormat_MHistory.keteranganElAwal+hasilQuery.getString("KETERANGAN")+GridExtJsFormat_MHistory.keteranganElAkhir+
                        GridExtJsFormat_MHistory.createdbyElAwal+hasilQuery.getString("CREATEDBY")+GridExtJsFormat_MHistory.createdbyElAkhir+
                        GridExtJsFormat_MHistory.createddateElAwal+hasilQuery.getString("CREATEDDATE")+GridExtJsFormat_MHistory.createddateElAkhir+
                        GridExtJsFormat_MHistory.updatedbyElAwal+hasilQuery.getString("UPDATEDBY")+GridExtJsFormat_MHistory.updatedbyElAkhir+
                        GridExtJsFormat_MHistory.updateddateElAwal+hasilQuery.getString("UPDATEDDATE")+GridExtJsFormat_MHistory.updateddateElAkhir+
                        GridExtJsFormat_MHistory.deletedbyElAwal+hasilQuery.getString("DELETEDBY")+GridExtJsFormat_MHistory.deletedbyElAkhir+
                        GridExtJsFormat_MHistory.deleteddateElAwal+hasilQuery.getString("DELETEDDATE")+GridExtJsFormat_MHistory.deleteddateElAkhir+
                       GridExtJsFormat_MHistory.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_MCoba : " + ex);
        }
        hasil+=GridExtJsFormat_MHistory.rootElAkhir;
        return hasil;
    }
}


