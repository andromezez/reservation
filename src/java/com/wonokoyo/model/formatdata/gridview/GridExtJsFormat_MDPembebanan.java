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
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodepembebanan></kodepembebanan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodecostcenter></kodecostcenter> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namacostcenter></namacostcenter>
 *           &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodepembebanan></kodepembebanan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodecostcenter></kodecostcenter> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namacostcenter></namacostcenter>
 *           &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
 *       {@code </dataset> }
 */
public class GridExtJsFormat_MDPembebanan {
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
     * {@code <kodepembebanan>}
     */
    public static final String kodepembebananElAwal = "<kodepembebanan>";

    /**
     * berisi elemen: {@code </kodepembebanan> }
     */
    public static final String kodepembebananElAkhir = "</kodepembebanan>";

    /**
     * berisi elemen : <br/>
     * {@code <kodecostcenter> }
     */
    public static final String kodecostcenterElAwal = "<kodecostcenter>";

    /**
     * berisi elemen: {@code </kodecostcenter>}
     */
    public static final String kodecostcenterElAkhir = "</kodecostcenter>";

    /**
     * berisi elemen : <br/>
     * {@code <namacostcenter> }
     */
    public static final String namacostcenterElAwal = "<namacostcenter>";

    /**
     * berisi elemen: {@code </namacostcenter>}
     */
    public static final String namacostcenterElAkhir = "</namacostcenter>";

    
}
