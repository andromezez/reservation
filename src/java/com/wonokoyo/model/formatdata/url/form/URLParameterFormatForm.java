/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.formatdata.url.form;

import com.wonokoyo.model.formatdata.url.form.FormMode;

/**
 *
 * @author bayu
 */
public class URLParameterFormatForm{

    /**
     * mengembalikan string "formid"
     */
    public static final String parameterFormId = "formid";

    /**
     * mengembalikan string "formmode"
     */
    public static final String parameterFormMode = "formmode";

    /**
     * generete parameter "formid=formiIdnya"
     */
    public static String generateParameterFormId(String formId) {
        return parameterFormId+"="+formId;
    }

    /**
     * generete parameter "formmode=INSERT" <br/>
     * nilai modenya diambil dari variabel enumerasi FormMode.INSERT
     */
    public static String generateParameterFormModeInsert() {
        return parameterFormMode+"="+FormMode.INSERT.toString();
    }

    /**
     * generete parameter "formmode=DELETE" <br/>
     * nilai modenya diambil dari variabel enumerasi FormMode.DELETE
     */
    public static String generateParameterFormModeDelete() {
        return parameterFormMode + "=" + FormMode.DELETE.toString();
    }

    /**
     * generete parameter "formmode=UPDATE" <br/>
     * nilai modenya diambil dari variabel enumerasi FormMode.UPDATE
     */
    public static String generateParameterFormModeUpdate() {
        return parameterFormMode+"="+FormMode.UPDATE.toString();
    }

    /**
     * generete parameter "formmode=READ" <br/>
     * nilai modenya diambil dari variabel enumerasi FormMode.READ
     */
    public static String generateParameterFormModeRead() {
        return parameterFormMode+"="+FormMode.READ.toString();
    }

    

    
}
