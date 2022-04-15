/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.formatdata.gridview;

/**
 *
 * @author SK
 *
 */

/**
 *Contoh bentuk XML nya : <br/>
        {@code <?xml version="1.0" encoding="ISO-8859-1"?> } <br/>
        {@code <dataset> }<br/>
            &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeacc></kodeacc> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeform></kodeform> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaform></namaform> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <prioritas></prioritas> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <keterangan></keterangan> }<br/>
            &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
            &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeacc></kodeacc> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeform></kodeform> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaform></namaform> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <prioritas></prioritas> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <keterangan></keterangan> }<br/>
            &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
        {@code </dataset> }
 *
 */
public class GridExtJsFormat_MHKonfigurasiAcc {

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
     * {@code <kodeform> }
     */
    public static final String kodeformElAwal = "<kodeform>";

    /**
     * berisi elemen: {@code </kodeform>}
     */
    public static final String kodeformElAkhir = "</kodeform>";

    /**
     * berisi elemen : <br/>
     * {@code <namaform> }
     */
    public static final String namaformElAwal = "<namaform>";

    /**
     * berisi elemen: {@code </namaform>}
     */
    public static final String namaformElAkhir = "</namaform>";

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
     * {@code <keterangan> }
     */
    public static final String keteranganElAwal = "<keterangan>";

    /**
     * berisi elemen: {@code </keterangan>}
     */
    public static final String keteranganElAkhir = "</keterangan>";
}
