/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_d_tglrealisasi = {
  get_selectBlankRow: function(){return "select sysdate tglawal, sysdate tglakhir from dual"},
  get_selectFilterByKoderealisasi: function(pkoderealisasi){ 
      return "select KODETR,KODEREALISASI ,to_char(TGLAWAL,'mm/dd/yyyy') TGLAWAL,to_char(TGLAKHIR,'mm/dd/yyyy') TGLAKHIR, namatarif " +
      "from d_tglrealisasi " +
      "where  koderealisasi= '"+pkoderealisasi+"' "}
};
var arrayField_d_tglrealisasiStore = [
    'kodetr','koderealisasi','tglawal','tglakhir','namatarif'
];
var d_tglRealisasiRecord = Ext.data.Record.create(arrayField_d_tglrealisasiStore);

function get_d_tglrealisasiStore(formMode,sqlString){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'d_tglrealisasi',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'d_tglrealisasi',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }

  var d_tglRealisasiStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, d_tglRealisasiRecord),
          autoLoad: true
   });
   return d_tglRealisasiStore;
}

