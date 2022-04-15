/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace.form;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.MDTarifItemDb;
import com.wonokoyo.model.MItemDb;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.url.form.FormMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;
import com.wonokoyo.model.strukturdata.MD_TarifItem;
import com.wonokoyo.model.strukturdata.M_Item;
import com.wonokoyo.model.utilities.validation.DateAndTimeValidation;
import com.wonokoyo.model.utilities.xpath.XpathProcessing;
import org.w3c.dom.*;

/**
 *
 * @author SK
 */
public class MItemHandler {
    public static String doProcess(String xmldata, String mode,String modereadtable,DBConnection dBConnection){
        MItemDb mItemDb = new MItemDb(dBConnection);
        MDTarifItemDb mdTarifItemDb = new MDTarifItemDb(dBConnection);
        String hasil ="";

        M_Item m_Item = new M_Item();
        MD_TarifItem[] md_TarifItem = null;
        MD_TarifItem[] md_TarifItem2 = null;

        if(xmldata!=null){
            NodeList nodes = XpathProcessing.doProcess(xmldata, "/header");
            for (int i = 0; i < nodes.getLength(); i++) {

                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("kodeitem");
                Element line = (Element) nodelist.item(0);
                m_Item.KODEITEM = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("namaitem");
                line = (Element) nodelist.item(0);
                m_Item.NAMAITEM =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodejenis");
                line = (Element) nodelist.item(0);
                m_Item.KODEJENIS =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("durasiminsewa");
                line = (Element) nodelist.item(0);
                m_Item.DURASIMINSEWA =  Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line).equalsIgnoreCase("")?"0":XpathProcessing.getCharacterDataFromElement(line));

                nodelist = element.getElementsByTagName("kapasitas");
                line = (Element) nodelist.item(0);
                m_Item.KAPASITAS =  Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line).equalsIgnoreCase("")?"0":XpathProcessing.getCharacterDataFromElement(line));

                nodelist = element.getElementsByTagName("keterangan");
                line = (Element) nodelist.item(0);
                m_Item.KETERANGAN =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("aktif");
                line = (Element) nodelist.item(0);
                m_Item.AKTIF =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("statusavailability");
                line = (Element) nodelist.item(0);
                m_Item.STATUSAVAILABILITY =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("pricechangeable");
                line = (Element) nodelist.item(0);
                m_Item.PRICECHANGEABLE =  XpathProcessing.getCharacterDataFromElement(line);
            }


            nodes = XpathProcessing.doProcess(xmldata, "/header/detail[@tablename=\"md_tarifitem\"][1]/row");
            md_TarifItem = new MD_TarifItem[nodes.getLength()];
            for (int i = 0; i < nodes.getLength(); i++) {
                md_TarifItem[i] = new MD_TarifItem();
                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("kodeitem");
                Element line = (Element) nodelist.item(0);
                md_TarifItem[i].KODEITEM = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("namatarif");
                line = (Element) nodelist.item(0);
                md_TarifItem[i].NAMATARIF =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("jenisrealisasi");
                line = (Element) nodelist.item(0);
                md_TarifItem[i].JENISREALISASI =  XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("jamawal");
                line = (Element) nodelist.item(0);
                md_TarifItem[i].JAMAWAL =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("jamakhir");
                line = (Element) nodelist.item(0);
                md_TarifItem[i].JAMAKHIR =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("biaya");
                line = (Element) nodelist.item(0);
                md_TarifItem[i].BIAYA =  Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line).equalsIgnoreCase("")?"0":XpathProcessing.getCharacterDataFromElement(line));

                nodelist = element.getElementsByTagName("ie");
                line = (Element) nodelist.item(0);
                md_TarifItem[i].IE =  XpathProcessing.getCharacterDataFromElement(line);

            }

            nodes = XpathProcessing.doProcess(xmldata, "/header/detail[@tablename=\"md_tarifitem\"][2]/row");
            md_TarifItem2 = new MD_TarifItem[nodes.getLength()];
            for (int i = 0; i < nodes.getLength(); i++) {
                md_TarifItem2[i] = new MD_TarifItem();
                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("kodeitem");
                Element line = (Element) nodelist.item(0);
                md_TarifItem2[i].KODEITEM = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("namatarif");
                line = (Element) nodelist.item(0);
                md_TarifItem2[i].NAMATARIF =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("jenisrealisasi");
                line = (Element) nodelist.item(0);
                md_TarifItem2[i].JENISREALISASI =  XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("jamawal");
                line = (Element) nodelist.item(0);
                md_TarifItem2[i].JAMAWAL =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("jamakhir");
                line = (Element) nodelist.item(0);
                md_TarifItem2[i].JAMAKHIR =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("biaya");
                line = (Element) nodelist.item(0);
                md_TarifItem2[i].BIAYA =  Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line));

                nodelist = element.getElementsByTagName("ie");
                line = (Element) nodelist.item(0);
                md_TarifItem2[i].IE =  XpathProcessing.getCharacterDataFromElement(line);
            }

        //md_Item.length;
        }
        if( !FormMode.READ.toString().equalsIgnoreCase(mode) && !FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            if (md_TarifItem.length==0) return SuccessErrorMessege.generateMessege(false,"", "Error tarif internal harus diisi minimal 1 record");
           
            
            for (int i = 0; i < md_TarifItem.length ; i++){
            
                if (md_TarifItem[i].JAMAKHIR.equalsIgnoreCase("") || md_TarifItem[i].JAMAWAL.equalsIgnoreCase(""))
                    {
                        return SuccessErrorMessege.generateMessege(false,"", "Error jamAwal-jamAkhir harus diisi di grid tarif internal");
                    }
                if(DateAndTimeValidation.isTime1_biggerThanOrEqual_time2(md_TarifItem[i].JAMAKHIR,md_TarifItem[i].JAMAWAL)==false)
                    {
                        return SuccessErrorMessege.generateMessege(false,"", "Error jamAwal lebih besar dari jamAkhir di grid tarif internal");
                    }
                }
            for (int i = 0; i < md_TarifItem2.length ; i++) {
                    if (md_TarifItem2[i].JAMAKHIR.equalsIgnoreCase("") || md_TarifItem2[i].JAMAWAL.equalsIgnoreCase(""))
                    {
                        return SuccessErrorMessege.generateMessege(false,"", "Error jamAwal-jamAkhir harus diisi di grid tarif external");
                    }
                    if(DateAndTimeValidation.isTime1_biggerThanOrEqual_time2(md_TarifItem2[i].JAMAKHIR,md_TarifItem2[i].JAMAWAL)==false)
                    {
                        return SuccessErrorMessege.generateMessege(false,"", "Error jamAwal lebih besar dari jamAkhir di grid tarif external");
                    }
                }
            boolean itemvalid = mdTarifItemDb.isValidJamawalJamakhir(md_TarifItem);
            if(itemvalid==false){
                dBConnection.isErrorProcessingDb = true;                    
                return SuccessErrorMessege.generateMessege(false,"", "Error jam awal dan jam akhir beririsan untuk namatarif yang sama di tarif internal");
            }
            itemvalid = mdTarifItemDb.isValidJamawalJamakhir(md_TarifItem2);
            if(itemvalid==false){
                dBConnection.isErrorProcessingDb = true;                    
                return SuccessErrorMessege.generateMessege(false,"", "Error jam awal dan jam akhir beririsan untuk namatarif yang sama di tarif eksternal");
            }
        }
        
        if (FormMode.READ.toString().equalsIgnoreCase(mode)){
            if(modereadtable.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mItemDb.convertToGridExtJsFormat_MItem();
            }else if(modereadtable.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
        }else if (FormMode.INSERT.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            try{
                hasil = mItemDb.insertOneRow(m_Item.KODEITEM, m_Item.KODEJENIS, m_Item.NAMAITEM, m_Item.DURASIMINSEWA, m_Item.KAPASITAS, m_Item.KETERANGAN, m_Item.AKTIF, m_Item.STATUSAVAILABILITY, m_Item.PRICECHANGEABLE);
                if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                }
                for (int i = 0; i < md_TarifItem.length ; i++) {
                    hasil = mdTarifItemDb.insertOneRow(md_TarifItem[i]);
                    if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                }
                for (int i = 0; i < md_TarifItem2.length ; i++) {
                    hasil = mdTarifItemDb.insertOneRow(md_TarifItem2[i]);
                    if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                }
                hasil = SuccessErrorMessege.generateMessege(true, "Master Item telah tersimpan.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
            dBConnection.endTransaction();
        }else if (FormMode.UPDATE.toString().equalsIgnoreCase(mode)){
           
            dBConnection.startTransaction();
            try{
                hasil = mItemDb.updateOneRow(m_Item.KODEITEM, m_Item.KODEJENIS, m_Item.NAMAITEM, m_Item.DURASIMINSEWA, m_Item.KAPASITAS, m_Item.KETERANGAN, m_Item.AKTIF, m_Item.STATUSAVAILABILITY, m_Item.PRICECHANGEABLE);
                if(dBConnection.isErrorProcessingDb){
                             dBConnection.endTransaction();
                             return hasil;
                    }
                hasil = mdTarifItemDb.deleteOneRow(m_Item.KODEITEM);
                if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                for (int i = 0; i < md_TarifItem.length ; i++) {
                    hasil = mdTarifItemDb.insertOneRow(md_TarifItem[i]);
                    if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                }
                for (int i = 0; i < md_TarifItem2.length ; i++) {
                    hasil = mdTarifItemDb.insertOneRow(md_TarifItem2[i]);
                    if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                }
                hasil = SuccessErrorMessege.generateMessege(true, "Master Item telah terupdate.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
            dBConnection.endTransaction();
        }else if (FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            try{
                hasil = mdTarifItemDb.deleteOneRow(m_Item.KODEITEM);
                if(dBConnection.isErrorProcessingDb){
                        dBConnection.endTransaction();
                        return hasil;
                }
                
                hasil = mItemDb.deleteOneRow(m_Item.KODEITEM);
                if(dBConnection.isErrorProcessingDb){
                        dBConnection.endTransaction();
                        return hasil;
                }
                hasil = SuccessErrorMessege.generateMessege(true, "Master Item telah terhapus.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
            dBConnection.endTransaction();
        }else{

        }
        //mItemDb.closeConn();
        return hasil;
    }
}

