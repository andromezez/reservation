/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.DB_Procedure;
import com.wonokoyo.model.DCostCenterPengajuanDb;
import com.wonokoyo.model.DCostCenterRealisasiDb;
import com.wonokoyo.model.DTglPengajuanDb;
import com.wonokoyo.model.DTglRealisasiDb;
import com.wonokoyo.model.HPengajuanDb;
import com.wonokoyo.model.HPerubahanDb;
import com.wonokoyo.model.HRealisasiDb;
import com.wonokoyo.model.JadwalSewaruangDb;
import com.wonokoyo.model.MAccDb;
import com.wonokoyo.model.MDKonfigurasiAccDb;
import com.wonokoyo.model.MDPembebananDb;
import com.wonokoyo.model.MDTarifItemDb;
import com.wonokoyo.model.MItemDb;
import com.wonokoyo.model.MJenisDb;
import com.wonokoyo.model.MHKonfigurasiAccDb;
import com.wonokoyo.model.MHistoryDb;
import com.wonokoyo.model.MMenuDb;
import com.wonokoyo.model.MParameterDb;
import com.wonokoyo.model.MPembebananDb;
import com.wonokoyo.model.MPermissionDb;
import com.wonokoyo.model.MSettingAccDb;
import com.wonokoyo.model.MUserDb;
import com.wonokoyo.model.SD_ItemPengajuanDb;
import com.wonokoyo.model.SD_ItemRealisasiDb;
import com.wonokoyo.model.Temp_sd_itempengajuanDb;
import com.wonokoyo.model.VMFormNeedAccDb;
import com.wonokoyo.model.VMHRIS_bpartnerlengkapDb;
import com.wonokoyo.model.VMHRIS_costcenterDb;
import com.wonokoyo.model.VM_MUser_KonfigurasiaccDb;
import com.wonokoyo.model.VM_hariliburDb;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.URLParameterFormatTableOperation;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.UpdateMode;
import com.wonokoyo.model.strukturdata.D_CostCenterPengajuan;
import com.wonokoyo.model.strukturdata.D_CostCenterRealisasi;
import com.wonokoyo.model.strukturdata.M_Acc;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Eddy
 */
public class DB_DirectRequest {
    public static String doProcess(HttpServletRequest request,HttpServletResponse response,DBConnection dBConnection){
        String tableName = request.getParameter(URLParameterFormatTableOperation.parameterTableName)==null?"":request.getParameter(URLParameterFormatTableOperation.parameterTableName);
        String tableoperationmode = request.getParameter(URLParameterFormatTableOperation.parameterTableOperation)==null?"":request.getParameter(URLParameterFormatTableOperation.parameterTableOperation);
        response.setContentType("text/xml;charset=UTF-8");

        String hasil = "";
        if(tableName.equalsIgnoreCase("m_jenis")){
            MJenisDb mJenisDb = new MJenisDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mJenisDb.selectAllRowToGridExtJsFormat_MJenis();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
            //mJenisDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("m_menu")){
            MMenuDb mMenuDb = new MMenuDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mMenuDb.selectAllRowToGridExtJsFormat_MMenu();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = mMenuDb.selectCustomRow(request.getParameter("sql"));
            }
            //mMenuDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("m_parameter")){
            MParameterDb mParameterDb = new MParameterDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mParameterDb.selectAllRowToGridExtJsFormat_MParameter();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = mParameterDb.selectCustomRow(request.getParameter("sql"));    
            }
            //mParameterDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("m_item")){
            MItemDb mItemDb = new MItemDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                //hasil = mItemDb.selectAllRowToGridExtJsFormat_MItem();
                hasil = mItemDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = mItemDb.selectCustomRow(request.getParameter("sql"));
            }
            //mItemDb.closeConn();
        }else        
        if(tableName.equalsIgnoreCase("m_pembebanan")){
            MPembebananDb mPembebananDb = new MPembebananDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mPembebananDb.selectAllRowToGridExtJsFormat_MPembebanan();     
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
            //mPembebananDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("m_history")){
            MHistoryDb mHistoryDb = new MHistoryDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mHistoryDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
            //mHistoryDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("m_settingacc")){
            MSettingAccDb mSettingAccDb = new MSettingAccDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mSettingAccDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = mSettingAccDb.selectCustomRow(request.getParameter("sql"));
            }
            //mSettingAccDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("m_user")){
            MUserDb mUserDb = new MUserDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mUserDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = mUserDb.selectCustomRow(request.getParameter("sql"));
            }else if(tableoperationmode.equals(UpdateMode.UPDATE_ONE_ROW.toString())){
                dBConnection.startTransaction();  
                hasil = mUserDb.updateOneRow(request.getParameter("userid"), request.getParameter("kodebp"), 
                        request.getParameter("pass"), request.getParameter("aktif"));
                dBConnection.endTransaction();
            }
            //mUserDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("vm_bpartnerLengkap")){
            VMHRIS_bpartnerlengkapDb vMBPartnerLengkapDb = new VMHRIS_bpartnerlengkapDb(dBConnection);
        
                if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                    hasil = vMBPartnerLengkapDb.selectAllRow();
                }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                    hasil = vMBPartnerLengkapDb.selectCustomRow(request.getParameter("sql"));
                }
            
            //vMBPartnerLengkapDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("t_pengajuan")){
            HPengajuanDb pengajuan = new HPengajuanDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = pengajuan.selectAllRowToGridExtJsFormat_HPengajuan();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = pengajuan.selectCustomRow(request.getParameter("sql"));
            }
            //vMBPartnerLengkapDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("t_realisasi")){
            HRealisasiDb realisasi = new HRealisasiDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = realisasi.selectAllRowToGridExtJsFormat_HRealisasi();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = realisasi.selectCustomRow(request.getParameter("sql"));
            }
            //vMBPartnerLengkapDb.closeConn();
        }else     
        if(tableName.equalsIgnoreCase("vm_formNeedAcc")){
            VMFormNeedAccDb vMFormNeedAccDb = new VMFormNeedAccDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = vMFormNeedAccDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                //hasil = vMFormNeedAccDb.selectAllRowToGridExtJsFormat_VMFormNeedAcc();
            }
            //vMFormNeedAccDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("vm_hris_costcenter")){
            VMHRIS_costcenterDb vMHRIS_costcenterDb = new VMHRIS_costcenterDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = vMHRIS_costcenterDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = vMHRIS_costcenterDb.selectCustomRow(request.getParameter("sql"));
            }
            //vMHRIS_costcenterDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("d_costcenterpengajuan")){
            DCostCenterPengajuanDb dCostCenterPengajuanDb = new DCostCenterPengajuanDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = dCostCenterPengajuanDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = dCostCenterPengajuanDb.selectCustomRow(request.getParameter("sql"));
            }else if(tableoperationmode.equals(UpdateMode.UPDATE_ONE_ROW.toString())){
                response.setContentType("text/html;charset=UTF-8");
                D_CostCenterPengajuan costCenterPengajuan = new D_CostCenterPengajuan();
                costCenterPengajuan.APPROVAL=request.getParameter("approval");
                costCenterPengajuan.KODECOSTCENTER=request.getParameter("kodecostcenter");
                costCenterPengajuan.KODEPENGAJUAN=request.getParameter("kodeheader");
                costCenterPengajuan.NOMINAL=Integer.parseInt(request.getParameter("nominal"));
                costCenterPengajuan.PRESENTASE=Double.parseDouble(request.getParameter("presentase"));
                costCenterPengajuan.USERID=request.getParameter("userid");   
                dBConnection.startTransaction(); 
                hasil = dCostCenterPengajuanDb.updateOneRow(costCenterPengajuan);
                dBConnection.endTransaction();                 
            }
            //dCostCenterPengajuanDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("d_tglpengajuan")){
            DTglPengajuanDb dTglPengajuanDb = new DTglPengajuanDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = dTglPengajuanDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = dTglPengajuanDb.selectCustomRow(request.getParameter("sql"));
            }
            //dTglPengajuanDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("sd_itempengajuan")){
            SD_ItemPengajuanDb sd_ItemPengajuanDb = new SD_ItemPengajuanDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = sd_ItemPengajuanDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = sd_ItemPengajuanDb.selectCustomRow(request.getParameter("sql"));
            }
            //sd_ItemPengajuanDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("md_pembebanan")){
            MDPembebananDb md_PembebananDb = new MDPembebananDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = md_PembebananDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = md_PembebananDb.selectCustomRow(request.getParameter("sql"));
            }
            //md_PembebananDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("mh_konfigurasiacc")){
            MHKonfigurasiAccDb mHKonfigurasiAccDb = new MHKonfigurasiAccDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mHKonfigurasiAccDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = mHKonfigurasiAccDb.selectCustomRow(request.getParameter("sql"));
            }
            //mHKonfigurasiAccDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("md_konfigurasiacc")){
            MDKonfigurasiAccDb mDKonfigurasiAccDb = new MDKonfigurasiAccDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mDKonfigurasiAccDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = mDKonfigurasiAccDb.selectCustomRow(request.getParameter("sql"));
            }
            //mDKonfigurasiAccDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("m_acc")){
            MAccDb mAccDb = new MAccDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mAccDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = mAccDb.selectCustomRow(request.getParameter("sql"));
            }else if(tableoperationmode.equals(UpdateMode.UPDATE_ONE_ROW.toString())){
                response.setContentType("text/html;charset=UTF-8");
                M_Acc m_Acc = new M_Acc();
                m_Acc.KODEFORM = request.getParameter("kodeform");
                m_Acc.KODEJABATAN = request.getParameter("kodejabatan");
                m_Acc.KODETRANSAKSI = request.getParameter("kodetransaksi");
                m_Acc.PRIORITAS = Integer.parseInt(request.getParameter("prioritas"));
                m_Acc.STATUSACC = Integer.parseInt(request.getParameter("statusacc"));
                m_Acc.TGLACC = request.getParameter("tglacc");
                m_Acc.USERID = request.getParameter("userid");   
                dBConnection.startTransaction(); 
                hasil = mAccDb.updateOneRow(m_Acc);
                dBConnection.endTransaction(); 
            }
            //mAccDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("vm_muser_konfigurasiacc")){
            VM_MUser_KonfigurasiaccDb table = new VM_MUser_KonfigurasiaccDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = table.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = table.selectCustomRow(request.getParameter("sql"));
            }
            //table.closeConn();
        }else
        if(tableName.equalsIgnoreCase("m_permission")){
            MPermissionDb mPermissionDb = new MPermissionDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mPermissionDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = mPermissionDb.selectCustomRow(request.getParameter("sql"));
            }
            //mPermissionDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("md_tarifitem")){
            MDTarifItemDb mTarifItemDb = new MDTarifItemDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = mTarifItemDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = mTarifItemDb.selectCustomRow(request.getParameter("sql"));
            }
            //mTarifItemDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("d_tglrealisasi")){
            DTglRealisasiDb dtglrealisasi = new DTglRealisasiDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                //hasil = mTarifItemDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = dtglrealisasi.selectCustomRow(request.getParameter("sql"));
            }
            //mTarifItemDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("sd_itemrealisasi")){
            SD_ItemRealisasiDb sd_itemrealisasi = new SD_ItemRealisasiDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                //hasil = mTarifItemDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = sd_itemrealisasi.selectCustomRow(request.getParameter("sql"));
            }
            //mTarifItemDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("d_costcenterrealisasi")){
            DCostCenterRealisasiDb dcostcenterrealisasi = new DCostCenterRealisasiDb(dBConnection);
            
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                //hasil = mTarifItemDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = dcostcenterrealisasi.selectCustomRow(request.getParameter("sql"));
            }else if(tableoperationmode.equals(UpdateMode.UPDATE_ONE_ROW.toString())){
                response.setContentType("text/html;charset=UTF-8");
                D_CostCenterRealisasi costCenterRealisasi = new D_CostCenterRealisasi();
                costCenterRealisasi.APPROVAL=request.getParameter("approval");
                costCenterRealisasi.KODECOSTCENTER=request.getParameter("kodecostcenter");
                costCenterRealisasi.KODEREALISASI=request.getParameter("kodeheader");
                costCenterRealisasi.NOMINAL=Integer.parseInt(request.getParameter("nominal"));
                costCenterRealisasi.PRESENTASE=Integer.parseInt(request.getParameter("presentase"));
                costCenterRealisasi.USERID=request.getParameter("userid");             
                dBConnection.startTransaction(); 
                hasil = dcostcenterrealisasi.updateOneRow(costCenterRealisasi);
                dBConnection.endTransaction();                 
            }
            //mTarifItemDb.closeConn();
        }else
        if(tableName.equalsIgnoreCase("jadwalsewaruang")){
            dBConnection.startTransaction();            
            JadwalSewaruangDb jadwalSewaruangDb = new JadwalSewaruangDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){                
                hasil = jadwalSewaruangDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = jadwalSewaruangDb.selectCustomRow(request.getParameter("sql"));
            }         
            dBConnection.endTransaction();
        }else
        if(tableName.equalsIgnoreCase("temp_sd_itempengajuan")){
            dBConnection.startTransaction();            
            Temp_sd_itempengajuanDb temp_sd_itempengajuanDb = new Temp_sd_itempengajuanDb(dBConnection);
            DB_Procedure procedure = new DB_Procedure(dBConnection);            
            procedure.generate_detilJamSewaItem(request.getParameter("kodebp"), request.getParameter("kodeitem"), 
                    request.getParameter("jamawal"), request.getParameter("jamakhir"), 
                    request.getParameter("namatarif"), Integer.parseInt(request.getParameter("jumlahawal")),Integer.parseInt(request.getParameter("jumlahakhir")), request.getParameter("IE")
                    ,request.getParameter("jenistransaksi"));
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){                
                hasil = temp_sd_itempengajuanDb.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = temp_sd_itempengajuanDb.selectCustomRow(request.getParameter("sql"));
            }         
            dBConnection.endTransaction();
        }else
        if(tableName.equalsIgnoreCase("vm_harilibur")){
            VM_hariliburDb table = new VM_hariliburDb(dBConnection);
            if(tableoperationmode.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = table.selectAllRow();
            }else if(tableoperationmode.equals(ReadMode.READ_CUSTOM_ROW.toString())){
                hasil = table.selectCustomRow(request.getParameter("sql"));
            }
            //table.closeConn();
        }
        return hasil;
    }
}
