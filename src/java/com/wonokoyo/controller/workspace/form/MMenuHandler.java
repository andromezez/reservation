/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace.form;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.MMenuDb;
import com.wonokoyo.model.formatdata.url.form.FormMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;
import com.wonokoyo.model.strukturdata.M_Menu;
import com.wonokoyo.model.utilities.xpath.XpathProcessing;
import org.w3c.dom.*;
/**
 *
 * @author SK
 */
public class MMenuHandler {
    public static String doProcess(String xmldata, String mode,String modereadtable,DBConnection dBConnection){
        MMenuDb mMenuDb = new MMenuDb(dBConnection);
        String hasil ="";
        M_Menu m_Menu = new M_Menu();
        if(xmldata!=null){
            NodeList nodes = XpathProcessing.doProcess(xmldata, "/header");
            for (int i = 0; i < nodes.getLength(); i++) {

                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("kodeform");
                Element line = (Element) nodelist.item(0);
                m_Menu.KODEFORM = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("namaform");
                line = (Element) nodelist.item(0);
                m_Menu.NAMAFORM =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodeparent");
                line = (Element) nodelist.item(0);
                m_Menu.KODEPARENT =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("url");
                line = (Element) nodelist.item(0);
                m_Menu.URL =  XpathProcessing.getCharacterDataFromElement(line);
            }
        }
        if (FormMode.READ.toString().equalsIgnoreCase(mode)){
            if(modereadtable.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mMenuDb.selectAllRowToGridExtJsFormat_MMenu();
            }else if(modereadtable.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
        }else if (FormMode.INSERT.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            hasil = mMenuDb.insertOneRow(m_Menu.KODEFORM, m_Menu.NAMAFORM, m_Menu.KODEPARENT, m_Menu.URL);
            dBConnection.endTransaction();
        }else if (FormMode.UPDATE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            hasil = mMenuDb.updateOneRow(m_Menu.KODEFORM, m_Menu.NAMAFORM, m_Menu.KODEPARENT, m_Menu.URL);
            dBConnection.endTransaction();
        }else if (FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            hasil = mMenuDb.deleteOneRow(m_Menu.KODEFORM);
            dBConnection.endTransaction();
        }else{

        }
        //mMenuDb.closeConn();
        return hasil;
    }
}
