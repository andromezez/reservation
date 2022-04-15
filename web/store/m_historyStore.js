/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var m_historyRecord = Ext.data.Record.create([
            'kodeform','tabel','pk1',
            'pk1value','pk2','pk2value',
            'pk3','pk3value','keterangan',
            'createdby','createddate','updaterby',
            'updateddate','deletedby','deleteddate'
        ]);
var m_history_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'m_history',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodeform'
          }, m_historyRecord),
          autoLoad: false
        });
function get_m_history(formMode,sqlString){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'m_history',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'m_history',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }

  var mHistory = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, Coba_Record),
          autoLoad: true
   });
   return mHistory;
}