/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace.form;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.DB_Procedure;
import com.wonokoyo.model.DCostCenterRealisasiDb;
import com.wonokoyo.model.DTglRealisasiDb;
import com.wonokoyo.model.HRealisasiDb;
import com.wonokoyo.model.MAccDb;
import com.wonokoyo.model.MItemDb;
import com.wonokoyo.model.MParameterDb;
import com.wonokoyo.model.SD_ItemRealisasiDb;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.url.form.FormMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;

import com.wonokoyo.model.strukturdata.D_CostCenterRealisasi;
import com.wonokoyo.model.strukturdata.D_TglRealisasi;
import com.wonokoyo.model.strukturdata.H_Realisasi;
import com.wonokoyo.model.strukturdata.M_Acc;
import com.wonokoyo.model.strukturdata.M_Item;
import com.wonokoyo.model.strukturdata.M_Parameter;
import com.wonokoyo.model.strukturdata.SD_ItemRealisasi;
import com.wonokoyo.model.utilities.validation.BasicValidation;
import com.wonokoyo.model.utilities.validation.FieldStatus;
import com.wonokoyo.model.utilities.validation.StringField;
import com.wonokoyo.model.utilities.xpath.XpathProcessing;
import org.w3c.dom.*;

/**
 *
 * @author Indra
 */
public class TRealisasiHandler {
    public static String doProcess(String xmldata, String mode,String modereadtable,DBConnection dBConnection/*String mode,H_Perubahan h_Perubahan,String modereadtable*/){

        HRealisasiDb hRealisasiDb = new HRealisasiDb(dBConnection);
        DCostCenterRealisasiDb dCostCenterRealisasiDb = new DCostCenterRealisasiDb(dBConnection);
        DTglRealisasiDb dTglRealisasiDb = new DTglRealisasiDb(dBConnection);
        SD_ItemRealisasiDb sD_ItemRealisasiDb = new SD_ItemRealisasiDb(dBConnection);
        MAccDb mAccDb = new MAccDb(dBConnection);
        String hasil ="";
        H_Realisasi h_Realisasi = new H_Realisasi();
        D_CostCenterRealisasi[] d_CostCenterRealisasi = null;
        DB_Procedure dB_Procedure = new DB_Procedure(dBConnection);
        D_TglRealisasi[] d_TglRealisasi = null;
        M_Acc[] m_Acc = null;
        SD_ItemRealisasi[] sD_ItemRealisasi = null;

        if(xmldata!=null){
            NodeList nodes = XpathProcessing.doProcess(xmldata, "/header");
            for (int i = 0; i < nodes.getLength(); i++) {

                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("koderealisasi");
                Element line = (Element) nodelist.item(0);
                h_Realisasi.KODEREALISASI = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodebp");
                line = (Element) nodelist.item(0);
                h_Realisasi.KODEBP =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodepengajuan");
                line = (Element) nodelist.item(0);
                h_Realisasi.KODEPENGAJUAN = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("jenisrealisasi");
                line = (Element) nodelist.item(0);
                h_Realisasi.JENISREALISASI =  XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("IE");
                line = (Element) nodelist.item(0);
                h_Realisasi.IE =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("tglrealisasi");
                line = (Element) nodelist.item(0);
                h_Realisasi.TGLREALISASI =  XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("versi");
                line = (Element) nodelist.item(0);
                String versi=XpathProcessing.getCharacterDataFromElement(line);
                if (versi.equalsIgnoreCase("") )
                {versi="0";}
                h_Realisasi.VERSI = Integer.parseInt(versi);
                
                nodelist = element.getElementsByTagName("biayatotal");
                line = (Element) nodelist.item(0);
                String tot=XpathProcessing.getCharacterDataFromElement(line);
                if (tot.equalsIgnoreCase("") )
                {tot="0";}
                h_Realisasi.TOTALBIAYA= Integer.parseInt(tot);

            }
            nodes = XpathProcessing.doProcess(xmldata, "/header/detail[@tablename=\"d_costcenterrealisasi\"]/row");
            d_CostCenterRealisasi= new D_CostCenterRealisasi[nodes.getLength()];
            for (int i = 0; i < nodes.getLength(); i++) {
                d_CostCenterRealisasi[i] = new D_CostCenterRealisasi();

                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("approval");
                Element line = (Element) nodelist.item(0);
                d_CostCenterRealisasi[i].APPROVAL = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodecostcenter");
                line = (Element) nodelist.item(0);
                d_CostCenterRealisasi[i].KODECOSTCENTER = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("koderealisasi");
                line = (Element) nodelist.item(0);
                d_CostCenterRealisasi[i].KODEREALISASI = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("nominal");
                line = (Element) nodelist.item(0);
                String nom=XpathProcessing.getCharacterDataFromElement(line);
                String [] b = nom.split(".");
                if (b.length>1){return SuccessErrorMessege.generateMessege(false,"", "Error nominal harus bilangan bulat");}
                d_CostCenterRealisasi[i].NOMINAL = Integer.parseInt(nom);

                nodelist = element.getElementsByTagName("presentase");
                line = (Element) nodelist.item(0);
                String presentase=XpathProcessing.getCharacterDataFromElement(line);
                if (presentase.equalsIgnoreCase("") )
                {presentase="0";}
                d_CostCenterRealisasi[i].PRESENTASE= Double.parseDouble(presentase);
                
                nodelist = element.getElementsByTagName("userid");
                line = (Element) nodelist.item(0);
                d_CostCenterRealisasi[i].USERID = XpathProcessing.getCharacterDataFromElement(line);
            }
            nodes = XpathProcessing.doProcess(xmldata, "/header/detail[@tablename=\"d_tglrealisasi\"]/row");
            d_TglRealisasi = new D_TglRealisasi[nodes.getLength()];
            for (int i = 0; i < nodes.getLength(); i++) {
                d_TglRealisasi[i] = new D_TglRealisasi();

                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("koderealisasi");
                Element line = (Element) nodelist.item(0);
                d_TglRealisasi[i].KODEREALISASI = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodetr");
                line = (Element) nodelist.item(0);
                d_TglRealisasi[i].KODETR = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("tglakhir");
                line = (Element) nodelist.item(0);
                d_TglRealisasi[i].TGLAKHIR = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("tglawal");
                line = (Element) nodelist.item(0);
                d_TglRealisasi[i].TGLAWAL = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("namatarif");
                line = (Element) nodelist.item(0);            
                d_TglRealisasi[i].NAMATARIF = XpathProcessing.getCharacterDataFromElement(line);
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
            nodes = XpathProcessing.doProcess(xmldata, "/header/detail[@tablename=\"d_tglrealisasi\"]/subdetail[@tablename=\"sd_itemrealisasi\"]/row");
            sD_ItemRealisasi = new SD_ItemRealisasi[nodes.getLength()];
            for (int i = 0; i < nodes.getLength(); i++) {
                sD_ItemRealisasi[i] = new SD_ItemRealisasi();

                Element element = (Element) nodes.item(i);

                
                NodeList nodelist = element.getElementsByTagName("biaya");
                Element line = (Element) nodelist.item(0);
                String biaya=XpathProcessing.getCharacterDataFromElement(line);
                if (biaya.equalsIgnoreCase("") )
                {biaya="0";}
                sD_ItemRealisasi[i].BIAYA= Integer.parseInt(biaya);
                
                nodelist = element.getElementsByTagName("durasi");
                line = (Element) nodelist.item(0);
                String durasi=XpathProcessing.getCharacterDataFromElement(line);
                if (durasi.equalsIgnoreCase("") )
                {durasi="0";}
                sD_ItemRealisasi[i].DURASI= Integer.parseInt(durasi); 
                
                nodelist = element.getElementsByTagName("jamakhir");
                line = (Element) nodelist.item(0);
                sD_ItemRealisasi[i].JAMAKHIR = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("jamawal");
                line = (Element) nodelist.item(0);
                sD_ItemRealisasi[i].JAMAWAL = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("jumlahakhir");
                line = (Element) nodelist.item(0);
                String jmlAkhir=XpathProcessing.getCharacterDataFromElement(line);
                if (jmlAkhir.equalsIgnoreCase("") )
                {jmlAkhir="0";}
                sD_ItemRealisasi[i].JUMLAHAKHIR= Integer.parseInt(jmlAkhir);
                
                nodelist = element.getElementsByTagName("keterangan");
                line = (Element) nodelist.item(0);
                sD_ItemRealisasi[i].KETERANGAN = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodeitem");
                line = (Element) nodelist.item(0);
                sD_ItemRealisasi[i].KODEITEM = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodetr");
                line = (Element) nodelist.item(0);
                sD_ItemRealisasi[i].KODETR = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("statussesudah");
                line = (Element) nodelist.item(0);
                sD_ItemRealisasi[i].STATUSSESUDAH = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("statussebelum");
                line = (Element) nodelist.item(0);
                sD_ItemRealisasi[i].STATUSSEBELUM = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("biayaperjam");
                line = (Element) nodelist.item(0);            
                String biayaperjam=XpathProcessing.getCharacterDataFromElement(line);
                if (biayaperjam.equalsIgnoreCase("") )
                {biayaperjam="0";}
                sD_ItemRealisasi[i].BIAYAPERJAM= Integer.parseInt(biayaperjam);
                
                nodelist = element.getElementsByTagName("jumlahpeserta");
                line = (Element) nodelist.item(0);
                String Jumlah=XpathProcessing.getCharacterDataFromElement(line);
                if (Jumlah.equalsIgnoreCase("undefined") )
                {Jumlah="0";}
                sD_ItemRealisasi[i].JUMLAHPESERTA = Integer.parseInt(Jumlah);
            }
        }
        boolean itemvalid = sD_ItemRealisasiDb.isValidJamawalJamakhir(sD_ItemRealisasi);
        if(itemvalid==false){
            dBConnection.isErrorProcessingDb = true;                    
            return SuccessErrorMessege.generateMessege(false,"", "Error jam awal dan jam akhir beririsan untuk item yang sama");
        }
        if( !FormMode.READ.toString().equalsIgnoreCase(mode) && !FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            StringField[] data = {new StringField("kode pengajuan", h_Realisasi.KODEPENGAJUAN),new StringField("nama pemohon", h_Realisasi.KODEBP),new StringField("Tanggal transaksi", h_Realisasi.TGLREALISASI)};                        
            FieldStatus status = BasicValidation.fieldsCannotNullString(data);
            if(status.isError) return SuccessErrorMessege.generateMessege(false,"", "Error "+status.fieldname+" harus diisi.");           
            
            String[] tgl = new String[d_TglRealisasi.length];
            for(int i=0;i<d_TglRealisasi.length;i++){
                tgl[i] = d_TglRealisasi[i].TGLAWAL;
            }
            if(!BasicValidation.isNoDuplicateString(tgl)) return SuccessErrorMessege.generateMessege(false,"", "Error duplikasi tanggal realisasi");
           
            StringField[] kodeItem=new StringField[sD_ItemRealisasi.length];
            StringField[] jamAwal=new StringField[sD_ItemRealisasi.length];
            
            MParameterDb mParameterDb = new MParameterDb(dBConnection);
            MItemDb mItemDb = new MItemDb(dBConnection);
            M_Parameter parameter = mParameterDb.selectOneRow("kodejenisygjumlahpesertanotnull");
            for(int i=0;i<sD_ItemRealisasi.length;i++)
            {
                M_Item item = mItemDb.selectCustomOneRow(sD_ItemRealisasi[i].KODEITEM);
                kodeItem[i]=new StringField("nama item row "+i, sD_ItemRealisasi[i].KODEITEM);
                jamAwal[i]=new StringField("jam awal row "+i, sD_ItemRealisasi[i].JAMAWAL);
                if(parameter.PARVALUE.equalsIgnoreCase(item.KODEJENIS))
                {
                    if (sD_ItemRealisasi[i].JUMLAHPESERTA==0)
                    {
                        return SuccessErrorMessege.generateMessege(false,"", "Error, ruang harus diisi jumlah peserta.");
                    }
                }   
            }
            FieldStatus statKodeItem = BasicValidation.fieldsCannotNullString(kodeItem);
            if(statKodeItem.isError) return SuccessErrorMessege.generateMessege(false,"", "Error, "+statKodeItem.fieldname+" harus diisi.");
            FieldStatus statJamAwal = BasicValidation.fieldsCannot_undefined_string(jamAwal);            
            if(statJamAwal.isError) return SuccessErrorMessege.generateMessege(false,"", "Error, "+statJamAwal.fieldname+" harus diisi.");
            
            if (d_CostCenterRealisasi.length==0){return SuccessErrorMessege.generateMessege(false,"", "Error grid costcenter.");}
            else if(d_TglRealisasi.length==0){return SuccessErrorMessege.generateMessege(false,"", "Error grid tanggal realisasi.");}
            else if(sD_ItemRealisasi.length==0){return SuccessErrorMessege.generateMessege(false,"", "Error grid Sd_ItemRealisasi.");}
            else if (m_Acc.length==0){return SuccessErrorMessege.generateMessege(false,"", "Error grid Persetujuan.");}
        
        }
        if (FormMode.READ.toString().equalsIgnoreCase(mode)){
            if(modereadtable.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = hRealisasiDb.selectAllRowToGridExtJsFormat_HRealisasi();
            }else if(modereadtable.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
        }else if (FormMode.INSERT.toString().equalsIgnoreCase(mode)){
           dBConnection.startTransaction();
           
            try{
                h_Realisasi.KODEREALISASI = dB_Procedure.GEN_KODEREALISASI(h_Realisasi.VERSI, h_Realisasi.TGLREALISASI);
                hasil = hRealisasiDb.insertOneRow(h_Realisasi);
                if (dBConnection.isErrorProcessingDb){                                              
                    dBConnection.endTransaction();
                    return hasil;
                }
                for(int i=0;i<d_CostCenterRealisasi.length;i++){
                    d_CostCenterRealisasi[i].KODEREALISASI = h_Realisasi.KODEREALISASI;
                    hasil = dCostCenterRealisasiDb.insertOneRow(d_CostCenterRealisasi[i]);
                    if(dBConnection.isErrorProcessingDb){
                        dBConnection.endTransaction();
                        return hasil;
                    }
                }
                for(int i=0;i<m_Acc.length;i++){
                    m_Acc[i].KODETRANSAKSI = h_Realisasi.KODEREALISASI;
                    hasil = mAccDb.insertOneRow(m_Acc[i]);
                    if(dBConnection.isErrorProcessingDb){
                        dBConnection.endTransaction();
                        return hasil;
                    }
                }
                String kodetrTemp;
                for(int i=0;i<d_TglRealisasi.length;i++){
                    d_TglRealisasi[i].KODEREALISASI = h_Realisasi.KODEREALISASI;
                    kodetrTemp = dB_Procedure.GEN_KODETR(h_Realisasi.KODEREALISASI, d_TglRealisasi[i].KODETR);
                    System.out.println("sD_ItemRealisasi[j].KODETR>> "+ sD_ItemRealisasi[0].KODETR);
                    System.out.println("sD_ItemRealisasi[j].jumalh peserta>> "+ sD_ItemRealisasi[0].JUMLAHPESERTA);
                    System.out.println("d_TglRealisasi[j].KODETR>> "+ d_TglRealisasi[0].KODETR);
                    for(int j=0;j<sD_ItemRealisasi.length;j++){
                        if(sD_ItemRealisasi[j].KODETR.equalsIgnoreCase(d_TglRealisasi[i].KODETR)){
                            sD_ItemRealisasi[j].KODETR = kodetrTemp;
                            System.out.println("kodetrtemp1 :"+kodetrTemp);
                            System.out.println("sd_item realisasi kodetr:"+sD_ItemRealisasi[j].KODETR);
                        }
                    }
                    d_TglRealisasi[i].KODETR = kodetrTemp;                
                    System.out.println("kodetemp 2: "+kodetrTemp);
                    System.out.println("d_tgl realisasi kodetr:"+d_TglRealisasi[i].KODETR);
                    hasil = dTglRealisasiDb.insertOneRow(d_TglRealisasi[i]);
                    if(dBConnection.isErrorProcessingDb){
                        dBConnection.endTransaction();
                        return hasil;
                    }
                }
                for(int i=0;i<sD_ItemRealisasi.length;i++){
                    hasil = sD_ItemRealisasiDb.insertOneRow(sD_ItemRealisasi[i]);
                    if(dBConnection.isErrorProcessingDb){
                        dBConnection.endTransaction();
                        return hasil;
                    }
                }
                hasil = SuccessErrorMessege.generateMessege(true, "Transaksi realisasi telah tersimpan.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                ex.printStackTrace();
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
           // hasil = mJenisDb.insertOneRow(m_Jenis.KODEJENIS, m_Jenis.NAMAJENIS);
            dBConnection.endTransaction();
        }else if (FormMode.UPDATE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();   
                try{
                    hasil = hRealisasiDb.updateOneRow(h_Realisasi);            
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }            
                    hasil = dCostCenterRealisasiDb.deleteRowByKodeRealisasi(h_Realisasi.KODEREALISASI);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }            
                    for(int i=0;i<d_CostCenterRealisasi.length;i++){
                        d_CostCenterRealisasi[i].KODEREALISASI = h_Realisasi.KODEREALISASI;
                        hasil = dCostCenterRealisasiDb.insertOneRow(d_CostCenterRealisasi[i]);
                        if(dBConnection.isErrorProcessingDb){
                            dBConnection.endTransaction();
                            return hasil;
                        }
                    }
                    hasil = mAccDb.deleteRowByKodeFormAndKodeTrans(m_Acc[0].KODEFORM,h_Realisasi.KODEREALISASI);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }
                    for(int i=0;i<m_Acc.length;i++){
                        m_Acc[i].KODETRANSAKSI = h_Realisasi.KODEREALISASI;
                        hasil = mAccDb.insertOneRow(m_Acc[i]);
                        if(dBConnection.isErrorProcessingDb){
                            dBConnection.endTransaction();
                            return hasil;
                        }
                    }                
                    for(int i=0;i<d_TglRealisasi.length;i++){
                        System.out.println("KodeTr tgl realisasi : "+d_TglRealisasi[i].KODETR);
                        hasil = sD_ItemRealisasiDb.deleteRowByKodeTr(d_TglRealisasi[i].KODETR);
                        if (dBConnection.isErrorProcessingDb){                                              
                            dBConnection.endTransaction();
                            return hasil;
                        }
                    }
                    hasil = dTglRealisasiDb.deleteRowByKodeRealisasi(h_Realisasi.KODEREALISASI);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }
                    String kodetrTemp;
                    for(int i=0;i<d_TglRealisasi.length;i++){
                        d_TglRealisasi[i].KODEREALISASI = h_Realisasi.KODEREALISASI;
                        kodetrTemp = dB_Procedure.GEN_KODETR(h_Realisasi.KODEREALISASI, d_TglRealisasi[i].KODETR);
                        System.out.println("sditemrealisasi>> "+sD_ItemRealisasi.length);
                        for(int j=0;j<sD_ItemRealisasi.length;j++){
                            if(sD_ItemRealisasi[j].KODETR.equalsIgnoreCase(d_TglRealisasi[i].KODETR)){
                                sD_ItemRealisasi[j].KODETR = kodetrTemp;
                            }
                        }
                        d_TglRealisasi[i].KODETR = kodetrTemp; 
                        hasil = dTglRealisasiDb.insertOneRow(d_TglRealisasi[i]);
                        if(dBConnection.isErrorProcessingDb){
                            dBConnection.endTransaction();
                            return hasil;
                        }
                    }

                    for(int i=0;i<sD_ItemRealisasi.length;i++){            
                        System.out.println(">>>"+sD_ItemRealisasi[i].KODETR);
                        hasil = sD_ItemRealisasiDb.insertOneRow(sD_ItemRealisasi[i]);
                        System.out.println(">>>"+sD_ItemRealisasi[i].JUMLAHAKHIR);
                        if(dBConnection.isErrorProcessingDb){
                            dBConnection.endTransaction();
                            return hasil;
                        }
                    }
                    hasil = SuccessErrorMessege.generateMessege(true, "Transaksi realisasi telah terupdate.", "");
                }catch (Exception ex){
                    dBConnection.isErrorProcessingDb = true;
                    ex.printStackTrace();
                    hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
                }
                //hasil = mJenisDb.updateOneRow(m_Jenis.KODEJENIS, m_Jenis.NAMAJENIS);
                dBConnection.endTransaction(); 
        }else if (FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            try{
                    for(int i=0;i<d_TglRealisasi.length;i++){
                        hasil = sD_ItemRealisasiDb.deleteRowByKodeTr(d_TglRealisasi[i].KODETR);
                        if (dBConnection.isErrorProcessingDb){                                              
                            dBConnection.endTransaction();
                            return hasil;
                        }
                    }
                    hasil = dTglRealisasiDb.deleteRowByKodeRealisasi(h_Realisasi.KODEREALISASI);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }                      
                    hasil = dCostCenterRealisasiDb.deleteRowByKodeRealisasi(h_Realisasi.KODEREALISASI);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }                       
                    hasil = mAccDb.deleteRowByKodeFormAndKodeTrans(m_Acc[0].KODEFORM,h_Realisasi.KODEREALISASI);
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }         
                    hasil = hRealisasiDb.deleteOneRow(h_Realisasi); 
                    if (dBConnection.isErrorProcessingDb){                                              
                        dBConnection.endTransaction();
                        return hasil;
                    }               
                    hasil = SuccessErrorMessege.generateMessege(true, "Transaksi realisasi telah terdelete.", "");
                }catch (Exception ex){
                    dBConnection.isErrorProcessingDb = true;                    
                    ex.printStackTrace();
                    hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
                }
            dBConnection.endTransaction();
        }else{

        }
        //hPengajuanDb.closeConn();
        return hasil;

    }
}
