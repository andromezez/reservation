/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var SqlCustom_vm_muser_konfigurasiacc = {
  selectFilterBypuserid_ppass_pkodeform_pprioritas_pjbtn: function(puserid,ppass,pkodeform,pprioritas,pkodejabatan){
      if((pkodejabatan=='') || (pkodejabatan==null)){
          return "SELECT distinct userid,pass,kodeform,prioritas,kodejabatan,namajabatan,namabp " +
            "from VM_MUSER_KONFIGURASIACC " +
            "where userid = '"+puserid+"' " +
            "and pass = '"+ppass+"' and kodeform='"+pkodeform+"' and prioritas='"+pprioritas+"' ";
      }else{
          return "SELECT distinct userid,pass,kodeform,prioritas,kodejabatan,namajabatan,namabp " +
            "from VM_MUSER_KONFIGURASIACC " +
            "where userid = '"+puserid+"' " +
            "and pass = '"+ppass+"' and kodeform='"+pkodeform+"' and prioritas='"+pprioritas+"' ";
      }      
	//"and kodejabatan in (select kodejabatan from VM_HRIS_BPARTNERLENGKAP where userid = 'coba')";
  }
};

var vm_muser_konfigurasiaccRecord = Ext.data.Record.create(['userid','pass',
    'kodeform', 'prioritas','kodejabatan',  'namajabatan', 'namabp']);

function customSelect_vm_muser_konfigurasiacc_xmlstore (sqlString) {
    return (new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'vm_muser_konfigurasiacc',
                tableoperation:'READ_CUSTOM_ROW',
                sql : sqlString
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row'            
          }, vm_muser_konfigurasiaccRecord),
          autoLoad: false
        }));
}
