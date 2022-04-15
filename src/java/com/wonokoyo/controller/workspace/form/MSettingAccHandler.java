/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace.form;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.MHKonfigurasiAccDb;
import com.wonokoyo.model.MSettingAccDb;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.url.form.FormMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;
import com.wonokoyo.model.strukturdata.MH_KonfigurasiAcc;
import com.wonokoyo.model.strukturdata.M_SettingAcc;
import com.wonokoyo.model.utilities.xpath.XpathProcessing;
import org.w3c.dom.*;
/**
 *
 * @author SK
 */
public class MSettingAccHandler {
    public static String doProcess(String xmldata, String mode,String modereadtable,DBConnection dBConnection){
        MSettingAccDb mSettingAccDb = new MSettingAccDb(dBConnection);
        String hasil ="";
        M_SettingAcc m_SettingAcc = new M_SettingAcc();
        if(xmldata!=null){
            NodeList nodes = XpathProcessing.doProcess(xmldata, "/header");
            for (int i = 0; i < nodes.getLength(); i++) {

                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("kodeform");
                Element line = (Element) nodelist.item(0);
                m_SettingAcc.KODEFORM = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("namaform");
                line = (Element) nodelist.item(0);
                m_SettingAcc.NAMAFORM =  XpathProcessing.getCharacterDataFromElement(line).equalsIgnoreCase("")?"":XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("accform");
                line = (Element) nodelist.item(0);
                m_SettingAcc.ACCFORM =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("editfinalapprove");
                line = (Element) nodelist.item(0);
                m_SettingAcc.EDITFINALAPPROVE =  XpathProcessing.getCharacterDataFromElement(line);
            }
        }
        if( !FormMode.READ.toString().equalsIgnoreCase(mode) && !FormMode.DELETE.toString().equalsIgnoreCase(mode)){
                /*  MHKonfigurasiAccDb konfigurasiDb = new MHKonfigurasiAccDb(dBConnection);
            
                MH_KonfigurasiAcc konf=konfigurasiDb.selectOneRow(m_SettingAcc.KODEFORM);
                    if (konf.TOTAL == 0)
                    {return SuccessErrorMessege.generateMessege(false,"", "Error, konfigurasi acc untuk form "+m_SettingAcc.NAMAFORM+ " masih kosong");}
             */            
        }
        if (FormMode.READ.toString().equalsIgnoreCase(mode)){
            if(modereadtable.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mSettingAccDb.selectAllRow();
            }else if(modereadtable.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
        }else if (FormMode.INSERT.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            hasil = mSettingAccDb.insertOneRow(m_SettingAcc.KODEFORM, m_SettingAcc.ACCFORM, m_SettingAcc.EDITFINALAPPROVE);
            dBConnection.endTransaction();
        }else if (FormMode.UPDATE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            hasil = mSettingAccDb.updateOneRow(m_SettingAcc.KODEFORM, m_SettingAcc.ACCFORM, m_SettingAcc.EDITFINALAPPROVE);
            dBConnection.endTransaction();
        }else if (FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            hasil = mSettingAccDb.deleteOneRow(m_SettingAcc.KODEFORM);
            dBConnection.endTransaction();
        }else{

        }
        //mSettingAccDb.closeConn();
        return hasil;
    }
}
