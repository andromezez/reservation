/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace;

import com.wonokoyo.controller.workspace.form.MHistoryHandler;
import com.wonokoyo.controller.workspace.form.MJenisHandler;
import com.wonokoyo.controller.workspace.form.MMenuHandler;
import com.wonokoyo.controller.workspace.form.MSettingAccHandler;
import com.wonokoyo.controller.workspace.form.MKonfigurasiAccHandler;
import com.wonokoyo.controller.workspace.form.MUserHandler;
import com.wonokoyo.controller.workspace.form.MPembebananHandler;
import com.wonokoyo.controller.workspace.form.MItemHandler;
import com.wonokoyo.controller.workspace.form.MParameterHandler;

import com.wonokoyo.controller.workspace.form.TPengajuanHandler;
import com.wonokoyo.controller.workspace.form.TPerubahanHandler;
import com.wonokoyo.controller.workspace.form.TRealisasiHandler;
import com.wonokoyo.model.DBConnection;

import com.wonokoyo.model.formatdata.url.form.URLParameterFormatForm;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.URLParameterFormatTableOperation;
import com.wonokoyo.model.strukturdata.H_Pengajuan;
import com.wonokoyo.model.strukturdata.M_Jenis;
import com.wonokoyo.model.strukturdata.M_Menu;
import com.wonokoyo.model.strukturdata.M_SettingAcc;
import com.wonokoyo.model.strukturdata.MH_KonfigurasiAcc;
import com.wonokoyo.model.strukturdata.M_History;
import com.wonokoyo.model.strukturdata.M_User;
import com.wonokoyo.model.strukturdata.M_Pembebanan;
import com.wonokoyo.model.strukturdata.M_Item;
import com.wonokoyo.model.strukturdata.M_Parameter;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**
 *
 * @author Eddy
 */
public class FormHandler {
    
    public static String filterForm(HttpServletRequest request,HttpServletResponse response,DBConnection dBConnection){
        String modereadtable = request.getParameter(URLParameterFormatTableOperation.parameterTableOperation)==null?"":request.getParameter(URLParameterFormatTableOperation.parameterTableOperation);

        if(modereadtable.equals(ReadMode.READ_ALL_ROW.toString()))
                response.setContentType("text/xml;charset=UTF-8");
        else
            response.setContentType("text/html;charset=UTF-8");
        String hasil = "";
        
        //System.out.println(request.getParameter("xmldata"));
        if(request.getParameter(URLParameterFormatForm.parameterFormId).
                equalsIgnoreCase("01-03-01")){            
            hasil = MJenisHandler.doProcess(
                    (request.getParameter("xmldata")==null?null:request.getParameter("xmldata")),
                    request.getParameter(URLParameterFormatForm.parameterFormMode),
                    modereadtable,
                    dBConnection
            );//,m_Jenis,);
          //  return getMenu(request.getParameter(URLParameterFormatForm.parameterFormId),
          //          context);
        }else
        if(request.getParameter(URLParameterFormatForm.parameterFormId).
                equalsIgnoreCase("01-02-01")){
            hasil = MMenuHandler.doProcess(
                    (request.getParameter("xmldata")==null?null:request.getParameter("xmldata")),
                    request.getParameter(URLParameterFormatForm.parameterFormMode),
                    modereadtable,dBConnection
            );
            /*M_Menu m_Menu = new M_Menu();
            m_Menu.KODEFORM = request.getParameter("kodeform")==null?null:request.getParameter("kodeform");
            m_Menu.NAMAFORM = request.getParameter("namaform")==null?null:request.getParameter("namaform");
            m_Menu.KODEPARENT = request.getParameter("kodeparent")==null?"":request.getParameter("kodeparent");
            m_Menu.URL = request.getParameter("url")==null?"":request.getParameter("url");
            hasil = MMenuHandler.doProcess(request.getParameter(URLParameterFormatForm.parameterFormMode),m_Menu,modereadtable);
             */
          //  return getMenu(request.getParameter(URLParameterFormatForm.parameterFormId),
          //          context);
        }else
        if(request.getParameter(URLParameterFormatForm.parameterFormId).
                equalsIgnoreCase("01-03-03")){
            hasil = MSettingAccHandler.doProcess(
                    (request.getParameter("xmldata")==null?null:request.getParameter("xmldata")),
                    request.getParameter(URLParameterFormatForm.parameterFormMode),
                    modereadtable,dBConnection
            );
            /*M_SettingAcc m_SettingAcc = new M_SettingAcc();
            m_SettingAcc.KODEFORM = request.getParameter("kodeform")==null?null:request.getParameter("kodeform");
            m_SettingAcc.NAMAFORM = request.getParameter("namaform")==null?null:request.getParameter("namaform");
            m_SettingAcc.ACCFORM = request.getParameter("accform")==null?null:request.getParameter("accform");
            m_SettingAcc.EDITFINALAPPROVE = request.getParameter("editfinalapprove")==null?null:request.getParameter("editfinalapprove");
            hasil = MSettingAccHandler.doProcess(request.getParameter(URLParameterFormatForm.parameterFormMode),m_SettingAcc,modereadtable);
          */
          //  return getMenu(request.getParameter(URLParameterFormatForm.parameterFormId),
          //          context);
        }else
        if(request.getParameter(URLParameterFormatForm.parameterFormId).
                equalsIgnoreCase("01-03-02")){
            hasil = MKonfigurasiAccHandler.doProcess(
                    (request.getParameter("xmldata")==null?null:request.getParameter("xmldata")),
                    request.getParameter(URLParameterFormatForm.parameterFormMode),
                    modereadtable,dBConnection
            );
            /*
            MH_KonfigurasiAcc mh_KonfigurasiAcc = new MH_KonfigurasiAcc();
            mh_KonfigurasiAcc.KODEACC = request.getParameter("kodeacc")==null?null:request.getParameter("kodeacc");
            mh_KonfigurasiAcc.KODEFORM = request.getParameter("kodeform")==null?null:request.getParameter("kodeform");
            mh_KonfigurasiAcc.NAMAFORM = request.getParameter("namaform")==null?null:request.getParameter("namaform");
            mh_KonfigurasiAcc.PRIORITAS = request.getParameter("prioritas")==null?null:Integer.parseInt(request.getParameter("prioritas"));
            mh_KonfigurasiAcc.KETERANGAN = request.getParameter("keterangan")==null?"":request.getParameter("keterangan");
            hasil = MKonfigurasiAccHandler.doProcess(request.getParameter(URLParameterFormatForm.parameterFormMode),mh_KonfigurasiAcc,modereadtable,dBConnection);
          */
             //  return getMenu(request.getParameter(URLParameterFormatForm.parameterFormId),
          //          context);
        }else
        if(request.getParameter(URLParameterFormatForm.parameterFormId).
                equalsIgnoreCase("01-03-13")){
            M_History m_History = new M_History();
            m_History.KODEFORM = request.getParameter("kodeform")==null?null:request.getParameter("kodeform");
            m_History.TABEL = request.getParameter("tabel")==null?null:request.getParameter("tabel");
            m_History.PK1 = request.getParameter("pk1")==null?null:request.getParameter("pk1");
            m_History.PK1VALUE = request.getParameter("pk1value")==null?null:request.getParameter("pk1value");
            m_History.PK2 = request.getParameter("pk2")==null?"":request.getParameter("pk2");
            m_History.PK2VALUE = request.getParameter("pk2value")==null?"":request.getParameter("pk2value");
            m_History.PK3 = request.getParameter("pk3")==null?"":request.getParameter("pk3");
            m_History.PK3VALUE = request.getParameter("pk3value")==null?"":request.getParameter("pk3value");
            m_History.KETERANGAN = request.getParameter("keterangan")==null?"":request.getParameter("keterangan");
            m_History.CREATEDBY = request.getParameter("createdby")==null?"":request.getParameter("createdby");
            m_History.CREATEDDATE = request.getParameter("createddate") == null ? "" : request.getParameter("createddate");
            m_History.UPDATEDBY = request.getParameter("updatedby")==null?"":request.getParameter("updatedby");
            m_History.UPDATEDDATE = request.getParameter("updateddate") == null ? "" :request.getParameter("updateddate");
            m_History.DELETEDBY = request.getParameter("deletedby")==null?"":request.getParameter("deletedby");
            m_History.DELETEDDATE = request.getParameter("deleteddate") == null ? "" : request.getParameter("deleteddate");
            hasil = MHistoryHandler.doProcess(request.getParameter(URLParameterFormatForm.parameterFormMode),m_History,modereadtable,dBConnection);
          //  return getMenu(request.getParameter(URLParameterFormatForm.parameterFormId),
          //          context);
        }else
        if(request.getParameter(URLParameterFormatForm.parameterFormId).
                equalsIgnoreCase("01-01-01")){
            hasil = MUserHandler.doProcess(
                    (request.getParameter("xmldata")==null?null:request.getParameter("xmldata")),
                    request.getParameter(URLParameterFormatForm.parameterFormMode),
                    modereadtable,dBConnection
            );
            /*
            M_User m_User = new M_User();
            m_User.USERID = request.getParameter("userid")==null?null:request.getParameter("userid");
            m_User.NAMABP = request.getParameter("namabp")==null?null:request.getParameter("namabp");
            m_User.KODEBP = request.getParameter("kodebp")==null?null:request.getParameter("kodebp");
            m_User.NIK = request.getParameter("nik")==null?null:request.getParameter("nik");
            m_User.PASS = request.getParameter("pass")==null?"":request.getParameter("pass");
            m_User.COSTCENTER = request.getParameter("costcenter")==null?"":request.getParameter("costcenter");
            m_User.AKTIF = request.getParameter("aktif")==null?"":request.getParameter("aktif");
            hasil = MUserHandler.doProcess(request.getParameter(URLParameterFormatForm.parameterFormMode),m_User,modereadtable,dBConnection);
            */
          //  return getMenu(request.getParameter(URLParameterFormatForm.parameterFormId),
          //          context);
        }else
        if(request.getParameter(URLParameterFormatForm.parameterFormId).
                equalsIgnoreCase("01-03-04")){
            hasil = MPembebananHandler.doProcess(
                    (request.getParameter("xmldata")==null?null:request.getParameter("xmldata")),
                    request.getParameter(URLParameterFormatForm.parameterFormMode),
                    modereadtable,dBConnection
            );
            /*M_Pembebanan m_Pembebanan = new M_Pembebanan();
            m_Pembebanan.KODEPEMBEBANAN = request.getParameter("kodepembebanan")==null?null:request.getParameter("kodepembebanan");
            m_Pembebanan.NAMAPEMBEBANAN = request.getParameter("namapembebanan")==null?null:request.getParameter("namapembebanan");

            hasil = MPembebananHandler.doProcess(request.getParameter(URLParameterFormatForm.parameterFormMode),m_Pembebanan,modereadtable);
          */
          //  return getMenu(request.getParameter(URLParameterFormatForm.parameterFormId),
          //          context);
        }
        else
        if(request.getParameter(URLParameterFormatForm.parameterFormId).
                equalsIgnoreCase("01-03-05")){
            hasil = MItemHandler.doProcess(
                    (request.getParameter("xmldata")==null?null:request.getParameter("xmldata")),
                    request.getParameter(URLParameterFormatForm.parameterFormMode),
                    modereadtable,dBConnection
            );
            /*
            M_Item m_Item = new M_Item();
            m_Item.KODEITEM = request.getParameter("kodeitem")==null?null:request.getParameter("kodeitem");
            m_Item.KODEJENIS = request.getParameter("kodejenis")==null?null:request.getParameter("kodejenis");
            m_Item.NAMAJENIS = request.getParameter("namajenis")==null?null:request.getParameter("namajenis");
            m_Item.NAMAITEM = request.getParameter("namaitem")==null?null:request.getParameter("namaitem");
            m_Item.DURASIMINSEWA = request.getParameter("durasiminsewa")==null?null:
                        (request.getParameter("durasiminsewa").isEmpty()?null:Integer.parseInt(request.getParameter("durasiminsewa")));
            m_Item.KAPASITAS = request.getParameter("kapasitas")==null?null:
                        (request.getParameter("kapasitas").isEmpty()?null:Integer.parseInt(request.getParameter("kapasitas")));
            m_Item.KETERANGAN = request.getParameter("keterangan")==null?"":request.getParameter("keterangan");
            m_Item.AKTIF = request.getParameter("aktif")==null?"":request.getParameter("aktif");
            m_Item.STATUSAVAILABILITY = request.getParameter("statusavailability")==null?"":request.getParameter("statusavailability");
            m_Item.PRICECHANGEABLE = request.getParameter("pricechangeable")==null?"":request.getParameter("pricechangeable");

            hasil = MItemHandler.doProcess(request.getParameter(URLParameterFormatForm.parameterFormMode),m_Item,modereadtable);
          */
             //  return getMenu(request.getParameter(URLParameterFormatForm.parameterFormId),
          //          context);
        }
        else
        if(request.getParameter(URLParameterFormatForm.parameterFormId).
                equalsIgnoreCase("01-02-02")){
            hasil = MParameterHandler.doProcess(
                    (request.getParameter("xmldata")==null?null:request.getParameter("xmldata")),
                    request.getParameter(URLParameterFormatForm.parameterFormMode),
                    modereadtable,dBConnection
            );
            /*
            M_Parameter m_Parameter = new M_Parameter();
            m_Parameter.PARNAME = request.getParameter("parname")==null?null:request.getParameter("parname");
            m_Parameter.PARVALUE = request.getParameter("parvalue")==null?null:request.getParameter("parvalue");
            m_Parameter.PARTYPE = request.getParameter("partype")==null?null:request.getParameter("partype");

            hasil = MParameterHandler.doProcess(request.getParameter(URLParameterFormatForm.parameterFormMode),m_Parameter,modereadtable);
            */
          //  return getMenu(request.getParameter(URLParameterFormatForm.parameterFormId),
          //          context);
        }
        else if(request.getParameter(URLParameterFormatForm.parameterFormId).
                equalsIgnoreCase("01-04-01")){        
            hasil = TPengajuanHandler.doProcess(
                    (request.getParameter("xmldata")==null?null:request.getParameter("xmldata")),
                    request.getParameter(URLParameterFormatForm.parameterFormMode),/*h_Pengajuan,*/modereadtable,dBConnection);
        }
        else if(request.getParameter(URLParameterFormatForm.parameterFormId).
                equalsIgnoreCase("01-04-02")){
            hasil = TPerubahanHandler.doProcess(
                    (request.getParameter("xmldata")==null?null:request.getParameter("xmldata")),
                    request.getParameter(URLParameterFormatForm.parameterFormMode),/*h_Perubahan,*/modereadtable,dBConnection);
        } 
        else if(request.getParameter(URLParameterFormatForm.parameterFormId).
                equalsIgnoreCase("01-04-03")){
            hasil = TRealisasiHandler.doProcess(
                    (request.getParameter("xmldata")==null?null:request.getParameter("xmldata")),
                    request.getParameter(URLParameterFormatForm.parameterFormMode),/*h_Pengajuan,*/modereadtable,dBConnection);
        }
        return hasil;
    }
}
