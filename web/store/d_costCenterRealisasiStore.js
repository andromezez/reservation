/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_d_costcenterrealisasi = {    
  get_selectBlankRow: function(){return "select ' ' koderealisasi,' ' kodecostcenter,' ' namacostcenter,' ' userid,' ' kodebp, ' ' nik, ' ' namabp,'0' approval,0 presentase,0 nominal from dual"},
  get_selectFilterByKoderealisasi: function(pkoderealisasi){ 
      return "select distinct a.koderealisasi,a.kodecostcenter,b.namacostcenter,a.userid,c.kodebp, c.nik, c.namabp,a.approval,a.presentase,a.nominal " +
      "from d_costcenterrealisasi a, vm_hris_costcenter b, vm_hris_bpartnerlengkap c " +
      "where  a.koderealisasi= '"+pkoderealisasi+"' "+
      "and a.KODECOSTCENTER = b.KODECOSTCENTER (+) " +
      "and a.userid = c.userid (+)"}
};
var arrayField_d_costcenterrealisasi = [
    'koderealisasi','kodecostcenter','namacostcenter','userid','kodebp', 
    'nik', 'namabp','approval','presentase','nominal'
];
var d_costCenterRealisasiRecord = Ext.data.Record.create(arrayField_d_costcenterrealisasi);

function get_d_costCenterRealisasiStore(formMode,sqlString){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'d_costcenterrealisasi',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'d_costcenterrealisasi',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }

  var d_costCenterRealisasiStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, d_costCenterRealisasiRecord),
          autoLoad: true
   });
   
   return d_costCenterRealisasiStore;
}
