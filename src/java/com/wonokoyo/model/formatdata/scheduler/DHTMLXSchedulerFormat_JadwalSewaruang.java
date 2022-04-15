/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model.formatdata.scheduler;

/**
 *
 * @author king peter
 */
/**
 * Contoh bentuk XML nya : <br/>
 *       {@code <?xml version="1.0" encoding="ISO-8859-1"?> } <br/>
 *       {@code <data>
 *           &nbsp;&nbsp;&nbsp;{@code <event id='1' start_date='2010-12-19 18:00' end_date='2010-12-19 20:15' text='coba 1' readonly='true' kode_item='0001' /> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <event id='2' start_date='2010-12-19 18:00' end_date='2010-12-19 20:15' text='coba 2' readonly='true' kode_item='0002' /> }<br/>
 *       {@code </data> }
 */
public class DHTMLXSchedulerFormat_JadwalSewaruang {
    /**
     * berisi elemen: <br/>
     *     {@code <?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><data> }
     */
    public static final String rootElAwal = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<data>";

    /**
     * berisi elemen: {@code </data> }
     */
    public static final String rootElAkhir = "</data>";

    /**
     * berisi elemen : <br/>
     * {@code <event id='#id' start_date='#start_date' end_date='#end_date' text='#text' readonly='#readonly' kode_item='#kode_item' />}
     */
    public static final String eventEl = "<event id='#id' start_date='#start_date' end_date='#end_date' text='#text' readonly='#readonly' kode_item='#kode_item' />";
    /**
     * berisi symbol : <br/>
     * {@code #id }
     */
    public static final String idSymbol = "#id";
    /**
     * berisi symbol : <br/>
     * {@code #start_date }
     */
    public static final String start_dateSymbol = "#start_date";
    /**
     * berisi symbol : <br/>
     * {@code #end_date }
     */
    public static final String end_dateSymbol = "#end_date";
    /**
     * berisi symbol : <br/>
     * {@code #text }
     */
    public static final String textSymbol = "#text";
    /**
     * berisi symbol : <br/>
     * {@code #readonly }
     */
    public static final String readonlySymbol = "#readonly";
    /**
     * berisi symbol : <br/>
     * {@code #kode_item }
     */
    public static final String kode_itemSymbol = "#kode_item";
        
}
