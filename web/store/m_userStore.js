/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_m_user = {
  get_selectBlankRow: function(){return "select ' ' userid,' ' namabp,' ' kodebp,' ' costcenter,' ' aktif from dual"},
  get_selectFilterByUserId: function(puserid){ return "select distinct a.userid,b.namabp,a.kodebp,b.nik,a.pass,a.aktif " +
      "FROM m_user a, vm_hris_bpartnerlengkap b  " +
      "WHERE a.kodebp = b.kodebp(+) "+
      "and a.userid= '"+puserid+"' "},
  get_selectFilterByUserIdAndPass: function(puserid,ppass){ return "select distinct a.userid,b.namabp,a.kodebp,b.nik,a.pass,a.aktif " +
      "FROM m_user a, vm_hris_bpartnerlengkap b  " +
      "WHERE a.kodebp = b.kodebp(+) "+
      "and a.userid= '"+puserid+"' and a.pass ='"+ppass+"' "},
  get_selectAllExceptUserid: function(puserid){ return "select distinct a.userid,b.namabp,a.kodebp,b.nik,a.pass,a.aktif " +
      "FROM m_user a, vm_hris_bpartnerlengkap b  " +
      "WHERE a.userid != '"+puserid+"' "+
      "and a.kodebp = b.kodebp(+)"}
};
var arrayField_m_user = [
    'userid','namabp','kodebp','nik','pass','aktif'
];
var m_userRecord = Ext.data.Record.create(arrayField_m_user);

var m_user_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'m_user',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'userid'
          }, m_userRecord),
          autoLoad: false
});

/*var m_userRecord = Ext.data.Record.create([
            'userid',
            'namauser',
            'kodebp',
            //'pass',
            'costcenter',
            'aktif'
        ]);*/
        
function get_m_userStore(formMode,sqlString){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'m_user',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'m_user',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
//

        var m_user_xmlstore2 = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:params,
            /*baseParams:{
                tablename:'m_user',
                tableoperation:'READ_ALL_ROW'
          },*/
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'userid'
          }, m_userRecord),
          autoLoad: false
        });
        return m_user_xmlstore2;
}

     