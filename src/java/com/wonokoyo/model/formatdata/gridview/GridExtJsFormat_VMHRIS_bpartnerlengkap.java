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
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodebp></kodebp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <nik></nik> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namabp></namabp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeperusahaan></kodeperusahaan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaperusahaan></namaperusahaan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeorg></kodeorg> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaorg></namaorg> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodejabatan></kodejabatan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namajabatan></namajabatan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodecostcenter></kodecostcenter> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <userid></userid> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodebp></kodebp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <nik></nik> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namabp></namabp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeperusahaan></kodeperusahaan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaperusahaan></namaperusahaan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeorg></kodeorg> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaorg></namaorg> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodejabatan></kodejabatan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namajabatan></namajabatan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodecostcenter></kodecostcenter> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <userid></userid> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
 *       {@code </dataset> }
 */
public class GridExtJsFormat_VMHRIS_bpartnerlengkap {
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
     * {@code <kodebp>}
     */
    public static final String kodebpElAwal = "<kodebp>";

    /**
     * berisi elemen: {@code </kodebp> }
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
     * {@code <kodeperusahaan> }
     */
    public static final String kodeperusahaanElAwal = "<kodeperusahaan>";

    /**
     * berisi elemen: {@code </kodeperusahaan>}
     */
    public static final String kodeperusahaanElAkhir = "</kodeperusahaan>";

    /**
     * berisi elemen : <br/>
     * {@code <namaperusahaan> }
     */
    public static final String namaperusahaanElAwal = "<namaperusahaan>";

    /**
     * berisi elemen: {@code </namaperusahaan>}
     */
    public static final String namaperusahaanElAkhir = "</namaperusahaan>";

    /**
     * berisi elemen : <br/>
     * {@code <kodeorg> }
     */
    public static final String kodeorgElAwal = "<kodeorg>";

    /**
     * berisi elemen: {@code </kodeorg>}
     */
    public static final String kodeorgElAkhir = "</kodeorg>";

    /**
     * berisi elemen : <br/>
     * {@code <namaorg> }
     */
    public static final String namaorgElAwal = "<namaorg>";

    /**
     * berisi elemen: {@code </namaorg>}
     */
    public static final String namaorgElAkhir = "</namaorg>";

    /**
     * berisi elemen : <br/>
     * {@code <kodejabatan> }
     */
    public static final String kodejabatanElAwal = "<kodejabatan>";

    /**
     * berisi elemen: {@code </kodejabatan>}
     */
    public static final String kodejabatanElAkhir = "</kodejabatan>";

     /**
     * berisi elemen : <br/>
     * {@code <namajabatan> }
     */
    public static final String namajabatanElAwal = "<namajabatan>";

    /**
     * berisi elemen: {@code </namajabatan>}
     */
    public static final String namajabatanElAkhir = "</namajabatan>";

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
     * {@code <userid> }
     */
    public static final String useridElAwal = "<userid>";

    /**
     * berisi elemen: {@code </userid>}
     */
    public static final String useridElAkhir = "</userid>";

}