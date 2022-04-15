/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model;

import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 *
 * @author Eddy
 */
public class DB_Procedure  {
    private DBConnection dBConnection;
    public DB_Procedure(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }
    public String GEN_KODEPENGAJUAN(int versi, String tanggaltrans ){   
        String hasil = "";
        try {
            String orac = "{call GEN_KODEPENGAJUAN(?,?,?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);            
            stmt.setInt(2, versi);
            stmt.setString(3, tanggaltrans);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();
            hasil = stmt.getString(1);

        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }
        return hasil;
    }
    public void generate_detilJamSewaItem(String pkodebp,String pkodeitem,String pjamawal,
            String pjamakhir,String pnamatarif,int pjumlahawal,int pjumlahakhir,String IE, String jenistransaksi){           
        try {
            String orac = "{call generate_detilJamSewaItem(?,?,?,?,?,?,?,?,?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);  
            stmt.setString(1,pkodebp);
            stmt.setString(2,pkodeitem);
            stmt.setString(3,pjamawal);
            stmt.setString(4,pjamakhir);
            stmt.setString(5,pnamatarif);
            stmt.setInt(6,pjumlahawal);
            stmt.setInt(7,pjumlahakhir);            
             stmt.setString(8,IE);
             stmt.setString(9,jenistransaksi);
            stmt.executeUpdate();                
        } 
        catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }        
    }
    public void gen_jdwlsewaruang(){           
        try {
            String orac = "{call gen_jdwlsewaruang}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);                        
            stmt.executeUpdate();    
            
        } 
        catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }        
    }
    
    public void gen_jdwlsewaruangperubahan(String kodepengajuan){           
        try {
            String orac = "{call gen_jdwlsewaruangperubahan(?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);                        
            stmt.setString(1, kodepengajuan);
            stmt.executeUpdate();    
            
        } 
        catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }        
    }
    
    public String GEN_KODETP(String kodepangajuan,String kodetpbefore){   
        if(!kodetpbefore.startsWith("PEN")){
            String hasil = "";
            try {
                String orac = "{call GEN_KODETP(?,?)}";
                CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);            
                stmt.setString(2, kodepangajuan);
                stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
                stmt.executeUpdate();
                hasil = stmt.getString(1);

            } catch (SQLException ex) {
                dBConnection.isErrorProcessingDb=true;
                System.err.println("Error execute procedure: " + ex);
            }
            return hasil;
        }
        return kodetpbefore;
    }
    
    public String GEN_KODETR(String koderealisasi,String kodetrbefore){   
        if(!kodetrbefore.startsWith("REA")){
            String hasil = "";
            try {
                String orac = "{call GEN_KODETR(?,?)}";
                CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);            
                stmt.setString(2, koderealisasi);
                stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
                stmt.executeUpdate();
                hasil = stmt.getString(1);

            } catch (SQLException ex) {
                dBConnection.isErrorProcessingDb=true;
                System.err.println("Error execute procedure: " + ex);
            }
            return hasil;
        }
        return kodetrbefore;
    }
    
    public String GEN_KODEREALISASI(int versi, String tanggaltrans )
    {
        String hasil = "";
        try {
            String orac = "{call GEN_KODEREALISASI(?,?,?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);            
            stmt.setInt(2, versi);
            stmt.setString(3, tanggaltrans);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();
            hasil = stmt.getString(1);
        
        } 
        catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }
        return hasil;
    }
    public String cekDataKembar_MJenis(String kodejenis, String namajenis, String mode){
    //panggil prosedur di oracle
        String hasil = "";
        try {
            String orac = "{call CEK_DATAKEMBAR_MJENIS(?,?,?,?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);
            stmt.setString(2, kodejenis);
            stmt.setString(3, namajenis);
            stmt.setString(4, mode);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();
            hasil = stmt.getString(1);

        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }
         return hasil;
    }

    public String cekDataKembar_MHKonfAcc(String kodeacc, String kodeform, Integer prioritas, String mode){
    //panggil prosedur di oracle
        String hasil = "";
        try {
            String orac = "{call CEK_DATAKEMBAR_MHKONFACC (?,?,?,?,?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);
            stmt.setString(2, kodeacc);
            stmt.setString(3, kodeform);
            stmt.setInt(4, prioritas);
            stmt.setString(5, mode);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();
            hasil = stmt.getString(1);

        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }
         return hasil;
    }

    public String cekDataKembar_MItem(String kodeitem, String namaitem, String mode){
    //panggil prosedur di oracle
        String hasil = "";
        try {
            String orac = "{call CEK_DATAKEMBAR_MITEM(?,?,?,?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);
            stmt.setString(2, kodeitem);
            stmt.setString(3, namaitem);
            stmt.setString(4, mode);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();
            hasil = stmt.getString(1);

        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }
         return hasil;
    }

    public String cekDataKembar_MMenu(String kodeform, String namaform, String url, String mode){
    //panggil prosedur di oracle
        String hasil = "";
        try {
            String orac = "{call CEK_DATAKEMBAR_MMENU(?,?,?,?,?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);
            stmt.setString(2, kodeform);
            stmt.setString(3, namaform);
            stmt.setString(4, url);
            stmt.setString(5, mode);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();
            hasil = stmt.getString(1);

        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }
         return hasil;
    }

    public String cekDataKembar_MPembebanan(String kodepembebanan, String namapembebanan, String mode){
    //panggil prosedur di oracle
        String hasil = "";
        try {
            String orac = "{call CEK_DATAKEMBAR_MPEMBEBANAN(?,?,?,?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);
            stmt.setString(2, kodepembebanan);
            stmt.setString(3, namapembebanan);
            stmt.setString(4, mode);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();
            hasil = stmt.getString(1);

        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }
         return hasil;
    }

    public String cekDataKembar_MSettingacc(String kodeform){
    //panggil prosedur di oracle
        String hasil = "";
        try {
            String orac = "{call CEK_DATAKEMBAR_MSETTINGACC(?,?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);
            stmt.setString(2, kodeform);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();
            hasil = stmt.getString(1);

        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }
         return hasil;
    }

    public String cekDataKembar_MUser(String userid, String kodebp, String mode){
    //panggil prosedur di oracle
        String hasil = "";
        try {
            String orac = "{call CEK_DATAKEMBAR_MUSER(?,?,?,?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);
            stmt.setString(2, userid);
            stmt.setString(3, kodebp);
            //stmt.setString(4, costcenter);
            stmt.setString(4, mode);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();
            hasil = stmt.getString(1);

        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }
         return hasil;
    }

    public String cekDataKembar_MPermission(String userid, String kodeform){
    //panggil prosedur di oracle
        String hasil = "";
        try {
            String orac = "{call CEK_DATAKEMBAR_MPERMISSION(?,?,?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);
            stmt.setString(2, userid);
            stmt.setString(3, kodeform);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();
            hasil = stmt.getString(1);

        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }
         return hasil;
    }

    public String cekDataKembar_MDPembebanan(String kodepembebanan, String kodecostcenter){
    //panggil prosedur di oracle
        String hasil = "";
        try {
            String orac = "{call CEK_DATAKEMBAR_MDPEMBEBANAN(?,?,?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);
            stmt.setString(2, kodepembebanan);
            stmt.setString(3, kodecostcenter);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();
            hasil = stmt.getString(1);

        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }
         return hasil;
    }
    
    public String cek_SDItemPengajuan(String kodetp, String kodeitem, String jamawal, String jamakhir){
    //panggil prosedur di oracle
        String hasil = "";
        try {
            String orac = "{call CEK_SDITEM_PENGAJUAN(?,?,?,?,?)}";
            CallableStatement stmt = dBConnection.getConnection().prepareCall(orac);
            stmt.setString(2, kodetp);
            stmt.setString(3, kodeitem);
            stmt.setString(4, jamawal);
            stmt.setString(5, jamakhir);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            stmt.executeUpdate();
            hasil = stmt.getString(1);

        } catch (SQLException ex) {
            dBConnection.isErrorProcessingDb=true;
            System.err.println("Error execute procedure: " + ex);
        }
         return hasil;
    }

}
