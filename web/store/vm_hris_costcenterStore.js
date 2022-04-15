/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_vm_hris_costcenter  = {
  get_selectCostCenter: function(){
      return "select * from vm_hris_costcenter where kodecostcenter not in (select kodecostcenter from md_pembebanan)";
  }
};
    arrayField_vm_hris_costcenter = [
    'kodecostcenter','namacostcenter','costcenter'
    ];
    var vm_hris_costcenterRecord = Ext.data.Record.create(arrayField_vm_hris_costcenter);

    var vm_hris_costcenter_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'vm_hris_costcenter',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodecostcenter'
          }, vm_hris_costcenterRecord),
          autoLoad: false
        });
     function get_vm_hris_costcenterStore(formMode,sqlString){
          var params ;
          if(formMode =='READ') {
            params ={
                tablename:'vm_hris_costcenter',
                tableoperation:'READ_CUSTOM_ROW',
                sql:sqlString
            };
          }
          else if(formMode =='DELETE'){}
          else if(formMode =='UPDATE'){}
          else if(formMode =='INSERT'){
            params ={
                tablename:'vm_hris_costcenter',
                tableoperation:'READ_CUSTOM_ROW',
                sql:sqlString
            };
          }

          var vm_hris_costcenterStore = new Ext.data.Store({
                  proxy : new Ext.data.HttpProxy ({
                        url: 'maincontroller'
                  }),
                  baseParams:params,
                  //url:'',
                  reader: new Ext.data.XmlReader({
                    record:'row'//,idPath:'id'
                  }, vm_hris_costcenterRecord),
                  autoLoad: true
           });
           return vm_hris_costcenterStore;
}