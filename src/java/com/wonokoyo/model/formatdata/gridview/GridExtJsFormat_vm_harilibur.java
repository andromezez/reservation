/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model.formatdata.gridview;

/**
 *
 * @author bayu
 */

/**
 * Contoh bentuk XML nya : <br/>
 *       {@code <?xml version="1.0" encoding="ISO-8859-1"?> } <br/>
 *       {@code <dataset> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <keterangan></keterangan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <tanggal></tanggal> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodelokasi></kodelokasi> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <keterangan></keterangan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <tanggal></tanggal> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodelokasi></kodelokasi> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
 *       {@code </dataset> }
 */
public class GridExtJsFormat_vm_harilibur {
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
     * {@code <keterangan>}
     */
    public static final String keteranganElAwal = "<keterangan>";

    /**
     * berisi elemen: {@code </keterangan> }
     */
    public static final String keteranganElAkhir = "</keterangan>";

    /**
     * berisi elemen : <br/>
     * {@code <tanggal> }
     */
    public static final String tanggalElAwal = "<tanggal>";

    /**
     * berisi elemen: {@code </tanggal>}
     */
    public static final String tanggalElAkhir = "</tanggal>";

    /**
     * berisi elemen : <br/>
     * {@code <kodelokasi> }
     */
    public static final String kodelokasiElAwal = "<kodelokasi>";

    /**
     * berisi elemen: {@code </kodelokasi>}
     */
    public static final String kodelokasiElAkhir = "</kodelokasi>";
}
