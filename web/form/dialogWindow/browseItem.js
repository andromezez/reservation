/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function browseItem(Store){
      var columnsheader= [{header: "Kode Item", dataIndex: 'kodeitem'},
          {header: "Kode Jenis", dataIndex: 'kodejenis'},
          {header: "Nama Item", dataIndex: 'namaitem',width:250},
          {header: "Durasi Min Sewa", dataIndex: 'durasiminsewa',align:'right'},
          {header: "Kapasitas", dataIndex: 'kapasitas',align:'right'},
          {header: "Keterangan", dataIndex: 'keterangan',width:300},
          {header: "Aktif", dataIndex: 'aktif',xtype: 'checkcolumn'},
          {header: "Status Availability", dataIndex: 'statusavailability',xtype: 'checkcolumn'},
          {header: "Price Changeable", dataIndex: 'pricechangeable',xtype: 'checkcolumn'}
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
        storeTemp = m_item_xmlstore;
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
    var formBrowse = new Ext.Window({
        title:'Daftar Item',
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
