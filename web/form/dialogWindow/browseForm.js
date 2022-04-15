/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function browseForm(/*fieldKodeForm,fieldNamaForm*/ Store){
      var columnsheader= [{header: "Kode Form", dataIndex: 'kodeform',width:100},
          {header: "Nama Form", dataIndex: 'namaform',width:250}
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
      /*var arrayColumnsHeader=[[columnsheader[0].header,columnsheader[0].dataIndex],
          [columnsheader[1].header,columnsheader[1].dataIndex]];
     */
      var storeTemp;
      if(Store==null){
        storeTemp = m_menu_xmlstore;
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
        title:'Daftar Form Transaksi',
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
