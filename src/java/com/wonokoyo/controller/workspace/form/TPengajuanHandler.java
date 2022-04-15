/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace.form;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.DB_Procedure;
import com.wonokoyo.model.DCostCenterPengajuanDb;
import com.wonokoyo.model.DTglPengajuanDb;
import com.wonokoyo.model.HPengajuanDb;
import com.wonokoyo.model.MAccDb;
import com.wonokoyo.model.SD_ItemPengajuanDb;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.strukturdata.H_Pengajuan;
import com.wonokoyo.model.formatdata.url.form.FormMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;
import com.wonokoyo.model.strukturdata.D_CostCenterPengajuan;
import com.wonokoyo.model.strukturdata.D_TglPengajuan;
import com.wonokoyo.model.strukturdata.M_Acc;
import com.wonokoyo.model.strukturdata.SD_ItemPengajuan;
import com.wonokoyo.model.utilities.xpath.XpathProcessing;
import java.sql.SQLException;
import org.w3c.dom.*;
/**
 *
 * @author Bayu
 */
public class TPengajuanHandler {
    public static String doProcess(String xmldata, String mode,String modereadtable,DBConnection dBConnection/*String mode,H_Pengajuan h_Pengajuan,String modereadtable*/){
        HPengajuanDb hPengajuanDb = new HPengajuanDb(dBConnection);
        DCostCenterPengajuanDb dCostCenterPengajuanDb = new DCostCenterPengajuanDb(dBConnection);
        DTglPengajuanDb dTglPengajuanDb = new DTglPengajuanDb(dBConnection);
        MAccDb mAccDb = new MAccDb(dBConnection);
        SD_ItemPengajuanDb sD_ItemPengajuanDb = new SD_ItemPengajuanDb(dBConnection);
        DB_Procedure dB_Procedure = new DB_Procedure(dBConnection);
        String hasil ="";
        H_Pengajuan h_Pengajuan = new H_Pengajuan();  
        D_CostCenterPengajuan[] d_CostCenterPengajuan=null;
        D_TglPengajuan[] d_TglPengajuan=null;
        M_Acc[] m_Acc=null;
        SD_ItemPengajuan[] sD_ItemPengajuan=null;
               
        if(xmldata!=null){
            NodeList nodes = XpathProcessing.doProcess(xmldata, "/header");
            for (int i = 0; i < nodes.getLength(); i++) {                        

                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("kodepengajuan");
                Element line = (Element) nodelist.item(0);            
                h_Pengajuan.KODEPENGAJUAN = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodebp");
                line = (Element) nodelist.item(0);            
                h_Pengajuan.KODEBP =  XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("jenisrealisasi");
                line = (Element) nodelist.item(0);            
                h_Pengajuan.JENISREALISASI =  XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("tgltransaksi");
                line = (Element) nodelist.item(0);            
                h_Pengajuan.TANGGAL =  XpathProcessing.getCharacterDataFromElement(line);
                                
                h_Pengajuan.VERSI =  0;
                
                nodelist = element.getElementsByTagName("totalbiaya");
                line = (Element) nodelist.item(0);            
                h_Pengajuan.TOTALBIAYA =  Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));
                
                nodelist = element.getElementsByTagName("kodeorg");
                line = (Element) nodelist.item(0);            
                h_Pengajuan.KODEORG =  XpathProcessing.getCharacterDataFromElement(line);
                                
                h_Pengajuan.BATAL =  0;
            }
            nodes = XpathProcessing.doProcess(xmldata, "/header/detail[@tablename=\"d_costcenterpengajuan\"]/row");
            d_CostCenterPengajuan= new D_CostCenterPengajuan[nodes.getLength()];
            for (int i = 0; i < nodes.getLength(); i++) {                        
                d_CostCenterPengajuan[i] = new D_CostCenterPengajuan();
                
                Element element = (Element) nodes.item(i);
                
                NodeList nodelist = element.getElementsByTagName("approval");
                Element line = (Element) nodelist.item(0);            
                d_CostCenterPengajuan[i].APPROVAL = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("kodecostcenter");
                line = (Element) nodelist.item(0);            
                d_CostCenterPengajuan[i].KODECOSTCENTER = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodepengajuan");
                line = (Element) nodelist.item(0);            
                d_CostCenterPengajuan[i].KODEPENGAJUAN = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("nominal");
                line = (Element) nodelist.item(0);            
                d_CostCenterPengajuan[i].NOMINAL = Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));
                
                nodelist = element.getElementsByTagName("presentase");
                line = (Element) nodelist.item(0);            
                d_CostCenterPengajuan[i].PRESENTASE = Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));
                                
                nodelist = element.getElementsByTagName("userid");
                line = (Element) nodelist.item(0);            
                d_CostCenterPengajuan[i].USERID = XpathProcessing.getCharacterDataFromElement(line);
            }
            nodes = XpathProcessing.doProcess(xmldata, "/header/detail[@tablename=\"d_tglpengajuan\"]/row");            
            d_TglPengajuan = new D_TglPengajuan[nodes.getLength()];
            for (int i = 0; i < nodes.getLength(); i++) {                        
                d_TglPengajuan[i] = new D_TglPengajuan();
                
                Element element = (Element) nodes.item(i);
                
                NodeList nodelist = element.getElementsByTagName("kodepengajuan");
                Element line = (Element) nodelist.item(0);            
                d_TglPengajuan[i].KODEPENGAJUAN = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("kodetp");
                line = (Element) nodelist.item(0);            
                d_TglPengajuan[i].KODETP = XpathProcessing.getCharacterDataFromElement(line);                
                
                nodelist = element.getElementsByTagName("tglakhir");
                line = (Element) nodelist.item(0);            
                d_TglPengajuan[i].TGLAKHIR = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("tglawal");
                line = (Element) nodelist.item(0);            
                d_TglPengajuan[i].TGLAWAL = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("namatarif");
                line = (Element) nodelist.item(0);            
                d_TglPengajuan[i].NAMATARIF = XpathProcessing.getCharacterDataFromElement(line);
            }
            
            nodes = XpathProcessing.doProcess(xmldata, "/header/detail[@tablename=\"m_acc\"]/row");            
            m_Acc = new M_Acc[nodes.getLength()];       
            for (int i = 0; i < nodes.getLength(); i++) {                        
                m_Acc[i] = new M_Acc();
                
                Element element = (Element) nodes.item(i);
                
                NodeList nodelist = element.getElementsByTagName("kodeform");
                Element line = (Element) nodelist.item(0);            
                m_Acc[i].KODEFORM = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("kodejabatan");
                line = (Element) nodelist.item(0);            
                m_Acc[i].KODEJABATAN = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("kodetransaksi");
                line = (Element) nodelist.item(0);            
                m_Acc[i].KODETRANSAKSI = XpathProcessing.getCharacterDataFromElement(line);                
                
                nodelist = element.getElementsByTagName("prioritas");
                line = (Element) nodelist.item(0);            
                m_Acc[i].PRIORITAS = Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));
                
                nodelist = element.getElementsByTagName("tglacc");
                line = (Element) nodelist.item(0);            
                m_Acc[i].TGLACC = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("userid");
                line = (Element) nodelist.item(0);            
                m_Acc[i].USERID = XpathProcessing.getCharacterDataFromElement(line);                     
                
                nodelist = element.getElementsByTagName("statusacc");
                line = (Element) nodelist.item(0);            
                m_Acc[i].STATUSACC = Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));
            }
            nodes = XpathProcessing.doProcess(xmldata, "/header/detail[@tablename=\"d_tglpengajuan\"]/subdetail[@tablename=\"sd_itempengajuan\"]/row");                        
            sD_ItemPengajuan = new SD_ItemPengajuan[nodes.getLength()];       
            for (int i = 0; i < nodes.getLength(); i++) {                        
                sD_ItemPengajuan[i] = new SD_ItemPengajuan();
                
                Element element = (Element) nodes.item(i);
                
                NodeList nodelist = element.getElementsByTagName("antrian");
                Element line = (Element) nodelist.item(0);            
                sD_ItemPengajuan[i].ANTRIAN = Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));
                
                nodelist = element.getElementsByTagName("biaya");
                line = (Element) nodelist.item(0);            
                sD_ItemPengajuan[i].BIAYA = Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));                
                
                nodelist = element.getElementsByTagName("durasi");
                line = (Element) nodelist.item(0);            
                sD_ItemPengajuan[i].DURASI = Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));                
                
                nodelist = element.getElementsByTagName("jamakhir");
                line = (Element) nodelist.item(0);            
                sD_ItemPengajuan[i].JAMAKHIR = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("jamawal");
                line = (Element) nodelist.item(0);            
                sD_ItemPengajuan[i].JAMAWAL = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("jumlahakhir");
                line = (Element) nodelist.item(0);            
                sD_ItemPengajuan[i].JUMLAHAKHIR = Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));
                
                nodelist = element.getElementsByTagName("jumlahawal");
                line = (Element) nodelist.item(0);            
                sD_ItemPengajuan[i].JUMLAHAWAL = Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));
                
                nodelist = element.getElementsByTagName("keterangan");
                line = (Element) nodelist.item(0);            
                sD_ItemPengajuan[i].KETERANGAN = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("kodeitem");
                line = (Element) nodelist.item(0);            
                sD_ItemPengajuan[i].KODEITEM = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("kodetp");
                line = (Element) nodelist.item(0);            
                sD_ItemPengajuan[i].KODETP = XpathProcessing.getCharacterDataFromElement(line);       
                
                nodelist = element.getElementsByTagName("biayaperjam");
                line = (Element) nodelist.item(0);            
                sD_ItemPengajuan[i].BIAYAPERJAM = Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));
            }
        }
        
        boolean itemvalid = sD_ItemPengajuanDb.isValidJamawalJamakhir(sD_ItemPengajuan);
        if(itemvalid==false){
            dBConnection.isErrorProcessingDb = true;                    
            return SuccessErrorMessege.generateMessege(false,"", "Error jam awal dan jam akhir item.");
        }
        if (FormMode.READ.toString().equalsIgnoreCase(mode)){
            if(modereadtable.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = hPengajuanDb.selectAllRowToGridExtJsFormat_HPengajuan();
            }else if(modereadtable.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
        }else if (FormMode.INSERT.toString().equalsIgnoreCase(mode)){            
            dBConnection.startTransaction();
            try{
                h_Pengajuan.KODEPENGAJUAN = dB_Procedure.GEN_KODEPENGAJUAN(h_Pengajuan.VERSI,h_Pengajuan.TANGGAL);
                hasil = hPengajuanDb.insertOneRow(h_Pengajuan);
                if (dBConnection.isErrorProcessingDb){                                              
                    dBConnection.endTransaction();
                    return hasil;
                }
                for(int i=0;i<d_CostCenterPengajuan.length;i++){
                    d_CostCenterPengajuan[i].KODEPENGAJUAN = h_Pengajuan.KODEPENGAJUAN;
                    hasil = dCostCenterPengajuanDb.insertOneRow(d_CostCenterPengajuan[i]);
                    if(dBConnection.isErrorProcessingDb){
                        dBConnection.endTransaction();
                        return hasil;
                    }
                }
                for(int i=0;i<m_Acc.length;i++){
                    m_Acc[i].KODETRANSAKSI = h_Pengajuan.KODEPENGAJUAN;
                    hasil = mAccDb.insertOneRow(m_Acc[i]);
                    if(dBConnection.isErrorProcessingDb){
                        dBConnection.endTransaction();
                        return hasil;
                    }
                }
                String kodetpTemp;
                for(int i=0;i<d_TglPengajuan.length;i++){
                    d_TglPengajuan[i].KODEPENGAJUAN = h_Pengajuan.KODEPENGAJUAN;
                    kodetpTemp = dB_Procedure.GEN_KODETP(h_Pengajuan.KODEPENGAJUAN, d_TglPengajuan[i].KODETP);
                    for(int j=0;j<sD_ItemPengajuan.length;j++){
                        if(sD_ItemPengajuan[j].KODETP.equalsIgnoreCase(d_TglPengajuan[i].KODETP)){
                            sD_ItemPengajuan[j].KODETP = kodetpTemp;
                        }
                    }
                    d_TglPengajuan[i].KODETP = kodetpTemp;                
                    hasil = dTglPengajuanDb.insertOneRow(d_TglPengajuan[i]);
                    if(dBConnection.isErrorProcessingDb){
                        dBConnection.endTransaction();
                        return hasil;
                    }
                }
                
                DB_Procedure procedure = new DB_Procedure(dBConnection);
                procedure.gen_jdwlsewaruangperubahan(h_Pengajuan.KODEPENGAJUAN);
                for(int i=0;i<sD_ItemPengajuan.length;i++){
                    String cek = procedure.cek_SDItemPengajuan(sD_ItemPengajuan[i].KODETP, sD_ItemPengajuan[i].KODEITEM, sD_ItemPengajuan[i].JAMAWAL, sD_ItemPengajuan[i].JAMAKHIR);
                    if(cek == null){
                        hasil = sD_ItemPengajuanDb.insertOneRow(sD_ItemPengajuan[i]);
                        if(dBConnection.isErrorProcessingDb){
                            dBConnection.endTransaction();
                            return hasil;
                        }
                    }
                    else{
                        dBConnection.isErrorProcessingDb=true;
                        hasil = SuccessErrorMessege.generateMessege(false, "", cek);
                        break;
                    }
                }
                if(!dBConnection.isErrorProcessingDb)                                   
                    hasil = SuccessErrorMessege.generateMessege(true, "Transaksi pengajuan telah tersimpan.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                ex.printStackTrace();
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
           // hasil = mJenisDb.insertOneRow(m_Jenis.KODEJENIS, m_Jenis.NAMAJENIS);
            dBConnection.endTransaction();
        }else if (FormMode.UPDATE.toString().equalsIgnoreCase(mode)){
            int rowcount = mAccDb.selectCountByKodeFormAndKodeTrans(m_Acc[0].KODEFORM, h_Pengajuan.KODEPENGAJUAN);
            if(rowcount<0){
                return SuccessErrorMessege.generateMessege(false,"", "Error select count in table m_acc.");
            }else if(rowcount>0){
                return SuccessErrorMessege.generateMessege(false,"", "Transaksi ini tidak dapat dihapus atau dirubah.");
            }else{
                dBConnection.startTransaction();   
                try{
                    hasil = hPengajuanDb.updateOneRow(h_Pengajuan);            
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }            
                    hasil = dCostCenterPengajuanDb.deleteRowByKodePengajuan(h_Pengajuan.KODEPENGAJUAN);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }            
                    for(int i=0;i<d_CostCenterPengajuan.length;i++){
                        d_CostCenterPengajuan[i].KODEPENGAJUAN = h_Pengajuan.KODEPENGAJUAN;
                        hasil = dCostCenterPengajuanDb.insertOneRow(d_CostCenterPengajuan[i]);
                        if(dBConnection.isErrorProcessingDb){
                            dBConnection.endTransaction();
                            return hasil;
                        }
                    }
                    hasil = mAccDb.deleteRowByKodeFormAndKodeTrans(m_Acc[0].KODEFORM,h_Pengajuan.KODEPENGAJUAN);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }
                    for(int i=0;i<m_Acc.length;i++){
                        m_Acc[i].KODETRANSAKSI = h_Pengajuan.KODEPENGAJUAN;
                        hasil = mAccDb.insertOneRow(m_Acc[i]);
                        if(dBConnection.isErrorProcessingDb){
                            dBConnection.endTransaction();
                            return hasil;
                        }
                    }                
                    for(int i=0;i<d_TglPengajuan.length;i++){                    
                        hasil = sD_ItemPengajuanDb.deleteRowByKodeTp(d_TglPengajuan[i].KODETP);
                        if (dBConnection.isErrorProcessingDb){                                              
                            dBConnection.endTransaction();
                            return hasil;
                        }
                    }
                    hasil = dTglPengajuanDb.deleteRowByKodePengajuan(h_Pengajuan.KODEPENGAJUAN);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }
                    
                    String kodetpTemp;
                    for(int i=0;i<d_TglPengajuan.length;i++){
                        d_TglPengajuan[i].KODEPENGAJUAN = h_Pengajuan.KODEPENGAJUAN;
                        kodetpTemp = dB_Procedure.GEN_KODETP(h_Pengajuan.KODEPENGAJUAN, d_TglPengajuan[i].KODETP);
                        for(int j=0;j<sD_ItemPengajuan.length;j++){
                            if(sD_ItemPengajuan[j].KODETP.equalsIgnoreCase(d_TglPengajuan[i].KODETP)){
                                sD_ItemPengajuan[j].KODETP = kodetpTemp;
                            }
                        }
                        d_TglPengajuan[i].KODETP = kodetpTemp; 
                        hasil = dTglPengajuanDb.insertOneRow(d_TglPengajuan[i]);
                        if(dBConnection.isErrorProcessingDb){
                            dBConnection.endTransaction();
                            return hasil;
                        }
                    }
                    
                    DB_Procedure procedure = new DB_Procedure(dBConnection);
                    procedure.gen_jdwlsewaruang();
                    for(int i=0;i<sD_ItemPengajuan.length;i++){                      
                        String cek = procedure.cek_SDItemPengajuan(sD_ItemPengajuan[i].KODETP, sD_ItemPengajuan[i].KODEITEM, sD_ItemPengajuan[i].JAMAWAL, sD_ItemPengajuan[i].JAMAKHIR);
                        if(cek == null){
                            hasil = sD_ItemPengajuanDb.insertOneRow(sD_ItemPengajuan[i]);
                            if(dBConnection.isErrorProcessingDb){
                                dBConnection.endTransaction();
                                return hasil;
                            }
                        }
                        else{
                            dBConnection.isErrorProcessingDb=true;
                            hasil = SuccessErrorMessege.generateMessege(false, "", cek);
                            break;
                        }
                    }
                    if(!dBConnection.isErrorProcessingDb)
                        hasil = SuccessErrorMessege.generateMessege(true, "Transaksi pengajuan telah terupdate.", "");
                }catch (Exception ex){
                    dBConnection.isErrorProcessingDb = true;
                    ex.printStackTrace();
                    hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
                }
                //hasil = mJenisDb.updateOneRow(m_Jenis.KODEJENIS, m_Jenis.NAMAJENIS);
                dBConnection.endTransaction();    
            }
        }else if (FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            int rowcount = mAccDb.selectCountByKodeFormAndKodeTrans(m_Acc[0].KODEFORM, h_Pengajuan.KODEPENGAJUAN);
            if(rowcount<0){
                return SuccessErrorMessege.generateMessege(false,"", "Error select count in table m_acc.");
            }else if(rowcount>0){
                return SuccessErrorMessege.generateMessege(false,"", "Transaksi ini tidak dapat dihapus atau dirubah.");
            }else{
                dBConnection.startTransaction(); 
                try{
                    for(int i=0;i<d_TglPengajuan.length;i++){
                        hasil = sD_ItemPengajuanDb.deleteRowByKodeTp(d_TglPengajuan[i].KODETP);
                        if (dBConnection.isErrorProcessingDb){                                              
                            dBConnection.endTransaction();
                            return hasil;
                        }
                    }
                    hasil = dTglPengajuanDb.deleteRowByKodePengajuan(h_Pengajuan.KODEPENGAJUAN);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }                      
                    hasil = dCostCenterPengajuanDb.deleteRowByKodePengajuan(h_Pengajuan.KODEPENGAJUAN);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }                       
                    hasil = mAccDb.deleteRowByKodeFormAndKodeTrans(m_Acc[0].KODEFORM,h_Pengajuan.KODEPENGAJUAN);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }         
                    hasil = hPengajuanDb.deleteOneRow(h_Pengajuan); 
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }               
                    hasil = SuccessErrorMessege.generateMessege(true, "Transaksi pengajuan telah terdelete.", "");
                }catch (Exception ex){
                    dBConnection.isErrorProcessingDb = true;                    
                    ex.printStackTrace();
                    hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
                }
                //hasil = mJenisDb.deleteOneRow(m_Jenis.KODEJENIS);
                dBConnection.endTransaction();    
            }            
        }else{

        }
        //hPengajuanDb.closeConn();
        return hasil;
    }
}
