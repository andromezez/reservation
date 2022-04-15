/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.formatdata.gridview;

/**
 *
 * @author Bayu
 */

/**
 *Contoh bentuk XML nya : <br/>
        {@code <?xml version="1.0" encoding="ISO-8859-1"?> } <br/>
        {@code <dataset> }<br/>
            &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeacc></kodeacc> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <userid></userid> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodebp></kodebp> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namabp></namabp> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodejabatan></kodejabatan> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namajabatan></namajabatan> }<br/>
            &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
            &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeacc></kodeacc> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <userid></userid> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodebp></kodebp> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namabp></namabp> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodejabatan></kodejabatan> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namajabatan></namajabatan> }<br/>
            &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
        {@code </dataset> }
 *
 */


public class GridExtJsFormat_MDKonfigurasiAcc {
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
     * {@code <kodeacc>}
     */
    public static final String kodeaccElAwal = "<kodeacc>";

    /**
     * berisi elemen: {@code </kodeacc> }
     */
    public static final String kodeaccElAkhir = "</kodeacc>";

    /**
     * berisi elemen : <br/>
     * {@code <userid> }
     */
    public static final String useridElAwal = "<userid>";

    /**
     * berisi elemen: {@code </userid>}
     */
    public static final String useridElAkhir = "</userid>";

    /**
     * berisi elemen : <br/>
     * {@code <kodebp>}
     */
    public static final String kodebpElAwal = "<kodebp>";

    /**
     * berisi elemen: {@code </kodebp> }
     */
    public static final String kodebpElAkhir = "</kodebp>";

    /**
     * berisi elemen : <br/>
     * {@code <namabp>}
     */
    public static final String namabpElAwal = "<namabp>";

    /**
     * berisi elemen: {@code </namabp> }
     */
    public static final String namabpElAkhir = "</namabp>";

    /**
     * berisi elemen : <br/>
     * {@code <kodejabatan> }
     */
    public static final String kodejabatanElAwal = "<kodejabatan>";

    /**
     * berisi elemen: {@code </kodejabatan>}
     */
    public static final String kodejabatanElAkhir = "</kodejabatan>";

    /**
     * berisi elemen : <br/>
     * {@code <namajabatan>}
     */
    public static final String namajabatanElAwal = "<namajabatan>";

    /**
     * berisi elemen: {@code </namajabatan> }
     */
    public static final String namajabatanElAkhir = "</namajabatan>";

     /**
     * berisi elemen : <br/>
     * {@code <defaultacc>}
     */
    public static final String defaultaccElAwal = "<defaultacc>";

    /**
     * berisi elemen: {@code </defaultacc> }
     */
    public static final String defaultaccElAkhir = "</defaultacc>";
    
    /**
     * berisi elemen : <br/>
     * {@code <keterangan>}
     */
    public static final String keteranganElAwal = "<keterangan>";

    /**
     * berisi elemen: {@code </keterangan> }
     */
    public static final String keteranganElAkhir = "</keterangan>";
}
