/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_t_realisasi  = {
    get_selectBlankRow: function(){return ""},
    get_selectRealisasi: function(awal,akhir,pcolumn,pvalue){
        return "select * from (select * from (select a.koderealisasi,a.kodebp, a.KODEPENGAJUAN, a.JENISREALISASI, "+
                "to_char(a.TANGGAL,'MM/dd/yyyy') tglrealisasi,to_char(b.TANGGAL,'MM/dd/yyyy') tglpengajuan, a.TOTALBIAYA, "+
                "a.IE,b.versi,c.NIK, c.namabp,c.NAMAPERUSAHAAN, c.NAMAORG, c.NAMAJABATAN, "+
                "(select (select count(*) total from mh_konfigurasiacc where kodeform='01-04-03')-(select count(*) total from m_acc where kodeform = '01-04-03' and statusacc = 1 "+
                "and kodetransaksi=a.koderealisasi) from dual) total "+
                "from h_realisasi a,h_pengajuan b, vm_hris_bpartnerlengkap c "+
                "where a.tanggal between to_date ('"+awal+"', 'mm/dd/yyyy') AND to_date ('"+akhir+"', 'mm/dd/yyyy') "+
                "and a.kodebp=c.kodebp and a.kodepengajuan=b.kodepengajuan) dual where total !=0) m where m."+pcolumn+" like '%"+pvalue+"%'";
        
        /*
        return 	"select * from (select a.koderealisasi,a.kodebp, a.KODEPENGAJUAN, a.JENISREALISASI, "+
	"to_char(a.TANGGAL,'MM/dd/yyyy') tglrealisasi,to_char(b.TANGGAL,'MM/dd/yyyy') tglpengajuan, "+
	"a.TOTALBIAYA,a.IE,b.versi,c.NIK, c.namabp,c.NAMAPERUSAHAAN, c.NAMAORG, c.NAMAJABATAN "+
	"from h_realisasi a,h_pengajuan b, vm_hris_bpartnerlengkap c "+
	"where a.tanggal between to_date ('"+awal+"', 'mm/dd/yyyy') AND to_date ('"+akhir+"', 'mm/dd/yyyy') "+
	"and a.kodebp=c.kodebp and a.kodepengajuan=b.kodepengajuan) m where m."+pcolumn+" like '%"+pvalue+"%'"; */
},
    getAccRealisasi: function(puserid){
        return "select a.koderealisasi,a.kodebp,a.kodepengajuan,a.jenisrealisasi,to_char(a.tanggal,'mm/dd/yyyy') tglrealisasi, "+
                "a.totalbiaya,a.IE,to_char(b.tanggal,'mm/dd/yyyy') tglpengajuan,c.namabp,c.namaperusahaan,nik,namaorg,namajabatan,versi "+
                "from h_realisasi a, h_pengajuan b,vm_hris_bpartnerlengkap c "+
                "where a.koderealisasi in ((select koderealisasi from d_costcenterrealisasi where userid='"+puserid+"' and approval=0) union "+
                "(select kodetransaksi from m_acc where userid='"+puserid+"' and statusacc=0 and kodetransaksi like 'REA%')) "+
                "and a.kodepengajuan=b.kodepengajuan and a.kodebp=c.kodebp ";
    },
    getSelectHistoryRealisasiFilterByUser: function(awal,akhir,pcolumn,pvalue,userid){
        return "select * from (select * from (select a.koderealisasi,a.kodebp, a.KODEPENGAJUAN, a.JENISREALISASI, to_char(a.TANGGAL,'MM/dd/yyyy') tglrealisasi,to_char(b.TANGGAL,'MM/dd/yyyy') tglpengajuan, a.TOTALBIAYA,a.IE,b.versi,c.NIK, c.namabp,c.NAMAPERUSAHAAN, c.NAMAORG, c.NAMAJABATAN, "+
                "(select (select count(*) total from mh_konfigurasiacc where kodeform='01-04-03')-(select count(*) total "+
                "from m_acc where kodeform = '01-04-03' and statusacc = 1 and kodetransaksi=a.koderealisasi) from dual) "+
                "total from h_realisasi a,h_pengajuan b, vm_hris_bpartnerlengkap c "+
                "where a.tanggal between to_date ('"+awal+"', 'mm/dd/yyyy') AND to_date ('"+akhir+"', 'mm/dd/yyyy') and a.kodebp = (select kodebp from m_user where userid like '"+userid+"') "+
                "and a.kodebp=c.kodebp and a.kodepengajuan=b.kodepengajuan) dual where total=0) m where m."+pcolumn+" like '%"+pvalue+"%'"
    },
    getSelectHistoryAll: function(awal,akhir,pcolumn,pvalue){
        return "select * from (select * from (select a.koderealisasi,a.kodebp, a.KODEPENGAJUAN, a.JENISREALISASI, to_char(a.TANGGAL,'MM/dd/yyyy') tglrealisasi,to_char(b.TANGGAL,'MM/dd/yyyy') tglpengajuan, a.TOTALBIAYA,a.IE,b.versi,c.NIK, c.namabp,c.NAMAPERUSAHAAN, c.NAMAORG, c.NAMAJABATAN, "+
                "(select (select count(*) total from mh_konfigurasiacc where kodeform='01-04-03')-(select count(*) total "+
                "from m_acc where kodeform = '01-04-03' and statusacc = 1 and kodetransaksi=a.koderealisasi) from dual) "+
                "total from h_realisasi a,h_pengajuan b, vm_hris_bpartnerlengkap c "+
                "where a.tanggal between to_date ('"+awal+"', 'mm/dd/yyyy') AND to_date ('"+akhir+"', 'mm/dd/yyyy')  "+
                "and a.kodebp=c.kodebp and a.kodepengajuan=b.kodepengajuan) dual where total=0) m where m."+pcolumn+" like '%"+pvalue+"%'"
    },
    
    get_selectCmbRealisasi: function(){
        return "select distinct a.kodebp,namabp, '' as koderealisasi,'' kodepengajuan ,'' as jenisrealisasi, " +
        "'' as tglpengajuan,'' tglrealisasi, 0 as totalbiaya,'' as IE,'' as nik, '' as namaperusahaan,namaorg, '' as namajabatan, 0 as versi "+
        "from h_realisasi a, vm_hris_bpartnerlengkap b "+
        "where a.kodebp=b.kodebp "
    }
}

var t_realisasiRecord = Ext.data.Record.create([
             'koderealisasi',
             'kodebp',
             'kodepengajuan',
             'jenisrealisasi',
             'tglpengajuan',
             'tglrealisasi',
             'totalbiaya',
             'namabp',
             'namaperusahaan',
             'nik',
             'namaorg',
             'namajabatan',
             'versi',
             'IE'
        ]);
var trealisasi_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'t_realisasi',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodeitem'
          }, t_realisasiRecord),
          autoLoad: false
        });
        
function get_t_realisasiStore(formMode,sqlString,pautoload){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'t_realisasi',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'t_realisasi',
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
          }, t_realisasiRecord),
          autoLoad: autoload
   });
   return xmlstore;
}