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
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <userid></userid> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namabp></namabp> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodebp></kodebp> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <nik></nik> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <aktif></aktif> }<br/>
            &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
            &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <userid></userid> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namauser></namauser> }<br/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodebp></kodebp> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <nik></nik> }<br/>
 *            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <aktif></aktif> }<br/>
            &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
        {@code </dataset> }
 *
 */
public class GridExtJsFormat_MUser {

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
     * {@code <namabp>}
     */
    public static final String namabpElAwal = "<namabp>";

    /**
     * berisi elemen: {@code </namabp> }
     */
    public static final String namabpElAkhir = "</namabp>";

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
     * {@code <aktif> }
     */
    public static final String aktifElAwal = "<aktif>";

    /**
     * berisi elemen: {@code </aktif>}
     */
    public static final String aktifElAkhir = "</aktif>";
}
