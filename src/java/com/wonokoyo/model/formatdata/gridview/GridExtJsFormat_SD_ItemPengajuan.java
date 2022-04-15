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
 * Contoh bentuk XML nya : <br/>
 *       {@code <?xml version="1.0" encoding="ISO-8859-1"?> } <br/>
 *       {@code <dataset> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodetp></kodetp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeitem></kodeitem> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaitem></namaitem> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jumlahawal></jumlahawal> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jumlahakhir></jumlahakhir> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jamawal></jamawal> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jamakhir></jamakhir> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <durasi></durasi> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <biaya></biaya> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <antrian></antrian> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <keterangan></keterangan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <biayaperjam></biayaperjam> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodetp></kodetp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeitem></kodeitem> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaitem></namaitem> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jumlahawal></jumlahawal> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jumlahakhir></jumlahakhir> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jamawal></jamawal> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jamakhir></jamakhir> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <durasi></durasi> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <biaya></biaya> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <antrian></antrian> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <keterangan></keterangan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <biayaperjam></biayaperjam> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
 *       {@code </dataset> }
 */

public class GridExtJsFormat_SD_ItemPengajuan {
        
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
     * {@code <kodetp>}
     */
    public static final String kodetpElAwal = "<kodetp>";

    /**
     * berisi elemen: {@code </kodetp> }
     */
    public static final String kodetpElAkhir = "</kodetp>";

    /**
     * berisi elemen : <br/>
     * {@code <kodeitem> }
     */
    public static final String kodeitemElAwal = "<kodeitem>";

    /**
     * berisi elemen: {@code </kodeitem>}
     */
    public static final String kodeitemElAkhir = "</kodeitem>";

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
     * {@code <jumlahawal> }
     */
    public static final String jumlahawalElAwal = "<jumlahawal>";

    /**
     * berisi elemen: {@code </jumlahawal>}
     */
    public static final String jumlahawalElAkhir = "</jumlahawal>";

    /**
     * berisi elemen : <br/>
     * {@code <jumlahakhir> }
     */
    public static final String jumlahakhirElAwal = "<jumlahakhir>";

    /**
     * berisi elemen: {@code </jumlahakhir>}
     */
    public static final String jumlahakhirElAkhir = "</jumlahakhir>";

    /**
     * berisi elemen : <br/>
     * {@code <jamawal> }
     */
    public static final String jamawalElAwal = "<jamawal>";

    /**
     * berisi elemen: {@code </jamawal>}
     */
    public static final String jamawalElAkhir = "</jamawal>";

    /**
     * berisi elemen : <br/>
     * {@code <jamakhir> }
     */
    public static final String jamakhirElAwal = "<jamakhir>";

    /**
     * berisi elemen: {@code </jamakhir>}
     */
    public static final String jamakhirElAkhir = "</jamakhir>";

    /**
     * berisi elemen : <br/>
     * {@code <durasi> }
     */
    public static final String durasiElAwal = "<durasi>";

    /**
     * berisi elemen: {@code </durasi>}
     */
    public static final String durasiElAkhir = "</durasi>";

    /**
     * berisi elemen : <br/>
     * {@code <biaya> }
     */
    public static final String biayaElAwal = "<biaya>";

    /**
     * berisi elemen: {@code </biaya>}
     */
    public static final String biayaElAkhir = "</biaya>";

    /**
     * berisi elemen : <br/>
     * {@code <antrian> }
     */
    public static final String antrianElAwal = "<antrian>";

    /**
     * berisi elemen: {@code </antrian>}
     */
    public static final String antrianElAkhir = "</antrian>";

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
     * {@code <biayaperjam> }
     */
    public static final String biayaperjamElAwal = "<biayaperjam>";

    /**
     * berisi elemen: {@code </biayaperjam>}
     */
    public static final String biayaperjamElAkhir = "</biayaperjam>";
    
    /**
     * berisi elemen : <br/>
     * {@code <jumlahpeserta> }
     */
    public static final String jumlahpesertaElAwal = "<jumlahpeserta>";

    /**
     * berisi elemen: {@code </jumlahawal>}
     */
    public static final String jumlahpesertaElAkhir = "</jumlahpeserta>";
    
    
}
