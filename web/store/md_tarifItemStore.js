/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


var SqlCustom_md_tarifItem = {
  get_selectBlankRow: function(){return "select ' ' kodeitem,' ' namatarif,' ' jamawal,' ' jamakhir, ' ' biaya,\n\
 ' ' ie from dual"},
  get_selectFilterByKodeItem: function(pkodeitem,pie){ return "select a.kodeitem, a.namatarif, "+
    "TO_CHAR(a.jamawal,'hh24:mi') jamawal, TO_CHAR(a.jamakhir,'hh24:mi') jamakhir, " +
    "a.biaya, a.ie, a.jenisrealisasi "+
    "from md_tarifitem a "+
    "where a.kodeitem= '"+pkodeitem+"' "+
    "and a.ie= '"+pie+"' "}
};
var arrayField_md_tarifItem = [
    'kodeitem','jenisrealisasi', 'namatarif', 'jamawal', 'jamakhir', 'biaya', 'ie'
];
var md_tarifItemRecord = Ext.data.Record.create(arrayField_md_tarifItem);

function get_md_tarifItemStore(formMode,sqlString,pautoload){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'md_tarifitem',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'md_tarifitem',
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
  var md_tarifItemStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, md_tarifItemRecord),
          autoLoad: autoload
   });
   return md_tarifItemStore;
}
