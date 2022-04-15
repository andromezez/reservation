/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_t_pengajuan  = {
  get_selectBlankRow: function(){return ""},
  get_selectPerubahan: function(formMode,awal,akhir,puserid,pcolumn,pvalue){
      if(formMode=='READ'){
            return "select * from (select * from (select a.KODEPENGAJUAN, a.KODEBP, a.JENISREALISASI, to_char(a.TANGGAL,'MM/dd/yyyy') tanggal, "+
                    "a.TOTALBIAYA,a.kodeorg,a.BATAL,'' as userid,a.IE,a.versi,c.NIK, c.namabp,c.NAMAPERUSAHAAN, c.NAMAORG, c.NAMAJABATAN, 0 total "+
                    "from h_pengajuan a, vm_hris_bpartnerlengkap c "+
                    "where a.batal=0 and a.kodebp = (select kodebp from m_user where userid like '"+puserid+"') and tanggal "+
                    "between to_date ('"+ awal +"', 'mm/dd/yyyy') AND to_date ('"+ akhir +"', 'mm/dd/yyyy') and a.kodebp=c.kodebp and (a.kodeorg=c.kodeorg or a.kodeorg is null)) dual) m "+
                    "where upper(m."+pcolumn+") like '%"+pvalue+"%' "+
                    "order by m.kodepengajuan";
          
        }else if(formMode=='INSERT'){
            return "select * from (select * from (select a.KODEPENGAJUAN, a.KODEBP, a.JENISREALISASI, to_char(a.TANGGAL,'MM/dd/yyyy') tanggal, "+
                    "a.TOTALBIAYA,a.kodeorg,a.BATAL,a.IE,a.versi,c.NIK,'' as userid, c.namabp,c.NAMAPERUSAHAAN, c.NAMAORG, c.NAMAJABATAN, "+
                    "(select (select count(*) total from mh_konfigurasiacc where kodeform='01-04-02')-(select count(*) total from m_acc where kodeform = '01-04-02' and statusacc = 1 and kodetransaksi=a.kodepengajuan) "+
                    "from dual) total from h_pengajuan a, vm_hris_bpartnerlengkap c "+
                    "where a.batal=0 and a.kodebp = (select kodebp from m_user where userid like '"+puserid+"') and tanggal between to_date ('"+ awal +"', 'mm/dd/yyyy') AND to_date ('"+ akhir +"', 'mm/dd/yyyy') and "+
                    "a.kodepengajuan not in (select kodepengajuan from h_realisasi)and a.kodebp=c.kodebp and (a.kodeorg=c.kodeorg or a.kodeorg is null)) dual where total=0) m "+
                    "where upper(m."+pcolumn+") like '%"+pvalue+"%' "+
                    "order by m.kodepengajuan";}
        return '';
   },
   get_selectPerubahanAll:function(formMode,awal,akhir,pcolumn,pvalue){
       if(formMode=='READ'){
            return "select * from (select * from (select a.KODEPENGAJUAN, a.KODEBP, a.JENISREALISASI, to_char(a.TANGGAL,'MM/dd/yyyy') tanggal, "+
                    "a.TOTALBIAYA,a.kodeorg,a.BATAL,a.IE,a.versi,c.NIK,c.userid as userid, c.namabp,c.NAMAPERUSAHAAN, c.NAMAORG, c.NAMAJABATAN, 0 total "+
                    "from h_pengajuan a, vm_hris_bpartnerlengkap c "+
                    "where a.batal=0 and tanggal between to_date ('"+awal+"', 'mm/dd/yyyy') AND to_date ('"+akhir+"', 'mm/dd/yyyy') and a.kodebp=c.kodebp and (a.kodeorg=c.kodeorg or a.kodeorg is null)) dual) m "+
                    "where upper(m."+pcolumn+") like '%"+pvalue+"%' "+
                    "order by m.kodepengajuan";
            
        }else if(formMode=='INSERT'){
            return "select * from (select * from (select a.KODEPENGAJUAN, a.KODEBP, a.JENISREALISASI, to_char(a.TANGGAL,'MM/dd/yyyy') tanggal, "+
                    "a.TOTALBIAYA,a.kodeorg,a.BATAL,a.IE,a.versi,c.NIK, c.namabp,c.NAMAPERUSAHAAN,c.userid as userid, c.NAMAORG, c.NAMAJABATAN, "+
                    "(select (select count(*) total from mh_konfigurasiacc where kodeform='01-04-02')-(select count(*) total from m_acc where kodeform = '01-04-02' and statusacc = 1 and kodetransaksi=a.kodepengajuan) "+
                    "from dual) total from h_pengajuan a, vm_hris_bpartnerlengkap c "+
                    "where a.batal=0  and tanggal between to_date ('"+awal+"', 'mm/dd/yyyy') AND to_date ('"+akhir+"', 'mm/dd/yyyy') and a.kodepengajuan not in (select kodepengajuan from h_realisasi) and a.kodebp=c.kodebp and (a.kodeorg=c.kodeorg or a.kodeorg is null)) dual where total=0) m "+
                    "where upper(m."+pcolumn+") like '%"+pvalue+"%' "+
                    "order by m.kodepengajuan";}
        return '';
   },
   get_selectAccPengajuan: function(puserid){
       return "select a.KODEPENGAJUAN, a.KODEBP, a.JENISREALISASI, to_char(a.TANGGAL,'MM/dd/yyyy') tanggal, "+
                "a.TOTALBIAYA,a.kodeorg,a.BATAL,a.IE,a.versi,b.NIK, b.namabp,b.NAMAPERUSAHAAN, b.NAMAORG,b.userid as userid, b.NAMAJABATAN,'0' total "+
                "from h_pengajuan a,vm_hris_bpartnerlengkap b where a.batal=0 and a.kodepengajuan in "+
                "((select kodepengajuan from d_costcenterpengajuan where userid='"+puserid+"' and approval=0)union "+
                "(select kodetransaksi from m_acc where userid='"+puserid+"' and statusacc=0 and kodetransaksi like 'PEN%')) and a.kodebp=b.kodebp and (a.kodeorg=b.kodeorg or a.kodeorg is null) order by a.kodepengajuan";     
   },
   get_selectHistoryFilterByUserId: function(pcolumn,pvalue,awal,akhir,puserid){
       return "select * from (select a.KODEPENGAJUAN, a.KODEBP, a.JENISREALISASI, to_char(a.TANGGAL,'MM/dd/yyyy') tanggal, "+
                "a.TOTALBIAYA,a.kodeorg,a.BATAL,a.IE,a.versi,b.NIK, b.namabp,b.NAMAPERUSAHAAN, b.NAMAORG,'' as userid, b.NAMAJABATAN,'0' total "+
                "from h_pengajuan a,vm_hris_bpartnerlengkap b where a.batal=1  and a.kodebp = (select kodebp from m_user where userid like '"+puserid+"') and a.kodebp=b.kodebp and (a.kodeorg=b.kodeorg or a.kodeorg is null)) m "+
                "where upper(m."+pcolumn+") like '%"+pvalue+"%' and (to_date (m.tanggal, 'mm/dd/yyyy') between to_date ('"+awal+"', 'mm/dd/yyyy') AND to_date ('"+akhir+"', 'mm/dd/yyyy')) order by m.kodepengajuan";
   },
   get_selectHistoryAll: function(pcolumn,pvalue,awal,akhir){
       return "select * from (select a.KODEPENGAJUAN, a.KODEBP, a.JENISREALISASI, to_char(a.TANGGAL,'MM/dd/yyyy') tanggal, "+
                "a.TOTALBIAYA,a.kodeorg,a.BATAL,a.IE,a.versi,b.NIK, b.namabp,b.NAMAPERUSAHAAN, b.NAMAORG,'' as userid, b.NAMAJABATAN,'0' total "+
                "from h_pengajuan a,vm_hris_bpartnerlengkap b where a.batal=1 and a.kodebp=b.kodebp and (a.kodeorg=b.kodeorg or a.kodeorg is null) ) m "+
                "where upper(m."+pcolumn+") like '%"+pvalue+"%' and (to_date (m.tanggal, 'mm/dd/yyyy') between to_date ('"+awal+"', 'mm/dd/yyyy') AND to_date ('"+akhir+"', 'mm/dd/yyyy')) order by m.kodepengajuan";
   },
   get_selectRealisasi: function(awal,akhir,pcolumn,pvalue){
       return "select * from (select a.KODEPENGAJUAN, a.KODEBP, a.JENISREALISASI, to_char(a.TANGGAL,'MM/dd/yyyy') tanggal,a.TOTALBIAYA, a.IE, "+
"a.versi,a.batal,c.NIK, c.namabp,c.NAMAPERUSAHAAN, c.NAMAORG, c.NAMAJABATAN,a.kodeorg,c.userid,(select (select count(*) total from mh_konfigurasiacc where kodeform='01-04-02')-(select count(*) total from m_acc where kodeform = '01-04-02' and statusacc = 1 and kodetransaksi=a.kodepengajuan) from dual) total "+
"from h_pengajuan a, vm_hris_bpartnerlengkap c "+
"where a.batal=0 and tanggal between to_date ('"+awal+"', 'mm/dd/yyyy') AND to_date ('"+akhir+"', 'mm/dd/yyyy') and (a.kodepengajuan not in(select kodepengajuan from h_realisasi)) and a.kodebp=c.kodebp and (a.kodeorg=c.kodeorg or a.kodeorg is null)) m "+
"where m.total=0 and upper(m."+pcolumn+") like '%"+pvalue+"%' order by m.kodepengajuan";
    },
    get_selectPengajuan : function(awal,akhir){
        return "SELECT a.KODEPENGAJUAN,a.KODEBP,a.JENISREALISASI, "+
                        "to_char(a.TANGGAL,'mm/dd/yyyy') TANGGAL,a.VERSI ,a.TOTALBIAYA,a.KODEORG,a.BATAL,"
                      + "b.namabp, b.namaperusahaan,b.nik,b.namaorg, b.namajabatan,'' total, a.IE,'' as userid "+
                        "FROM H_PENGAJUAN a, vm_hris_bpartnerlengkap b "+
                        "where a.versi = 0 and a.tanggal between to_date ('"+ awal +"', 'mm/dd/yyyy') AND to_date ('"+ akhir +"', 'mm/dd/yyyy')" +
                        "and a.kodebp=b.kodebp and (a.kodeorg=b.kodeorg or a.kodeorg is null) order by a.kodepengajuan";
    },
    
    get_CmbPemohon : function(){
        return "select distinct a.kodebp,namabp, '' as kodepengajuan, '' as jenisrealisasi, " +
        "'' as tanggal, '' as totalbiaya,  '' as kodeorg, '' as batal, '' as versi, "+
        "b.nik as nik, '' as namaperusahaan,namaorg, '' as namajabatan, '' as total,'' as IE,'' as userid "+
        "from h_pengajuan a, vm_hris_bpartnerlengkap b "+
        "where and a.kodebp=b.kodebp and (a.kodeorg=b.kodeorg or a.kodeorg is null)";
    }
      };
var t_pengajuanRecord = Ext.data.Record.create([
             'kodepengajuan',
             'kodebp',
             'jenisrealisasi',
             'tanggal',
             'totalbiaya',
             'kodeorg',
             'batal',
             'versi',
             'nik',
             'namabp',
             'namaperusahaan',
             'namaorg',
             'namajabatan',
             'total',
             'IE',
             'userid'
        ]);
 var h_pengajuan_record = Ext.data.Record.create([
            'kodepengajuan','kodebp','jenisrealisasi','tanggal',
            'versi','totalbiaya','kodeorg','batal','namabp', 
            'namaperusahaan','nik','namaorg'
        ]);
     /*     
 var hpengajuan_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'t_pengajuan',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodepengajuan'
          }, h_pengajuan_record),
          autoLoad: false
        });
        */
var tpengajuan_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'t_pengajuan',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodeitem'
          }, h_pengajuan_record),
          autoLoad: false
        });
        
function get_t_pengajuanStore(formMode,sqlString,pautoload){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'t_pengajuan',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'t_pengajuan',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  var autoload;
  if (typeof pautoload == "undefined"){
      autoload = true;
  }else{
      autoload = pautoload;
  }
  var xmlstore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, t_pengajuanRecord),
          autoLoad: autoload
   });
   return xmlstore;
}
