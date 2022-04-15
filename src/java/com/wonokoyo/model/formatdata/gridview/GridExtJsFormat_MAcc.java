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
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <userid></userid> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeform></kodeform> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodetransaksi></kodetransaksi> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <tglacc></tglacc> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <prioritas></prioritas> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodejabatan></kodejabatan> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namajabatan></namajabatan> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namabp></namabp> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <keterangan></keterangan> }<br/>
            &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
            &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <userid></userid> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeform></kodeform> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodetransaksi></kodetransaksi> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <tglacc></tglacc> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <prioritas></prioritas> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodejabatan></kodejabatan> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namajabatan></namajabatan> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namabp></namabp> }<br/> 
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <keterangan></keterangan> }<br/>
 *          &nbsp;&nbsp;&nbsp;{@code </row>  }<br/>
        {@code </dataset> }
 *
 */
public class GridExtJsFormat_MAcc {
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
     * {@code <userid>}
     */
    public static final String useridElAwal = "<userid>";

    /**
     * berisi elemen: {@code </userid> }
     */
    public static final String useridElAkhir = "</userid>";

    /**
     * berisi elemen : <br/>
     * {@code <kodeform> }
     */
    public static final String kodeformElAwal = "<kodeform>";

    /**
     * berisi elemen: {@code </kodeform>}
     */
    public static final String kodeformElAkhir = "</kodeform>";

    /**
     * berisi elemen : <br/>
     * {@code <kodetransaksi> }
     */
    public static final String kodetransaksiElAwal = "<kodetransaksi>";

    /**
     * berisi elemen: {@code </kodetransaksi>}
     */
    public static final String kodetransaksiElAkhir = "</kodetransaksi>";

    /**
     * berisi elemen : <br/>
     * {@code <tglacc> }
     */
    public static final String tglaccElAwal = "<tglacc>";

    /**
     * berisi elemen: {@code </tglacc>}
     */
    public static final String tglaccElAkhir = "</tglacc>";

    /**
     * berisi elemen : <br/>
     * {@code <prioritas> }
     */
    public static final String prioritasElAwal = "<prioritas>";

    /**
     * berisi elemen: {@code </prioritas>}
     */
    public static final String prioritasElAkhir = "</prioritas>";

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
     * {@code <namajabatan> }
     */
    public static final String namajabatanElAwal = "<namajabatan>";

    /**
     * berisi elemen: {@code </namajabatan>}
     */
    public static final String namajabatanElAkhir = "</namajabatan>";

    /**
     * berisi elemen : <br/>
     * {@code <namabp> }
     */
    public static final String namabpElAwal = "<namabp>";

    /**
     * berisi elemen: {@code </namabp>}
     */
    public static final String namabpElAkhir = "</namabp>";
    
    /**
     * berisi elemen : <br/>
     * {@code <keterangan> }
     */
    public static final String keteranganElAwal = "<keterangan>";

    /**
     * berisi elemen: {@code </keterangan>}
     */
    public static final String keteranganElAkhir = "</keterangan>";
    
    /**
     * berisi elemen : <br/>
     * {@code <statusacc> }
     */
    public static final String statusaccElAwal = "<statusacc>";

    /**
     * berisi elemen: {@code </statusacc>}
     */
    public static final String statusaccElAkhir = "</statusacc>";
}