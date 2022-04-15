/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.formatdata.url.loginaccount;

/**
 *
 * @author Bayu
 */
public class URLParameterFormatLogin {
    /**
     * mengembalikan string "userid"
     */
    public static final String parameterUserId = "userid";
    public static final String parameterPass = "pass";

    /**
     *  generete parameter "userid=useridnya"
     */
    public static String generateParameterUserId(String userid) {
        return parameterUserId+"="+userid;
    }   
}
