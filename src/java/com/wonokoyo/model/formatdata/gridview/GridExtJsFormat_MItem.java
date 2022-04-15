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
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeitem></kodeitem> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodejenis></kodejenis> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namajenis></namajenis> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaitem></namaitem> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <durasiminsewa></durasiminsewa> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kapasitas></kapasitas> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <keterangan></keterangan> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <aktif></aktif> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <statusavailability></statusavailability> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <pricechangeable></pricechangeable> }<br/>
            &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
            &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeitem></kodeitem> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodejenis></kodejenis> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namajenis></namajenis> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeitem></kodeitem> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <durasiminsewa></durasiminsewa> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kapasitas></kapasitas> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <keterangan></keterangan> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <aktif></aktif> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <statusavailability></statusavailability> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <pricechangeable></pricechangeable> }<br/>
            &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
        {@code </dataset> }
 *
 */
public class GridExtJsFormat_MItem {

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
     * {@code <kodeitem>}
     */
    public static final String kodeitemElAwal = "<kodeitem>";

    /**
     * berisi elemen: {@code </kodeitem> }
     */
    public static final String kodeitemElAkhir = "</kodeitem>";

    /**
     * berisi elemen : <br/>
     * {@code <kodejenis> }
     */
    public static final String kodejenisElAwal = "<kodejenis>";

    /**
     * berisi elemen: {@code </kodejenis>}
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

    /**
     * berisi elemen : <br/>
     * {@code <namaitem> }
     */
    public static final String namaitemElAwal = "<namaitem>";

    /**
     * berisi elemen: {@code </namaitem>}
     */
    public static final String namaitemElAkhir = "</namaitem>";

    /**
     * berisi elemen : <br/>
     * {@code <durasiminsewa> }
     */
    public static final String durasiminsewaElAwal = "<durasiminsewa>";

    /**
     * berisi elemen: {@code </durasiminsewa>}
     */
    public static final String durasiminsewaElAkhir = "</durasiminsewa>";

    /**
     * berisi elemen : <br/>
     * {@code <kapasitas> }
     */
    public static final String kapasitasElAwal = "<kapasitas>";

    /**
     * berisi elemen: {@code </kapasitas>}
     */
    public static final String kapasitasElAkhir = "</kapasitas>";

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
     * {@code <aktif> }
     */
    public static final String aktifElAwal = "<aktif>";

    /**
     * berisi elemen: {@code </aktif>}
     */
    public static final String aktifElAkhir = "</aktif>";

     /**
     * berisi elemen : <br/>
     * {@code <statusavailability> }
     */
    public static final String statusavailabilityElAwal = "<statusavailability>";

    /**
     * berisi elemen: {@code </statusavailability>}
     */
    public static final String statusavailabilityElAkhir = "</statusavailability>";

     /**
     * berisi elemen : <br/>
     * {@code <pricechangeable> }
     */
    public static final String pricechangeableElAwal = "<pricechangeable>";

    /**
     * berisi elemen: {@code </pricechangeable>}
     */
    public static final String pricechangeableElAkhir = "</pricechangeable>";
}
