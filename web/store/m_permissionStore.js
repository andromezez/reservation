/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


var SqlCustom_m_permission = {
  get_selectBlankRow: function(){return "select ' ' kodeform,' ' namaform,' ' userid,' ' pview, ' ' padd,\n\
 ' ' pupdate, ' ' pdelete, ' ' pprint,' ' batal_trans  from dual"},
  get_selectFilterByUserId: function(puserid){ return "select a.kodeform, b.namaform, a.userid, a.pview, " +
    "a.padd, a.pupdate, a.pdelete, a.pprint,a.batal_trans "+
    "from m_permission a, m_menu b "+
    "where a.kodeform = b.kodeform "+
    "and a.userid= '"+puserid+"' "},
  get_selectFilterByKodeFormAndUserId: function(pkodeform,puserid){
      return "select a.kodeform, b.namaform, a.userid, a.pview, "+
    "a.padd, a.pupdate, a.pdelete, a.pprint,a.batal_trans "+
    "from m_permission a, m_menu b "+
    "where a.kodeform='"+pkodeform+"'and a.userid= '"+puserid+"' and a.kodeform = b.kodeform "
  }
};
var arrayField_m_permission = [
    'kodeform', 'namaform', 'userid', 'pview', 'padd', 'pupdate', 'pdelete', 'pprint','batal_trans'
];
var m_permissionRecord = Ext.data.Record.create(arrayField_m_permission);

function get_m_permissionStore(formMode,sqlString){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'m_permission',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'m_permission',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }

  var m_permissionStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, m_permissionRecord),
          autoLoad: true
   });
   return m_permissionStore;
}

