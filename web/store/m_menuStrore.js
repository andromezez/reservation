/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_m_formmenu = {
  get_selectBlankRow: function(){return "select ' ' kodeform,' ' namaform from dual"},
  get_selectFilterByUrl: function(){ return "select a.kodeform,a.namaform, a.kodeparent, a.url " +
      "from m_menu a " +
      "where a.url is not null "},
  get_selectAllNotInMsettingAcc: function(){ return "SELECT * FROM M_MENU " +      
      "where url is not null and kodeform not in (select kodeform from m_settingacc)"}
};
var m_menuRecord = Ext.data.Record.create([
            'kodeform',
            'namaform',
            'kodeparent',
            'url'
        ]);
var m_menu_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'m_menu',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodeform'
          }, m_menuRecord),
          autoLoad: false
        });


function get_m_formmenuStore(formMode,sqlString){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'m_menu',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'m_menu',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }

  var m_formmenuStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, m_menuRecord),
          autoLoad: false
   });
   return m_formmenuStore;
}
///



