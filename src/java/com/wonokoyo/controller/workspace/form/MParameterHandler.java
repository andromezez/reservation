/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace.form;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.MParameterDb;
import com.wonokoyo.model.formatdata.url.form.FormMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;
import com.wonokoyo.model.strukturdata.M_Parameter;
import com.wonokoyo.model.utilities.xpath.XpathProcessing;
import org.w3c.dom.*;
/**
 *
 * @author SK
 */
public class MParameterHandler {
    public static String doProcess(String xmldata, String mode,String modereadtable,DBConnection dBConnection){
        MParameterDb mParameterDb = new MParameterDb(dBConnection);
        String hasil ="";
        M_Parameter m_Parameter = new M_Parameter();
        if(xmldata!=null){
            NodeList nodes = XpathProcessing.doProcess(xmldata, "/header");
            for (int i = 0; i < nodes.getLength(); i++) {

                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("parname");
                Element line = (Element) nodelist.item(0);
                m_Parameter.PARNAME = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("parvalue");
                line = (Element) nodelist.item(0);
                m_Parameter.PARVALUE =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("partype");
                line = (Element) nodelist.item(0);
                m_Parameter.PARTYPE =  XpathProcessing.getCharacterDataFromElement(line);

            }
        }
        if (FormMode.READ.toString().equalsIgnoreCase(mode)){
            if(modereadtable.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mParameterDb.selectAllRowToGridExtJsFormat_MParameter();
            }else if(modereadtable.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
        }else if (FormMode.INSERT.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            hasil = mParameterDb.insertOneRow(m_Parameter.PARNAME, m_Parameter.PARVALUE, m_Parameter.PARTYPE);
            dBConnection.endTransaction();
        }else if (FormMode.UPDATE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            hasil = mParameterDb.updateOneRow(m_Parameter.PARNAME, m_Parameter.PARVALUE, m_Parameter.PARTYPE);
            dBConnection.endTransaction();
        }else if (FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            hasil = mParameterDb.deleteOneRow(m_Parameter.PARNAME);
            dBConnection.endTransaction();
        }else{

        }
        //mParameterDb.closeConn();
        return hasil;
    }
}

