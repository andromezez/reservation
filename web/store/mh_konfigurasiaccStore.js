/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_mh_konfigurasiacc = {  
  get_selectFilterByKodeForm: function(pkodeform){
      return "select a.kodeacc, a.kodeform, b.namaform, a.prioritas, a.keterangan "
      + "FROM MH_KONFIGURASIACC a, m_menu b "
      + "WHERE a.kodeform='"+pkodeform+"' "
      + "and a.kodeform = b.kodeform "
      + "order by a.prioritas asc"}
};
var mh_konfigurasiaccRecord = Ext.data.Record.create([
            'kodeacc',
            'kodeform',
            'namaform',
            'prioritas',
            'keterangan'
        ]);
        var mh_konfigurasiacc_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'mh_konfigurasiacc',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodeacc'
          }, mh_konfigurasiaccRecord),
          autoLoad: false
        });
function get_mh_konfigurasiaccStore(formMode,sqlString){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'mh_konfigurasiacc',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'mh_konfigurasiacc',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }

  var mh_konfigurasiaccStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, mh_konfigurasiaccRecord),
          autoLoad: true
   });
   return mh_konfigurasiaccStore;
}

