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
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodepengajuan></kodepengajuan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodecostcenter></kodecostcenter> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namacostcenter></namacostcenter> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <userid></userid> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodebp></kodebp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <nik></nik> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namabp></namabp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <approval></approval> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <presentase></presentase> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <nominal></nominal> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodepengajuan></kodepengajuan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodecostcenter></kodecostcenter> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namacostcenter></namacostcenter> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <userid></userid> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodebp></kodebp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <nik></nik> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namabp></namabp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <approval></approval> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <presentase></presentase> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <nominal></nominal> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
 *       {@code </dataset> }
 */
public class GridExtJsFormat_DCostCenterPengajuan {
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
     * {@code <kodepengajuan>}
     */
    public static final String kodepengajuanElAwal = "<kodepengajuan>";

    /**
     * berisi elemen: {@code </kodepengajuan> }
     */
    public static final String kodepengajuanElAkhir = "</kodepengajuan>";

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
     * {@code <kodebp> }
     */
    public static final String kodebpElAwal = "<kodebp>";

    /**
     * berisi elemen: {@code </kodebp>}
     */
    public static final String kodebpElAkhir = "</kodebp>";

    /**
     * berisi elemen : <br/>
     * {@code <nik> }
     */
    public static final String nikElAwal = "<nik>";

    /**
     * berisi elemen: {@code </nik>}
     */
    public static final String nikElAkhir = "</nik>";

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
     * {@code <approval> }
     */
    public static final String approvalElAwal = "<approval>";

    /**
     * berisi elemen: {@code </approval>}
     */
    public static final String approvalElAkhir = "</approval>";

    /**
     * berisi elemen : <br/>
     * {@code <presentase> }
     */
    public static final String presentaseElAwal = "<presentase>";

    /**
     * berisi elemen: {@code </presentase>}
     */
    public static final String presentaseElAkhir = "</presentase>";

    /**
     * berisi elemen : <br/>
     * {@code <nominal> }
     */
    public static final String nominalElAwal = "<nominal>";

    /**
     * berisi elemen: {@code </nominal>}
     */
    public static final String nominalElAkhir = "</nominal>";
}
