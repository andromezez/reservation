/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace.form;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.MDPembebananDb;
import com.wonokoyo.model.MPembebananDb;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.url.form.FormMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;
import com.wonokoyo.model.strukturdata.M_Pembebanan;
import com.wonokoyo.model.strukturdata.MD_Pembebanan;
import com.wonokoyo.model.utilities.validation.BasicValidation;
import com.wonokoyo.model.utilities.validation.FieldStatus;
import com.wonokoyo.model.utilities.validation.StringField;
import com.wonokoyo.model.utilities.xpath.XpathProcessing;
import org.w3c.dom.*;
/**
 *
 * @author SK
 */
public class MPembebananHandler {
    public static String doProcess(String xmldata, String mode,String modereadtable,DBConnection dBConnection){

        MPembebananDb mPembebananDb = new MPembebananDb(dBConnection);
        MDPembebananDb mdPembebananDb = new MDPembebananDb(dBConnection);
        String hasil ="";
        ///header/detail[@tablename="d_tglpengajuan"]/subdetail/row
        M_Pembebanan m_Pembebanan = new M_Pembebanan();
        MD_Pembebanan[] md_Pembebanan = null;
        if(xmldata!=null){
            NodeList nodes = XpathProcessing.doProcess(xmldata, "/header");
            for (int i = 0; i < nodes.getLength(); i++) {

                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("kodepembebanan");
                Element line = (Element) nodelist.item(0);
                m_Pembebanan.KODEPEMBEBANAN = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("namapembebanan");
                line = (Element) nodelist.item(0);
                m_Pembebanan.NAMAPEMBEBANAN =  XpathProcessing.getCharacterDataFromElement(line);
            }

        
            nodes = XpathProcessing.doProcess(xmldata, "/header/detail[@tablename=\"md_pembebanan\"]/row");
            md_Pembebanan = new MD_Pembebanan[nodes.getLength()];
            for (int i = 0; i < nodes.getLength(); i++) {
                md_Pembebanan[i] = new MD_Pembebanan();
                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("kodepembebanan");
                Element line = (Element) nodelist.item(0);
                md_Pembebanan[i].KODEPEMBEBANAN = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodecostcenter");
                line = (Element) nodelist.item(0);
                md_Pembebanan[i].KODECOSTCENTER =  XpathProcessing.getCharacterDataFromElement(line);
            }
        
        //md_Pembebanan.length;
        }

        if( !FormMode.READ.toString().equalsIgnoreCase(mode) && !FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            if (md_Pembebanan.length==0) return SuccessErrorMessege.generateMessege(false,"", "Error detail cost center, harus diisi minimal 1 record");
            if (mdPembebananDb.isDuplicateCostcenter(md_Pembebanan)) return SuccessErrorMessege.generateMessege(false,"", "Error duplikasi costcenter pada detail");
            
            for(int i=0;i<md_Pembebanan.length;i++){
                System.out.println(md_Pembebanan[i].KODECOSTCENTER);
                if (md_Pembebanan[i].KODECOSTCENTER.equalsIgnoreCase(""))
                {
                    return SuccessErrorMessege.generateMessege(false,"", "Error kode cost center harus diisi.");
                }
                MD_Pembebanan[] mdp =  mdPembebananDb.selectCustomRow2("select distinct a.kodepembebanan,a.kodecostcenter " +
                  "from md_pembebanan a " +
                  "where a.kodepembebanan != '"+md_Pembebanan[i].KODEPEMBEBANAN+"' " +
                  "and a.kodecostcenter = '"+md_Pembebanan[i].KODECOSTCENTER+"'");
                if(mdp.length>0){
                    return SuccessErrorMessege.generateMessege(false,"", "Error kode costcenter "+mdp[0].KODECOSTCENTER+" sudah ada dalam kode pembebanan "+mdp[0].KODEPEMBEBANAN);
                }
            }
           
            
        }

        if (FormMode.READ.toString().equalsIgnoreCase(mode)){
            if(modereadtable.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mPembebananDb.selectAllRowToGridExtJsFormat_MPembebanan();
            }else if(modereadtable.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
        }else if (FormMode.INSERT.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            try{
                hasil = mPembebananDb.insertOneRow(m_Pembebanan.KODEPEMBEBANAN, m_Pembebanan.NAMAPEMBEBANAN);
                if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                }
                for (int i = 0; i < md_Pembebanan.length ; i++) {
                    hasil = mdPembebananDb.insertOneRow(md_Pembebanan[i].KODEPEMBEBANAN, md_Pembebanan[i].KODECOSTCENTER);
                    if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                }
                hasil = SuccessErrorMessege.generateMessege(true, "Master Pembebanan telah tersimpan.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
            dBConnection.endTransaction();
        }else if (FormMode.UPDATE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            try{
                hasil = mPembebananDb.updateOneRow(m_Pembebanan.KODEPEMBEBANAN, m_Pembebanan.NAMAPEMBEBANAN);
                if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                hasil = mdPembebananDb.deleteOneRow(m_Pembebanan.KODEPEMBEBANAN);
                if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                for (int i = 0; i < md_Pembebanan.length ; i++) {

                    hasil = mdPembebananDb.insertOneRow(md_Pembebanan[i].KODEPEMBEBANAN, md_Pembebanan[i].KODECOSTCENTER);
                    if(dBConnection.isErrorProcessingDb){
                         dBConnection.endTransaction();
                         return hasil;
                    }
                }
                hasil = SuccessErrorMessege.generateMessege(true, "Master Pembebanan telah terupdate.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
            dBConnection.endTransaction();

        }else if (FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            try{
                hasil = mdPembebananDb.deleteOneRow(m_Pembebanan.KODEPEMBEBANAN);
                if(dBConnection.isErrorProcessingDb){
                     dBConnection.endTransaction();
                     return hasil;
                }
                hasil = mPembebananDb.deleteOneRow(m_Pembebanan.KODEPEMBEBANAN);
                if(dBConnection.isErrorProcessingDb){
                     dBConnection.endTransaction();
                     return hasil;
                }
                hasil = SuccessErrorMessege.generateMessege(true, "Master Pembebanan telah terhapus.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
            dBConnection.endTransaction();
        }else{

        }
        //mPembebananDb.closeConn();
        return hasil;
    }
}
