/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace.form;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.MJenisDb;
import com.wonokoyo.model.formatdata.url.form.FormMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;
import com.wonokoyo.model.strukturdata.M_Jenis;
import com.wonokoyo.model.utilities.xpath.XpathProcessing;
import org.w3c.dom.*;
/**
 *
 * @author Eddy
 */
public class MJenisHandler {
    public static String doProcess(String xmldata, String mode,String modereadtable,DBConnection dBConnection){
        MJenisDb mJenisDb = new MJenisDb(dBConnection);
        String hasil ="";       
        M_Jenis m_Jenis = new M_Jenis();
        if(xmldata!=null){
            NodeList nodes = XpathProcessing.doProcess(xmldata, "/header");
            for (int i = 0; i < nodes.getLength(); i++) {                        

                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("kodejenis");
                Element line = (Element) nodelist.item(0);            
                m_Jenis.KODEJENIS = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("namajenis");
                line = (Element) nodelist.item(0);            
                m_Jenis.NAMAJENIS =  XpathProcessing.getCharacterDataFromElement(line);
            }

        }
        
        if (FormMode.READ.toString().equalsIgnoreCase(mode)){
            if(modereadtable.equals(ReadMode.READ_ALL_ROW.toString())){                
                hasil = mJenisDb.selectAllRowToGridExtJsFormat_MJenis();
            }else if(modereadtable.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
        }else if (FormMode.INSERT.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            hasil = mJenisDb.insertOneRow(m_Jenis.KODEJENIS, m_Jenis.NAMAJENIS);
            dBConnection.endTransaction();
        }else if (FormMode.UPDATE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            hasil = mJenisDb.updateOneRow(m_Jenis.KODEJENIS, m_Jenis.NAMAJENIS);
            dBConnection.endTransaction();
        }else if (FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            hasil = mJenisDb.deleteOneRow(m_Jenis.KODEJENIS);
            dBConnection.endTransaction();
        }else{

        }
        //mJenisDb.closeConn();
        return hasil;
    }
}
