/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function browseYgAccTransaksi(pKodeform,pPrioritas){
    var columnsheader= [{header: "User ID", dataIndex: 'userid'},
          {header: "Kode BP", dataIndex: 'kodebp'},          
          {header: "Nama BP", dataIndex: 'namabp',width:250},
          {header: "Jabatan", dataIndex: 'namajabatan',width:300}
      ];      
      var storeTemp= get_md_konfigurasiaccStore('READ', SqlCustom_md_konfigurasiacc.get_selectFilterByKodeform_Kodetrans(pKodeform, pPrioritas));
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
          if(fieldKodeJenis!=null){
            fieldKodeJenis.setValue(row.get('kodejenis'));
          }
          if(fieldNamaJenis!= null){
            fieldNamaJenis.setValue(row.get('namajenis'));
          }

          //console.log(form.getForm().getValues());
          formBrowse.close();
      }); */
    var formBrowse = new Ext.Window({
        title:'Daftar yang berhak meng-acc',
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