/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.gridview.GridExtJsFormat_MDTarifItem;
import com.wonokoyo.model.strukturdata.MD_TarifItem;
import com.wonokoyo.model.utilities.datetime.OracleDateAndTimeStringFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Eddy
 */
public class MDTarifItemDb {
    private ResultSet hasilQuery;
    private DBConnection dBConnection;
    public MDTarifItemDb(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    private void closeHasilQuery(){
        try {
            if (hasilQuery != null)
                this.hasilQuery.close();
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error close resultset pada class MDTarifItemDb: " + ex);
        }
    }
    public boolean isValidJamawalJamakhir(MD_TarifItem[] mD_TarifItems){
        boolean report = true;                    
            for (int i=0;i<mD_TarifItems.length;i++){
                for(int j=0;j<mD_TarifItems.length;j++){
                    if(i!=j){
                        if(mD_TarifItems[i].NAMATARIF.equals(mD_TarifItems[j].NAMATARIF)&&
                                mD_TarifItems[i].IE.equals(mD_TarifItems[j].IE)){
                            if( 
                                Integer.parseInt(mD_TarifItems[j].JAMAWAL.replaceFirst(":", "")) > 
                                    Integer.parseInt(mD_TarifItems[i].JAMAKHIR.replaceFirst(":", ""))
                            ){

                            }else if(Integer.parseInt(mD_TarifItems[j].JAMAKHIR.replaceFirst(":", "")) < 
                                    Integer.parseInt(mD_TarifItems[i].JAMAWAL.replaceFirst(":", ""))){

                            }else{
                                report = false;
                                break;
                            }
                        }
                    }
                }
                if(report==false){
                    break;
                }
            }
        return report;
    }
    public String insertOneRow(MD_TarifItem md_TarifItem){
        String tambahmdtarifitem = "INSERT INTO MD_TARIFITEM (KODEITEM,NAMATARIF,JAMAWAL,JAMAKHIR,"
                + "BIAYA,IE,JENISREALISASI) " +
                "VALUES ('"+md_TarifItem.KODEITEM +"', '"+ md_TarifItem.NAMATARIF+"',"
                +"TO_DATE('12/30/1899 "+ md_TarifItem.JAMAWAL +"','"+OracleDateAndTimeStringFormat.dateAndTimeFormat+"'), "
                +"TO_DATE('12/30/1899 "+ md_TarifItem.JAMAKHIR +"','"+OracleDateAndTimeStringFormat.dateAndTimeFormat+"'), "
                +"'"+md_TarifItem.BIAYA+"', '"+md_TarifItem.IE+"', '"+md_TarifItem.JENISREALISASI+"')";
                
        String hasil = SuccessErrorMessege.generateMessege(true, "Data detail item telah tersimpan.", "");
        try {
            /*DB_Procedure procedure = new DB_Procedure();
            String datakembar = procedure.cekDataKembar_MJenis(kodepembebanan, kodecostcenter, "insert");
            if( datakembar == null){*/
                dBConnection.getStmt().executeUpdate(tambahmdtarifitem);
            /*}else{
                hasil = SuccessErrorMessege.generateMessege(false, "", datakembar);
            }*/
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error insert table MD_TarifItem in class MD_TarifItemDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menyimpan data detail item: "+DBConnection.getOracleErrorSentence(ex));
        }
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String deleteOneRow(String kodeitem){
        String deletemdtarifitem = "DELETE FROM MD_TARIFITEM WHERE KODEITEM = '"+kodeitem+"'";
        String hasil = SuccessErrorMessege.generateMessege(true, "Data detail item telah terhapus.", "");
        try {
            dBConnection.getStmt().executeUpdate(deletemdtarifitem);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error update table MD_TarifItem in class MD_TarifItemDb: " + ex);
            hasil = SuccessErrorMessege.generateMessege(false, "", "Tidak bisa menghapus data detail item: "+DBConnection.getOracleErrorSentence(ex));
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
            System.err.println("Error class MDTarifItemDb method selectCustomRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MDTarifItem();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }

    public String selectAllRow(){
        //String sql = "select KODEACC, USERID from MD_KONFIGURASIACC";
        String sql = "select * from MD_TARIFITEM";

        try {
            hasilQuery = dBConnection.getStmt().executeQuery(sql);
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error select table in class MDTarifItemDb method selectAllRow: " + ex);
        }
        String hasil = convertToGridExtJsFormat_MDTarifItem();
        closeHasilQuery();
        dBConnection.closeStmt();
        return hasil;
    }
    public String convertToGridExtJsFormat_MDTarifItem(){
        String hasil = GridExtJsFormat_MDTarifItem.rootElAwal;
        try {
            while(hasilQuery.next()){
                   hasil+=GridExtJsFormat_MDTarifItem.rowElAwal;
                   hasil+=GridExtJsFormat_MDTarifItem.kodeitemElAwal+hasilQuery.getString("kodeitem")+GridExtJsFormat_MDTarifItem.kodeitemElAkhir;
                   hasil+=GridExtJsFormat_MDTarifItem.namatarifElAwal+(hasilQuery.getString("namatarif")==null?"":hasilQuery.getString("namatarif"))
                           +GridExtJsFormat_MDTarifItem.namatarifElAkhir;
                   hasil+=GridExtJsFormat_MDTarifItem.jamawalElAwal+(hasilQuery.getString("jamawal")==null?"":hasilQuery.getString("jamawal"))
                           +GridExtJsFormat_MDTarifItem.jamawalElAkhir;
                   hasil+=GridExtJsFormat_MDTarifItem.jamakhirElAwal+(hasilQuery.getString("jamakhir")==null?"":hasilQuery.getString("jamakhir"))
                           +GridExtJsFormat_MDTarifItem.jamakhirElAkhir;
                   hasil+=GridExtJsFormat_MDTarifItem.biayaElAwal+(Integer.toString(hasilQuery.getInt("biaya"))==null?"":hasilQuery.getInt("biaya"))
                           +GridExtJsFormat_MDTarifItem.biayaElAkhir;
                   hasil+=GridExtJsFormat_MDTarifItem.ieElAwal+(hasilQuery.getString("ie")==null?"":hasilQuery.getString("ie"))
                           +GridExtJsFormat_MDTarifItem.ieElAkhir;
                   hasil+=GridExtJsFormat_MDTarifItem.jenisrealisasiElAwal+(hasilQuery.getString("jenisrealisasi")==null?"":hasilQuery.getString("jenisrealisasi"))
                           +GridExtJsFormat_MDTarifItem.jenisrealisasiElAkhir;
                   hasil+=GridExtJsFormat_MDTarifItem.rowElAkhir;



            }
        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error convertToGridExtJsFormat_MDTarifItem : " + ex);
        }
        hasil+=GridExtJsFormat_MDTarifItem.rootElAkhir;
        return hasil;
    }
}
