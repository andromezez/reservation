/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_m_settingacc  = {
  get_selectBlankRow: function(){return ""},
  get_selectFilterByKodeForm: function(pkodeform){
      return "SELECT a.kodeform, b.namaform, a.accform, a.editfinalapprove  "
                + "FROM M_SETTINGACC a, M_MENU b "
                + "WHERE a.kodeform = '"+pkodeform+"' "
                + "and a.accform = '1' and a.kodeform = b.kodeform "}
};
var arrayField_m_settingaccStore = [
    'kodeform','namaform','accform','editfinalapprove'
];
var m_settingaccRecord = Ext.data.Record.create(arrayField_m_settingaccStore);

        var m_settingacc_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'m_settingacc',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodeform'
          }, m_settingaccRecord),
          autoLoad: false
        });
function get_m_settingaccStore(formMode,sqlString){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'m_settingacc',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'m_settingacc',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }

  var m_settingaccStore2 = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, m_settingaccRecord),
          autoLoad: true
   });
   return m_settingaccStore2;
}
