/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.formatdata.url.directaccess_tabledb;

/**
 *
 * @author Eddy
 */
public class URLParameterFormatTableOperation {
    /**
     * mengembalikan string "tableoperation"
     */
    public static final String parameterTableOperation = "tableoperation";
     /**
     * mengembalikan string "tablename"
     */
    public static final String parameterTableName = "tablename";

    /**
     * generete parameter "tableoperation=tableoperationnya"
     */
    public static String generateParameterTableOperation_READ_ALL_ROW() {
        return parameterTableOperation+"="+ReadMode.READ_ALL_ROW.toString();
    }
    public static String generateParameterTableOperation_READ_SINGLE_ROW() {
        return parameterTableOperation+"="+ReadMode.READ_CUSTOM_ROW.toString();
    }
}
