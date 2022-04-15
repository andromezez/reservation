/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.formatdata.gridview;

/**
 *
 * @author Eddy
 */

/**
 * Contoh bentuk XML nya : <br/>
 *       {@code <?xml version="1.0" encoding="ISO-8859-1"?> } <br/>
 *       {@code <dataset> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodetp></kodetp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodepengajuan></kodepengajuan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <tglawal></tglawal> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <tglakhir></tglakhir> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namatarif></namatarif> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodetp></kodetp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodepengajuan></kodepengajuan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <tglawal></tglawal> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <tglakhir></tglakhir> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namatarif></namatarif> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
 *       {@code </dataset> }
 */
public class GridExtJsFormat_DTglPengajuan {
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
     * {@code <kodepengajuan> }
     */
    public static final String kodepengajuanElAwal = "<kodepengajuan>";

    /**
     * berisi elemen: {@code </kodepengajuan>}
     */
    public static final String kodepengajuanElAkhir = "</kodepengajuan>";

    /**
     * berisi elemen : <br/>
     * {@code <tglawal> }
     */
    public static final String tglawalElAwal = "<tglawal>";

    /**
     * berisi elemen: {@code </tglawal>}
     */
    public static final String tglawalElAkhir = "</tglawal>";

    /**
     * berisi elemen : <br/>
     * {@code <tglakhir> }
     */
    public static final String tglakhirElAwal = "<tglakhir>";

    /**
     * berisi elemen: {@code </tglakhir>}
     */
    public static final String tglakhirElAkhir = "</tglakhir>";
    
    /**
     * berisi elemen : <br/>
     * {@code <namatarif> }
     */
    public static final String namatarifElAwal = "<namatarif>";

    /**
     * berisi elemen: {@code </namatarif>}
     */
    public static final String namatarifElAkhir = "</namatarif>";
    
}
