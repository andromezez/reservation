/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;

import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MMenu;
import com.wonokoyo.model.formatdata.TreepanelXMLFormat;
import com.wonokoyo.model.formatdata.url.form.URLParameterFormatForm;
import com.wonokoyo.model.formatdata.url.treemenu.URLParameterFormatTreeMenu;
import com.wonokoyo.model.strukturdata.M_Menu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.internal.OracleCallableStatement;


/**
 *
 * @author SK
 */
public class MMenuDb {
    private ResultSet hasilQuery;    
    private DBConnection dBConnection;
    public MMenuDb(DBConnection dBConnection) {
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
    
    public String insertOneRow(String kodeform, String namaform, String kodeparent, String url){
        
        String tambahmenu = "INSERT INTO M_MENU(KODEFORM,NAMAFORM,KODEPARENT,URL)" +
                "VALUES ('"+kodeform +"', '"+ namaform +"', '"+ kodeparent +"', '"+ url +"')";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master menu telah tersimpan.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MMenu(kodeform, namaform, url, "insert");
            if(datakembar == null){
                dBConnection.getStmt().executeUpdate(tambahmenu);
            }else{
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table M_MENU in class MMenuDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data master menu: "+DBConnection.getOracleErrorSentence(ex));

        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(String kodeform, String namaform, String kodeparent, String url){
        String updatemenu = "UPDATE M_MENU SET NAMAFORM = '"+namaform+"', "
                + "KODEPARENT='"+kodeparent+"'," + "URL='"+url+"' "
                + " WHERE KODEFORM = '"+kodeform+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master menu telah terupdate.", "");
        try {
            DB_Procedure procedure = new DB_Procedure(dBConnection);
            String datakembar = procedure.cekDataKembar_MMenu(kodeform, namaform, url, "update");
            if(datakembar == null){
                dBConnection.getStmt().executeUpdate(updatemenu);
            }else{
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);

            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_MENU in class MMenuDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data master menu: "+DBConnection.getOracleErrorSentence(ex));

        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteOneRow(String kodeform){
        String deletemenu = "DELETE FROM M_MENU WHERE KODEFORM = '"+kodeform+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data master menu telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(deletemenu);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error delete table M_MENU in class MMenuDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data master menu: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public M_Menu selectOneRow(String menuid){
        String selectmenu = "SELECT * FROM M_MENU WHERE KODEFORM = '"+menuid+"'";
        M_Menu mmenu = new M_Menu();
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selectmenu);            
            while(hasilQuery.next()){
                mmenu.KODEFORM=hasilQuery.getString("KODEFORM");
                mmenu.KODEPARENT=hasilQuery.getString("KODEPARENT");
                mmenu.NAMAFORM=hasilQuery.getString("NAMAFORM");
                mmenu.URL=hasilQuery.getString("URL");
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_MENU in class MMenuDb method selectOneRow: " + ex);
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return mmenu;
    }

    public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class M_MENUDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MMenu();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String convertToGridExtJsFormat_MMenu(){
        //String selectmenu = "SELECT * FROM M_MENU";
        String hasil = GridExtJsFormat_MMenu.rootElAwal;
        int n,i;
        try {
            //hasilQuery = getStmt().executeQuery(selectmenu);
            while(hasilQuery.next()){
                hasil+=GridExtJsFormat_MMenu.rowElAwal+
                        GridExtJsFormat_MMenu.kodeformElAwal+hasilQuery.getString("KODEFORM")+GridExtJsFormat_MMenu.kodeformElAkhir+
                        GridExtJsFormat_MMenu.namaformElAwal+hasilQuery.getString("NAMAFORM").replace("&", "&amp;")+GridExtJsFormat_MMenu.namaformElAkhir+
                       GridExtJsFormat_MMenu.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_MENU in class MMenuDb method selectAllRow: " + ex);
        }
        hasil+=GridExtJsFormat_MMenu.rootElAkhir;
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String selectAllRowToGridExtJsFormat_MMenu(){
        String selectmenu = "SELECT * FROM M_MENU";
        String hasil = GridExtJsFormat_MMenu.rootElAwal;
        int n,i;
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(selectmenu);
            while(hasilQuery.next()){
                String kodeparent = hasilQuery.getString("KODEPARENT");
                String url = hasilQuery.getString("URL");
                if(kodeparent==null){
                    kodeparent = "";
                }
                if(url==null){
                    url = "";
                }

                hasil+=GridExtJsFormat_MMenu.rowElAwal+
                        GridExtJsFormat_MMenu.kodeformElAwal+hasilQuery.getString("KODEFORM")+GridExtJsFormat_MMenu.kodeformElAkhir+
                        GridExtJsFormat_MMenu.namaformElAwal+hasilQuery.getString("NAMAFORM").replace("&", "&amp;")+GridExtJsFormat_MMenu.namaformElAkhir+
                        GridExtJsFormat_MMenu.kodeparentElAwal+kodeparent+GridExtJsFormat_MMenu.kodeparentElAkhir+
                        GridExtJsFormat_MMenu.urlElAwal+url+GridExtJsFormat_MMenu.urlElAkhir+
                       GridExtJsFormat_MMenu.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table M_MENU in class MMenuDb method selectAllRow: " + ex);
        }
        hasil+=GridExtJsFormat_MMenu.rootElAkhir;
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String getAllMenuToXMLforTreepanel(String userid){
        String xmlFormat = TreepanelXMLFormat.rootElAwal;
        String selectListMenu="SELECT column_value form " +
                        "FROM TABLE (CAST (splitStrToRowTable_withkres_2 (getListMenu('01',0),'|','"+userid+"') AS mytabletype))";
        try {
            hasilQuery= dBConnection.getStmt().executeQuery(selectListMenu);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            //System.err.println("Error select tabel menu pada method getAllMenuToXMLforTreepanel: " + ex);
            return("Error select tabel menu pada method getAllMenuToXMLforTreepanel: " + ex);
        }
        try {
            int j=0,n;
            hasilQuery.last();
            n=hasilQuery.getRow();
            int level[] = new int[n];
            hasilQuery.beforeFirst();
            while(hasilQuery.next()){
                String[] hasilSplit = hasilQuery.getString("form").split(":");
                if(hasilSplit[0].contains("+++")){
                    level[j] = 3;
                }else if(hasilSplit[0].contains("++")){
                    level[j] = 2;
                }else if(hasilSplit[0].contains("+")){
                    level[j] = 1;
                }else{
                    level[j] = 0;
                }
                j++;
            }

            j=0;
            hasilQuery.beforeFirst();
            while(hasilQuery.next()){
                String[] hasilSplit = hasilQuery.getString("form").split(":");
                String url = "maincontroller?"+URLParameterFormatTreeMenu.generateParameterTreeModeOPEN_FORM()+"&amp;"+
                            URLParameterFormatForm.generateParameterFormId(hasilSplit[1]);
                if(hasilSplit[0].contains("+++")){
                    xmlFormat = xmlFormat +  TreepanelXMLFormat.level3ElAwal;
                    xmlFormat=xmlFormat.replace("#title", hasilSplit[0].replace("+++", "").replace("&", "&amp;"));
                    xmlFormat=xmlFormat.replace("#id", hasilSplit[1]);
                    if (hasilSplit[2].equalsIgnoreCase("null")){
                        xmlFormat= xmlFormat.replace("#url", hasilSplit[2]);
                        xmlFormat= xmlFormat.replace("#daun", "false");
                    }else{
                        xmlFormat= xmlFormat.replace("#url", url);
                        xmlFormat= xmlFormat.replace("#daun", "true");
                    }
                    if (j+1<n){
                        if (level[j+1]>level[j]){}
                        else if(level[j+1]==level[j]){
                            xmlFormat = xmlFormat + TreepanelXMLFormat.level3ElAkhir;
                        }else{
                            xmlFormat = xmlFormat + TreepanelXMLFormat.level3ElAkhir;
                            xmlFormat = xmlFormat + TreepanelXMLFormat.level2ElAkhir;
                            if(level[j+1]==1){
                                xmlFormat = xmlFormat + TreepanelXMLFormat.level1ElAkhir;
                            }
                        }
                    }else{
                        xmlFormat = xmlFormat + TreepanelXMLFormat.level3ElAkhir;
                        xmlFormat = xmlFormat + TreepanelXMLFormat.level2ElAkhir;
                        xmlFormat = xmlFormat + TreepanelXMLFormat.level1ElAkhir;
                    }
                }else if(hasilSplit[0].contains("++")){
                    xmlFormat = xmlFormat +  TreepanelXMLFormat.level2ElAwal;
                    xmlFormat=xmlFormat.replace("#title", hasilSplit[0].replace("++", "").replace("&", "&amp;"));
                    xmlFormat=xmlFormat.replace("#id", hasilSplit[1]);
                    if (hasilSplit[2].equalsIgnoreCase("null")){
                        xmlFormat=xmlFormat.replace("#url", hasilSplit[2]);
                        xmlFormat= xmlFormat.replace("#daun", "false");
                    }else{
                        xmlFormat= xmlFormat.replace("#url", url);
                        xmlFormat= xmlFormat.replace("#daun", "true");
                    }
                    if (j+1<n){
                        if (level[j+1]>level[j]){}
                        else if(level[j+1]==level[j]){
                            xmlFormat = xmlFormat + TreepanelXMLFormat.level2ElAkhir;
                        }else{
                            xmlFormat = xmlFormat + TreepanelXMLFormat.level2ElAkhir;
                            xmlFormat = xmlFormat + TreepanelXMLFormat.level1ElAkhir;
                        }
                    }else{
                        xmlFormat = xmlFormat + TreepanelXMLFormat.level2ElAkhir;
                        xmlFormat = xmlFormat + TreepanelXMLFormat.level1ElAkhir;
                    }
                }else if(hasilSplit[0].contains("+")){
                    xmlFormat = xmlFormat +  TreepanelXMLFormat.level1ElAwal;
                    xmlFormat=xmlFormat.replace("#title", hasilSplit[0].replace("+", "").replace("&", "&amp;"));
                    xmlFormat=xmlFormat.replace("#id", hasilSplit[1]);
                    if (hasilSplit[2].equalsIgnoreCase("null")){
                        xmlFormat=xmlFormat.replace("#url", hasilSplit[2]);
                        xmlFormat= xmlFormat.replace("#daun", "false");
                    }else{
                        xmlFormat= xmlFormat.replace("#url", url);
                        xmlFormat= xmlFormat.replace("#daun", "true");
                    }
                    if (j+1<n){
                        if (level[j+1]>level[j]){}
                        else if(level[j+1]==level[j]){
                            xmlFormat = xmlFormat + TreepanelXMLFormat.level1ElAkhir;
                        }else{
                            xmlFormat = xmlFormat + TreepanelXMLFormat.level1ElAkhir;
                        }
                    }else{
                        xmlFormat = xmlFormat + TreepanelXMLFormat.level1ElAkhir;
                    }
                }
                try {
                    xmlFormat=xmlFormat.replace("#userid", userid);
                    ResultSet permission = dBConnection.getStmt().executeQuery("select PVIEW,PADD,PUPDATE,PDELETE,PPRINT,BATAL_TRANS " +
                                "from m_permission " +
                                "where kodeform = '"+hasilSplit[1]+"' and userid = '"+userid+"'");
                    permission.last();
                    if(permission.getRow()<1){
                        xmlFormat=xmlFormat.replace("#pview", "null");
                        xmlFormat=xmlFormat.replace("#padd", "null");
                        xmlFormat=xmlFormat.replace("#pupdate", "null");
                        xmlFormat=xmlFormat.replace("#pdelete", "null");
                        xmlFormat=xmlFormat.replace("#pprint","null");
                        xmlFormat=xmlFormat.replace("#batal_trans","null");
                    }else{
                        permission.beforeFirst();
                        while(permission.next()){
                            xmlFormat=xmlFormat.replace("#pview", permission.getString("PVIEW"));
                            xmlFormat=xmlFormat.replace("#padd", permission.getString("PADD"));
                            xmlFormat=xmlFormat.replace("#pupdate", permission.getString("PUPDATE"));
                            xmlFormat=xmlFormat.replace("#pdelete", permission.getString("PDELETE"));
                            xmlFormat=xmlFormat.replace("#pprint", permission.getString("PPRINT"));
                            xmlFormat=xmlFormat.replace("#batal_trans", permission.getString("BATAL_TRANS"));
                        }
                    }
                } catch (SQLException ex) {
                    dBConnection.isErrorProcessingDb=true;
                    //System.err.println("Error select tabel menu pada method getAllMenuToXMLforTreepanel: " + ex);
                    System.err.println("Error select permission pada method getAllMenuToXMLforTreepanel: " + ex);
                }
                j++;
            }
            xmlFormat = xmlFormat + TreepanelXMLFormat.rootElAkhir;
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error baca resultset in method getAllMenuToXMLforTreepanel: " + ex);
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return xmlFormat;
    }

}
