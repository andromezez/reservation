/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MAcc;
import com.wonokoyo.model.strukturdata.M_Acc;
import com.wonokoyo.model.utilities.datetime.OracleDateAndTimeStringFormat;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 *
 * @author Bayu
 */
public class MAccDb{
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    //private ResultSet rs testing;
    public MAccDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class MAccDb: " + ex);
        }
    }
    public String selectCustomRow(String sql){
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class MAccDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MAcc();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public int selectCountByKodeFormAndKodeTrans(String kodeform, String kodetransaksi){
        int hasil=-1;
        try {
            String sql = "select count(*) jumlahrow from m_acc where kodeform='"+kodeform+"' and kodetransaksi='"+kodetransaksi+"' "+
                    "and statusacc is not null and statusacc != ''";
            hasilQuery = dBConnection.getStmt().executeQuery(sql);            
            while(hasilQuery.next()){
                hasil = hasilQuery.getInt("jumlahrow");
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error class MAccDb method selectCountByKodeFormAndKodeTrans: " + ex);
        }        
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String selectAllRow(){
        String sql = "select distinct a.userid  , a.kodeform  , a.kodetransaksi  , TO_CHAR(a.tglacc,'mm/dd/yyyy') tglacc, "
                     + "a.prioritas, a.kodejabatan,  b.namajabatan, c.namabp, d.keterangan, a.statusacc "
                     + "from m_acc a, hris.m_jabatan b , VM_HRIS_BPARTNERLENGKAP c, MH_KONFIGURASIACC d "
                     + "where a.kodejabatan= b.kodejabatan(+) and a.userid = c.userid(+) "
                     + "and (a.KODEFORM=d.KODEFORM and a.prioritas=d.prioritas)";
        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table in class MAccDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MAcc();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String convertToGridExtJsFormat_MAcc(){
        String hasil = GridExtJsFormat_MAcc.rootElAwal;
        try {
            while(hasilQuery.next()){
                   hasil+=GridExtJsFormat_MAcc.rowElAwal;
                   hasil+=GridExtJsFormat_MAcc.useridElAwal+(hasilQuery.getString("userid")==null?"":hasilQuery.getString("userid"))+GridExtJsFormat_MAcc.useridElAkhir;
                   hasil+=GridExtJsFormat_MAcc.kodeformElAwal+(hasilQuery.getString("kodeform")==null?"":hasilQuery.getString("kodeform"))+GridExtJsFormat_MAcc.kodeformElAkhir;
                   hasil+=GridExtJsFormat_MAcc.kodetransaksiElAwal+(hasilQuery.getString("kodetransaksi")==null?"":hasilQuery.getString("kodetransaksi"))+GridExtJsFormat_MAcc.kodetransaksiElAkhir;
                   hasil+=GridExtJsFormat_MAcc.tglaccElAwal+(hasilQuery.getString("tglacc")==null?"":hasilQuery.getString("tglacc"))+GridExtJsFormat_MAcc.tglaccElAkhir;
                   hasil+=GridExtJsFormat_MAcc.prioritasElAwal+(hasilQuery.getString("prioritas")==null?"":hasilQuery.getString("prioritas"))+GridExtJsFormat_MAcc.prioritasElAkhir;
                   hasil+=GridExtJsFormat_MAcc.kodejabatanElAwal+(hasilQuery.getString("kodejabatan")==null?"":hasilQuery.getString("kodejabatan"))+GridExtJsFormat_MAcc.kodejabatanElAkhir;
                   hasil+=GridExtJsFormat_MAcc.namajabatanElAwal+(hasilQuery.getString("namajabatan")==null?"":hasilQuery.getString("namajabatan"))+GridExtJsFormat_MAcc.namajabatanElAkhir;
                   hasil+=GridExtJsFormat_MAcc.namabpElAwal+(hasilQuery.getString("namabp")==null?"":hasilQuery.getString("namabp"))+GridExtJsFormat_MAcc.namabpElAkhir;
                   hasil+=GridExtJsFormat_MAcc.keteranganElAwal+(hasilQuery.getString("keterangan")==null?"":hasilQuery.getString("keterangan"))+GridExtJsFormat_MAcc.keteranganElAkhir;
                   hasil+=GridExtJsFormat_MAcc.statusaccElAwal+hasilQuery.getInt("statusacc")+GridExtJsFormat_MAcc.statusaccElAkhir;
                   hasil+=GridExtJsFormat_MAcc.rowElAkhir;
            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_MAcc : " + ex);
            ex.printStackTrace();
        }
        hasil+=GridExtJsFormat_MAcc.rootElAkhir;
        return hasil;
    }
    public String insertOneRow(M_Acc m_Acc){        
        String sql = "INSERT INTO M_ACC(USERID,KODEFORM,KODETRANSAKSI,TGLACC,PRIORITAS,KODEJABATAN,STATUSACC)" +
                "VALUES ('"+m_Acc.USERID +"', '"+ m_Acc.KODEFORM +"', '"+ m_Acc.KODETRANSAKSI
                +"',TO_DATE(substr('"+ m_Acc.TGLACC +"',1,10),'"+OracleDateAndTimeStringFormat.dateFormat+"') , "
                + m_Acc.PRIORITAS +", '"+ m_Acc.KODEJABATAN+"',"+m_Acc.STATUSACC+")";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data acc telah tersimpan.", "");
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table M_ACC in class MAccDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data acc: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String updateOneRow(M_Acc m_Acc){
        String sql = "UPDATE M_ACC SET USERID = '"+ m_Acc.USERID+"', TGLACC =TO_DATE(substr('"+ m_Acc.TGLACC +"',1,10),'"+OracleDateAndTimeStringFormat.dateFormat+"'), "
                +" KODEJABATAN = '"+   m_Acc.KODEJABATAN  +"', STATUSACC='" + m_Acc.STATUSACC + "' "
                +" WHERE KODETRANSAKSI = '"+ m_Acc.KODETRANSAKSI+"' and "
                +" KODEFORM = '"+m_Acc.KODEFORM+"' and PRIORITAS ='"+m_Acc.PRIORITAS+"'";
         String hasil = SuccessErrorMessege.generateMessege(true, "Data acc pengajuan telah terupdate.", "");               
        try {            
                dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table M_ACC in class MAccDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa mengupdate data acc: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteRowByKodeFormAndKodeTrans(String kodeform,String kodetransaksi){
        String sql = "DELETE FROM M_ACC WHERE KODEFORM = '"+kodeform+"' and KODETRANSAKSI='"+kodetransaksi+"'";
            String hasil = SuccessErrorMessege.generateMessege(true, "Data acc telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error delete table M_ACC in class MAccDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data acc: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
}