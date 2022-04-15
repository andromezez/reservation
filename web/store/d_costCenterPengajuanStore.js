/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_d_costcenterpengajuan = {    
  get_selectBlankRow: function(){return "select ' ' kodepengajuan,' ' kodecostcenter,' ' namacostcenter,' ' userid,' ' kodebp, ' ' nik, ' ' namabp,'0' approval,0 presentase,0 nominal from dual"},
  get_selectFilterByKodepengajuan: function(pkodepengajuan){ return "select distinct a.kodepengajuan,a.kodecostcenter,b.namacostcenter,a.userid,c.kodebp, c.nik, c.namabp,a.approval,a.presentase,a.nominal " +
      "from d_costcenterpengajuan a, vm_hris_costcenter b, vm_hris_bpartnerlengkap c " +
      "where  a.kodepengajuan= '"+pkodepengajuan+"' "+
      "and a.KODECOSTCENTER = b.KODECOSTCENTER (+) " +
      "and a.userid = c.userid (+)"}
};
var arrayField_d_costcenterpengajuan = [
    'kodepengajuan','kodecostcenter','namacostcenter','userid','kodebp', 'nik', 'namabp','approval','presentase','nominal'
];
var d_costCenterPengajuanRecord = Ext.data.Record.create(arrayField_d_costcenterpengajuan);

function get_d_costCenterPengajuanStore(formMode,sqlString){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'d_costcenterpengajuan',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'d_costcenterpengajuan',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }

  var d_costCenterPengajuanStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, d_costCenterPengajuanRecord),
          autoLoad: true
   });
   
   return d_costCenterPengajuanStore;
}