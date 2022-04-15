/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model;
import com.wonokoyo.model.formatdata.scheduler.DHTMLXSchedulerFormat_JadwalSewaruang;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author king peter
 */
public class JadwalSewaruangDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;    
    public JadwalSewaruangDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class JadwalSewaruangDb: " + ex);
        }
    }
    public String selectCustomRow(String sql){
        try {
            DB_Procedure dB_Procedure = new DB_Procedure(dBConnection);  
            dB_Procedure.gen_jdwlsewaruang();
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error in class JadwalSewaruangDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_jadwalsewaruang();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String selectAllRow(){
        String sql = "SELECT a.* "
                + "FROM jadwalsewaruang a, (select to_number(parvalue) minusday from m_parameter where parname='minusday') b "
                + "where to_date(to_char(to_date(a.START_DATE,'yyyy-mm-dd hh24:mi'),'yyyy-mm-dd'),'yyyy-mm-dd') >= (to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')-b.minusday)";
        DB_Procedure dB_Procedure = new DB_Procedure(dBConnection);            
        try {
            dB_Procedure.gen_jdwlsewaruang();
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table in class JadwalSewaruangDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_jadwalsewaruang();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String convertToGridExtJsFormat_jadwalsewaruang(){
        String hasil = DHTMLXSchedulerFormat_JadwalSewaruang.rootElAwal;
        String text = "";
        try {
            while(hasilQuery.next()){
                text = hasilQuery.getString("TEXT") == null?"":hasilQuery.getString("TEXT");
                hasil+=DHTMLXSchedulerFormat_JadwalSewaruang.eventEl;                    
                hasil=hasil.replaceFirst(DHTMLXSchedulerFormat_JadwalSewaruang.idSymbol, hasilQuery.getString("ID"));
                hasil=hasil.replaceFirst(DHTMLXSchedulerFormat_JadwalSewaruang.start_dateSymbol, hasilQuery.getString("START_DATE"));
                hasil=hasil.replaceFirst(DHTMLXSchedulerFormat_JadwalSewaruang.end_dateSymbol, hasilQuery.getString("END_DATE"));
                hasil=hasil.replaceFirst(DHTMLXSchedulerFormat_JadwalSewaruang.textSymbol, text.replace("&", "&amp;"));                
                hasil=hasil.replaceFirst(DHTMLXSchedulerFormat_JadwalSewaruang.kode_itemSymbol, hasilQuery.getString("KODEITEM"));
                hasil=hasil.replaceFirst(DHTMLXSchedulerFormat_JadwalSewaruang.readonlySymbol, hasilQuery.getString("READONLY"));    
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convert to convertToGridExtJsFormat_jadwalsewaruang : " + ex);
        }catch (Exception ex) {            
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convert to convertToGridExtJsFormat_jadwalsewaruang : " + ex);
        }
        hasil+=DHTMLXSchedulerFormat_JadwalSewaruang.rootElAkhir;
        return hasil;
    }
}
