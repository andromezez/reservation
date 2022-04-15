/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_d_tglpengajuan = {
  get_selectBlankRow: function(){return "select sysdate tglawal, sysdate tglakhir from dual"},
  get_selectFilterByKodepengajuan: function(pkodepengajuan){ 
      return "select KODETP,KODEPENGAJUAN ,to_char(TGLAWAL,'mm/dd/yyyy') TGLAWAL,to_char(TGLAKHIR,'mm/dd/yyyy') TGLAKHIR, namatarif " +
      "from d_tglpengajuan " +
      "where  kodepengajuan= '"+pkodepengajuan+"' "}
};
var arrayField_d_tglpengajuanStore = [
    'kodetp','kodepengajuan','tglawal','tglakhir','namatarif'
];
var d_tglPengajuanRecord = Ext.data.Record.create(arrayField_d_tglpengajuanStore);

function get_d_tglpengajuanStore(formMode,sqlString,pautoload){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'d_tglpengajuan',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'d_tglpengajuan',
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
  var d_tglPengajuanStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, d_tglPengajuanRecord),
          autoLoad: autoload
   });
   return d_tglPengajuanStore;
}