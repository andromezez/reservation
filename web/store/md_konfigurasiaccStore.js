/* "
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var SqlCustom_md_konfigurasiacc = {
  get_selectBlankRow: function(){return "select ' ' kodeacc,' ' userid,' ' namauser,' ' kodejabatan, ' ' namajabatan, ' ' defaultacc, '' keterangan  from dual"},
  get_selectFilterByKodeAcc: function(pkodeacc){ return "select d.kodeacc, d.userid,d.kodebp,e.NAMABP, d.kodejabatan, e.namajabatan,d.defaultacc, '' keterangan " +
            "from "+
                "(select b.kodeacc, b.userid, b.kodejabatan, b.defaultacc, c.kodebp " +
                  "from mh_konfigurasiacc a, md_konfigurasiacc b, m_user c " +
                  "where  a.kodeacc = '"+pkodeacc+"' " +                  
                  "and a.kodeacc = b.kodeacc " +
                  "and b.userid = c.userid) d, " +
                "VM_HRIS_BPARTNERLENGKAP e "+
            "where d.kodebp = e.kodebp and d.kodejabatan = e.kodejabatan"},
  get_selectFilterByKeterangan:function(pkodeform)
  {
      return "select d.kodeacc, d.userid,d.kodebp,e.NAMABP, d.kodejabatan, e.namajabatan,d.defaultacc ,d.keterangan "+
            "from (select b.kodeacc, b.userid, b.kodejabatan, b.defaultacc, c.kodebp,a.keterangan "+
            "from mh_konfigurasiacc a, md_konfigurasiacc b, m_user c "+
            "where kodeform ='"+pkodeform+"' and b.defaultacc = '1'  and a.kodeacc = b.kodeacc and b.userid = c.userid) d, VM_HRIS_BPARTNERLENGKAP e "+
            "where d.kodebp = e.kodebp and d.kodejabatan = e.kodejabatan"
  },
  get_selectFilterByKodeform_Kodetrans:function(pkodeform,pprioritas){
      return "select d.kodeacc, d.userid,d.kodebp,e.NAMABP, d.kodejabatan, d.defaultacc, e.namajabatan,'' keterangan " +
            "from "+
                "(select b.kodeacc, b.userid, b.kodejabatan,b.defaultacc, c.kodebp " +
                  "from mh_konfigurasiacc a, md_konfigurasiacc b, m_user c " +
                  "where  a.kodeform = '"+pkodeform+"' " +
                  "and a.prioritas = "+pprioritas+" " +
                  "and a.kodeacc = b.kodeacc " +
                  "and b.userid = c.userid) d, " +
                "VM_HRIS_BPARTNERLENGKAP e "+
            "where d.kodebp = e.kodebp and d.kodejabatan = e.kodejabatan" ;
  }
};
var arrayField_md_konfigurasiacc = [
    'kodeacc', 'userid', 'kodebp', 'namabp', 'kodejabatan', 'namajabatan','defaultacc','keterangan'
];
var md_konfigurasiaccRecord = Ext.data.Record.create(arrayField_md_konfigurasiacc);

function get_md_konfigurasiaccStore(formMode,sqlString,pautoload){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'md_konfigurasiacc',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'md_konfigurasiacc',
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
  var md_konfigurasiaccStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, md_konfigurasiaccRecord),
          autoLoad: autoload
   });
   return md_konfigurasiaccStore;
}

/*var md_konfigurasiaccRecord = Ext.data.Record.create([
    'kodeacc',
    'userid',
    'kodejabatan',
    'namajabatan'
]);
        var mh_konfigurasiacc_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'md_konfigurasiacc',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row'            
          }, mh_konfigurasiaccRecord),
          autoLoad: false
        });

*/

