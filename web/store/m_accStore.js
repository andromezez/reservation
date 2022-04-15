/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_m_acc  = {
  get_selectBlankRow: function(){return ""},
  get_selectFilterByKodetransKodeform: function(pkodeform,pkodetransaksi){
      return "select  distinct a.userid  , a.kodeform  , a.kodetransaksi  , "
            +"TO_CHAR(a.tglacc,'mm/dd/yyyy') tglacc , a.prioritas, "
            +"a.kodejabatan,  b.namajabatan, c.namabp,  d.keterangan,a.statusacc "
            + "from m_acc a, hris.m_jabatan b, VM_HRIS_BPARTNERLENGKAP c,MH_KONFIGURASIACC d "
            +"where a.kodeform='"+pkodeform+"' "
            +" and kodetransaksi='"+pkodetransaksi+"'"
            +" and a.kodejabatan= b.kodejabatan(+) and a.userid = c.userid(+) "
            +" and (a.KODEFORM=d.KODEFORM and a.prioritas=d.prioritas) "
            +" order by prioritas"}
};
var arrayField_m_accStore = [
    'userid'  ,  'kodeform'  ,  'kodetransaksi'  ,  'tglacc' ,  'prioritas', 
    'kodejabatan',  'namajabatan', 'namabp','keterangan','statusacc'
];
var m_accRecord = Ext.data.Record.create(arrayField_m_accStore);

function get_m_accStore(formMode,sqlString){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'m_acc',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'m_acc',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }

  var m_accStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, m_accRecord),
          autoLoad: true
   });
   return m_accStore;
}

