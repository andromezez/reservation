/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function browseUser(Store){
      var columnsheader= [{header: "User ID", dataIndex: 'userid'},
          {header: "Kode BP", dataIndex: 'kodebp'},
          {header: "NIK", dataIndex: 'nik'},
          {header: "Nama BP", dataIndex: 'namabp',width:300}
      ];
      var storeTemp;
      if(Store==null){
        storeTemp = m_user_xmlstore;
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
        title:'Daftar User',
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
