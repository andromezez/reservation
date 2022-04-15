/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace.form;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.DB_Procedure;
import com.wonokoyo.model.DCostCenterPengajuanDb;
import com.wonokoyo.model.DTglPengajuanDb;
import com.wonokoyo.model.HPerubahanDb;
import com.wonokoyo.model.MAccDb;
import com.wonokoyo.model.MItemDb;
import com.wonokoyo.model.MParameterDb;
import com.wonokoyo.model.SD_ItemPengajuanDb;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.strukturdata.H_Perubahan;
import com.wonokoyo.model.formatdata.url.form.FormMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;
import com.wonokoyo.model.strukturdata.D_CostCenterPengajuan;
import com.wonokoyo.model.strukturdata.D_TglPengajuan;
import com.wonokoyo.model.strukturdata.M_Acc;
import com.wonokoyo.model.strukturdata.M_Item;
import com.wonokoyo.model.strukturdata.M_Parameter;
import com.wonokoyo.model.strukturdata.SD_ItemPengajuan;
import com.wonokoyo.model.utilities.validation.BasicValidation;
import com.wonokoyo.model.utilities.validation.FieldStatus;
import com.wonokoyo.model.utilities.validation.StringField;
import com.wonokoyo.model.utilities.xpath.XpathProcessing;
import org.w3c.dom.*;

/**
 *
 * @author Indra
 */
public class TPerubahanHandler {
    public static String doProcess(String xmldata, String mode,String modereadtable,DBConnection dBConnection/*String mode,H_Perubahan h_Perubahan,String modereadtable*/){

        HPerubahanDb hPerubahanDb = new HPerubahanDb(dBConnection);
        DCostCenterPengajuanDb dCostCenterPengajuanDb = new DCostCenterPengajuanDb(dBConnection);
        DTglPengajuanDb dTglPengajuanDb = new DTglPengajuanDb(dBConnection);
        String hasil ="";
        SD_ItemPengajuanDb sD_ItemPengajuanDb = new SD_ItemPengajuanDb(dBConnection);
        H_Perubahan h_Perubahan = new H_Perubahan();
        MAccDb mAccDb = new MAccDb(dBConnection);
        D_TglPengajuan[] d_TglPengajuan=null;
        M_Acc[] m_Acc=null;
        DB_Procedure dB_Procedure = new DB_Procedure(dBConnection);
        D_CostCenterPengajuan[] d_CostCenterPengajuan=null;
        SD_ItemPengajuan[] sD_ItemPengajuan=null;
        if(xmldata!=null){
            NodeList nodes = XpathProcessing.doProcess(xmldata, "/header");
            for (int i = 0; i < nodes.getLength(); i++) {

                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("kodepengajuan");
                Element line = (Element) nodelist.item(0);
                h_Perubahan.KODEPENGAJUAN = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodebp");
                line = (Element) nodelist.item(0);
                h_Perubahan.KODEBP =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("jenisrealisasi");
                line = (Element) nodelist.item(0);
                h_Perubahan.JENISREALISASI =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("tgltransaksi");
                line = (Element) nodelist.item(0);
                h_Perubahan.TANGGAL =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("versi");
                line = (Element) nodelist.item(0);
                h_Perubahan.VERSI =  Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));

                nodelist = element.getElementsByTagName("totalbiaya");
                line = (Element) nodelist.item(0);
                h_Perubahan.TOTALBIAYA =  Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));

                nodelist = element.getElementsByTagName("kodeorg");
                line = (Element) nodelist.item(0);
                h_Perubahan.KODEORG =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("IE");
                line = (Element) nodelist.item(0);
                h_Perubahan.IE =  XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("batal");
                line = (Element) nodelist.item(0);
                h_Perubahan.BATAL =  Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));
                
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
                String nom=XpathProcessing.getCharacterDataFromElement(line);
                d_CostCenterPengajuan[i].NOMINAL = Integer.parseInt(nom);
                
                nodelist = element.getElementsByTagName("presentase");
                line = (Element) nodelist.item(0);
                d_CostCenterPengajuan[i].PRESENTASE = Double.parseDouble(XpathProcessing.getCharacterDataFromElement(line));

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
                String biaya=XpathProcessing.getCharacterDataFromElement(line);
                if (biaya.equalsIgnoreCase("undefined") )
                {biaya="0";}
                sD_ItemPengajuan[i].BIAYA = Integer.parseInt(biaya);

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
                String biayaPerJam=XpathProcessing.getCharacterDataFromElement(line);
                if (biayaPerJam.equalsIgnoreCase("undefined") )
                {biayaPerJam="0";}
                sD_ItemPengajuan[i].BIAYAPERJAM = Integer.parseInt(biayaPerJam);
                
                nodelist = element.getElementsByTagName("jumlahpeserta");
                line = (Element) nodelist.item(0);
                String Jumlah=XpathProcessing.getCharacterDataFromElement(line);
                
                if (Jumlah.equalsIgnoreCase("undefined") )
                {Jumlah="0";}
                sD_ItemPengajuan[i].JUMLAHPESERTA = Integer.parseInt(Jumlah);
            }
        }
        boolean itemvalid = sD_ItemPengajuanDb.isValidJamawalJamakhir(sD_ItemPengajuan);
        if(itemvalid==false){
            dBConnection.isErrorProcessingDb = true;                    
            return SuccessErrorMessege.generateMessege(false,"", "Error jam awal dan jam akhir beririsan untuk item yang sama");
        }

        if( !FormMode.READ.toString().equalsIgnoreCase(mode) && !FormMode.DELETE.toString().equalsIgnoreCase(mode) && h_Perubahan.BATAL == 0 ){
            System.out.println(h_Perubahan.KODEPENGAJUAN);
            double tot1=0;double tot2=0;
            if (d_CostCenterPengajuan.length==0) return SuccessErrorMessege.generateMessege(false,"", "Error costcenter harus diisi minimal 1 record");
            else if(d_TglPengajuan.length==0) return SuccessErrorMessege.generateMessege(false,"", "Error tanggal pengajuan harus diisi minimal 1 record");
            else if(sD_ItemPengajuan.length==0) return SuccessErrorMessege.generateMessege(false,"", "Error item harus diisi minimal 1 record");
            else if (m_Acc.length==0) return SuccessErrorMessege.generateMessege(false,"", "Error grid acc transaksi");
            
            StringField[] data = {new StringField("nama pemohon", h_Perubahan.KODEBP),new StringField("Tanggal transaksi", h_Perubahan.TANGGAL)};                        
            FieldStatus status = BasicValidation.fieldsCannotNullString(data);
            if(status.isError) return SuccessErrorMessege.generateMessege(false,"", "Error "+status.fieldname+" harus diisi.");           
            
            String[] tgl = new String[d_TglPengajuan.length];
            for(int i=0;i<d_TglPengajuan.length;i++){
                tgl[i] = d_TglPengajuan[i].TGLAWAL;
            }
            if(!BasicValidation.isNoDuplicateString(tgl)) return SuccessErrorMessege.generateMessege(false,"", "Error duplikasi tanggal pengajuan");
           
            StringField[] kodeItem=new StringField[sD_ItemPengajuan.length];
            StringField[] jamAwal=new StringField[sD_ItemPengajuan.length];
            
            MParameterDb mParameterDb = new MParameterDb(dBConnection);
            MItemDb mItemDb = new MItemDb(dBConnection);
            M_Parameter parameter = mParameterDb.selectOneRow("kodejenisygjumlahpesertanotnull");
            for(int i=0;i<sD_ItemPengajuan.length;i++)
            {
                M_Item item = mItemDb.selectCustomOneRow(sD_ItemPengajuan[i].KODEITEM);
                kodeItem[i]=new StringField("nama item row "+i, sD_ItemPengajuan[i].KODEITEM);
                jamAwal[i]=new StringField("jam awal row "+i, sD_ItemPengajuan[i].JAMAWAL);
                if(parameter.PARVALUE.equalsIgnoreCase(item.KODEJENIS))
                {
                    if (sD_ItemPengajuan[i].JUMLAHPESERTA==0)
                    {
                        return SuccessErrorMessege.generateMessege(false,"", "Error, ruang harus diisi jumlah peserta.");
                    }
                }   
            }
            FieldStatus statKodeItem = BasicValidation.fieldsCannotNullString(kodeItem);
            if(statKodeItem.isError) return SuccessErrorMessege.generateMessege(false,"", "Error, "+statKodeItem.fieldname+" harus diisi.");
            FieldStatus statJamAwal = BasicValidation.fieldsCannot_undefined_string(jamAwal);
            if(statJamAwal.isError) return SuccessErrorMessege.generateMessege(false,"", "Error, "+statJamAwal.fieldname+" harus diisi.");
            
            for(int i=0;i<d_CostCenterPengajuan.length;i++){
                tot1 = d_CostCenterPengajuan[i].PRESENTASE;
                tot2=tot2+tot1;
                System.out.println(tot2);
            }
            StringField[] namabp=new StringField[m_Acc.length];
            for(int i=0;i<m_Acc.length;i++)
            {
                namabp[i]=new StringField("nama bp row "+i, m_Acc[i].USERID);
            }
            FieldStatus statNamaBp = BasicValidation.fieldsCannotNullString(namabp);
            if(statNamaBp.isError) return SuccessErrorMessege.generateMessege(false,"", "Error, nama karyawan pada grid acc harus diisi.");
        }                
        if( !FormMode.READ.toString().equalsIgnoreCase(mode) && !FormMode.DELETE.toString().equalsIgnoreCase(mode) && h_Perubahan.BATAL == 1 ){
            
        } 
        if (FormMode.READ.toString().equalsIgnoreCase(mode)){
            if(modereadtable.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = hPerubahanDb.selectAllRow();
            }else if(modereadtable.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                
            }
        }else if (FormMode.INSERT.toString().equalsIgnoreCase(mode)){
           
            
            dBConnection.startTransaction();
           try{
               if(h_Perubahan.KODEPENGAJUAN.equalsIgnoreCase(""))
               {
                   h_Perubahan.KODEPENGAJUAN = dB_Procedure.GEN_KODEPENGAJUAN(h_Perubahan.VERSI,h_Perubahan.TANGGAL);
               }
               else{
                   String kodeBaru=h_Perubahan.KODEPENGAJUAN.substring(0, 15)+h_Perubahan.VERSI;
                   
                   hasil = hPerubahanDb.updateBatal(h_Perubahan.KODEPENGAJUAN,1);
                   h_Perubahan.KODEPENGAJUAN = kodeBaru;
                  
               }
               
                hasil = hPerubahanDb.insertOneRow(h_Perubahan);
                if (dBConnection.isErrorProcessingDb){
                    dBConnection.endTransaction();
                    return hasil;
                }
                for(int i=0;i<d_CostCenterPengajuan.length;i++){
                    d_CostCenterPengajuan[i].KODEPENGAJUAN = h_Perubahan.KODEPENGAJUAN;
                    hasil = dCostCenterPengajuanDb.insertOneRow(d_CostCenterPengajuan[i]);
                    if(dBConnection.isErrorProcessingDb){
                        dBConnection.endTransaction();
                        return hasil;
                    }
                }
                for(int i=0;i<m_Acc.length;i++){
                    m_Acc[i].KODETRANSAKSI = h_Perubahan.KODEPENGAJUAN;
                    hasil = mAccDb.insertOneRow(m_Acc[i]);
                    if(dBConnection.isErrorProcessingDb){
                        dBConnection.endTransaction();
                        return hasil;
                    }
                }
                String kodetpTemp,kodetpBefore;
                for(int i=0;i<d_TglPengajuan.length;i++){
                    d_TglPengajuan[i].KODEPENGAJUAN = h_Perubahan.KODEPENGAJUAN;

                    kodetpBefore = d_TglPengajuan[i].KODETP;
                    d_TglPengajuan[i].KODETP = "LAMA";

                    kodetpTemp = dB_Procedure.GEN_KODETP(h_Perubahan.KODEPENGAJUAN, d_TglPengajuan[i].KODETP);
                    for(int j=0;j<sD_ItemPengajuan.length;j++){
                        
                        if(kodetpBefore.equalsIgnoreCase(sD_ItemPengajuan[j].KODETP))
                        {
                            sD_ItemPengajuan[j].KODETP = kodetpTemp;  
                        }
                             
                    }
                    d_TglPengajuan[i].KODETP = kodetpTemp;
                    System.out.println("d_TglPengajuan[ "+i+" ].KODETP :"+d_TglPengajuan[i].KODETP); 
                    hasil = dTglPengajuanDb.insertOneRow(d_TglPengajuan[i]);
                    if(dBConnection.isErrorProcessingDb){
                        dBConnection.endTransaction();
                        return hasil;
                    }
                }
                
                DB_Procedure procedure = new DB_Procedure(dBConnection);
                procedure.gen_jdwlsewaruangperubahan(h_Perubahan.KODEPENGAJUAN);
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
                    if (h_Perubahan.VERSI==0)
                    {hasil = SuccessErrorMessege.generateMessege(true, "Transaksi pengajuan telah tersimpan.", "");}
                    else {hasil = SuccessErrorMessege.generateMessege(true, "Transaksi perubahan telah tersimpan.", "");}
                    
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                ex.printStackTrace();
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
            dBConnection.endTransaction();
        }else if (FormMode.UPDATE.toString().equalsIgnoreCase(mode)){            
            int rowcount = mAccDb.selectCountByKodeFormAndKodeTrans(m_Acc[0].KODEFORM, h_Perubahan.KODEPENGAJUAN);
            if(rowcount<0){
                return SuccessErrorMessege.generateMessege(false,"", "Error select count in table m_acc.");
            }else if((rowcount>0)&&(h_Perubahan.BATAL==0)){               
                return SuccessErrorMessege.generateMessege(false,"", "Transaksi ini tidak dapat dihapus atau dirubah.");
            }else{
                dBConnection.startTransaction();   
                try{
                    hasil = hPerubahanDb.updateOneRow(h_Perubahan);            
                    //h_Perubahan.BATAL
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }            
                    hasil = dCostCenterPengajuanDb.deleteRowByKodePengajuan(h_Perubahan.KODEPENGAJUAN);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }            
                    for(int i=0;i<d_CostCenterPengajuan.length;i++){
                        d_CostCenterPengajuan[i].KODEPENGAJUAN = h_Perubahan.KODEPENGAJUAN;
                        hasil = dCostCenterPengajuanDb.insertOneRow(d_CostCenterPengajuan[i]);
                        if(dBConnection.isErrorProcessingDb){
                            dBConnection.endTransaction();
                            return hasil;
                        }
                    }
                    hasil = mAccDb.deleteRowByKodeFormAndKodeTrans(m_Acc[0].KODEFORM,h_Perubahan.KODEPENGAJUAN);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }
                    for(int i=0;i<m_Acc.length;i++){
                        m_Acc[i].KODETRANSAKSI = h_Perubahan.KODEPENGAJUAN;
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
                    hasil = dTglPengajuanDb.deleteRowByKodePengajuan(h_Perubahan.KODEPENGAJUAN);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }
                    String kodetpTemp;
                    for(int i=0;i<d_TglPengajuan.length;i++){
                        d_TglPengajuan[i].KODEPENGAJUAN = h_Perubahan.KODEPENGAJUAN;
                        kodetpTemp = dB_Procedure.GEN_KODETP(h_Perubahan.KODEPENGAJUAN, d_TglPengajuan[i].KODETP);
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
                    procedure.gen_jdwlsewaruangperubahan(h_Perubahan.KODEPENGAJUAN);
                    for(int i=0;i<sD_ItemPengajuan.length;i++){   
                        String cek = procedure.cek_SDItemPengajuan(sD_ItemPengajuan[i].KODETP, sD_ItemPengajuan[i].KODEITEM, sD_ItemPengajuan[i].JAMAWAL, sD_ItemPengajuan[i].JAMAKHIR);
                        if(cek == null){
                            hasil = sD_ItemPengajuanDb.insertOneRow(sD_ItemPengajuan[i]);
                            System.out.println("biaya akhir>>> "+sD_ItemPengajuan[i].JUMLAHAKHIR);
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
                        if (h_Perubahan.VERSI==0)
                             {hasil = SuccessErrorMessege.generateMessege(true, "Transaksi pengajuan telah terupdate.", "");}
                        else {hasil = SuccessErrorMessege.generateMessege(true, "Transaksi perubahan telah terupdate.", "");}
                }catch (Exception ex){
                    dBConnection.isErrorProcessingDb = true;
                    ex.printStackTrace();
                    hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
                }
                //hasil = mJenisDb.updateOneRow(m_Jenis.KODEJENIS, m_Jenis.NAMAJENIS);
                dBConnection.endTransaction();    
            }
        }else if (FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            int rowcount = mAccDb.selectCountByKodeFormAndKodeTrans(m_Acc[0].KODEFORM, h_Perubahan.KODEPENGAJUAN);
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
                    hasil = dTglPengajuanDb.deleteRowByKodePengajuan(h_Perubahan.KODEPENGAJUAN);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }                      
                    hasil = dCostCenterPengajuanDb.deleteRowByKodePengajuan(h_Perubahan.KODEPENGAJUAN);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }                       
                    hasil = mAccDb.deleteRowByKodeFormAndKodeTrans(m_Acc[0].KODEFORM,h_Perubahan.KODEPENGAJUAN);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }         
                    hasil = hPerubahanDb.deleteOneRow(h_Perubahan); 
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }               
                    if (h_Perubahan.VERSI==0)
                    {hasil = SuccessErrorMessege.generateMessege(true, "Transaksi pengajuan telah terhapus.", "");}
                    else {hasil = SuccessErrorMessege.generateMessege(true, "Transaksi perubahan telah terhapus.", "");}
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
