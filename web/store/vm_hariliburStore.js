/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_vm_harilibur = {
  selectFilterByTanggal: function(ptanggal){
      return "SELECT * FROM vm_harilibur "+
             "where kodelokasi=(select distinct parvalue from m_parameter where parname='kodelokasi_tarifitem') "+
             "and tanggal = to_date('"+ptanggal+"','mm/dd/yyyy')";
  }
};
var vm_hariliburRecord = Ext.data.Record.create([
    "keterangan","tanggal","kodelokasi"
]);
function customSelect_vm_harilibur_xmlstore (sqlString) {
    return (new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'vm_harilibur',
                tableoperation:'READ_CUSTOM_ROW',
                sql : sqlString
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row'            
          }, vm_hariliburRecord),
          autoLoad: false
        }));
}
