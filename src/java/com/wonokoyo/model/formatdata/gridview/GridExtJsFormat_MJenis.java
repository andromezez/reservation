/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.formatdata.gridview;

/**
 *
 * @author Bayu
 *
 */

/**
 *Contoh bentuk XML nya : <br/>
        {@code <?xml version="1.0" encoding="ISO-8859-1"?> } <br/>
        {@code <dataset> }<br/>
            &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodejenis></kodejenis> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namajenis></namajenis> }<br/>
            &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
            &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodejenis></kodejenis> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namajenis></namajenis> }<br/>
            &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
        {@code </dataset> }
 *
 */
public class GridExtJsFormat_MJenis {

    /**
     * berisi elemen: <br/>
     *     {@code <?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><dataset> }
     */
    public static final String rootElAwal = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<dataset>";

    /**
     * berisi elemen: {@code </dataset> }
     */
    public static final String rootElAkhir = "</dataset>";

    /**
     * berisi elemen : <br/>
     * {@code <row>}
     */
    public static final String rowElAwal = "<row>";

    /**
     * berisi elemen: {@code </row> }
     */
    public static final String rowElAkhir = "</row>";

    /**
     * berisi elemen : <br/>
     * {@code <kodejenis>}     
     */
    public static final String kodejenisElAwal = "<kodejenis>";

    /**
     * berisi elemen: {@code </kodejenis> }
     */
    public static final String kodejenisElAkhir = "</kodejenis>";

    /**
     * berisi elemen : <br/>
     * {@code <namajenis> }     
     */
    public static final String namajenisElAwal = "<namajenis>";

    /**
     * berisi elemen: {@code </namajenis>}
     */
    public static final String namajenisElAkhir = "</namajenis>";
}
