/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
   function browseHRealisasi(Store,awal,akhir)
{
   var columnsheader= [{header: "Kode Realisasi", dataIndex: 'koderealisasi'},
          {header: "Tanggal", dataIndex: 'tglrealisasi', xtype: 'datecolumn',format: 'd M Y'},
          {header: "Kode Pengajuan", dataIndex: 'kodepengajuan'},
          {header: "Kode Bp", dataIndex: 'kodebp'},
          {header: "Nama Bp", dataIndex: 'namabp',width:200},
          {header: "Nama Perusahaan", dataIndex: 'namaperusahaan',width:250},
          {header: "Nama Organisasi", dataIndex: 'namaorg',width:300},
          {header: "Jenis Realisasi", dataIndex: 'jenisrealisasi'}
          
          //{header: "Total Biaya", dataIndex: 'totalbiaya', renderer: Ext.util.Format.CurrencyFactory(2,',','.','Rp',true)}

      ];
      
    var arrayColumnsHeader=[];
      for (i = 0 ; i < columnsheader.length ;i++)
          {
              arrayColumnsHeader[i] =[columnsheader[i].dataIndex,columnsheader[i].header];
          }
      var storeTemp;
      if(Store==null){
        storeTemp = trealisasi_xmlstore;
      }else{
        storeTemp = Store;
      }
    var tglAwal =new Ext.form.DateField({
                 itemId :'TglAwal',
                 name: 'TglAwal',
                 fieldLabel: 'Tanggal Awal',
                 value:awal
                 });
    var tglAkhir =new Ext.form.DateField({
                 itemId :'TglAkhir',
                 name: 'TglAkhir',
                 fieldLabel: 'Tanggal Akhir',
                 value:akhir
                 });
    var arrayStore = new Ext.data.ArrayStore({
                     
                     fields: ['myId','displayText'],
                     data: arrayColumnsHeader
                     });

      var combo=new Ext.form.ComboBox({
                     typeAhead: true,
                     itemId :'combo',
                     triggerAction: 'all',
                     lazyRender:true,
                     mode: 'local',
                     store: arrayStore,
                     colspan:2,
                     valueField: 'myId',
                     displayField: 'displayText',
                     value : 'namabp'
                    });
   var hidden_TglAwal= new Ext.form.Hidden({
            itemId :'hTglAwal',
            name: 'hidden_TglAwal',
            value:awal
         });                  
   var hidden_TglAkhir= new Ext.form.Hidden({
            itemId :'hTglAkhir',
            name: 'hidden_TglAkhir',
            value:akhir
         });
    tglAwal.on('change',function(field, newValue, oldValue){
                    akhir=(tglAkhir.getValue().getMonth()+1)+"/"+tglAkhir.getValue().getDate()+"/"+tglAkhir.getValue().getFullYear();
                    awal=(newValue.getMonth()+1)+"/"+newValue.getDate()+"/"+newValue.getFullYear();
                    hidden_TglAwal.setValue(awal);
                    hidden_TglAkhir.setValue(akhir);
                });
                
   tglAkhir.on('change',function(field, newValue, oldValue){
                    awal=(tglAwal.getValue().getMonth()+1)+"/"+tglAwal.getValue().getDate()+"/"+tglAwal.getValue().getFullYear();
                    akhir=(newValue.getMonth()+1)+"/"+newValue.getDate()+"/"+newValue.getFullYear();
                    hidden_TglAwal.setValue(awal);
                    hidden_TglAkhir.setValue(akhir);
                });
    var btnSearch = new Ext.Button ({
              text: 'Cari',
              itemId :'btnSearch',
              width : 90
              });
              
   var rb_FilterAcc=new Ext.form.Radio({
              name      : 'rbFilter',
              itemId :'rbAcc',
              boxLabel: 'Perlu Anda Acc',
              width : 110,
              checked : true
    });
  
   var rb_FilterAll=new Ext.form.Radio({
              name      : 'rbFilter',
              itemId :'rbAll',
              boxLabel: 'Aktif',
              width : 60
              
    });
   var rb_FilterHistory=new Ext.form.Radio({
              name      : 'rbFilter',
              boxLabel: 'History',
              itemId :'rbHistory'
    });
   var txt_Search = new Ext.form.TextField({
              itemId :'txt_Search',
              name: 'txt_Search',
              value:'',
              width : 80
    });
   var bg_filter1 = new Ext.ButtonGroup({            
            itemId :'Filter1',
            name: 'Filter1',
            columns: 2,
            height:60 ,
            defaults: {
                scale: 'small'
            },items: [rb_FilterAcc,rb_FilterAll,rb_FilterHistory]
            
        });
   var bg_filter2 = new Ext.ButtonGroup({            
            itemId :'Filter2',
            name: 'Filter2',
            columns: 4,
            height:60 ,
            defaults: {
                scale: 'small'
            },items: [{xtype:'label',html:'Tanggal Awal : '},tglAwal,{xtype:'label',html:'Tanggal Akhir : '},
                tglAkhir,combo,txt_Search,btnSearch  ]
            
        });
    var FilterToolbar= [bg_filter1,bg_filter2,hidden_TglAwal,hidden_TglAkhir];
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
                tbar: FilterToolbar
            });
    rb_FilterAcc.on('check',function(a,b){
       
        if(b==true)
          {
              FilterToolbar[1].disable();
          }
        else if(b==false)
          {
               FilterToolbar[1].enable(); 
          }
        });        
    var formBrowse = new Ext.Window({
        title:'Realisasi',
        layout:'border',
        width:850,
        height: 500,
        modal:true,
        items:[grid]
    });
    storeTemp.load();
    formBrowse.show();
    return (new ListObjectInWIndowBrowse(grid, formBrowse));
}
