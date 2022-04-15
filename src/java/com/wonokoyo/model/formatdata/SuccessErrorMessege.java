/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.formatdata;

/**
 *
 * @author Eddy
 */
public class SuccessErrorMessege {
    public static String generateMessege(boolean success, String successMsg, String errorMsg){
        String hasil = "{" +
                  "success: "+success+"," +
                  "errors: {"+
                  "},"+
                    "successmsg: \""+ successMsg +"\"" + "," +
                    "errormsg: \""+ errorMsg +"\""+
                  "}";
        return hasil;
    }
}
