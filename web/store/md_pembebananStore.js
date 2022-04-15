/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_md_pembebanan = {
  get_selectBlankRow: function(){return "select ' ' kodepembebanan,' ' kodecostcenter,' ' namacostcenter from dual"},
  get_selectFilterByKodePembebanan: function(pkodepembebanan){ return "select distinct a.kodepembebanan,a.kodecostcenter,b.namacostcenter " +
      "from md_pembebanan a, vm_hris_costcenter b " +
      "where a.KODECOSTCENTER = b.KODECOSTCENTER (+) "+
      "and a.kodepembebanan= '"+pkodepembebanan+"' "}  
};
var arrayField_md_pembebanan = [
    'kodepembebanan','kodecostcenter','namacostcenter'
];
var md_pembebananRecord = Ext.data.Record.create(arrayField_md_pembebanan);

function get_md_pembebananStore(formMode,sqlString){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'md_pembebanan',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'md_pembebanan',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }

  var md_pembebananStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, md_pembebananRecord),
          autoLoad: true
   });
   return md_pembebananStore;
}