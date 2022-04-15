/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.formatdata.gridview;

/**
 *
 * @author Bayu<br/>
 */

/**
 * Contoh bentuk XML nya : <br/>
 *       {@code <?xml version="1.0" encoding="ISO-8859-1"?> } <br/>
 *       {@code <dataset> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodepengajuan></kodepengajuan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodebp></kodebp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jenisrealisasi></jenisrealisasi> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <tanggal></tanggal> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <versi></versi> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <totalbiaya></totalbiaya> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeorg></kodeorg> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <batal></batal> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namabp></namabp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaperusahaan></namaperusahaan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <nik></nik> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaorg></namaorg> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code </row> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code <row> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodepengajuan></kodepengajuan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodebp></kodebp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <jenisrealisasi></jenisrealisasi> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <tanggal></tanggal> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <versi></versi> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <totalbiaya></totalbiaya> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <kodeorg></kodeorg> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <batal></batal> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namabp></namabp> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaperusahaan></namaperusahaan> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <nik></nik> }<br/>
 *             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@code <namaorg></namaorg> }<br/>
 *           &nbsp;&nbsp;&nbsp;{@code</row>  }<br/>
 *       {@code </dataset> }
 */

public class GridExtJsFormat_HPengajuan {
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
     * {@code <kodebp> }
     */
    public static final String kodebpElAwal = "<kodebp>";

    /**
     * berisi elemen: {@code </kodebp>}
     */
    public static final String kodebpElAkhir = "</kodebp>";

    /**
     * berisi elemen : <br/>
     * {@code <jenisrealisasi> }
     */
    public static final String jenisrealisasiElAwal = "<jenisrealisasi>";

    /**
     * berisi elemen: {@code </jenisrealisasi>}
     */
    public static final String jenisrealisasiElAkhir = "</jenisrealisasi>";

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
     * {@code <versi> }
     */
    public static final String versiElAwal = "<versi>";

    /**
     * berisi elemen: {@code </versi>}
     */
    public static final String versiElAkhir = "</versi>";

    /**
     * berisi elemen : <br/>
     * {@code <totalbiaya> }
     */
    public static final String totalbiayaElAwal = "<totalbiaya>";

    /**
     * berisi elemen: {@code </totalbiaya>}
     */
    public static final String totalbiayaElAkhir = "</totalbiaya>";

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
     * {@code <batal> }
     */
    public static final String batalElAwal = "<batal>";

    /**
     * berisi elemen: {@code </batal>}
     */
    public static final String batalElAkhir = "</batal>";
    
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
     * {@code <namnamaperusahaanbp> }
     */
    public static final String namaperusahaanElAwal = "<namaperusahaan>";

    /**
     * berisi elemen: {@code </namaperusahaan>}
     */
    public static final String namaperusahaanElAkhir = "</namaperusahaan>";
    
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
     * {@code <namaorg> }
     */
    public static final String namaorgElAwal = "<namaorg>";

    /**
     * berisi elemen: {@code </namaorg>}
     */
    public static final String namaorgElAkhir = "</namaorg>";
    
    /**
     * berisi elemen : <br/>
     * {@code <namajabatan> }
     */
    public static final String namajabatanElAwal = "<namajabatan>";

    /**
     * berisi elemen: {@code </namaorg>}
     */
    public static final String namajabatanElAkhir = "</namajabatan>";
    
    /**
     * berisi elemen : <br/>
     * {@code <total> }
     */
    public static final String totalElAwal = "<total>";

    /**
     * berisi elemen: {@code </total>}
     */
    public static final String totalElAkhir = "</total>";
    
    /**
     * berisi elemen : <br/>
     * {@code <IE> }
     */
    public static final String IeElAwal = "<IE>";

    /**
     * berisi elemen: {@code </IE>}
     */
    public static final String IeElAkhir = "</IE>";
    
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
