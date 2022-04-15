/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.controller.workspace;

import com.wonokoyo.model.DBConnection;
import com.wonokoyo.model.MMenuDb;
import com.wonokoyo.model.fle.FileOperation;
import com.wonokoyo.model.formatdata.url.form.URLParameterFormatForm;
import com.wonokoyo.model.formatdata.url.loginaccount.URLParameterFormatLogin;
import com.wonokoyo.model.formatdata.url.treemenu.URLParameterFormatTreeMenu;
import com.wonokoyo.model.formatdata.url.treemenu.treeMenuMode;
import com.wonokoyo.model.strukturdata.M_Jenis;
import com.wonokoyo.model.strukturdata.M_Menu;
import com.wonokoyo.model.strukturdata.M_SettingAcc;
import com.wonokoyo.model.strukturdata.M_User;
import com.wonokoyo.model.strukturdata.MH_KonfigurasiAcc;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Bayu
 */
public class TreeMenuHandler {

    public static String filterMenu(HttpServletRequest request,HttpServletResponse response,ServletContext context,DBConnection dBConnection){
        /*if(request.getParameter(URLParameterFormatTreeMenu.parameterTreeMode).
                equalsIgnoreCase(treeMenuMode.OPEN_FORM.toString())){
            response.setContentType("text/javascript;charset=UTF-8");
            return getMenu(request.getParameter(URLParameterFormatForm.parameterFormId),
                    context);
        }else*/ if(request.getParameter(URLParameterFormatTreeMenu.parameterTreeMode).
                equalsIgnoreCase(treeMenuMode.VIEW_TREEMENU.toString())){
            response.setContentType("text/xml;charset=UTF-8");
            MMenuDb mmenudb = new MMenuDb(dBConnection);
            HttpSession session =  request.getSession(true);
            M_User userdetail = (M_User)session.getAttribute("userdetil");
            String hasil = mmenudb.getAllMenuToXMLforTreepanel(userdetail.USERID);
            //mmenudb.closeConn();
            return hasil;
        }
        return "";
    }
/*
    private static String getMenu(String menuid,ServletContext context){
        String textHtml="";
        MMenuDb mmenudb = new MMenuDb();
        M_Menu mmenu = new M_Menu();
        mmenu=mmenudb.selectOneRow(menuid);
        
        
        textHtml = FileOperation.readFile(mmenu.URL,context);
        
        mmenudb.closeConn();
        return textHtml;
    }*/
}
