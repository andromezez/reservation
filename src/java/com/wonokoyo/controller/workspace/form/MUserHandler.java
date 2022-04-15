/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace.form;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.MPermissionDb;
import com.wonokoyo.model.MUserDb;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.url.form.FormMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;
import com.wonokoyo.model.strukturdata.M_Permission;
import com.wonokoyo.model.strukturdata.M_User;
import com.wonokoyo.model.utilities.xpath.XpathProcessing;
import org.w3c.dom.*;
/**
 *
 * @author Eddy
 */
public class MUserHandler {
    public static String doProcess(String xmldata, String mode,String modereadtable,DBConnection dBConnection){
        MUserDb mUserDb = new MUserDb(dBConnection);
        MPermissionDb mPermissionDb = new MPermissionDb(dBConnection);
        String hasil ="";

        M_User m_User = new M_User();
        M_Permission[] m_Permission = null;

        if(xmldata!=null){
            NodeList nodes = XpathProcessing.doProcess(xmldata, "/header");
            for (int i = 0; i < nodes.getLength(); i++) {

                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("userid");
                Element line = (Element) nodelist.item(0);
                m_User.USERID = XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("kodebp");
                line = (Element) nodelist.item(0);
                m_User.KODEBP =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("aktif");
                line = (Element) nodelist.item(0);
                m_User.AKTIF =  XpathProcessing.getCharacterDataFromElement(line);

                /*nodelist = element.getElementsByTagName("costcenter");
                line = (Element) nodelist.item(0);
                m_User.COSTCENTER =  XpathProcessing.getCharacterDataFromElement(line);*/
            }


            nodes = XpathProcessing.doProcess(xmldata, "/header/detail[@tablename=\"m_permission\"]/row");
            m_Permission = new M_Permission[nodes.getLength()];
            for (int i = 0; i < nodes.getLength(); i++) {
                m_Permission[i] = new M_Permission();
                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("userid");
                Element line = (Element) nodelist.item(0);
                m_Permission[i].USERID = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodeform");
                line = (Element) nodelist.item(0);
                m_Permission[i].KODEFORM =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("pview");
                line = (Element) nodelist.item(0);
                m_Permission[i].PVIEW =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("padd");
                line = (Element) nodelist.item(0);
                m_Permission[i].PADD =  XpathProcessing.getCharacterDataFromElement(line);
            
                nodelist = element.getElementsByTagName("pupdate");
                line = (Element) nodelist.item(0);
                m_Permission[i].PUPDATE =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("pdelete");
                line = (Element) nodelist.item(0);
                m_Permission[i].PDELETE =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("pprint");
                line = (Element) nodelist.item(0);
                m_Permission[i].PPRINT =  XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("batal_trans");
                line = (Element) nodelist.item(0);
                m_Permission[i].BATAL_TRANS =  XpathProcessing.getCharacterDataFromElement(line);

            }
        }
        if( !FormMode.READ.toString().equalsIgnoreCase(mode) && !FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            if (m_Permission.length==0) return SuccessErrorMessege.generateMessege(false,"", "Error grid permission harus diisi minimal 1 record");
            }
        if (FormMode.READ.toString().equalsIgnoreCase(mode)){
            if(modereadtable.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mUserDb.selectAllRow();
            }else if(modereadtable.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
        }else if (FormMode.INSERT.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            try{
                hasil = mUserDb.insertOneRow(m_User.USERID, m_User.KODEBP, m_User.PASS, m_User.AKTIF);
                if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                }
                for (int i = 0; i < m_Permission.length ; i++) {
                    hasil = mPermissionDb.insertOneRow(m_Permission[i].KODEFORM, m_Permission[i].USERID, m_Permission[i].PVIEW, m_Permission[i].PADD, m_Permission[i].PUPDATE, m_Permission[i].PDELETE, m_Permission[i].PPRINT,m_Permission[i].BATAL_TRANS);
                    if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                }
                hasil = SuccessErrorMessege.generateMessege(true, "Master User telah tersimpan.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
            dBConnection.endTransaction();
        }else if (FormMode.UPDATE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            try{
                hasil = mUserDb.updateOneRowForTransaction(m_User.USERID, m_User.KODEBP, m_User.AKTIF);
                if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                hasil = mPermissionDb.deleteOneRow(m_User.USERID);
                if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                for (int i = 0; i < m_Permission.length ; i++) {
                    hasil = mPermissionDb.insertOneRow(m_Permission[i].KODEFORM, m_Permission[i].USERID, m_Permission[i].PVIEW, m_Permission[i].PADD, m_Permission[i].PUPDATE, m_Permission[i].PDELETE, m_Permission[i].PPRINT, m_Permission[i].BATAL_TRANS);
                    if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                }
                hasil = SuccessErrorMessege.generateMessege(true, "Master User telah terupdate.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
            dBConnection.endTransaction();
        }else if (FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            try{
                hasil = mPermissionDb.deleteOneRow(m_User.USERID);
                if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                hasil = mUserDb.deleteOneRow(m_User.USERID);
                if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                }
                hasil = SuccessErrorMessege.generateMessege(true, "Master User telah terhapus.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
            dBConnection.endTransaction();
        }else{

        }
        //mUserDb.closeConn();
        return hasil;
    }
}
