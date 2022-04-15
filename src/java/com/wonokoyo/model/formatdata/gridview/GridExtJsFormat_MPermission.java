/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.formatdata.gridview;

/**
 *
 * @author SK
 */

/**
 * Contoh bentuk XML nya : <br/>
 *       {@code <?xml version="1.0" encoding="ISO-8859-1"?> } <br/>
 *       {@code <dataset> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeform></kodeform> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaform></namaform> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <userid></userid> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <pview></pview> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <padd></padd> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <pupdate></pupdate> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <pdelete></pdelete> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <pprint></pprint> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeform></kodeform> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaform></namaform> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <userid></userid> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <pview></pview> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <padd></padd> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <pupdate></pupdate> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <pdelete></pdelete> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <pprint></pprint> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
 *       {@code </dataset> }
 */

public class GridExtJsFormat_MPermission {
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
     * {@code <kodeform>}
     */
    public static final String kodeformElAwal = "<kodeform>";

    /**
     * berisi elemen: {@code </kodeform> }
     */
    public static final String kodeformElAkhir = "</kodeform>";

    /**
     * berisi elemen : <br/>
     * {@code <namaform>}
     */
    public static final String namaformElAwal = "<namaform>";

    /**
     * berisi elemen: {@code </namaform> }
     */
    public static final String namaformElAkhir = "</namaform>";

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
     * {@code <pview> }
     */
    public static final String pviewElAwal = "<pview>";

    /**
     * berisi elemen: {@code </pview>}
     */
    public static final String pviewElAkhir = "</pview>";

    /**
     * berisi elemen : <br/>
     * {@code <padd> }
     */
    public static final String paddElAwal = "<padd>";

    /**
     * berisi elemen: {@code </padd>}
     */
    public static final String paddElAkhir = "</padd>";

    /**
     * berisi elemen : <br/>
     * {@code <pupdate> }
     */
    public static final String pupdateElAwal = "<pupdate>";

    /**
     * berisi elemen: {@code </pupdate>}
     */
    public static final String pupdateElAkhir = "</pupdate>";

    /**
     * berisi elemen : <br/>
     * {@code <pdelete> }
     */
    public static final String pdeleteElAwal = "<pdelete>";

    /**
     * berisi elemen: {@code </pdelete>}
     */
    public static final String pdeleteElAkhir = "</pdelete>";

    /**
     * berisi elemen : <br/>
     * {@code <pprint> }
     */
    public static final String pprintElAwal = "<pprint>";

    /**
     * berisi elemen: {@code </pprint>}
     */
    public static final String pprintElAkhir = "</pprint>";

    /**
     * berisi elemen : <br/>
     * {@code <batal_trans> }
     */
    public static final String bataltransElAwal = "<batal_trans>";

    /**
     * berisi elemen: {@code </pprint>}
     */
    public static final String bataltransElAkhir = "</batal_trans>";

}
