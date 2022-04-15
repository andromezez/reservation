/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace.form;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.MDKonfigurasiAccDb;
import com.wonokoyo.model.MHKonfigurasiAccDb;
import com.wonokoyo.model.VMHRIS_jabatankaryawanDb;
import com.wonokoyo.model.formatdata.SuccessErrorMessege;
import com.wonokoyo.model.formatdata.url.form.FormMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;
import com.wonokoyo.model.strukturdata.MD_KonfigurasiAcc;
import com.wonokoyo.model.strukturdata.MH_KonfigurasiAcc;
import com.wonokoyo.model.strukturdata.VM_Hris_JabatanKaryawan;
import com.wonokoyo.model.utilities.validation.BasicValidation;
import com.wonokoyo.model.utilities.validation.FieldStatus;
import com.wonokoyo.model.utilities.validation.StringField;

import com.wonokoyo.model.utilities.xpath.XpathProcessing;
import org.w3c.dom.*;

/**
 *
 * @author Sk
 */
public class MKonfigurasiAccHandler {

    public static String doProcess(String xmldata, String mode,String modereadtable,DBConnection dBConnection){

        MHKonfigurasiAccDb mhKonfigurasiAccDb = new MHKonfigurasiAccDb(dBConnection);
        MDKonfigurasiAccDb mdKonfigurasiAccDb = new MDKonfigurasiAccDb(dBConnection);

        MH_KonfigurasiAcc mh_KonfigurasiAcc = new MH_KonfigurasiAcc();
        MD_KonfigurasiAcc[] md_KonfigurasiAcc = null;

        if(xmldata!=null){
            NodeList nodes = XpathProcessing.doProcess(xmldata, "/header");
            for (int i = 0; i < nodes.getLength(); i++) {

                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("kodeacc");
                Element line = (Element) nodelist.item(0);
                mh_KonfigurasiAcc.KODEACC = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodeform");
                line = (Element) nodelist.item(0);
                mh_KonfigurasiAcc.KODEFORM =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("prioritas");
                line = (Element) nodelist.item(0);
                mh_KonfigurasiAcc.PRIORITAS =  Integer.parseInt(XpathProcessing.getCharacterDataFromElement(line).equalsIgnoreCase("")?"1":XpathProcessing.getCharacterDataFromElement(line));

                nodelist = element.getElementsByTagName("keterangan");
                line = (Element) nodelist.item(0);
                mh_KonfigurasiAcc.KETERANGAN =  XpathProcessing.getCharacterDataFromElement(line);
            }


            nodes = XpathProcessing.doProcess(xmldata, "/header/detail[@tablename=\"md_konfigurasiacc\"]/row");
            md_KonfigurasiAcc = new MD_KonfigurasiAcc[nodes.getLength()];
            for (int i = 0; i < nodes.getLength(); i++) {
                md_KonfigurasiAcc[i] = new MD_KonfigurasiAcc();
                Element element = (Element) nodes.item(i);

                NodeList nodelist = element.getElementsByTagName("kodeacc");
                Element line = (Element) nodelist.item(0);
                md_KonfigurasiAcc[i].KODEACC = XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("userid");
                line = (Element) nodelist.item(0);
                md_KonfigurasiAcc[i].USERID =  XpathProcessing.getCharacterDataFromElement(line);

                nodelist = element.getElementsByTagName("kodejabatan");
                line = (Element) nodelist.item(0);
                md_KonfigurasiAcc[i].KODEJABATAN =  XpathProcessing.getCharacterDataFromElement(line);
                
                nodelist = element.getElementsByTagName("defaultacc");
                line = (Element) nodelist.item(0);
                md_KonfigurasiAcc[i].DEFAULTACC =  XpathProcessing.getCharacterDataFromElement(line);
            }
        }
        if( !FormMode.READ.toString().equalsIgnoreCase(mode) && !FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            StringField[] data = {new StringField("kode form", mh_KonfigurasiAcc.KODEFORM),new StringField("keterangan", mh_KonfigurasiAcc.KETERANGAN)};                        
            FieldStatus status = BasicValidation.fieldsCannotNullString(data);
            if(status.isError) return SuccessErrorMessege.generateMessege(false,"", "Error "+status.fieldname+" harus diisi.");           
            if (md_KonfigurasiAcc.length==0) return SuccessErrorMessege.generateMessege(false,"", "Error daftar user harus diisi minimal 1 record");
           
            String[] DataGrid=new String[md_KonfigurasiAcc.length];
            int acc=0;
            VMHRIS_jabatankaryawanDb jabatanDb = new VMHRIS_jabatankaryawanDb(dBConnection);
            for (int i = 0; i < md_KonfigurasiAcc.length ; i++) {
               if (md_KonfigurasiAcc[i].DEFAULTACC.equalsIgnoreCase("1"))
               {
                   acc += 1;
               }
               if (acc > 1)
               {return SuccessErrorMessege.generateMessege(false,"", "Error, default acc hanya boleh 1");}
               
               VM_Hris_JabatanKaryawan jabatan=jabatanDb.selectOneRow(md_KonfigurasiAcc[i].USERID);
               
               DataGrid[i] = md_KonfigurasiAcc[i].NAMABP+md_KonfigurasiAcc[i].KODEJABATAN;
                if (jabatan.TOTAL > 0)
                {
                    if(md_KonfigurasiAcc[i].KODEJABATAN.equalsIgnoreCase(""))
                    {
                        return SuccessErrorMessege.generateMessege(false,"", "Error,userid : "+md_KonfigurasiAcc[i].USERID+" harus diisi kodejabatan.");
                    }
                }
            }
            if (BasicValidation.isNoDuplicateString(DataGrid)==false)
                    {
                        return SuccessErrorMessege.generateMessege(false,"", "Error, nama bp dan nama jabatan duplikat.");
                    }
        }
        String hasil ="";
        if (FormMode.READ.toString().equalsIgnoreCase(mode)){
            if(modereadtable.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mhKonfigurasiAccDb.selectAllRow();
            }else if(modereadtable.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
        }else if (FormMode.INSERT.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            try{
                hasil = mhKonfigurasiAccDb.insertOneRow(mh_KonfigurasiAcc.KODEACC, mh_KonfigurasiAcc.KODEFORM, mh_KonfigurasiAcc.PRIORITAS, mh_KonfigurasiAcc.KETERANGAN);
                if(dBConnection.isErrorProcessingDb){
                             dBConnection.endTransaction();
                             return hasil;
                    }
                for (int i = 0; i < md_KonfigurasiAcc.length ; i++) {
                    hasil = mdKonfigurasiAccDb.insertOneRow(md_KonfigurasiAcc[i].KODEACC, md_KonfigurasiAcc[i].USERID, md_KonfigurasiAcc[i].KODEJABATAN,md_KonfigurasiAcc[i].DEFAULTACC);
                    if(dBConnection.isErrorProcessingDb){
                             dBConnection.endTransaction();
                             return hasil;
                    }
                }
                hasil = SuccessErrorMessege.generateMessege(true, "Master Konfigurasi Acc telah tersimpan.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
            dBConnection.endTransaction();
        }else if (FormMode.UPDATE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            try{
                hasil = mhKonfigurasiAccDb.updateOneRow(mh_KonfigurasiAcc.KODEACC, mh_KonfigurasiAcc.KODEFORM, mh_KonfigurasiAcc.PRIORITAS, mh_KonfigurasiAcc.KETERANGAN);
                if(dBConnection.isErrorProcessingDb){
                             dBConnection.endTransaction();
                             return hasil;
                    }
                hasil = mdKonfigurasiAccDb.deleteOneRow(mh_KonfigurasiAcc.KODEACC);
                if(dBConnection.isErrorProcessingDb){
                             dBConnection.endTransaction();
                             return hasil;
                    }
                for (int i = 0; i < md_KonfigurasiAcc.length ; i++) {
                    hasil = mdKonfigurasiAccDb.insertOneRow(md_KonfigurasiAcc[i].KODEACC, md_KonfigurasiAcc[i].USERID, md_KonfigurasiAcc[i].KODEJABATAN,md_KonfigurasiAcc[i].DEFAULTACC);
                    if(dBConnection.isErrorProcessingDb){
                             dBConnection.endTransaction();
                             return hasil;
                    }
                }
                hasil = SuccessErrorMessege.generateMessege(true, "Master Konfigurasi Acc telah terupdate.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
            dBConnection.endTransaction();
        }else if (FormMode.DELETE.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            try{
                hasil = mdKonfigurasiAccDb.deleteOneRow(mh_KonfigurasiAcc.KODEACC);
                if(dBConnection.isErrorProcessingDb){
                             dBConnection.endTransaction();
                             return hasil;
                    }
                hasil = mhKonfigurasiAccDb.deleteOneRow(mh_KonfigurasiAcc.KODEACC);
                if(dBConnection.isErrorProcessingDb){
                             dBConnection.endTransaction();
                             return hasil;
                    }
                hasil = SuccessErrorMessege.generateMessege(true, "Master Konfigurasi Acc telah terhapus.", "");
            }catch (Exception ex){
                dBConnection.isErrorProcessingDb = true;
                hasil = SuccessErrorMessege.generateMessege(false,"", "Error transaction handler.");
            }
            dBConnection.endTransaction();
        }else{

        }
        //mKonfigurasiAccDb.closeConn();
        return hasil;
    }
}
