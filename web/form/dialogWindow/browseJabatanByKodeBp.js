 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function browseJabatanByKodeBp(/*fieldKodeForm,fieldNamaForm*/ Store){
      var columnsheader= [{header: "Kode BP", dataIndex: 'kodebp'},
          {header: "Kode Jabatan", dataIndex: 'kodejabatan'},
          {header: "Nama Jabatan", dataIndex: 'namajabatan',width:300},
          {header: "Kode Org", dataIndex: 'kodeorg'},
          {header: "Nama Org", dataIndex: 'namaorg',width:300}
      ];
      var storeTemp;
      if(Store==null){
        storeTemp = vm_hris_bpartnerLengkap_xmlstore;
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
                })
            });
      /*grid.on('rowdblclick', function(grid, rowIndex, e) {
          var row = grid.getStore().getAt(rowIndex);
          if(fieldKodeForm!=null){
            fieldKodeForm.setValue(row.get('kodeform'));
          }
          if(fieldNamaForm!= null){
            fieldNamaForm.setValue(row.get('namaform'));
          }

          //console.log(form.getForm().getValues());
          formBrowse.close();
      });*/
    var formBrowse = new Ext.Window({
        title:'Daftar Jabatan',
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
