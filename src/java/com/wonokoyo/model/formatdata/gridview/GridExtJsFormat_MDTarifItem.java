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
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namatarif></namatarif> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jamawal></jamawal> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jamakhir></jamakhir> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <biaya></biaya> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <ie></ie> }<br/>
 *          &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
            &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeitem></kodeitem> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namatarif></namatarif> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jamawal></jamawal> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jamakhir></jamakhir> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <biaya></biaya> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <ie></ie> }<br/>
 *          &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
        {@code </dataset> }
 *
 */
public class GridExtJsFormat_MDTarifItem {

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
     * {@code <namatarif> }
     */
    public static final String namatarifElAwal = "<namatarif>";

    /**
     * berisi elemen: {@code </namatarif>}
     */
    public static final String namatarifElAkhir = "</namatarif>";

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
     * {@code <durasiminsewa> }
     */
    public static final String durasiminsewaElAwal = "<durasiminsewa>";

    /**
     * berisi elemen: {@code </biaya>}
     */
    public static final String biayaElAkhir = "</biaya>";

    /**
     * berisi elemen : <br/>
     * {@code <biaya> }
     */
    public static final String biayaElAwal = "<biaya>";

    /**
     * berisi elemen : <br/>
     * {@code <ie> }
     */
    public static final String ieElAwal = "<ie>";
    
    /**
     * berisi elemen: {@code </ie>}
     */
    public static final String ieElAkhir = "</ie>";

    /**
     * berisi elemen : <br/>
     * {@code <jenisrealisasi> }
     */
    public static final String jenisrealisasiElAwal = "<jenisrealisasi>";
    
    /**
     * berisi elemen: {@code </jenisrealisasi>}
     */
    public static final String jenisrealisasiElAkhir = "</jenisrealisasi>";
    
}
