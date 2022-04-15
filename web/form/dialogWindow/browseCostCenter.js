/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function browseCostCenter(/*fieldKodeCostCenter,fieldNamaCostCenter,fieldCostCenter,*/
        Store){
      var columnsheader= [{header: "Kode CC", dataIndex: 'kodecostcenter',width:150},
          {header: "Nama CC", dataIndex: 'namacostcenter',width:250}
      ];
      var arrayColumnsHeader=[];var pvalue;
      for (i = 0 ; i < columnsheader.length ;i++)
          {
              arrayColumnsHeader[i] =[columnsheader[i].dataIndex,columnsheader[i].header];
             
              if(columnsheader[i].header.substr(0, 4)=='Nama')
                  {
                      pvalue=columnsheader[i].dataIndex;
                  }
          }
      var storeTemp;
      if(Store==null){
        storeTemp = vm_hris_costcenter_xmlstore;
      }else{
        storeTemp = Store;
      }
      var grid = new Ext.grid.GridPanel({
               /* viewConfig: {
                    forceFit: true
                },*/
               region:'center',
                store: storeTemp,
                colModel: new Ext.grid.ColumnModel({
                   defaultSortable: true,
                   columns: columnsheader
                }),
                tbar: [get_GridFilterToolbar(arrayColumnsHeader,storeTemp,pvalue)]
            });
     /* grid.on('rowdblclick', function(grid, rowIndex, e) {
          var row = grid.getStore().getAt(rowIndex);
          record_vm_hris_costcenterRecord.set(arrayField_vm_hris_costcenter[0],row.get(arrayField_vm_hris_costcenter[0]));
          record_vm_hris_costcenterRecord.set(arrayField_vm_hris_costcenter[1],row.get(arrayField_vm_hris_costcenter[1]));
          record_vm_hris_costcenterRecord.set(arrayField_vm_hris_costcenter[2],row.get(arrayField_vm_hris_costcenter[2]));
          /*record_vm_hris_costcenterRecord.set('kodecostcenter',row.get('kodecostcenter'));
          record_vm_hris_costcenterRecord.set('namacostcenter',row.get('namacostcenter'));
          record_vm_hris_costcenterRecord.set('costcenter',row.get('costcenter'));*/
          /*if(fieldKodeCostCenter!= null){
             fieldKodeCostCenter.setValue(row.get('kodecostcenter'));
          }
          if(fieldNamaCostCenter!=null){
             fieldNamaCostCenter.setValue(row.get('namacostcenter'));
          }
          if(fieldCostCenter!=null){
             fieldCostCenter.setValue(row.get('costcenter'));
          }*/

          //console.log(fieldKodeCostCenter.getValue());
    //      formBrowse.close();
    //  }); */
    var formBrowse = new Ext.Window({
        title:'Daftar Cost Center',
        layout:'border',
        width:600,
        height: 300,
        modal:true,
        items:[grid]
    });
    storeTemp.load();
    formBrowse.show();
    return (new ListObjectInWIndowBrowse(grid, formBrowse));
}
