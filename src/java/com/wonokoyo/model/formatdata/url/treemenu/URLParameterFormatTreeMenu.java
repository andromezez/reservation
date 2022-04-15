/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.formatdata.url.treemenu;

/**
 *
 * @author Bayu
 */
public class URLParameterFormatTreeMenu {
    /**
     * mengembalikan string "treemode"
     */
    public static final String parameterTreeMode = "treemode";

    /**
     * generete parameter "treemode=OPEN_FORM" <br/>
     * nilai modenya diambil dari variabel enumerasi treeMenuMode.OPEN_FORM
     */
    public static String generateParameterTreeModeOPEN_FORM() {
        return parameterTreeMode+"="+treeMenuMode.OPEN_FORM.toString();
    }

    /**
     * generete parameter "treemode=VIEW_TREEMENU" <br/>
     * nilai modenya diambil dari variabel enumerasi treeMenuMode.VIEW_TREEMENU
     */
    public static String generateParameterTreeModeVIEW_TREEMENU() {
        return parameterTreeMode+"="+treeMenuMode.VIEW_TREEMENU.toString();
    }
}
