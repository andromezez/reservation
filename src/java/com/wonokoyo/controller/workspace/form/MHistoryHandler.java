/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace.form;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.MHistoryDb;
import com.wonokoyo.model.formatdata.url.form.FormMode;
import com.wonokoyo.model.formatdata.url.directaccess_tabledb.ReadMode;
import com.wonokoyo.model.strukturdata.M_History;

/**
 *
 * @author Indra
 */
public class MHistoryHandler {
    public static String doProcess(String mode,M_History m_history,String modereadtable,DBConnection dBConnection){
        MHistoryDb MHistoryDb = new MHistoryDb(dBConnection);
        String hasil ="";
        if (FormMode.READ.toString().equalsIgnoreCase(mode)){
            if(modereadtable.equals(ReadMode.READ_ALL_ROW.toString())){
                hasil = MHistoryDb.selectAllRow();
            }else if(modereadtable.equals(ReadMode.READ_CUSTOM_ROW.toString())){

            }
        }else if (FormMode.INSERT.toString().equalsIgnoreCase(mode)){
            dBConnection.startTransaction();
            hasil = MHistoryDb.insertOneRow(m_history.KODEFORM,m_history.TABEL, m_history.PK1,m_history.PK1VALUE,m_history.PK2,
                    m_history.PK2VALUE,m_history.PK3,m_history.PK3VALUE, m_history.KETERANGAN,m_history.CREATEDBY,m_history.CREATEDDATE,
                    m_history.UPDATEDBY,m_history.UPDATEDDATE, m_history.DELETEDBY,m_history.DELETEDDATE);
            dBConnection.endTransaction();
        }else if (FormMode.UPDATE.toString().equalsIgnoreCase(mode)){
          //  hasil = MCobaDb.updateOneRow(m_Coba.KODEFORM,m_Coba.USERID, m_Coba.PVIEW,m_Coba.PADD,m_Coba.PUPDATE,m_Coba.PDELETE);
        }else if (FormMode.DELETE.toString().equalsIgnoreCase(mode)){
          //  hasil = MCobaDb.deleteOneRow(m_Coba.KODEFORM);
        }else{

        }
        //MHistoryDb.closeConn();
        return hasil;
    }
}
